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
 *
 * @author DOSHE
 */
public class inventairemtr extends javax.swing.JInternalFrame {
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
//DefaultTableModel mode;
 String rolls;
 String NUM_ID;
 DefaultTableModel mode; 
    public inventairemtr() {
        initComponents();
              con=JavaDbConnect.dbConnect();
        Call_ID_TO_Chantier();
        Call_ID_TO_material();
        call_table();
        webDateField2.setDate(new Date());
        webDateField1.setDate(new Date());
    }
public void Call_ID_TO_material()
    {
         {
        try{
            String sql="select distinct(ITEM) from inventairemtr";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("ITEM");
                  mat.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
 public void Call_ID_TO_Chantier()
    {
         {
        try{
            String sql="select DISTINCT(SITE) from inventairemtr";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("SITE");
                  site.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
 public void Call_SUMINVERTAIRE()
    {
          SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
          String addDate2 = dateFormat1.format(webDateField2.getDate());
        Double c;
         {
        try{
            String sql="select SUM(`DEBIT`), SUM(`CREDIT`) from inventairemtr where ITEM='"+mat.getSelectedItem()+"' and DATES BETWEEN '"+addDate1+"' and '"+addDate2+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                Double sum=rs.getDouble("SUM(`DEBIT`)");
                  qtyin.setText(sum.toString());
                  
                    Double sum1=rs.getDouble("SUM(`CREDIT`)");
                 qtyout.setText(sum1.toString());
                 
                 c= sum-sum1;
                 sold.setText(c.toString());
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
 
 public void Call_SUMINVERTAIRESITE()
    {
         SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
          String addDate2 = dateFormat1.format(webDateField2.getDate());
        Double c;
         {
                  try{
            String sql="select SUM(`DEBIT`), SUM(`CREDIT`) from inventairemtr where ITEM='"+mat.getSelectedItem()+"' and DATES BETWEEN '"+addDate1+"' and '"+addDate2+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                Double sum=rs.getDouble("SUM(`DEBIT`)");
                  qtyin.setText(sum.toString());
                  
                    Double sum1=rs.getDouble("SUM(`CREDIT`)");
                 qtyout.setText(sum1.toString());
                 
                 c= sum-sum1;
                 sold.setText(c.toString());
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        } 
        try{
            String sql="select SUM(`CREDIT`) from inventairemtr where ITEM='"+mat.getSelectedItem()+"' and site='"+site.getSelectedItem()+"' and DATES BETWEEN '"+addDate1+"' and '"+addDate2+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              
                  
                    Double sum1=rs.getDouble("SUM(`CREDIT`)");
                 qty_site.setText(sum1.toString());
                 
               
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      
    }
    }
 
   public void call_table(){
      
           try{
           
             String sql="SELECT `ITEM`, `DEBIT`, `CREDIT`, `UNITY`, `DATES`, `SITE`, `PARPUSE`, `EMP`, `NUM_ID` FROM `inventairemtr` ";
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
        TableColumn col8=jTable1.getColumnModel().getColumn(8);
       
       
       
      
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(150);
     col7.setPreferredWidth(80);
       col8.setPreferredWidth(50);
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
   
   
    public void call_table_ITEM(){
       SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
          String addDate2 = dateFormat1.format(webDateField2.getDate());
           try{
           
             String sql="SELECT `ITEM`, `DEBIT`, `CREDIT`, `UNITY`, `DATES`, `SITE`, `PARPUSE`, `EMP`, `NUM_ID` FROM `inventairemtr` WHERE ITEM='"+mat.getSelectedItem()+"' and DATES BETWEEN '"+addDate1+"' and '"+addDate2+"'";
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
        TableColumn col8=jTable1.getColumnModel().getColumn(8);
       
       
       
      
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(150);
     col7.setPreferredWidth(80);
       col8.setPreferredWidth(50);
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
     public void call_table_ITEMSITE(){
SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
          String addDate2 = dateFormat1.format(webDateField2.getDate());
           try{
           
             String sql="SELECT `ITEM`, `DEBIT`, `CREDIT`, `UNITY`, `DATES`, `SITE`, `PARPUSE`, `EMP`, `NUM` FROM `inventairemtr` WHERE ITEM='"+mat.getSelectedItem()+"' and DATES BETWEEN '"+addDate1+"' and '"+addDate2+"'";
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
        TableColumn col8=jTable1.getColumnModel().getColumn(8);
       
       
       
      
       
       col0.setPreferredWidth(150);
       col1.setPreferredWidth(20);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(50);
       col6.setPreferredWidth(150);
     col7.setPreferredWidth(80);
       col8.setPreferredWidth(50);
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
    
     public void call_table_ITEM_site(){
      SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
          String addDate2 = dateFormat1.format(webDateField2.getDate());
           try{
           
             String sql="SELECT `ITEM`,`CREDIT`, `UNITY`, `DATES`,`PARPUSE`, `EMP`, `NUM_ID` FROM `inventairemtr` WHERE site='"+site.getSelectedItem()+"'and DATES BETWEEN '"+addDate1+"' and '"+addDate2+"'";
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
        
        
       
       
       
      
       
       col0.setPreferredWidth(250);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(150);
       col5.setPreferredWidth(100);
       col6.setPreferredWidth(100);
    
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
     
     
      public void call_table_ITEM_sitesS(){
      SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
          String addDate2 = dateFormat1.format(webDateField2.getDate());
           try{
           
             String sql="SELECT `ITEM`,`CREDIT`, `UNITY`, `DATES`,`PARPUSE`, `EMP`, `NUM_ID` FROM `inventairemtr` WHERE ITEM='"+mat.getSelectedItem()+"' and SITE='"+site.getSelectedItem()+"' and DATES BETWEEN '"+addDate1+"' and '"+addDate2+"'";
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
        
        
       
       
       
      
       
       col0.setPreferredWidth(250);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(20);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(150);
       col5.setPreferredWidth(100);
       col6.setPreferredWidth(100);
    
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     try{
            String sql="select SUM(`CREDIT`) from inventairemtr where ITEM='"+mat.getSelectedItem()+"' and site='"+site.getSelectedItem()+"' and DATES BETWEEN '"+addDate1+"' and '"+addDate2+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
              
                  
                    Double sum1=rs.getDouble("SUM(`CREDIT`)");
                 qty_site.setText(sum1.toString());
                 
               
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      
      }
    
      
      public void report(){
      SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
          String addDate2 = dateFormat1.format(webDateField2.getDate());
       try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"inv_materiaux.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                String sql="SELECT * FROM  inventairemtr where ITEM ='"+mat.getSelectedItem ()+"' and DATES BETWEEN '"+addDate1+"' and '"+addDate2+"'";
              
     HashMap param= new HashMap();
    param.put("date2", webDateField2.getText());
     param.put("date1",webDateField1.getText());
                
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer ms= new JasperViewer(jp);
                 ms.setAlwaysOnTop (true);
                 ms.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
             }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
        
      
      }
      
      public void report_SITE(){
      SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
          String addDate2 = dateFormat1.format(webDateField2.getDate());
       try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"invt_materiaux_site.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                String sql="SELECT * FROM  inventairemtr where SITE ='"+site.getSelectedItem ()+"' and DATES BETWEEN '"+addDate1+"' and '"+addDate2+"'";
              
     HashMap param= new HashMap();
    param.put("date2", webDateField2.getText());
     param.put("date1",webDateField1.getText());
                
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer ms= new JasperViewer(jp);
                 ms.setAlwaysOnTop (true);
                 ms.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
             }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
        
      
      }
       public void report_SITE_MAT(){
      SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
          String addDate2 = dateFormat1.format(webDateField2.getDate());
       try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"invt_materiaux_site.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                String sql="SELECT * FROM  inventairemtr where ITEM='"+mat.getSelectedItem()+"' AND  SITE ='"+site.getSelectedItem ()+"' and DATES BETWEEN '"+addDate1+"' and '"+addDate2+"'";
              
     HashMap param= new HashMap();
    param.put("date2", webDateField2.getText());
     param.put("date1",webDateField1.getText());
                
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer ms= new JasperViewer(jp);
                 ms.setAlwaysOnTop (true);
                 ms.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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

        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        qtyin = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        qtyout = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        sold = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        mat = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        site = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        webDateField1 = new com.alee.extended.date.WebDateField();
        webDateField2 = new com.alee.extended.date.WebDateField();
        jLabel2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        qty_site = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jToggleButton1.setText("jToggleButton1");

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setTitle("Materials Inventory");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Edit_Property_16px.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Materials", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        jPanel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        qtyin.setEditable(false);
        qtyin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        qtyin.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(qtyin, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(qtyin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, 58));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Materials", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        jPanel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        qtyout.setEditable(false);
        qtyout.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        qtyout.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(qtyout, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(qtyout, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(711, 10, 100, 58));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Materials", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        jPanel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        sold.setEditable(false);
        sold.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        sold.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sold, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(sold, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, -1, 58));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Materials", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        mat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Materials" }));
        mat.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mat.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                matPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        mat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mat, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(mat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, 58));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Materials", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N

        site.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        site.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Site" }));
        site.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        site.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                sitePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(site, 0, 141, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(site, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, -1, 58));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Date1");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 20));

        webDateField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(webDateField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 110, -1));

        webDateField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(webDateField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 110, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Date2");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 40, 20));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Materials", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        jPanel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        qty_site.setEditable(false);
        qty_site.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        qty_site.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(qty_site, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(qty_site, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, -1, 58));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 1010, 70));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1006, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 93, 1010, 310));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 420));

        jMenu2.setText("X");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu1.setText("File");

        jMenuItem1.setText("Print");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Print_All");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        setBounds(15, 10, 1040, 467);
    }// </editor-fold>//GEN-END:initComponents

    private void matPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_matPopupMenuWillBecomeInvisible
if(site.getSelectedItem().equals("Select Site")){
  call_table_ITEM();  
Call_SUMINVERTAIRE();
}else{
    jTable1.clearSelection();
call_table_ITEMSITE();
Call_SUMINVERTAIRESITE();
}
      // TODO add your handling code here:
    }//GEN-LAST:event_matPopupMenuWillBecomeInvisible

    private void sitePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_sitePopupMenuWillBecomeInvisible
if(mat.getSelectedItem().equals("Select Materials")){
        call_table_ITEM_site(); 
}else{
        call_table_ITEM_sitesS();
}// TODO add your handling code here:
    }//GEN-LAST:event_sitePopupMenuWillBecomeInvisible

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
// TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
       // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formMousePressed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
       // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameActivated

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
this.dispose();  
 logs.invent.setText("");// TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
if(site.getSelectedItem().equals("Select Site")){
report();
}else if(!mat.getSelectedItem().equals("Select Materials")) {
    report_SITE_MAT();

}else{
report_SITE();
}

      
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void matActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_matActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JComboBox<String> mat;
    private javax.swing.JTextField qty_site;
    private javax.swing.JTextField qtyin;
    private javax.swing.JTextField qtyout;
    private javax.swing.JComboBox<String> site;
    private javax.swing.JTextField sold;
    private com.alee.extended.date.WebDateField webDateField1;
    private com.alee.extended.date.WebDateField webDateField2;
    // End of variables declaration//GEN-END:variables
}
