/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
 *
 * @author Dosh
 */
public class Pharma_inventaire extends javax.swing.JPanel {
 private Connection con;
private Statement st;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls;
String NUM_ID,Table_click=null;
 DefaultTableModel mode;
 String ID=null;
  String IDS=null;
  String Total=null;
  String MAX=null;
    public Pharma_inventaire() {
        initComponents();
        con=JavaDbConnect.dbConnect();
        call_in_tables();
    }
public void call_in_tables(){
      try{
            // HERE
           ///MEDICAMENT,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM_ID,NUM,FOUR,STATUT,QTY_DD,QTY_DC
             String sql="SELECT MEDICAMENT,QTY_DEBIT AS QTY_IN,QTY_CREDIT AS QTY_OUT,DEBIT AS PT_IN,CREDIT AS PT_OUT,FOUR AS 'FOURNISSEUR  & CLIENT',DATES FROM  h_p_inventaire_medoc  order  by medicament";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
      
        
       TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
        TableColumn col3=jTable2.getColumnModel().getColumn(3);
        TableColumn col4=jTable2.getColumnModel().getColumn(4);
        TableColumn col5=jTable2.getColumnModel().getColumn(5);
       TableColumn col6=jTable2.getColumnModel().getColumn(6);
        
       
       
      
       
       col0.setPreferredWidth(300);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(50);
         col3.setPreferredWidth(50);
         col4.setPreferredWidth(50);
         col5.setPreferredWidth(150);
          col6.setPreferredWidth(20);
      
        
      
      
     
       
       
      // jTable1.setModel(mode);
       
     
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
       
     }

public void call_in_table_medoc(){
    if(jComboBox1.getSelectedItem().equals("Medicament")){
     try{
            // HERE
           ///MEDICAMENT,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM_ID,NUM,FOUR,STATUT,QTY_DD,QTY_DC
             String sql="SELECT MEDICAMENT,QTY_DEBIT AS QTY_IN,QTY_CREDIT AS QTY_OUT,DEBIT AS PT_IN,CREDIT AS PT_OUT,FOUR AS 'FOURNISSEUR  & CLIENT',DATES FROM  h_p_inventaire_medoc where medicament like '"+search.getText()+"%'  order  by medicament";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
      
        
       TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
        TableColumn col3=jTable2.getColumnModel().getColumn(3);
        TableColumn col4=jTable2.getColumnModel().getColumn(4);
        TableColumn col5=jTable2.getColumnModel().getColumn(5);
       TableColumn col6=jTable2.getColumnModel().getColumn(6);
        
       
       
      
       
       col0.setPreferredWidth(300);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(50);
         col3.setPreferredWidth(50);
         col4.setPreferredWidth(50);
         col5.setPreferredWidth(150);
          col6.setPreferredWidth(20);
      
     
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
     try{
            String sql="select medicament,sum(qty_debit),sum(qty_credit),sum(debit),sum(credit) from h_p_inventaire_medoc where medicament like '"+search.getText()+"%' ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("medicament");
                medoce.setText(sum);
                
                 Double sum1=rs.getDouble("sum(qty_debit)");
                qtyin.setText(""+sum1);
                
                 Double sum2=rs.getDouble("sum(qty_credit)");
                qtyout.setText(""+sum2);
                
                 Double sum3=rs.getDouble("sum(debit)");
                debit.setText(""+sum3);
                
                Double sum4=rs.getDouble("sum(credit)");
                credit.setText(""+sum4);
                
                Double soldqty=sum1-sum2;
                sold.setText(""+soldqty);
                
                 Double benefs=sum3-sum4;
                benef.setText(""+benefs);
                        
                  
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);  
          
        }
    
    }else  if(jComboBox1.getSelectedItem().equals("Client")){
         try{
            // HERE
           ///MEDICAMENT,CAT,CLASSI,QTY_DEBIT,QTY_CREDIT,DEBIT,CREDIT,NUM_ID,NUM,FOUR,STATUT,QTY_DD,QTY_DC
             String sql="SELECT MEDICAMENT,QTY_DEBIT AS QTY_IN,QTY_CREDIT AS QTY_OUT,DEBIT AS PT_IN,CREDIT AS PT_OUT,FOUR AS 'FOURNISSEUR  & CLIENT',DATES FROM  h_p_inventaire_medoc where FOUR like '"+search.getText()+"%'  and statut='Vente' order  by medicament";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
      
        
       TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
        TableColumn col3=jTable2.getColumnModel().getColumn(3);
        TableColumn col4=jTable2.getColumnModel().getColumn(4);
        TableColumn col5=jTable2.getColumnModel().getColumn(5);
       TableColumn col6=jTable2.getColumnModel().getColumn(6);
        
       
       
      
       
       col0.setPreferredWidth(300);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(50);
         col3.setPreferredWidth(50);
         col4.setPreferredWidth(50);
         col5.setPreferredWidth(150);
          col6.setPreferredWidth(20);
      
        
      
      
     
       
       
      // jTable1.setModel(mode);
       
     
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
          try{
            String sql="select * from h_p_inventaire_medoc where FOUR like '"+search.getText()+"%' AND STATUT='Vente'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("FOUR");
                medoce.setText(sum); }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);}
          qtyin.setText("Qty_In");
                qtyout.setText("Qty_Out");
                debit.setText("Debit");
                credit.setText("Credit");
                sold.setText("Sold");
                benef.setText("Benef");
    }else  if(jComboBox1.getSelectedItem().equals("Fournisseur")){
       try{
             String sql="SELECT MEDICAMENT,QTY_DEBIT AS QTY_IN,QTY_CREDIT AS QTY_OUT,DEBIT AS PT_IN,CREDIT AS PT_OUT,FOUR AS 'FOURNISSEUR  & CLIENT',DATES FROM  h_p_inventaire_medoc where FOUR like '"+search.getText()+"%'  and statut='Achat' order  by medicament";
     pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
      
        
       TableColumn col0=jTable2.getColumnModel().getColumn(0);
        TableColumn col1=jTable2.getColumnModel().getColumn(1);
        TableColumn col2=jTable2.getColumnModel().getColumn(2);
        TableColumn col3=jTable2.getColumnModel().getColumn(3);
        TableColumn col4=jTable2.getColumnModel().getColumn(4);
        TableColumn col5=jTable2.getColumnModel().getColumn(5);
       TableColumn col6=jTable2.getColumnModel().getColumn(6);
        
       
       
      
       
       col0.setPreferredWidth(300);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(50);
         col3.setPreferredWidth(50);
         col4.setPreferredWidth(50);
         col5.setPreferredWidth(150);
          col6.setPreferredWidth(20);
      
        
      
      
     
       
       
      // jTable1.setModel(mode);
       
     
    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);} 
          try{
            String sql="select * from h_p_inventaire_medoc where FOUR like '"+search.getText()+"%' AND STATUT='Achat'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("FOUR");
                medoce.setText(sum); }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);}
          qtyin.setText("Qty_In");
                qtyout.setText("Qty_Out");
                debit.setText("Debit");
                credit.setText("Credit");
                sold.setText("Sold");
                benef.setText("Benef");
    }else{
    
    
    }
     
       
     }

  public void report()
     {
          
     
          String tmp = medoce.getText();
             try{
                
                 
                 
                 String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"Pharma_inventaire_medicament.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                 
                 
                // String report ="C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\maretiauxrepport.jrxml";
                // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\Fiche_Materiaux_out.jrxml");
                String sql="select * from  h_p_inventaire_medoc where medicament ='"+tmp+"'";
                
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
         
//setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
     }
  
   public void report_four()
     {
          
     
          String tmp = medoce.getText();
             try{
                
                 
                 
                 String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"Pharma_inventaire_clients.jrxml";
                 
                    HashMap param= new HashMap();
    param.put("nom",jComboBox1.getSelectedItem());  
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                 
                 
                // String report ="C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\maretiauxrepport.jrxml";
                // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\Fiche_Materiaux_out.jrxml");
                String sql="select * from  h_p_inventaire_medoc where FOUR ='"+tmp+"'";
                
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
         
//setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        medoce = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        search = new javax.swing.JTextField();
        qtyin = new javax.swing.JTextField();
        qtyout = new javax.swing.JTextField();
        sold = new javax.swing.JTextField();
        debit = new javax.swing.JTextField();
        credit = new javax.swing.JTextField();
        benef = new javax.swing.JTextField();
        dates = new com.alee.extended.date.WebDateField();
        dates1 = new com.alee.extended.date.WebDateField();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(192, 255, 192));

        jPanel3.setBackground(new java.awt.Color(128, 255, 128));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Inventaire");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("X");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        medoce.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        medoce.setText("...");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(medoce)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(medoce))
                .addContainerGap())
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(192, 255, 192));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable2.setBackground(new java.awt.Color(192, 255, 192));
        jTable2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.setRowHeight(24);
        jTable2.setSelectionForeground(new java.awt.Color(198, 215, 233));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jScrollPane11.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(192, 255, 192));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Medicament", "Client", "Fournisseur" }));

        search.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        search.setText("Search");
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        qtyin.setEditable(false);
        qtyin.setBackground(new java.awt.Color(240, 240, 241));
        qtyin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        qtyin.setText("Qty_In");

        qtyout.setEditable(false);
        qtyout.setBackground(new java.awt.Color(240, 240, 241));
        qtyout.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        qtyout.setText("Qty_Out");

        sold.setEditable(false);
        sold.setBackground(new java.awt.Color(240, 240, 241));
        sold.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        sold.setText("Sold");

        debit.setEditable(false);
        debit.setBackground(new java.awt.Color(240, 240, 241));
        debit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        debit.setText("Debit");

        credit.setEditable(false);
        credit.setBackground(new java.awt.Color(240, 240, 241));
        credit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        credit.setText("Credit");

        benef.setEditable(false);
        benef.setBackground(new java.awt.Color(240, 240, 241));
        benef.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        benef.setText("Benef");

        dates.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        dates1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Print_30px.png"))); // NOI18N
        jLabel2.setText("Imprimer");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dates, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(dates1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(qtyin, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(qtyout, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sold, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(debit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(credit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(benef, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dates, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(search)
                    .addComponent(jComboBox1)
                    .addComponent(qtyout, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(debit, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sold)
                    .addComponent(credit, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dates1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(qtyin)
                    .addComponent(benef, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        this.show(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MousePressed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
       if(jComboBox1.getSelectedItem().equals("Medicament")){
        report();
        }else if(jComboBox1.getSelectedItem().equals("Client")){
       report_four();
        }else if(jComboBox1.getSelectedItem().equals("Fournisseur")){
       report_four();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
call_in_table_medoc();        // TODO add your handling code here:
    }//GEN-LAST:event_searchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField benef;
    private javax.swing.JTextField credit;
    private com.alee.extended.date.WebDateField dates;
    private com.alee.extended.date.WebDateField dates1;
    private javax.swing.JTextField debit;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel medoce;
    private javax.swing.JTextField qtyin;
    private javax.swing.JTextField qtyout;
    private javax.swing.JTextField search;
    private javax.swing.JTextField sold;
    // End of variables declaration//GEN-END:variables
}
