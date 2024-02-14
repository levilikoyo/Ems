/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
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
public class NewJInternalFrameDevis extends javax.swing.JInternalFrame {
DefaultTableModel mode;
    
   Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs= null;
    String rolls;
    public NewJInternalFrameDevis() {
        initComponents();
              con=JavaDbConnect.dbConnect();
         jComboBox2.addItem("MATERIELS");
        jComboBox2.addItem("MATERIAUX");
        jComboBox2.addItem("RESOURCES_HUMAINES");
        jComboBox1.addItem("UNITE");
        jComboBox1.addItem("pcs");
        jComboBox1.addItem("Sacs");
        jComboBox1.addItem("kg");
        jComboBox1.addItem("Ltrs");
        jComboBox1.addItem("rouloux");
        jComboBox1.addItem("tours");
        jComboBox1.addItem("cources");
        jComboBox1.addItem("m2");
        jComboBox1.addItem("m3");
        jComboBox1.addItem("gm");
        jComboBox1.addItem("m");
         jComboBox3.addItem("UNITE");
        jComboBox3.addItem("pcs");
        jComboBox3.addItem("Sacs");
        jComboBox3.addItem("kg");
        jComboBox3.addItem("Ltrs");
        jComboBox3.addItem("rouloux");
        jComboBox3.addItem("tours");
        jComboBox3.addItem("cources");
        jComboBox3.addItem("m2");
        jComboBox3.addItem("m3");
        jComboBox3.addItem("gm");
        jComboBox3.addItem("m");
        
        
        rh.enable(false);
        nbre.enable(false);
        add2.setEnabled(false);
        mat.enable(false);
        desc.enable(false);
        qty.enable(false);
        add1.setEnabled(false);
        jComboBox1.enable(false);
        mat1.enable(false);
        desc1.enable(false);
        qty1.enable(false);
        add3.setEnabled(false);
        jComboBox3.enable(false);
        Call_ID();
    }
    
     //CONNECTION
     public static Connection getConnection()
    {
        Connection con = null;
        try{
             con= DriverManager.getConnection("jdbc:mysql://localhost/entreprise_management_system","root","");
              // JOptionPane.showMessageDialog(null,"connected");
             return con;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"not connected");
           
        }
         return null;
    }
     
     // AUTO ROLL NUMBER
     public void roll()
     {
         try{
             String sql="SELECT CODE FROM devis ORDER BY CODE DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("CODE");
                 int ln=rl.length();
                 String stxt=rl.substring(0,2);
                 String snum=rl.substring(2,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum;
                 
                 
             }else{
               
                 rolls="DV1001";
             }
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
     public void if_nothing()
    {
        if(jComboBox2.getSelectedItem().equals("RESOURCES_HUMAINES")){
           // if(roll.getText().equals("")){
             rh.enable(true);
        nbre.enable(true);
        add2.setEnabled(true);
        
        mat.enable(false);
        desc.enable(false);
        qty.enable(false);
        add1.setEnabled(false);
        jComboBox1.enable(false);
        mat1.enable(false);
        desc1.enable(false);
        qty1.enable(false);
        add3.setEnabled(false);
        jComboBox3.enable(false);
        }
        else if(jComboBox2.getSelectedItem().equals("MATERIELS")){
            mat.enable(true);
        desc.enable(true);
        qty.enable(true);
        add1.setEnabled(true);
        jComboBox1.enable(true); 
        
        rh.enable(false);
        nbre.enable(false);
        add2.setEnabled(false);
        mat1.enable(false);
        desc1.enable(false);
        qty1.enable(false);
        add3.setEnabled(false);
        jComboBox3.enable(false);
        }else{
            mat1.enable(true);
        desc1.enable(true);
        qty1.enable(true);
        add3.setEnabled(true);
        jComboBox3.enable(true);
        
         mat.enable(false);
        desc.enable(false);
        qty.enable(false);
        add1.setEnabled(false);
        jComboBox1.enable(false);
         rh.enable(false);
        nbre.enable(false);
        add2.setEnabled(false);
        
        
            
        }
      
    }
  
      public void if_IDnothing()
    {
        if(id1.getText().equals("")){
            id1.setText("0");   
        }
    }
     
      public void Call_ID_MAX()
    {
         {
        try{
            String sql="select MAX(CODE) from devis ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("MAX(CODE)");
                  code.setText(sum);
                   recherche.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
        
      //SAVE
     
      public void savema_attendance()
    {
        roll();
       
        String rollss = code.getText();
        String a = id.getText();
        if(rollss.isEmpty()){
        try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO devis(ID,MAT_NAME,MAT_DESC,MAT_CAT,MAT_QTY,MATR_NAME,MATR_DESC,MATR_CAT,MATR_QTY,NOM,NBRE,IR,CODE,CHANTIER) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pst.setString(1, id1.getText());
        pst.setString(2, mat.getText());
         pst.setString(3, desc.getText());
         pst.setString(5, qty.getText());
        String value=jComboBox1.getSelectedItem().toString();
         pst.setString(4, value);
         
         pst.setString(6, mat1.getText());
         pst.setString(7, desc1.getText());
         pst.setString(9, qty1.getText());
           pst.setString(8, cat1.getText());
         
         pst.setString(10, rh.getText());
          pst.setString(11, nbre.getText());
        pst.setString(12, ir.getText());
         pst.setString(13, rolls);
         pst.setString(14, chat.getText());
         
      
         
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);
    }
    }else{
             try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO devis(ID,MAT_NAME,MAT_DESC,MAT_CAT,MAT_QTY,MATR_NAME,MATR_DESC,MATR_CAT,MATR_QTY,NOM,NBRE,IR,CODE,CHANTIER) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pst.setString(1, id1.getText());
        pst.setString(2, mat.getText());
         pst.setString(3, desc.getText());
         pst.setString(5, qty.getText());
        String value=jComboBox1.getSelectedItem().toString();
         pst.setString(4, value);
         
         pst.setString(6, mat1.getText());
         pst.setString(7, desc1.getText());
         pst.setString(9, qty1.getText());
           pst.setString(8, cat1.getText());
         
         pst.setString(10, rh.getText());
          pst.setString(11, nbre.getText());
        pst.setString(12, ir.getText());
         pst.setString(13, code.getText());
         pst.setString(14, chat.getText());
         
      
         
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex);
    }
        }
        
    }
      
       public void savema_matr()
    { roll();
      
        String rollss = code.getText();
        if(rollss.isEmpty()){
        try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO devis(ID,MAT_NAME,MAT_DESC,MAT_CAT,MAT_QTY,MATR_NAME,MATR_DESC,MATR_CAT,MATR_QTY,NOM,NBRE,IR,CODE,CHANTIER) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pst.setString(1, id1.getText());
        pst.setString(2, mat.getText());
         pst.setString(3, desc.getText());
         pst.setString(5, qty.getText());
          pst.setString(4, cat1.getText());
         
         pst.setString(6, mat1.getText());
         pst.setString(7, desc1.getText());
         pst.setString(9, qty1.getText());
        String value1=jComboBox3.getSelectedItem().toString();
         pst.setString(8, value1);
         
         pst.setString(10, rh.getText());
          pst.setString(11, nbre.getText());
        pst.setString(12, ir.getText());
         pst.setString(13, rolls);
         pst.setString(14, chat.getText());
         
      
         
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    }else{
             try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO devis(ID,MAT_NAME,MAT_DESC,MAT_CAT,MAT_QTY,MATR_NAME,MATR_DESC,MATR_CAT,MATR_QTY,NOM,NBRE,IR,CODE,CHANTIER) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pst.setString(1, id1.getText());
        pst.setString(2, mat.getText());
         pst.setString(3, desc.getText());
         pst.setString(5, qty.getText());
          pst.setString(4, cat1.getText());
         
         pst.setString(6, mat1.getText());
         pst.setString(7, desc1.getText());
         pst.setString(9, qty1.getText());
        String value1=jComboBox3.getSelectedItem().toString();
         pst.setString(8, value1);
         
         pst.setString(10, rh.getText());
          pst.setString(11, nbre.getText());
        pst.setString(12, ir.getText());
         pst.setString(13, code.getText());
         pst.setString(14, chat.getText());
         
      
         
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        }
    }
       
        public void savema_rh()
    {
        roll();
       
         String rollss = code.getText();
        if(rollss.isEmpty()){
        try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO devis(ID,MAT_NAME,MAT_DESC,MAT_CAT,MAT_QTY,MATR_NAME,MATR_DESC,MATR_CAT,MATR_QTY,NOM,NBRE,IR,CODE,CHANTIER) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pst.setString(1, id1.getText());
        pst.setString(2, mat.getText());
         pst.setString(3, desc.getText());
         pst.setString(5, qty.getText());
           pst.setString(4, cat1.getText());
         
         pst.setString(6, mat1.getText());
         pst.setString(7, desc1.getText());
         pst.setString(9, qty1.getText());
           pst.setString(8, cat1.getText());
         
         pst.setString(10, rh.getText());
          pst.setString(11, nbre.getText());
        pst.setString(12, ir.getText());
         pst.setString(13, rolls);
         pst.setString(14, chat.getText());
         
      
         
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    }else{
                try {
         Connection con = getConnection();
        PreparedStatement pst = con.prepareStatement("INSERT INTO devis(ID,MAT_NAME,MAT_DESC,MAT_CAT,MAT_QTY,MATR_NAME,MATR_DESC,MATR_CAT,MATR_QTY,NOM,NBRE,IR,CODE,CHANTIER) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pst.setString(1, id1.getText());
        pst.setString(2, mat.getText());
         pst.setString(3, desc.getText());
         pst.setString(5, qty.getText());
           pst.setString(4, cat1.getText());
         
         pst.setString(6, mat1.getText());
         pst.setString(7, desc1.getText());
         pst.setString(9, qty1.getText());
           pst.setString(8, cat1.getText());
         
         pst.setString(10, rh.getText());
          pst.setString(11, nbre.getText());
        pst.setString(12, ir.getText());
         pst.setString(13, code.getText());
         pst.setString(14, chat.getText());
         
      
         
          pst.executeUpdate();
        
                // JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
        }
    }
      
        
   // DELETE
    public void delete()
    {
         
         try{
        String sql = "DELETE FROM devis WHERE ID=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,id.getText());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 

    }
    
    
    //UPDATE
     public void update_emp()
    {
         
       // addition_qty();
         try{
           
        String sql = "UPDATE devis SET MAT_NAME=?,MAT_DESC=?,MAT_CAT=?,MAT_QTY=?,MATR_NAME=?,MATR_DESC=?,MATR_CAT=?,MATR_QTY=?,NOM=?,NBRE=?,IR=?,CODE=?,CHANTIER=? WHERE ID=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(14,id.getText());
         pst.setString(1,mat.getText());
          pst.setString(2,desc.getText());
        pst.setString(3,qty.getText());
            String value=jComboBox1.getSelectedItem().toString();
         pst.setString(4, value);
             pst.setString(5,mat1.getText());
              pst.setString(6,desc1.getText());
               pst.setString(7,qty.getText());
                       String values=jComboBox2.getSelectedItem().toString();
         pst.setString(8, values);
         
         pst.setString(9,rh.getText());
              pst.setString(10,nbre.getText());
               pst.setString(12,code.getText());
                pst.setString(13,chat.getText());
                 pst.setString(11,ir.getText());
              
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    }        
     
        
        
        public void Call_ID()
    {
         {
        try{
            String sql="select max(ID) from devis";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("max(ID)");
                  id1.setText(sum);
                 
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
        
         public void select_jTable_recu()
   {
        try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM devis WHERE ID= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
         
              String add1 = rs.getString("MAT_NAME");
              mat.setText(add1);
               String add2 = rs.getString("MAT_DESC");
              desc.setText(add2);
               String add3 = rs.getString("MAT_QTY");
               qty.setText(add3);
               String add4 = rs.getString("MAT_CAT");
              jComboBox1.setSelectedItem(add4);
              
               String add11 = rs.getString("MATR_NAME");
              mat1.setText(add11);
               String add21 = rs.getString("MATR_DESC");
              desc1.setText(add21);
               String add31 = rs.getString("MATR_CAT");
               qty1.setText(add31);
               String add41 = rs.getString("MATR_QTY");
              jComboBox2.setSelectedItem(add41);
              
               String add12 = rs.getString("NOM");
              rh.setText(add12);
               String add22 = rs.getString("NBRE");
              nbre.setText(add22);
              
               String add13 = rs.getString("IR");
              ir.setText(add13);
               String add23 = rs.getString("CHANTIER");
              chat.setText(add23);
               String add24 = rs.getString("CODE");
              code.setText(add24);
              String add25 = rs.getString("ID");
              id.setText(add25);
              
       
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
       
   }
        
         //SEARCH AND ADDREADER
     
      public void ReadData(String sql)
    {
            Statement st;
            try{
                
                st=con.createStatement();
                ResultSet rs;
                rs=st.executeQuery(sql);
                if(rs.first()){
                    String Data[]=new String[14];
                    do{
                        for(int i=0;i<14;i++){
                            Data[i]=rs.getString(i+1);
                        }
                    
                    mode.addRow(Data);
                }while(rs.next());
            }
                rs.close();
            }catch(Exception ex){
                
            }
    }
    
    public void AddModel(){
        
       // ETAT_CIVIL=?,PROFFESSION=?,NOMS=?,ADRESSE1=?,RELATION=?,TEL1=?,TEL2=?,EMAIL1=?,SALARY=?,STATUT=?,DEPARTEMENT=?,DATE_OUT=? WHERE ID=?
        
        mode=new DefaultTableModel();
        mode.addColumn("ID");
        mode.addColumn("MATERIEL");
        mode.addColumn("DESCRIPTION");    
        mode.addColumn("UNTITE");
        mode.addColumn("QTY");
        mode.addColumn("MATERIAUX");
        mode.addColumn("DESCRIPTION");    
        mode.addColumn("UNTITE");
        mode.addColumn("QTY");
        
        mode.addColumn("PERSONNEL");
        mode.addColumn("NOMBRE");
        
        mode.addColumn("INGENIERE");
        mode.addColumn("CODE");
        mode.addColumn("CHANTIER");    

        
        jTable1.setModel(mode);
    }
      
      public void remove(){
          while(jTable1.getRowCount()>0){
              mode.removeRow(0);
          }
      }
              
             public void search()
             {//NAME,LNAME,ADRESSE,DOB,ROLLNUM,TITRE,TEL,EMAIL,BANKACC,DEGREE,NATIONALITE,DATEIN,SUP
                  String st=recherche.getText().trim();
    remove();
    ReadData("select * from devis where CODE like '%"+st+"%'");
    
             }
        
        public void clear()
        {
        
         mat.setText(null);
        desc.setText(null);
        qty.setText(null);
        cat1.setText(null);
         
        mat1.setText(null);
        desc1.setText(null);
        qty1.setText(null);
        cat1.setText(null);
         
        rh.setText(null);
        nbre.setText(null);
       // ir.setText(null);
       // code.setText(null);
            jComboBox1.setSelectedItem("UNITES");
            jComboBox3.setSelectedItem("UNITES");
           // jComboBox2.setSelectedItem(null);
        }
        //UPDATE
     public void update_materiaux()
    {
      
        jComboBox1.setSelectedItem(null);
         try{
             
        String sql = "UPDATE devis SET MAT_CAT=? WHERE ID =?";
        
         pst = con.prepareStatement(sql);
         pst.setString(2,id1.getText());
         String value=jComboBox1.getSelectedItem().toString();
         pst.setString(1, value);
        
            
         pst.executeUpdate();
         JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    }        
     
          public void reports()
     {
         
     
            // try{
               // String report ="C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\devis.jrxml";
                String tmp =(String) code.getText();
               try{
                   
                   
                    String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"devis.jrxml";
           // String NameFiless=""+NameFiles+"newReport1IMAGE.jrxml";
                 
                 
            
            
            
            
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 //String report =NameFiless ;
               // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\devis.jrxml");
                String sql="select * from devis  where code='"+tmp+"'";
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
              //  JasperReport jrs =JasperCompileManager.compileReport(report);
             //   JasperPrint jps=JasperFillManager.fillReport(jrs,null,con);
               // JasperViewer.viewReport(jps,false);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                 
                 JasperViewer.viewReport(jp,false);
                  
                 
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        add2 = new javax.swing.JButton();
        nbre = new javax.swing.JTextField();
        rh = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        qty = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        add1 = new javax.swing.JButton();
        desc = new javax.swing.JTextField();
        mat = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        ir = new javax.swing.JTextField();
        code = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        qty1 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        add3 = new javax.swing.JButton();
        desc1 = new javax.swing.JTextField();
        mat1 = new javax.swing.JTextField();
        id1 = new javax.swing.JTextField();
        chat = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cat1 = new javax.swing.JTextField();
        recherche = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setClosable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
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
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jComboBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox2MouseClicked(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });
        jPanel2.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Description a besoin Resources humaines");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, -1, -1));

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Nbre");

        add2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add2.setText("Add");
        add2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add2ActionPerformed(evt);
            }
        });

        nbre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nbre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nbreKeyTyped(evt);
            }
        });

        rh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rh, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(nbre, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(add2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nbre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(add2))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 1300, 70));

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        qty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qtyKeyTyped(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Description");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Materiels");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Qty");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Category");

        add1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add1.setText("Add");
        add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add1ActionPerformed(evt);
            }
        });

        desc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        mat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        id.setEditable(false);
        id.setBackground(new java.awt.Color(204, 255, 204));
        id.setForeground(new java.awt.Color(204, 255, 204));
        id.setBorder(null);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(280, 280, 280))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel1))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(mat, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(desc, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel3)
                                .addGap(73, 73, 73)
                                .addComponent(jLabel4)
                                .addContainerGap(168, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(add1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add1)
                    .addComponent(desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 1300, 70));

        ir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel2.add(ir, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 290, -1));

        code.setEditable(false);
        code.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.add(code, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 10, 210, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Nom de Ir.");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, -1));

        jPanel5.setBackground(new java.awt.Color(204, 255, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        qty1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qty1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qty1KeyTyped(evt);
            }
        });

        jComboBox3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Description");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Materiaux");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Qty");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Category");

        add3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add3.setText("Add");
        add3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add3ActionPerformed(evt);
            }
        });

        desc1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        mat1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(296, 296, 296)
                        .addComponent(jLabel8)
                        .addGap(534, 534, 534))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(mat1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(desc1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(qty1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(add3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel10)
                        .addGap(72, 72, 72)
                        .addComponent(jLabel11)
                        .addContainerGap(154, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(qty1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add3)
                    .addComponent(desc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 1300, -1));

        id1.setEditable(false);
        id1.setBackground(new java.awt.Color(204, 255, 204));
        id1.setForeground(new java.awt.Color(204, 255, 204));
        id1.setBorder(null);
        jPanel2.add(id1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 60, 20));

        chat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel2.add(chat, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, 230, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Chantier/Loc");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, -1, -1));

        cat1.setEditable(false);
        cat1.setBackground(new java.awt.Color(204, 255, 204));
        cat1.setForeground(new java.awt.Color(204, 255, 204));
        cat1.setBorder(null);
        jPanel2.add(cat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 50, -1));

        recherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rechercheKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rechercheKeyReleased(evt);
            }
        });
        jPanel2.add(recherche, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 130, -1));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 40, 20, 10));

        jTable1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1312, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jMenu1.setText("File");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Update");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Delete");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jMenu3.setText("Impression");

        jMenuItem1.setText("Imprimer Devis");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox2PopupMenuWillBecomeInvisible
        if_nothing();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2PopupMenuWillBecomeInvisible

    private void jComboBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox2MouseClicked
        //if_nothing();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2MouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        //if_nothing();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed
        //if_nothing();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2KeyPressed

    private void nbreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nbreKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
        }          // TODO add your handling code here:
    }//GEN-LAST:event_nbreKeyTyped

    private void add2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add2ActionPerformed
       
        String s = id1.getText();
        int billNumber = Integer.valueOf(s);
        s = Integer.toString(++billNumber);
        id1.setText(s);
        savema_rh();
        clear();
        search();  // TODO add your handling code here:
    }//GEN-LAST:event_add2ActionPerformed

    private void qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
        }          // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyTyped

    private void add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add1ActionPerformed
    
        JDialog.setDefaultLookAndFeelDecorated(true);
        if(jComboBox2.getSelectedItem().equals("MATERIELS")){
            int response = JOptionPane.showConfirmDialog(null, "Es_tu sure que ce la categorie MATERIEL???", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.NO_OPTION) {
                System.out.println("No button clicked");
            } else if (response == JOptionPane.YES_OPTION) {
                System.out.println("Yes button clicked");
                String s = id1.getText();
                int billNumber = Integer.valueOf(s);
                s = Integer.toString(++billNumber);
                id1.setText(s);
                savema_attendance();
                Call_ID_MAX();
                clear();
                search();
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("JOptionPane closed");
            }

        }

    }//GEN-LAST:event_add1ActionPerformed

    private void qty1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qty1KeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
            evt.consume();
        }          // TODO add your handling code here:
    }//GEN-LAST:event_qty1KeyTyped

    private void add3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add3ActionPerformed
      
        JDialog.setDefaultLookAndFeelDecorated(true);
        if(jComboBox2.getSelectedItem().equals("MATERIAUX")){
            int response = JOptionPane.showConfirmDialog(null, "Es_tu sure que ce la categorie MATERIAUX???", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.NO_OPTION) {
                System.out.println("No button clicked");
            } else if (response == JOptionPane.YES_OPTION) {
                System.out.println("Yes button clicked");
                String s = id1.getText();
                int billNumber = Integer.valueOf(s);
                s = Integer.toString(++billNumber);
                id1.setText(s);
                savema_matr();
                clear();
                search();

            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("JOptionPane closed");
            }

        }
    }//GEN-LAST:event_add3ActionPerformed

    private void rechercheKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rechercheKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_rechercheKeyPressed

    private void rechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rechercheKeyReleased
        search();          // TODO add your handling code here:
    }//GEN-LAST:event_rechercheKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        select_jTable_recu();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        update_emp();
        clear();
        search();  // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        delete();
        clear();
        search();  // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        reports();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
AddModel();        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add1;
    private javax.swing.JButton add2;
    private javax.swing.JButton add3;
    private javax.swing.JTextField cat1;
    private javax.swing.JTextField chat;
    private javax.swing.JTextField code;
    private javax.swing.JTextField desc;
    private javax.swing.JTextField desc1;
    private javax.swing.JTextField id;
    private javax.swing.JTextField id1;
    private javax.swing.JTextField ir;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField mat;
    private javax.swing.JTextField mat1;
    private javax.swing.JTextField nbre;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField qty1;
    private javax.swing.JTextField recherche;
    private javax.swing.JTextField rh;
    // End of variables declaration//GEN-END:variables
}
