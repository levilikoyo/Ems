/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DOSHE
 */
public class warehousestock extends javax.swing.JPanel {
   DefaultTableModel mode;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String ID=null;
String check=null;
String rolls;
    public warehousestock() {
        initComponents();
              con=JavaDbConnectUMCO.dbConnect();
        buss();
        call_table();
      

    }
 public void buss(){
    
        
          try{
                         String sql="SELECT distinct(NAME) FROM  ClassCat WHERE STATUT='Cat'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("NAME");
                cat.addItem(sums);
            }
           
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
          
           try{
                         String sql="SELECT distinct(NAME) FROM  ClassCat WHERE STATUT='Class'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("NAME");
                clas.addItem(sums);
            }
            
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
 Double USD,CDF;
 public void call_currency(){
 
  try{
                         String sql="SELECT * FROM  currency";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
             CDF=rs.getDouble("ACDF");
             USD=rs.getDouble("VUSD"); 
            }
         
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 }
 //INSERT INTO `warehouse_stock`(`ID`, `NUM`, `SIGN`, `NAME`, `CLASS`, `CAT`, `DESCR`, `PR`, `PV`)
 
 
  public void roll()
     {
         try{
            String sql="SELECT NUM FROM  warehouse_stock ORDER BY NUM DESC LIMIT 1";
            

            
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,6);
                 String snum=rl.substring(6,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum ;
                 
                 
                 
             }else{
                 rolls="STOCK/1001";
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
  
  public void save_ohada_pos(){
   //  String CHECK;
       try{
                         String sql="SELECT NAME FROM  ohada_pos where NAME='"+name.getText()+" "+clas.getSelectedItem().toString()+" "+cat.getSelectedItem().toString()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
              
            }else{
             try{
        String sqls="INSERT INTO `ohada_pos`(`NAME`, `STATUS`, `STATUS2`, `STATUS3`)"+
                "values(?,?,?,?)";
        pst=con.prepareStatement(sqls);
pst.setString(1,name.getText()+" "+clas.getSelectedItem().toString()+" "+cat.getSelectedItem().toString());
pst.setString(2,"0");
pst.setString(3,"0");
pst.setString(4,"0");


    pst.executeUpdate();
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
           
           }
           
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  
       
  
  }
  
  
  public void save(){
     
      call_currency();
       Double PR = Double.parseDouble(pr.getText()),PV = Double.parseDouble(pv.getText());
      save_ohada_pos();
roll();
if(prmoney.getSelectedItem().equals("USD") ||pvmomey.getSelectedItem().equals("USD")){
    NumberFormat nf1 = new DecimalFormat("#.##");//##.##
//String fn1 = nf1.format(cdf);
Double ur=PR*USD;
Double uv=PV*USD;
String pru = nf1.format(ur);
String pvu = nf1.format(uv);

  try{
        String sql="INSERT INTO `warehouse_stock`(`NUM`, `SIGN`, `NAME`, `CLASS`, `CAT`, `DESCR`, `PR`, `PV`, `PR_USD`, `PV_USD`, `NAMES`,`USD`, `CDF`, `STORE`)"+
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst=con.prepareStatement(sql);
pst.setString(1,rolls);
pst.setString(2,sign.getText());
pst.setString(3,name.getText());
pst.setString(4,clas.getSelectedItem().toString());
pst.setString(5,cat.getSelectedItem().toString());
pst.setString(6,descr.getText());
 
pst.setString(7,pru);
pst.setString(8,pvu);
pst.setString(9,pr.getText());
pst.setString(10,pv.getText());
pst.setString(11,name.getText()+" "+clas.getSelectedItem().toString()+" "+cat.getSelectedItem().toString());
pst.setString(12,"USD");
pst.setString(13,"CDF");
pst.setString(14,store.getSelectedItem().toString());


    pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "DATA SAVED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
}else{
       NumberFormat nf1 = new DecimalFormat("#.##");//##.##
Double cr=PR/CDF;
Double cv=PV/CDF;
    String prc = nf1.format(cr);
String pvc = nf1.format(cv);
try{
        String sql="INSERT INTO `warehouse_stock`(`NUM`, `SIGN`, `NAME`, `CLASS`, `CAT`, `DESCR`, `PR`, `PV`, `PR_USD`, `PV_USD`, `NAMES`,`USD`, `CDF`,STORE)"+
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst=con.prepareStatement(sql);
pst.setString(1,rolls);
pst.setString(2,sign.getText());
pst.setString(3,name.getText());
pst.setString(4,clas.getSelectedItem().toString());
pst.setString(5,cat.getSelectedItem().toString());
pst.setString(6,descr.getText());
pst.setString(9,prc);
pst.setString(10,pvc);
pst.setString(7,pr.getText());
pst.setString(8,pv.getText());
pst.setString(11,name.getText()+" "+clas.getSelectedItem().toString()+" "+cat.getSelectedItem().toString());
pst.setString(12,"USD");
pst.setString(13,"CDF");
pst.setString(14,store.getSelectedItem().toString());


    pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "DATA SAVED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}

}
      
      
    }
 public void Update(){
     call_currency();
    Double PR = Double.parseDouble(pr.getText()),PV = Double.parseDouble(pv.getText());
    
    if(prmoney.getSelectedItem().equals("USD") ||pvmomey.getSelectedItem().equals("USD")){
    NumberFormat nf1 = new DecimalFormat("#.##");//##.##
//String fn1 = nf1.format(cdf);
Double ur=PR*USD;
Double uv=PV*USD;
String pru = nf1.format(ur);
String pvu = nf1.format(uv);
    
try{
        String sql="UPDATE `warehouse_stock` SET  `SIGN`=?, `NAME`=?, `CLASS`=?, `CAT`=?, `DESCR`=?, `PR`=?, `PV`=?, `PR_USD`=?, `PV_USD`=? ,`NAMES`=?,`STORE`=? WHERE NUM='"+num.getText()+"'";
        pst=con.prepareStatement(sql);
pst.setString(1,sign.getText());
pst.setString(2,name.getText());
pst.setString(3,clas.getSelectedItem().toString());
pst.setString(4,cat.getSelectedItem().toString());
pst.setString(5,descr.getText());
pst.setString(6,pr.getText());
pst.setString(7,pv.getText());
pst.setString(8,pru);
pst.setString(9,pvu);
pst.setString(10,name.getText()+" "+clas.getSelectedItem().toString()+" "+cat.getSelectedItem().toString());
pst.setString(11,store.getSelectedItem().toString());


    pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "DATA UPDATED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}  
    
    }else{
    
    NumberFormat nf1 = new DecimalFormat("#.##");//##.##
Double cr=PR/CDF;
Double cv=PV/CDF;
    String prc = nf1.format(cr);
String pvc = nf1.format(cv);
    
try{
        String sql="UPDATE `warehouse_stock` SET  `SIGN`=?, `NAME`=?, `CLASS`=?, `CAT`=?, `DESCR`=?, `PR`=?, `PV`=?, `PR_USD`=?, `PV_USD`=? ,`NAMES`=?,`STORE`=? WHERE NUM='"+num.getText()+"'";
        pst=con.prepareStatement(sql);
pst.setString(1,sign.getText());
pst.setString(2,name.getText());
pst.setString(3,clas.getSelectedItem().toString());
pst.setString(4,cat.getSelectedItem().toString());
pst.setString(5,descr.getText());
pst.setString(6,pr.getText());
pst.setString(7,pv.getText());
pst.setString(8,prc);
pst.setString(9,pvc);
pst.setString(10,name.getText()+" "+clas.getSelectedItem().toString()+" "+cat.getSelectedItem().toString());
pst.setString(11,store.getSelectedItem().toString());



    pst.executeUpdate();
    JOptionPane.showMessageDialog(null, "DATA UPDATED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}  

    }
      
          
    
    }
 
   public void call_table(){
      
           try{
           
             String sql="SELECT `NUM`, `SIGN`, `NAME`, `CLASS`, `CAT`, `DESCR`, `PR`, `PV` FROM `warehouse_stock` ORDER BY NAME";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        TableColumn col3=jTable1.getColumnModel().getColumn(3);
        TableColumn col4=jTable1.getColumnModel().getColumn(4);
        TableColumn col5=jTable1.getColumnModel().getColumn(5);
        TableColumn col6=jTable1.getColumnModel().getColumn(6);
        TableColumn col7=jTable1.getColumnModel().getColumn(7);
       
       
      
       
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(10);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(100);
       col4.setPreferredWidth(100);
       col5.setPreferredWidth(100);
       col6.setPreferredWidth(50);
     col7.setPreferredWidth(50);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
                public void select_jTable()
   {
        try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM warehouse_stock WHERE NUM= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
          //
              
            ID = rs.getString("NUM");
            
              String add1 = rs.getString("NUM");
              String add2 = rs.getString("SIGN");
              String add3 = rs.getString("NAME");
              String add4 = rs.getString("CLASS");
              String add5 = rs.getString("CAT");
              String add6 = rs.getString("DESCR");
              String add7 = rs.getString("PR");
              String add8 = rs.getString("PV");
              String add9 = rs.getString("PR_USD");
              String add10 = rs.getString("PV_USD");
              
              
              num.setText(add1);
                sign.setText(add2);
                  name.setText(add3);
                    clas.setSelectedItem(add4);
                      cat.setSelectedItem(add5);
                       descr.setText(add6);
                          pr.setText(add7);
                            pv.setText(add8);
                            prmoney.setSelectedItem(add9);
                      pvmomey.setSelectedItem(add10);
                            
              
              
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }

 public void delete()
    {
     
         try{
        String sql = "DELETE FROM warehouse_stock WHERE NUM=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,num.getText());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException  ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
    }
    public void refresh(){
    num.setText("");
    sign.setText("");
    name.setText("");
    clas.setSelectedItem("......");
    cat.setSelectedItem("......");
    store.setSelectedItem("......");
    prmoney.setSelectedItem("USD");
     pvmomey.setSelectedItem("USD");
    descr.setText("");
    pr.setText("0.00");
    pv.setText("0.00");
    ID=null;
    
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
        jLabel1 = new javax.swing.JLabel();
        num = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        name = new javax.swing.JTextField();
        clas = new javax.swing.JComboBox<>();
        cat = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        sign = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        descr = new javax.swing.JTextField();
        store = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        Prices = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        pr = new javax.swing.JTextField();
        pv = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        prmoney = new javax.swing.JComboBox<>();
        pvmomey = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        num.setEditable(false);
        num.setBackground(new java.awt.Color(240, 240, 241));
        num.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        name.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        clas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        clas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......" }));

        cat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Strore");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Stock Name");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Classification");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Category");

        sign.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Signalisation Stock");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Description");

        descr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        store.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        store.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......" }));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2))
                .addGap(24, 24, 24)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(store, 0, 267, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sign, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(descr)
                    .addComponent(name))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(sign, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(store, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(descr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Prices.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prices and Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        Prices.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Price", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pr.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pr.setText("0.00");
        pr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prMouseClicked(evt);
            }
        });
        pr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                prKeyReleased(evt);
            }
        });
        jPanel10.add(pr, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 16, 110, -1));

        pv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pv.setText("0.00");
        pv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pvMouseClicked(evt);
            }
        });
        jPanel10.add(pv, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 42, 110, -1));

        Prices.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 130, 80));

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Monnaies", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        prmoney.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        prmoney.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USD", "CDF" }));
        jPanel11.add(prmoney, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 70, -1));

        pvmomey.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pvmomey.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USD", "CDF" }));
        jPanel11.add(pvmomey, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 43, 70, -1));

        Prices.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 100, 80));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText(" Cost price");
        Prices.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText(" Selling price");
        Prices.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 66, -1, -1));

        jPanel14.add(Prices, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 390, 100));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 27, 240, -1));

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel4.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 55, 240, -1));

        jPanel14.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 270, 100));

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

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("New");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("Print");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 119, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
this.show(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void prKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prKeyReleased
              // TODO add your handling code here:
    }//GEN-LAST:event_prKeyReleased

    private void prMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prMouseClicked
pr.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_prMouseClicked

    private void pvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pvMouseClicked
pv.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_pvMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
select_jTable();       // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MousePressed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
refresh();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
if(sign.getText().equals("") || name.getText().equals("") ||descr.getText().equals("")||clas.getSelectedItem().equals("......")||store.getSelectedItem().equals("......") ||cat.getSelectedItem().equals("......")||pr.getText().equals("0.00")||pv.getText().equals("0.00")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{


        if(ID==null){
save();
}else{
Update();
}
call_table();
refresh(); 
}// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
delete(); 
call_table();
refresh();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Prices;
    private javax.swing.JComboBox<String> cat;
    private javax.swing.JComboBox<String> clas;
    private javax.swing.JTextField descr;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField name;
    private javax.swing.JTextField num;
    private javax.swing.JTextField pr;
    private javax.swing.JComboBox<String> prmoney;
    private javax.swing.JTextField pv;
    private javax.swing.JComboBox<String> pvmomey;
    private javax.swing.JTextField sign;
    private javax.swing.JComboBox<String> store;
    // End of variables declaration//GEN-END:variables
}
