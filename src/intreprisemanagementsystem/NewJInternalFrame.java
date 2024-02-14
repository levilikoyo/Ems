/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import static intreprisemanagementsystem.journal_caisse.jTable1;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author DOSHE_PC
 */
public class NewJInternalFrame extends javax.swing.JInternalFrame {
  Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
    public NewJInternalFrame() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
       bui.setNorthPane(null);
       con=JavaDbConnect.dbConnect();
       calltask();
       callbus();
       Groupe1();
       Groupe2();
    }
         public void Groupe1(){
ButtonGroup bg1 = new ButtonGroup();
bg1.add(ongoing);
bg1.add(end);
bg1.add(postpone);

}
               public void Groupe2(){
ButtonGroup bg1 = new ButtonGroup();
bg1.add(stop);
bg1.add(endp);
bg1.add(running);

}
    public void calltask(){
     boody.add(new project_task_dash());
boody.repaint();
boody.revalidate();
    }
    public void callbus(){
    
         try{
            String sql="select * from projet";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("PROJET_NUM");
                 buss.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }   
    
    }
      public void show_in_table(){
try{
            String sql="select * from projet where PROJET_NUM='"+buss.getSelectedItem()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("PROJET");
                jEditorPane1.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }   

 try{
    String sql="select PROJET.PROJET_LOC AS 'SITE',PROJET.DATEIN AS'START',PROJET.DATEOUT AS 'END',PROJET.COUTS AS 'BUDGET',PROJET.SUP AS 'SUPERVISOR',projet_bayeur.BAYEUR AS'SPONSOR',projet_bayeur.SUPERVISEUR AS 'CONTACT' from projet,projet_bayeur where projet.PROJET_NUM='"+buss.getSelectedItem()+"' and projet_bayeur.PROJET_NUM='"+buss.getSelectedItem()+"'" ;
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable_projectInfo.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex); }
 
      }
      //BUDGET
     public void show_in_tabledash(){
try{
     String sql="SELECT CODE,ITEM, SUM(DEBIT) AS DEBIT, SUM(CREDIT) AS CREDIT, (sum(debit)-sum(credit)) as SOLD ,((SUM(CREDIT)*100/SUM(DEBIT)))AS 'POURCENTAGE',CAT,SUB_CAT FROM  budget_trans  WHERE  PROJET='"+buss.getSelectedItem()+"' GROUP BY CODE";
  //  String sql="SELECT `CODE`,`ITEM`,`DEBIT`,`CREDIT`,`SOLD`,(CREDIT*100/DEBIT)AS 'POURCENTAGE',`CAT`,`SUB_CAT` FROM `budget_show` WHERE `PROJET`= '"+buss.getSelectedItem()+"'" ;
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       project_task_dash.jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
DefaultTableCellRenderer rigth =new DefaultTableCellRenderer();
centre.setHorizontalAlignment(JLabel.CENTER);
rigth.setHorizontalAlignment(JLabel.RIGHT);
         TableColumn col0=project_task_dash.jTable2.getColumnModel().getColumn(0);
        TableColumn col1=project_task_dash.jTable2.getColumnModel().getColumn(1);
        TableColumn col2=project_task_dash.jTable2.getColumnModel().getColumn(2);
        TableColumn col3=project_task_dash.jTable2.getColumnModel().getColumn(3);
        
        project_task_dash.jTable2.getColumnModel().getColumn(0).setCellRenderer(centre);
       project_task_dash.jTable2.getColumnModel().getColumn(2).setCellRenderer(rigth);
       project_task_dash.jTable2.getColumnModel().getColumn(3).setCellRenderer(rigth);
           
        TableColumn col4=project_task_dash.jTable2.getColumnModel().getColumn(4);
          
         project_task_dash.jTable2.getColumnModel().getColumn(4).setCellRenderer(rigth);
           TableColumn col5=project_task_dash.jTable2.getColumnModel().getColumn(5);
            project_task_dash.jTable2.getColumnModel().getColumn(5).setCellRenderer(rigth);
            TableColumn col6=project_task_dash.jTable2.getColumnModel().getColumn(6);
            TableColumn col7=project_task_dash.jTable2.getColumnModel().getColumn(7);
      
       
       col0.setPreferredWidth(50);
       col1.setPreferredWidth(200);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(100);
       col4.setPreferredWidth(100);
       col5.setPreferredWidth(120);
       col6.setPreferredWidth(200);
        col7.setPreferredWidth(200);

               show_in_tabledash_task();
             }
     
      public void show_in_tabledash_task(){
try{
    //`projet_task`(`TASK`, `NAME`, `DURATION`, `DATE_IN`, `DATE_OUT`,PROJET, `NUM`)
    String sql="SELECT  TASK,NAME,DURATION,DATE_IN,DATE_OUT,NUM,STATUS,P_D,P_DATE FROM projet_task WHERE PROJET = '"+buss.getSelectedItem()+"'" ;
    //`LIBELLE`, `ENTRE`, `SORTIE`, `DATE`, `BUSS`, `USED`, `NUM`
    pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       project_task_dash.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
    
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, ex);
             }
//DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
//DefaultTableCellRenderer rigth =new DefaultTableCellRenderer();
//centre.setHorizontalAlignment(JLabel.CENTER);
//rigth.setHorizontalAlignment(JLabel.RIGHT);
         TableColumn col0=project_task_dash.jTable1.getColumnModel().getColumn(0);
        TableColumn col1=project_task_dash.jTable1.getColumnModel().getColumn(1);
        TableColumn col2=project_task_dash.jTable1.getColumnModel().getColumn(2);
        TableColumn col3=project_task_dash.jTable1.getColumnModel().getColumn(3);
        
       // project_task_dash.jTable1.getColumnModel().getColumn(0).setCellRenderer(centre);
     //  project_task_dash.jTable1.getColumnModel().getColumn(2).setCellRenderer(rigth);
       //project_task_dash.jTable1.getColumnModel().getColumn(3).setCellRenderer(rigth);
           
        TableColumn col4=project_task_dash.jTable1.getColumnModel().getColumn(4);
          
      //   project_task_dash.jTable1.getColumnModel().getColumn(4).setCellRenderer(rigth);
           TableColumn col5=project_task_dash.jTable1.getColumnModel().getColumn(5);
           TableColumn col6=project_task_dash.jTable1.getColumnModel().getColumn(6);
           TableColumn col7=project_task_dash.jTable1.getColumnModel().getColumn(7);
           TableColumn col8=project_task_dash.jTable1.getColumnModel().getColumn(8);
          // TableColumn col9=project_task_dash.jTable1.getColumnModel().getColumn(9);
         //   project_task_dash.jTable1.getColumnModel().getColumn(5).setCellRenderer(rigth);
            
       
         
       col0.setPreferredWidth(200);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(10);
       col3.setPreferredWidth(30);
       col4.setPreferredWidth(30);
       col5.setPreferredWidth(50);
       
       col6.setPreferredWidth(50);
       col7.setPreferredWidth(10);
       col8.setPreferredWidth(30);
       
             }
     
    
    public void update(){
       
    int row= project_task_dash.jTable1.getSelectedRow();
            String Table_click = (project_task_dash.jTable1.getModel().getValueAt(row,5). toString());
     try{
        String sql="UPDATE `projet_task` SET `STATUS`=? WHERE  NUM='"+Table_click+"'";
        pst=con.prepareStatement(sql);
        if(ongoing.isSelected()){
        pst.setString(1,"On Going");
        }else if(end.isSelected()){
         pst.setString(1,"End Task");
        
        }else{
        pst.setString(1,"Postpone Task");
        }



    pst.executeUpdate();
   // JOptionPane.showMessageDialog(null, "DATA UPDATED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}   
          show_in_tabledash_task();
    Table_click=null;
    }
     
     public void callbudget(){
      DecimalFormat x = new DecimalFormat("$ #,##0.00");
      DecimalFormat xx = new DecimalFormat("% #,##");
      try{
            String sql="SELECT SUM(DEBIT),SUM(CREDIT) FROM   budget_trans Where PROJET='"+buss.getSelectedItem()+"' ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                Double sums=rs.getDouble("sum(CREDIT)");
                int sumsi=rs.getInt("sum(CREDIT)");
               // credit.setText(x.format(sums));
               credit.setText(""+sums);
                Double sums1=rs.getDouble("sum(DEBIT)");
               int sums1i=rs.getInt("sum(DEBIT)");
                debit.setText(x.format(sums1));
                Double sums2= sums1-sums;
                int sums2i= sums1i-sumsi;
                sold.setText(x.format(sums2));
                int POURi= sumsi*100/sums1i;
                Double POUR= sums*100/sums1;
                pour.setText(xx.format(POUR));
                ProgressBar1.setValue(POURi);
                if(POURi >=85){
     
     ProgressBar1.setProgressTopColor(Color.red);
    // ProgressBar1.setAlignmentY(TOP_ALIGNMENT);
     }else {
     ProgressBar1.setProgressTopColor(Color.GREEN);
     }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     
     }
 
     
     
   public void call_staus_project(){
   
   
     try{
          String sql = "SELECT * FROM  projet WHERE PROJET_NUM= '"+buss.getSelectedItem()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
               
               String sum3=rs.getString("STATUS");
               if(sum3.equals("On Going")){
               running.setSelected(true);
              stop.setSelected(false);
               endp.setSelected(false);
               }else if(sum3.equals("End")){
               running.setSelected(false);
               endp.setSelected(true);
               stop.setSelected(false);
               }else{
               running.setSelected(false);
               endp.setSelected(false);
               stop.setSelected(true);
               }
                 
                
                 
                 }
         
    }catch(Exception ex ){
         JOptionPane.showMessageDialog(null,ex);
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
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        projet = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel10 = new javax.swing.JPanel();
        stop = new javax.swing.JRadioButton();
        running = new javax.swing.JRadioButton();
        endp = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_projectInfo = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jTextField7 = new javax.swing.JTextField();
        webDateField1 = new com.alee.extended.date.WebDateField();
        jButton1 = new javax.swing.JButton();
        jTextField8 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        pour = new javax.swing.JLabel();
        ProgressBar1 = new com.alee.laf.progressbar.WebProgressBar();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        boody = new javax.swing.JPanel();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTabbedPane1.addTab("Home", jTabbedPane2);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Project");

        buss.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Project" }));
        buss.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                bussPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jEditorPane1.setEditable(false);
        projet.setViewportView(jEditorPane1);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        stop.setText("Stop");
        stop.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        stop.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        stop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stopMouseClicked(evt);
            }
        });

        running.setText("Running");
        running.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        running.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        running.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                runningMouseClicked(evt);
            }
        });

        endp.setText("End");
        endp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        endp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        endp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                endpMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stop)
                    .addComponent(running)
                    .addComponent(endp))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(stop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(running)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 38, Short.MAX_VALUE))
                    .addComponent(projet, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(projet, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable_projectInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTable_projectInfo);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Information on Project", jPanel3);

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        ongoing.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ongoing.setText("On going Task");

        end.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        end.setText("End Task");

        postpone.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        postpone.setText("Postpone Task");

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        webDateField1.setText("webDateField1");
        webDateField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField8.setEditable(false);
        jTextField8.setBackground(new java.awt.Color(240, 240, 241));
        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField8.setText("D");

        name_task.setEditable(false);
        name_task.setBackground(new java.awt.Color(240, 240, 241));
        name_task.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        task.setEditable(false);
        task.setBackground(new java.awt.Color(240, 240, 241));
        task.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(task);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(name_task, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(ongoing)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(end)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(postpone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(name_task, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ongoing)
                    .addComponent(end)
                    .addComponent(postpone)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Print Task");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Project Task", jPanel4);

        pour.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pour.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pour.setText("0%");
        pour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pourMouseClicked(evt);
            }
        });

        debit.setEditable(false);
        debit.setBackground(new java.awt.Color(240, 240, 241));
        debit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        debit.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        credit.setEditable(false);
        credit.setBackground(new java.awt.Color(240, 240, 241));
        credit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        credit.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        sold.setEditable(false);
        sold.setBackground(new java.awt.Color(240, 240, 241));
        sold.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sold.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable_tasklb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(jTable_tasklb);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(debit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(credit, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sold)
                    .addComponent(pour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(ProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pour)
                        .addGap(8, 8, 8)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(debit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(credit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Project Budget", jPanel5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        boody.setLayout(new javax.swing.BoxLayout(boody, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane1.setViewportView(boody);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bussPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_bussPopupMenuWillBecomeInvisible
show_in_table();
show_in_tabledash();
callbudget();
call_staus_project();// TODO add your handling code here:
    }//GEN-LAST:event_bussPopupMenuWillBecomeInvisible

    private void pourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pourMouseClicked
             // TODO add your handling code here:
    }//GEN-LAST:event_pourMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
update();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void stopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopMouseClicked
  try{
        String sql="UPDATE `projet` SET `STATUS`=? WHERE  PROJET_NUM='"+buss.getSelectedItem()+"'";
        pst=con.prepareStatement(sql);
        pst.setString(1,"Stop");
    pst.executeUpdate();
   // JOptionPane.showMessageDialog(null, "DATA UPDATED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}           // TODO add your handling code here:
    }//GEN-LAST:event_stopMouseClicked

    private void runningMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_runningMouseClicked
  try{
        String sql="UPDATE `projet` SET `STATUS`=? WHERE  PROJET_NUM='"+buss.getSelectedItem()+"'";
        pst=con.prepareStatement(sql);
        pst.setString(1,"On Going");
    pst.executeUpdate();
   // JOptionPane.showMessageDialog(null, "DATA UPDATED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}          // TODO add your handling code here:
    }//GEN-LAST:event_runningMouseClicked

    private void endpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_endpMouseClicked
  try{
        String sql="UPDATE `projet` SET `STATUS`=? WHERE  PROJET_NUM='"+buss.getSelectedItem()+"'";
        pst=con.prepareStatement(sql);
        pst.setString(1,"End");
    pst.executeUpdate();
   // JOptionPane.showMessageDialog(null, "DATA UPDATED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}          // TODO add your handling code here:
    }//GEN-LAST:event_endpMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.laf.progressbar.WebProgressBar ProgressBar1;
    private javax.swing.JPanel boody;
    public static final javax.swing.JComboBox<String> buss = new javax.swing.JComboBox<>();
    public static final javax.swing.JTextField credit = new javax.swing.JTextField();
    public static final javax.swing.JTextField debit = new javax.swing.JTextField();
    public static final javax.swing.JRadioButton end = new javax.swing.JRadioButton();
    private javax.swing.JRadioButton endp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable_projectInfo;
    public static final javax.swing.JTable jTable_tasklb = new javax.swing.JTable();
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    public static final javax.swing.JTextField name_task = new javax.swing.JTextField();
    public static final javax.swing.JRadioButton ongoing = new javax.swing.JRadioButton();
    public static final javax.swing.JRadioButton postpone = new javax.swing.JRadioButton();
    private javax.swing.JLabel pour;
    private javax.swing.JScrollPane projet;
    private javax.swing.JRadioButton running;
    public static final javax.swing.JTextField sold = new javax.swing.JTextField();
    private javax.swing.JRadioButton stop;
    public static final javax.swing.JEditorPane task = new javax.swing.JEditorPane();
    private com.alee.extended.date.WebDateField webDateField1;
    // End of variables declaration//GEN-END:variables
}
