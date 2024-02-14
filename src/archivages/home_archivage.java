/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivages;

import intreprisemanagementsystem.JavaDbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Doshe PC
 */
public class home_archivage extends javax.swing.JInternalFrame {

 Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
DefaultTableModel mode;
    public home_archivage() {
        initComponents();
          con=JavaDbConnect.dbConnect();
          this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
       bui.setNorthPane(null);
          call();
    }
  public void call(){
   
try{
      String sql="SELECT count(id)  FROM  lettre  WHERE  status='Letter In'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                String sum=rs.getString("count(id)");
                lettrein.setText(sum);
            }
               
         } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);}
try{
      String sql="SELECT count(id) FROM  lettre  WHERE  status='Letter out'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                String sum=rs.getString("count(id)");
                lettreout.setText(sum);
            }
               
         } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);}

try{
      String sql="SELECT count(id) FROM  lettre  WHERE  status='DAO'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                String sum=rs.getString("count(id)");
                dao.setText(sum);
            }
               
         } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);}

try{
      String sql="SELECT count(id) FROM  lettre  WHERE  status='PRODOC'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                String sum=rs.getString("count(id)");
                prodoc.setText(sum);
            }
               
         } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);}

try{
      String sql="SELECT count(id) FROM  lettre  WHERE  status='DOC Administratifs'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                String sum=rs.getString("count(id)");
                doc_admin.setText(sum);
            }
               
         } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);}

try{
      String sql="SELECT count(id) FROM  lettre  WHERE  status='Rapports Annuel'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                String sum=rs.getString("count(id)");
               rapannuel.setText(sum);
            }
               
         } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);}

try{
      String sql="SELECT count(id) FROM  lettre  WHERE  status='Contrats Fournisseur'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                String sum=rs.getString("count(id)");
              contrat_four.setText(sum);
            }
               
         } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);}

try{
      String sql="SELECT count(id) FROM  lettre  WHERE  status='Factures et docs achat'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                String sum=rs.getString("count(id)");
              facture.setText(sum);
            }
               
         } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);}

try{
      String sql="SELECT count(id) FROM  lettre  WHERE  status='Autres'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                String sum=rs.getString("count(id)");
              autres.setText(sum);
            }
               
         } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);}

  }
    
     public void Table_lettrein()
    {
         try{
           
             String sql="SELECT Dates,Num,exp as Expediteur,Objet FROM lettre where STATUS='Letter In'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
    lettre_show.tbshows.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=lettre_show.tbshows.getColumnModel().getColumn(0);
        TableColumn col1=lettre_show.tbshows.getColumnModel().getColumn(1);
        TableColumn col2=lettre_show.tbshows.getColumnModel().getColumn(2);
        TableColumn col3=lettre_show.tbshows.getColumnModel().getColumn(3);
      
        col0.setPreferredWidth(30);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(250);
       
 }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    } 
      public void Table_lettreout()
    {
          try{
           
             String sql="SELECT Dates,Num,exp as Expediteur,Objet FROM lettre where STATUS='"+lettre_show.title.getText()+"'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
    
    lettre_show.tbshows.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
       
        TableColumn col0=lettre_show.tbshows.getColumnModel().getColumn(0);
        TableColumn col1=lettre_show.tbshows.getColumnModel().getColumn(1);
        TableColumn col2=lettre_show.tbshows.getColumnModel().getColumn(2);
        TableColumn col3=lettre_show.tbshows.getColumnModel().getColumn(3);
      
        col0.setPreferredWidth(30);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(250);
       
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        prodoc = new javax.swing.JLabel();
        contrat_four = new javax.swing.JLabel();
        lettreout = new javax.swing.JLabel();
        dao = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lettrein = new javax.swing.JLabel();
        doc_admin = new javax.swing.JLabel();
        rapannuel = new javax.swing.JLabel();
        facture = new javax.swing.JLabel();
        autres = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Course_16px.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        prodoc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        prodoc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prodoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-folder-100.png"))); // NOI18N
        prodoc.setText("0");
        prodoc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRODOC", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        prodoc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        prodoc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        prodoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prodocMouseClicked(evt);
            }
        });

        contrat_four.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        contrat_four.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contrat_four.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-folder-100.png"))); // NOI18N
        contrat_four.setText("0");
        contrat_four.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contrats Fournisseur", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        contrat_four.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        contrat_four.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        contrat_four.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contrat_fourMouseClicked(evt);
            }
        });

        lettreout.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lettreout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lettreout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-folder-100.png"))); // NOI18N
        lettreout.setText("0");
        lettreout.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lettres sortant", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        lettreout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lettreout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lettreout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lettreoutMouseClicked(evt);
            }
        });

        dao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-folder-100.png"))); // NOI18N
        dao.setText("0");
        dao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DAO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        dao.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dao.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        dao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                daoMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-folder-100.png"))); // NOI18N
        jLabel5.setText("0");
        jLabel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dossier de personnel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        lettrein.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lettrein.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lettrein.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-folder-100.png"))); // NOI18N
        lettrein.setText("0");
        lettrein.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lettres entrant", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        lettrein.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lettrein.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lettrein.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lettreinMouseClicked(evt);
            }
        });

        doc_admin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        doc_admin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        doc_admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-folder-100.png"))); // NOI18N
        doc_admin.setText("0");
        doc_admin.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DOC Administratifs", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        doc_admin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        doc_admin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        doc_admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                doc_adminMouseClicked(evt);
            }
        });

        rapannuel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rapannuel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rapannuel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-folder-100.png"))); // NOI18N
        rapannuel.setText("0");
        rapannuel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rapports Annuel", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        rapannuel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rapannuel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        rapannuel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rapannuelMouseClicked(evt);
            }
        });

        facture.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        facture.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        facture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-folder-100.png"))); // NOI18N
        facture.setText("0");
        facture.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Factures et docs achat", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        facture.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        facture.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        facture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                factureMouseClicked(evt);
            }
        });

        autres.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        autres.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        autres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-folder-100.png"))); // NOI18N
        autres.setText("0");
        autres.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Autres", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        autres.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        autres.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        autres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                autresMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lettrein)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contrat_four, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(prodoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doc_admin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rapannuel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lettreout, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(autres)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(facture)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contrat_four)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(prodoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(doc_admin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rapannuel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lettrein, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lettreout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(autres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(facture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(152, 152, 152))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
        );

        boody.setBackground(new java.awt.Color(255, 255, 255));
        boody.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        boody.setLayout(new javax.swing.BoxLayout(boody, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boody, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
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
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Course_16px.png"))); // NOI18N
        jMenuItem1.setText("New");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Refresh_16px.png"))); // NOI18N
        jMenuItem2.setText("Refresh");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        home_archivage.boody.removeAll();
        home_archivage.boody.add(new newarchivage());
        // openpdf();
       home_archivage.boody.repaint();
         home_archivage.boody.revalidate();         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        call();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked

    private void prodocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prodocMouseClicked
        lettre_show m= new lettre_show ();
        m.show();
        lettre_show.title.setText("PRODOC");
        Table_lettreout();        // TODO add your handling code here:
    }//GEN-LAST:event_prodocMouseClicked

    private void contrat_fourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contrat_fourMouseClicked
        lettre_show m= new lettre_show ();
        m.show();
        lettre_show.title.setText("Contrats Fournisseur");
        Table_lettreout();        // TODO add your handling code here:
    }//GEN-LAST:event_contrat_fourMouseClicked

    private void lettreoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lettreoutMouseClicked
        lettre_show m= new lettre_show ();
        m.show();
        lettre_show.title.setText("Lettre Out");
        Table_lettreout();
        // TODO add your handling code here:
    }//GEN-LAST:event_lettreoutMouseClicked

    private void daoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_daoMouseClicked

        lettre_show m= new lettre_show ();
        m.show();
        lettre_show.title.setText("DAO");
        Table_lettreout();
        // TODO add your handling code here:
    }//GEN-LAST:event_daoMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        lettre_show m= new lettre_show ();
        m.show();
        lettre_show.title.setText("Dossier de personnel");
        Table_lettreout();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void lettreinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lettreinMouseClicked

        lettre_show m= new lettre_show ();
        m.show();
        lettre_show.title.setText("Lettre In");
        Table_lettrein();
        // TODO add your handling code here:
    }//GEN-LAST:event_lettreinMouseClicked

    private void doc_adminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doc_adminMouseClicked
        lettre_show m= new lettre_show ();
        m.show();
        lettre_show.title.setText("DOC Administratifs");
        Table_lettreout();        // TODO add your handling code here:
    }//GEN-LAST:event_doc_adminMouseClicked

    private void rapannuelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rapannuelMouseClicked
        lettre_show m= new lettre_show ();
        m.show();
        lettre_show.title.setText("Rapports Annuel");
        Table_lettreout();         // TODO add your handling code here:
    }//GEN-LAST:event_rapannuelMouseClicked

    private void factureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_factureMouseClicked
        lettre_show m= new lettre_show ();
        m.show();
        lettre_show.title.setText("Factures et docs achat");
        Table_lettreout();         // TODO add your handling code here:
    }//GEN-LAST:event_factureMouseClicked

    private void autresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_autresMouseClicked
        lettre_show m= new lettre_show ();
        m.show();
        lettre_show.title.setText("Autres");
        Table_lettreout();        // TODO add your handling code here:
    }//GEN-LAST:event_autresMouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel autres;
    public static final javax.swing.JPanel boody = new javax.swing.JPanel();
    private javax.swing.JLabel contrat_four;
    private javax.swing.JLabel dao;
    private javax.swing.JLabel doc_admin;
    private javax.swing.JLabel facture;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lettrein;
    private javax.swing.JLabel lettreout;
    private javax.swing.JLabel prodoc;
    private javax.swing.JLabel rapannuel;
    // End of variables declaration//GEN-END:variables
}
