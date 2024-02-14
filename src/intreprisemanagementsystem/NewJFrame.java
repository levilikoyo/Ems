/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import com.alee.laf.WebLookAndFeel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dosh
 */
public class NewJFrame extends javax.swing.JFrame {

    Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
//DefaultTableModel mode;
 String rolls;
 String NUM_ID;
 DefaultTableModel mode; 
    public NewJFrame() {
        initComponents();
         con=JavaDbConnect.dbConnect();
    }
    
    
    
    
    
    
    
    
public void calll(){
    
      String CATT = null;
          Double pour=null,PRICEE=null;
          float daysBetween = 0;
          
          
          Calendar calendar1 = Calendar.getInstance();
    Calendar calendar2 = Calendar.getInstance();
    
SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
  SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd MM yyyy");
  
         String dateAfterString = dateFormat1.format(webDateField2.getDate());
         String dateBeforeString = myFormat.format(webDateField1.getDate());
	
	// String dateAfterString = webDateField2.getText();

	 try {
	       Date dateBefore = myFormat.parse(dateBeforeString);
	       Date dateAfter = myFormat.parse(dateAfterString);
	       long difference = dateAfter.getTime() - dateBefore.getTime();
	      daysBetween = (difference / (1000*60*60*24));
              
               /* You can also convert the milliseconds to days using this method
                * float daysBetween = 
                *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
                */
	      JOptionPane.showMessageDialog(null,daysBetween);  
	 } catch (Exception e) {
	       e.printStackTrace();
	 }

}




     
      public void calcul_amorti(){
          String CATT = null;
          Double pour=null,PRICEE=null;
          float daysBetween = 0;
          Date date2 = null;
          
          
          Calendar calendar1 = Calendar.getInstance();
    Calendar calendar2 = Calendar.getInstance();
  
          
          
          
          try{
            String sql="select * from eqipement_trans where ITEM_ID ='ORD/ADED/001' ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                CATT=rs.getString("CAT");}
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
           try{
            String sql="select * from equipement_in where ITEM_ID='ORD/ADED/001' ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               PRICEE=rs.getDouble("PRICE");
               
            
 date2=rs.getDate("DATES");
        // dateBeforeString = dateFormat1.format(date2);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
          
           try{
            String sql="select * from amortissement where CAT='"+CATT+"' ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               pour=rs.getDouble("VAL");
               }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

          
           
 SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
  SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd MM yyyy");
       String dateAfterString = dateFormat1.format(webDateField2.getDate());
        String dateBeforeString = myFormat.format(date2);
         
	
	 try {
	       Date dateBefore = myFormat.parse(dateBeforeString);
	       Date dateAfter = myFormat.parse(dateAfterString);
	       long difference = dateAfter.getTime() - dateBefore.getTime();
	      daysBetween = (difference / (1000*60*60*24));
              
               /* You can also convert the milliseconds to days using this method
                * float daysBetween = 
                *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
                */
	      
	 } catch (Exception e) {
	       e.printStackTrace();
	 }
          
          String  aaa= ""+daysBetween;
         Double b=pour*30;//total jour d'amorticement
         Double c=PRICEE/b;//motant par jour
         Double d=Double.parseDouble(aaa); // Nbre de date entre deux date
         
        Double A=b-d; // JOUR RESTANTS
       Double B= A*c; //AMORTISEMENT ACTUEL
      String C= String.format("%.2f",B);
       // sold3.setText(C);
         
      //   540 JOUR TOTAL
      //  533 JOUR RESTANT
      //1.08 MONTANT PAR JOUR
        
         
     JOptionPane.showMessageDialog(null,C );     
      }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        webDateField2 = new com.alee.extended.date.WebDateField();
        webDateField1 = new com.alee.extended.date.WebDateField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        webDateField2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        webDateField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(webDateField2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(257, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webDateField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(286, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
calcul_amorti();       // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                WebLookAndFeel.install(true);
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private com.alee.extended.date.WebDateField webDateField1;
    private com.alee.extended.date.WebDateField webDateField2;
    // End of variables declaration//GEN-END:variables
}
