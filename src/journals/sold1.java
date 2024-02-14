/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package journals;

import intreprisemanagementsystem.JavaDbConnect;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Doshe PC
 */
public class sold1 extends javax.swing.JPanel {

  Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls,rolls_batch;
 String ID_TOKEN=null,ORGANIZATION=null;
//String roll,rolls,rolls_batch;
    public sold1() {
        initComponents();
          con=JavaDbConnect.dbConnect();
    }
public void etroll()
     {
         try{
         
         String sql="SELECT * FROM  ID_TOKEN  where id=1";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
         ID_TOKEN = rs.getString("ID_USER"); }
            }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
         try{
                  SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyyMMdd");
         String addDateS = dateFormatsS.format(journal1.jDateChooser1.getDate());
         
         String sql="SELECT NUM FROM ohada_trans where SUBSTR(NUM,5,8)='"+addDateS+"' ORDER BY NUM DESC LIMIT 1";
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
               rolls= ID_TOKEN+"-"+addDateS+"1001";
                
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }
        
         
     }
 public void RollBATCH(){
     try{
         
         String sql="SELECT * FROM  ID_TOKEN  where id=1";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
         ID_TOKEN = rs.getString("ID_USER"); }
            }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
      String Batch_ecri=null;
       SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyyMMdd");
         String addDateS = dateFormatsS.format(journal1.jDateChooser1.getDate());
        String sql="SELECT BATCH_ECRITURE FROM ohada_trans where SUBSTR(BATCH_ECRITURE,5,8)='"+addDateS+"' ORDER BY BATCH_ECRITURE DESC LIMIT 1";
       if(journal1.jTextField1.getText().equals("")){
        try{
          String sqls="SELECT MAX(BATCH_ECRITURE) FROM ohada_trans where buss ='"+journal1.buss.getText()+"'";
        pst = con.prepareStatement(sqls);          rs=pst.executeQuery();
           if(rs.next()){
              Batch_ecri=rs.getString("MAX(BATCH_ECRITURE)");
             // jTextField1.setText(Batch_ecri);
              
           try{
		   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
             if(rs.next()){
                 String rl=Batch_ecri;
                 int ln=rl.length();
                 String stxt=rl.substring(0,12);
                 String snum=rl.substring(12,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                rolls_batch=stxt+snum;
                journal1.jTextField1.setText(rolls_batch);
             }else{
            rolls_batch= ID_TOKEN+"-"+addDateS+"1001";
                journal1.jTextField1.setText(rolls_batch);  
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }  
               
          }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        } 
       }else{
       rolls_batch=journal1.jTextField1.getText();
       }
         
          
         }
public void save(){
     try{
         
         String sql="SELECT * FROM  ID_TOKEN  where id=1";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
         ORGANIZATION = rs.getString("ORGANIZATION"); }
            }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
    etroll();
   RollBATCH();
   String Table_click1  = amount.getText();
String replaceString=Table_click1.replace(",", "");
    //call_currency();
     String DEVICEEs = null,DEVICEEss = null,PRINCIPAL=null,DEVICEEssC=null;
        Double PR= Double.parseDouble(replaceString),sale = null,BUY,SALE = null,buy = null,CDF,USD,CDFC,USDC;
        if(journal1.classe1.getText().equals("5")){
         try{
            String sql="select * from monais WHERE caisse='"+journal1.code1.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEss=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEss+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
            BUY=buy;//Achete le principal currency
            SALE=sale;//Ventre le principal currency
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

       if(PRINCIPAL.equals("Yes")){
         try{
            String sql="select * from monais WHERE caisse='"+journal1.code1.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEss=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEss+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
            //device=rs.getString("APPR")
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
             //  PRINCIPAL=rs.getString("PRINCIPAL"); 
           // BUY=buy;//Achete le principal currency
           // SALE=sale;//Ventre le principal currency
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }   
           
   CDF=PR*sale;
  USD=PR;
  
  }else{
     
  USD=PR/SALE;
  CDF=PR;
  }
   
/*
     try{
            String sql="select * from monais WHERE caisse='"+journal1.code1.getText()+"'";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          DEVICEEssC=rs.getString("DEVICE");  
            }
            }
        
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
    
     try{
String sql="SELECT * FROM  currency where APPR='"+DEVICEEssC+"'";
pst = con.prepareStatement(sql);          rs=pst.executeQuery();
   while(rs.next()){
             sale=rs.getDouble("SALE");
             buy=rs.getDouble("BUY"); 
               PRINCIPAL=rs.getString("PRINCIPAL"); 
            }
          
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
       if(PRINCIPAL.equals("Yes")){ 
   CDFC=PR*buy;
  USDC=PR;
  
  }else{
     
  USDC=PR/sale;
  CDFC=PR;
  }       
 */ 
    
             try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,SOLDE,LB,BATCH_ECRITURE,ORGANIZATION)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, "");
        pst.setString(2, "Caisse");
         pst.setString(3, "");
         pst.setString(4,journal1.code1.getText());
         pst.setString(5,"5");
         pst.setString(6, "57");
         pst.setDouble(7,USD);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(journal1.jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,journal1.buss.getText());
         pst.setString(12,sold_lib.getText());
         pst.setString(13,"[JC]");
          pst.setString(14,"-");
           pst.setDouble(15,CDF);
            pst.setDouble(16,0.0);
            pst.setString(17,journal1.jComboBox2.getSelectedItem().toString());
            pst.setString(18,"Yes");
            pst.setString(19,"-");
            pst.setString(20,rolls_batch);
            pst.setString(21,ORGANIZATION);
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        }else{
 try {
               PreparedStatement pst = con.prepareStatement("INSERT INTO `ohada_trans`(`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`,DEBIT_CDF,CREDIT_CDF,DEVICE,SOLDE,LB,BATCH_ECRITURE,ORGANIZATION)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, "");
        pst.setString(2, "Caisse");
         pst.setString(3, "");
         pst.setString(4,journal1.code1.getText());
         pst.setString(5,journal1.classe1.getText());
         pst.setString(6, journal1.substr1.getText());
         pst.setDouble(7,PR);
         pst.setString(8,"0");
         pst.setString(9,rolls);
         
       SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(journal1.jDateChooser1.getDate());
         pst.setString(10, addDate);
         pst.setString(11,journal1.buss.getText());
         pst.setString(12,sold_lib.getText());
         pst.setString(13,journal1.code1.getText());
          pst.setString(14,"-");
           pst.setDouble(15,0.0);
            pst.setDouble(16,0.0);
            pst.setString(17,journal1.jComboBox2.getSelectedItem().toString());
            pst.setString(18,"Yes");
            pst.setString(19,"-");
            pst.setString(20,rolls_batch);
            pst.setString(21,ORGANIZATION);
       
          
         
          pst.executeUpdate();
        
           //      JOptionPane.showMessageDialog(null,"Caisse saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
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
        sold_lib = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        amount = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        sold_lib.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sold_lib.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Libelle");

        amount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        amount.setText("0.00");
        amount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                amountKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                amountKeyTyped(evt);
            }
        });

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Amount");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("X");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Repport Solde");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sold_lib)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 240, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(amount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sold_lib, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(192, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(221, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(126, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        save();
       
journal1.boody.removeAll();
journal1.boody.add(new home_Table());
journal1.boody.repaint();
journal1.boody.revalidate();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
journal1.boody.removeAll();
journal1.boody.add(new home_Table());
journal1.boody.repaint();
journal1.boody.revalidate();       // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void amountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountKeyTyped
  char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_amountKeyTyped

    private void amountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountKeyPressed
 if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            try{

                NumberFormat nf = new DecimalFormat("#,###.00");
                String Table_click1  = amount.getText();
                String replaceString=Table_click1.replace(",", "");
                Double c= Double.parseDouble(replaceString);
                String fn = nf.format(c);
                //PR=c;
                amount.setText(fn);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex); }
        }             // TODO add your handling code here:
    }//GEN-LAST:event_amountKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField sold_lib;
    // End of variables declaration//GEN-END:variables
}
