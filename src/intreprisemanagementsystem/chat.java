/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import com.alee.laf.WebLookAndFeel;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dosh
 */

public class chat extends javax.swing.JFrame {

    Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
  Timer timer;
    public chat() {
        initComponents();
          setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
          con=JavaDbConnect.dbConnect();
          call();
         lock_employee();
         sname.setText(homme.user.getText());
        
    }
    
    
       public void lock_employee(){
         ActionListener actionListener = new  ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e) {
          receiver();
          callmiss();
             }
         };
         timer = new Timer(1000,actionListener); 
    
timer.start();
       }
    
    public void call(){
     DefaultListModel list = new DefaultListModel();
         try{
            String sql="SELECT * FROM `register`  order by NAME";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("NAME");
                 list.addElement(sums);
                
                 jList1.setModel(list);
              
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
    
     public void callmissS(){
     DefaultListModel list = new DefaultListModel();
     
      try{
          String sql="SELECT Distinct(SENDER) FROM `message`  where RECEIVER='"+sname.getText()+"' and LUS='NO'";
           
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
                 DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
        centre.setHorizontalAlignment(JLabel.CENTER);
         TableColumn col0=jTable1.getColumnModel().getColumn(0);
//        TableColumn col1=jTable1.getColumnModel().getColumn(1);
//         jTable1.getColumnModel().getColumn(1).setCellRenderer(centre);
//      
      
       
       col0.setPreferredWidth(300);
      // col1.setPreferredWidth(20);
         
    }
    
     public void call_budget(){
  String tmp =(String)jList1.getSelectedValue();
        try{
          String sql="SELECT NAME,FUNCTION FROM  register WHERE NAME='"+tmp+"' ";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                 
                String ad1 = rs.getString("NAME");
             rname.setText(ad1);     
               String add1 = rs.getString("FUNCTION");
              rfunction.setText(add1);
              
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
      //  here message
//               String smsg = null,rmsg = null;
//                 try {
//          
//           
//          String sql="SELECT message FROM  message WHERE  sender='"+rname.getText()+"'  and RECEIVER='"+sname.getText()+"' AND STATUT='YES'" ;
//        //    String sql="SELECT * FROM  budget_code where CODE like '"+recherche1.getText()+"%'  AND CAT='"+buss.getSelectedItem()+"' order by CODE";
//          
//            pst=con.prepareStatement(sql);
//            //pst.setString(1, tmp);
//            rs=pst.executeQuery();
//           while(rs.next()){
//                 
//              rmsg=rs.getString("message");
//              
//              
//              
//          }
// 
//StyledDocument  doc = jTextPane1.getStyledDocument();
//
//SimpleAttributeSet left = new SimpleAttributeSet();
//StyleConstants.setAlignment(left, StyleConstants.ALIGN_RIGHT);
//StyleConstants.setForeground(left, Color.BLACK);
//StyleConstants.setFontSize(left, 14);
//
//doc.insertString(doc.getLength(),"\n"+ rmsg+"  ", left );
//doc.setParagraphAttributes(doc.getLength(), 1, left, false);
//Toolkit.getDefaultToolkit().beep();
//        } catch (Exception ex) {
//          JOptionPane.showMessageDialog(null,ex);
//        } 
//       
//               //   String smsg,rmsg = null;
//                 try {
//          
//           
//          String sql="SELECT message FROM  message WHERE  sender='"+sname.getText()+"'  and RECEIVER='"+rname.getText()+"' AND STATUT='YES'" ;
//        //    String sql="SELECT * FROM  budget_code where CODE like '"+recherche1.getText()+"%'  AND CAT='"+buss.getSelectedItem()+"' order by CODE";
//          
//            pst=con.prepareStatement(sql);
//            //pst.setString(1, tmp);
//            rs=pst.executeQuery();
//           while(rs.next()){
//                 
//              smsg=rs.getString("message");
//              
//              
//              
//          }
// 
//StyledDocument  doc = jTextPane1.getStyledDocument();
//
//SimpleAttributeSet left = new SimpleAttributeSet();
//StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
//StyleConstants.setForeground(left, Color.RED);
//StyleConstants.setFontSize(left, 14);
//
//doc.insertString(doc.getLength(),"\n"+ smsg+"  ", left );
//doc.setParagraphAttributes(doc.getLength(), 1, left, false);
//Toolkit.getDefaultToolkit().beep();
//        } catch (Exception ex) {
//          JOptionPane.showMessageDialog(null,ex);
//        }      
       
 }
       public void  call_budgets()
   {
       
       
        try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
         String sql="SELECT NAME,FUNCTION FROM  register WHERE NAME='"+Table_click+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
               String ad1 = rs.getString("NAME");
             rname.setText(ad1);     
               String add1 = rs.getString("FUNCTION");
              rfunction.setText(add1);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         try {
        PreparedStatement pst = con.prepareStatement("UPDATE `message` SET `LUS`=? WHERE SENDER='"+rname.getText()+"' AND RECEIVER='"+sname.getText()+"'");
        
//`ACTION`, `NOMS`, `FONCTION`, `ROLL`, `MOTIF`, `DESTINATION`, `AUTORISER`, `DATE1`, `DATE2`, `JRS`, `HEURE`, `COMMENTAIRE`, `STATUT` 
       
         pst.setString(1, "YES");
        
       
         
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }  
       
    }
     
       
       
     //  here message
//               String smsg = null,rmsg = null;
//                 try {
//          
//           
//          String sql="SELECT message FROM  message WHERE  sender='"+rname.getText()+"'  and RECEIVER='"+sname.getText()+"'" ;
//        //    String sql="SELECT * FROM  budget_code where CODE like '"+recherche1.getText()+"%'  AND CAT='"+buss.getSelectedItem()+"' order by CODE";
//          
//            pst=con.prepareStatement(sql);
//            //pst.setString(1, tmp);
//            rs=pst.executeQuery();
//           while(rs.next()){
//                 
//              rmsg=rs.getString("message");
//              
//              
//              
//          }
// 
//StyledDocument  doc = jTextPane1.getStyledDocument();
//
//SimpleAttributeSet left = new SimpleAttributeSet();
//StyleConstants.setAlignment(left, StyleConstants.ALIGN_RIGHT);
//StyleConstants.setForeground(left, Color.BLACK);
//StyleConstants.setFontSize(left, 14);
//
//doc.insertString(doc.getLength(),"\n"+ rmsg+"  ", left );
//doc.setParagraphAttributes(doc.getLength(), 1, left, false);
//Toolkit.getDefaultToolkit().beep();
//        } catch (Exception ex) {
//          JOptionPane.showMessageDialog(null,ex);
//        } 
//       
//               //   String smsg,rmsg = null;
//                 try {
//          
//           
//          String sql="SELECT message FROM  message WHERE  sender='"+sname.getText()+"'  and RECEIVER='"+rname.getText()+"'" ;
//        //    String sql="SELECT * FROM  budget_code where CODE like '"+recherche1.getText()+"%'  AND CAT='"+buss.getSelectedItem()+"' order by CODE";
//          
//            pst=con.prepareStatement(sql);
//            //pst.setString(1, tmp);
//            rs=pst.executeQuery();
//           while(rs.next()){
//                 
//              smsg=rs.getString("message");
//              
//              
//              
//          }
// 
//StyledDocument  doc = jTextPane1.getStyledDocument();
//
//SimpleAttributeSet left = new SimpleAttributeSet();
//StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
//StyleConstants.setForeground(left, Color.RED);
//StyleConstants.setFontSize(left, 14);
//
//doc.insertString(doc.getLength(),"\n"+ smsg+"  ", left );
//doc.setParagraphAttributes(doc.getLength(), 1, left, false);
//Toolkit.getDefaultToolkit().beep();
//        } catch (Exception ex) {
//          JOptionPane.showMessageDialog(null,ex);
//        }  
 //}
    public void callmiss(){
             try {
          
           
          String sql="SELECT count(DISTINCT(RECEIVER))  FROM  message WHERE  RECEIVER='"+sname.getText()+"' and LUS='NO'" ;
        //    String sql="SELECT * FROM  budget_code where CODE like '"+recherche1.getText()+"%'  AND CAT='"+buss.getSelectedItem()+"' order by CODE";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
           while(rs.next()){
                 
             String msg=rs.getString("count(DISTINCT(RECEIVER))");
             bell.setText(msg);
              
              
              
          }
             }catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
             }
    }
    public void PlayBeep() {        
        //AudioClip clip = Applet.newAudioClip(getClass().getResource("/sounds/beep3.wav"));
        AudioClip clip = Applet.newAudioClip(getClass().getResource("MessageTone.mp3"));
        clip.play();
    }
    public void receiver(){
    String msg = null;
    try {
          
           
          String sqls="SELECT * FROM  message WHERE   sender='"+rname.getText()+"' and RECEIVER='"+sname.getText()+"' AND STATUT='NO'";
        //    String sql="SELECT * FROM  budget_code where CODE like '"+recherche1.getText()+"%'  AND CAT='"+buss.getSelectedItem()+"' order by CODE";
          
            pst=con.prepareStatement(sqls);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
           if(rs.next()){
                 
             
              try {
          
           
          String sql="SELECT message FROM  message WHERE  sender='"+rname.getText()+"'  and RECEIVER='"+sname.getText()+"'" ;
        //    String sql="SELECT * FROM  budget_code where CODE like '"+recherche1.getText()+"%'  AND CAT='"+buss.getSelectedItem()+"' order by CODE";
          
            pst=con.prepareStatement(sql);
            //pst.setString(1, tmp);
            rs=pst.executeQuery();
           while(rs.next()){
                 
              msg=rs.getString("message");
              
              
              
          }
 
StyledDocument  doc = jTextPane1.getStyledDocument();

SimpleAttributeSet left = new SimpleAttributeSet();
StyleConstants.setAlignment(left, StyleConstants.ALIGN_RIGHT);
StyleConstants.setForeground(left, Color.BLACK);
StyleConstants.setFontSize(left, 14);

doc.insertString(doc.getLength(),"\n"+ msg+"  ", left );
doc.setParagraphAttributes(doc.getLength(), 1, left, false);
Toolkit.getDefaultToolkit().beep();
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null,ex);
        } 
        //  UPDATE HERE 
                  try {
        PreparedStatement pst = con.prepareStatement("UPDATE `message` SET `STATUT`=? WHERE SENDER='"+rname.getText()+"' AND RECEIVER='"+sname.getText()+"'");
        
//`ACTION`, `NOMS`, `FONCTION`, `ROLL`, `MOTIF`, `DESTINATION`, `AUTORISER`, `DATE1`, `DATE2`, `JRS`, `HEURE`, `COMMENTAIRE`, `STATUT` 
       
         pst.setString(1, "YES");
        
       
         
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
              
          }else{
           
           
           }
 
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null,ex);
        } 
      
    
    }
public void save(){
 try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `message`(`SENDER`, `RECEIVER`, `MESSAGE`,`STATUT`,`LUS`)"
        +"value(?,?,?,?,?)");
        
//`ACTION`, `NOMS`, `FONCTION`, `ROLL`, `MOTIF`, `DESTINATION`, `AUTORISER`, `DATE1`, `DATE2`, `JRS`, `HEURE`, `COMMENTAIRE`, `STATUT` 
        pst.setString(1,sname.getText());
         pst.setString(2, rname.getText());
        pst.setString(3, msg.getText());
         pst.setString(4, "NO");
          pst.setString(5, "NO");
        
       
         
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        sname = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        bell = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        rfunction = new javax.swing.JLabel();
        rname = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        msg = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTabbedPane1.setBackground(new java.awt.Color(204, 255, 204));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jPanel7.setBackground(new java.awt.Color(204, 255, 204));

        jList1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Sebitu TRAORE", "Tresor LOROKENE", "Francois XANVIER", "Veronique MASUWA", "Judith MAKOFI" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Contacts", jPanel7);

        jPanel8.setBackground(new java.awt.Color(204, 255, 204));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setFocusable(false);
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setRowHeight(25);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Messages Nos Lus", jPanel8);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Human_Head_80px_1.png"))); // NOI18N
        jLabel3.setText("jLabel3");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Me.");

        sname.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sname.setText("Levi Likoyo");

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));

        bell.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Message_20px_1.png"))); // NOI18N
        bell.setText("0");
        bell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bellMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bell, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bell)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(jSeparator1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(sname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sname)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Vulcan_Head_50px.png"))); // NOI18N

        rfunction.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rfunction.setText("Him.");

        rname.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rname.setText("Sender");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rfunction)
                    .addComponent(rname))
                .addGap(181, 181, 181)
                .addComponent(jButton1)
                .addContainerGap(266, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(rfunction)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(rname))
                        .addComponent(jLabel6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 255, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Paper_Plane_24px.png"))); // NOI18N
        jLabel2.setText("Send");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jTextPane1.setEditable(false);
        jTextPane1.setBackground(new java.awt.Color(204, 255, 204));
        jTextPane1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jScrollPane4.setViewportView(jTextPane1);

        msg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(msg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(msg))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
if(msg.getText().equals("")){

}else{
        try {

StyledDocument  doc = jTextPane1.getStyledDocument();

SimpleAttributeSet left = new SimpleAttributeSet();
StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);

StyleConstants.setForeground(left, Color.RED);
 StyleConstants.setFontSize(left, 14);
 


doc.insertString(doc.getLength(),"\n  "+msg.getText()+"  ", left );
doc.setParagraphAttributes(doc.getLength(), 1, left, false);
        } catch (BadLocationException ex) {
            Logger.getLogger(chat.class.getName()).log(Level.SEVERE, null, ex);
        }
        save();
     msg.setText("");
}
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
jTextPane1.setText("");
        call_budget();        // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseClicked

    private void bellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bellMouseClicked
    callmissS();
      
            // TODO add your handling code here:
    }//GEN-LAST:event_bellMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
jTextPane1.setText("");
call_budgets();// TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 PlayBeep();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    //     EventQueue.invokeLater( () -> createAndShowGUI() );
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
            java.util.logging.Logger.getLogger(chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 WebLookAndFeel.install(true);
                new chat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bell;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextField msg;
    private javax.swing.JLabel rfunction;
    private javax.swing.JLabel rname;
    private javax.swing.JLabel sname;
    // End of variables declaration//GEN-END:variables
}
