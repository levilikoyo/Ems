/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import com.alee.laf.WebLookAndFeel;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
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
public class ohada extends javax.swing.JFrame {

     DefaultTableModel mode;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String etrolls,SUBSTR;
    public ohada() {
        initComponents();
        con=JavaDbConnect.dbConnect();
          setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
          attLIST_from_EMPLOYEView();
          attLIST_from_EMPLOYEView_used();
    }
  public void attLIST_from_EMPLOYEView()
    {
        try{
         // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
       String sql="select `numero_compte` AS 'CODE', `intitule_compte` AS 'ACCOUNT DESCRIPTION', `class_compte`AS 'CLASS' from  parametre_default";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
       TableColumn col0=jTable1.getColumnModel().getColumn(0);
       
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        
       
       
      
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(500);
       col2.setPreferredWidth(100);
       
      centre.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centre); 
        centre.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(centre);
       
      
        
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
}
   public void attLIST_from_EMPLOYEView_used()
    {
        try{
       
      
     
         // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
       String sql="SELECT  CODE,`NAME` AS 'ACCOUNT DESCRIPTION', `CLASS` FROM `ohada` ";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
       TableColumn col0=jTable3.getColumnModel().getColumn(0);
       
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
        TableColumn col2=jTable3.getColumnModel().getColumn(2);
      //  TableColumn col3=jTable3.getColumnModel().getColumn(3);
        
       
       
      
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(500);
       col2.setPreferredWidth(100);
       
      centre.setHorizontalAlignment(JLabel.CENTER);
        jTable3.getColumnModel().getColumn(0).setCellRenderer(centre); 
        centre.setHorizontalAlignment(JLabel.CENTER);
        jTable3.getColumnModel().getColumn(2).setCellRenderer(centre);
       
      
        
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
}
   
   public void save(){
      try{
          String sqls = "SELECT * FROM ohada WHERE CODE= '"+code.getText()+"'";
          pst = con.prepareStatement(sqls);
          rs=pst.executeQuery();
          if(rs.next()){
         JOptionPane.showMessageDialog(null,"tentative de doublons","Error",JOptionPane.ERROR_MESSAGE);
          }else{
         substring();
    
     try{
     String sql="INSERT INTO `ohada`(`NAME`, `CODE`, `COMPTEMERE`, `CODEMERE`, `CLASS`,SUBSTR)"+"VALUE(?,?,?,?,?,?)";
     pst=con.prepareStatement(sql);
     
     pst.setString(1,bus.getText());
     pst.setString(2,code.getText()); 
     pst.setString(3,compte.getText()); 
     pst.setString(4,codem.getText());
     pst.setString(5,classe.getText());
      pst.setString(6,SUBSTR);
      pst.executeUpdate();
      JOptionPane.showMessageDialog(null,"Transaction Saved"); 
     }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
          
          }
         
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
      
     
    }
     public void tableselected(){
         
         
          try{
          int row= jTable1.getSelectedRow();
         // String rows =jTable1.getName()
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM parametre_default WHERE numero_compte= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        
               String add11= rs.getString("intitule_compte");
              compte.setText(add11);
               String add12= rs.getString("numero_compte");
             codem.setText(add12);
               String add13= rs.getString("class_compte");
              classe.setText(add13);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         
         substring();
         }
      public void tableselected_ohada(){
         
         
          try{
          int row= jTable3.getSelectedRow();
         // String rows =jTable1.getName()
          String Table_click = (jTable3.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM ohada WHERE CODE= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        //`NAME`, `CODE`, `COMPTEMERE`, `CODEMERE`, `CLASS`,SUBSTR 
               String add11= rs.getString("NAME");
              bus.setText(add11);
               String add14= rs.getString("COMPTEMERE");
              compte.setText(add14);
               String add12= rs.getString("CODE");
             code.setText(add12);
             String add15= rs.getString("CODEMERE");
             codem.setText(add15);
               String add13= rs.getString("CLASS");
              classe.setText(add13);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         
         substring();
         }
     
       public void substring(){
    
      try{
       
      
       
       String sql="SELECT LEFT(numero_compte,2)  FROM parametre_default WHERE numero_compte ='"+codem.getText()+"'";
       //select substring(col1, 4)from table1
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      if(rs.next()){
        String   substr=rs.getString("LEFT(numero_compte,2)");
       
       
        SUBSTR=substr;
       
                
            
                
                 
              
               
      }
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);} 
    
    
    }
      
        public void attLIST_from_EMPLOYEsearch()
    {
        
        try{
         // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
       String sql="select `numero_compte` AS 'CODE', `intitule_compte` AS 'ACCOUNT DESCRIPTION', `class_compte`AS 'CLASS' from  parametre_default where numero_compte like '"+checks.getText()+"%' or intitule_compte like '"+checks.getText()+"%' or class_compte like '"+checks.getText()+"%'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
       TableColumn col0=jTable1.getColumnModel().getColumn(0);
       
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        
       
       
      
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(500);
       col2.setPreferredWidth(100);
       
      centre.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centre); 
        centre.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(centre);
       
      
        
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
    }
         
    
}
  public void report()
     {
          
     
             try{
              //   String tmp=num.getText();
                 
                 
                 String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"ohada.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                 
                 
                // String report ="C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\maretiauxrepport.jrxml";
                // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\Fiche_Materiaux_out.jrxml");
                String sql="select * from ohada  ORDER BY CODEMERE,CODE";
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
  
  
        public void delete(){
           try{
        String sql = "DELETE FROM OHADA WHERE CODE=? AND NAME=? AND CODEMERE=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,code.getText());
         pst.setString(2,bus.getText());
         pst.setString(3,codem.getText());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
          rs.close();
            pst.close();     
     }catch(SQLException  ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
        
        }
  
  
  public void refresh(){
 bus.setText("");
 code.setText(""); 
 compte.setText(""); 
 codem.setText("");
 classe.setText("");
  
  
  }
  
    public void attLIST_from_EMPLOYEView_SEARCH()
    {
        try{
       
      
     
         // String Table_click = (jList2.getModel().getSelectedItem(tmps,0). toString());
       String sql="SELECT  CODE,`NAME` AS 'ACCOUNT DESCRIPTION', `CLASS` FROM `ohada`  WHERE CODE LIKE '"+jTextField1.getText()+"%' or NAME LIKE '"+jTextField1.getText()+"%'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
     
       jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
       TableColumn col0=jTable3.getColumnModel().getColumn(0);
       
        TableColumn col1=jTable3.getColumnModel().getColumn(1);
        TableColumn col2=jTable3.getColumnModel().getColumn(2);
      //  TableColumn col3=jTable3.getColumnModel().getColumn(3);
        
       
       
      
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(500);
       col2.setPreferredWidth(100);
       
      centre.setHorizontalAlignment(JLabel.CENTER);
        jTable3.getColumnModel().getColumn(0).setCellRenderer(centre); 
        centre.setHorizontalAlignment(JLabel.CENTER);
        jTable3.getColumnModel().getColumn(2).setCellRenderer(centre);
       
      
        
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
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        bus = new Palette.TextField();
        compte = new Palette.TextField();
        classe = new Palette.TextField();
        codem = new Palette.TextField();
        code = new Palette.TextField();
        checks = new Palette.TextField();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new Palette.TextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nouveau compte comptable", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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
        jTable1.setRowHeight(30);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        bus.setLabelText("Nom du compte");

        compte.setEditable(false);
        compte.setLabelText("Compte mère");

        classe.setEditable(false);
        classe.setLabelText("Classe");

        codem.setEditable(false);
        codem.setLabelText("Code mère");

        code.setLabelText("Code");

        checks.setLabelText("Recherche");
        checks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                checksKeyReleased(evt);
            }
        });

        jButton4.setText("Révisé le compte");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("NB : La révision se fait ligne par ligne");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(checks, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(compte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(classe, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codem, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(bus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(compte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jTable3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.setRowHeight(30);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Del");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField1.setLabelText("Recherche");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton3))
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(bus.getText().equals("")||code.getText().equals("")){
JOptionPane.showMessageDialog(null,"Wrong Data Item Exists","Error",JOptionPane.ERROR_MESSAGE);
}else{
        save();        
attLIST_from_EMPLOYEView_used();
bus.setText("");
code.setText("");
}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
refresh();
        tableselected();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
report();
refresh();// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
refresh();
        tableselected_ohada();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
delete();  
attLIST_from_EMPLOYEView_used();
refresh();// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
Home_page.menu_finance.ohada.setText("Compte comptable");      // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void checksKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checksKeyReleased
attLIST_from_EMPLOYEsearch();         // TODO add your handling code here:
    }//GEN-LAST:event_checksKeyReleased

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
attLIST_from_EMPLOYEView_SEARCH();         // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    
          int indexs[]=jTable1.getSelectedRows();
        for(int i=0; i < indexs.length;i++){
             String num = (jTable1.getModel().getValueAt(indexs[i],0). toString());
              String item = (jTable1.getModel().getValueAt(indexs[i],1). toString());
               String classe = (jTable1.getModel().getValueAt(indexs[i],2). toString());
               String code = null;
               
 try{
       
      
       
       String sql="SELECT * FROM parametre_default WHERE numero_compte ='"+num+"'";
       //select substring(col1, 4)from table1
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      if(rs.next()){
        code=rs.getString("id_parametre");
        }
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);} 

  try{
        String sql = " UPDATE parametre_default SET numero_compte='"+num+"',intitule_compte='"+item+"',class_compte='"+classe+"' WHERE 	id_parametre='"+code+"'";
        
         pst = con.prepareStatement(sql);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
     } 
        }
        JOptionPane.showMessageDialog(null,"Transactrion Done");
             // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(ohada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ohada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ohada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ohada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 WebLookAndFeel.install(true);
                new ohada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Palette.TextField bus;
    private Palette.TextField checks;
    private Palette.TextField classe;
    private Palette.TextField code;
    private Palette.TextField codem;
    private Palette.TextField compte;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private Palette.TextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
