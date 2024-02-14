/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;


import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Doshe
 */
public class impetatdebesoin extends javax.swing.JFrame {

   Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String etrolls;
DefaultTableModel mode;
    public impetatdebesoin() {
        initComponents();
              con=JavaDbConnect.dbConnect();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
        etjComboBox6.addItem("NOM DE SUPERVISEUR CHANTIER");
        etjComboBox6.addItem("CONSULTANT");
        etjComboBox6.addItem("EMPLOYE");
        etEMP_NAME_TO_BOMBOBOX2();
          etsup1.setEditable(false);
    etchat.setEditable(false);
    etdate_now();
    //if_nothing();
    }
  public void etdate_now()
    {
       etjDateChooser4.setDate(new Date());
      
    }
   public void etif_nothing()
    {
        if(etchat.getText().equals("")){
            etchat.setText(".                                              ");
            etsearch();
        }
    }
    
    //CONNECTION
    
     
      public void etEMP_NAME_TO_BOMBOBOX2()
    {
         {
        try{
            String sql="select * from  projet";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("SUP");
                  etjComboBox6.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
       public void etcombobox1_from_materiel()
    {
        if(etjComboBox6.getSelectedItem().equals("CONSULTANT")){
            etsup1.setEditable(true);
            etchat.setEditable(true);
        }else{
        
     String tmp =(String)etjComboBox6.getSelectedItem();
     String sql="select * from  projet where SUP=?";
     
        try{
         
          
            pst=con.prepareStatement(sql);
            pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){

              String add9 = rs.getString("SUP");
              etsup1.setText(add9);
              String add2 = rs.getString("CHANTIER");
              etchat.setText(add2);

          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        }    
    }     
    
       // AUTO ROLL NUMBER
     public void etroll()
     {
         try{
             String sql="SELECT NUM FROM etat_de_besoin ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,2);
                 String snum=rl.substring(2,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 etrolls=stxt+snum;
                 
                 
             }else{
                 //rolls="FICHE/EB/2018/1";
                 etrolls="EB1001";
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
     
      //SAVE
     
      public void etsavema_attendance()
    {
        
        String rollss = etroll1.getText();
        if(rollss.isEmpty()){
        try {
            etroll();
    
        PreparedStatement pst = con.prepareStatement("INSERT INTO etat_de_besoin(SUP,CHANT,NUM,DET,QTY,PU,PT,DATES) "
        +"value(?,?,?,?,?,?,?,?)");
        
        pst.setString(1, etsup1.getText());
         pst.setString(2, etchat.getText());
        pst.setString(3, etrolls);
         pst.setString(4, etdet.getText());
         pst.setString(5, etqty.getText());
         pst.setString(6, etpu.getText());
         pst.setString(7, etpt.getText());
         
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(etjDateChooser4.getDate());
         pst.setString(8, addDate);
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    }else{
            try {
            etroll();
       
        PreparedStatement pst = con.prepareStatement("INSERT INTO etat_de_besoin(SUP,CHANT,NUM,DET,QTY,PU,PT,DATES) "
        +"value(?,?,?,?,?,?,?,?)");
        
        pst.setString(1, etsup1.getText());
         pst.setString(2, etchat.getText());
        pst.setString(3, etroll1.getText());
         pst.setString(4, etdet.getText());
         pst.setString(5, etqty.getText());
         pst.setString(6, etpu.getText());
         pst.setString(7, etpt.getText());
         
       
          SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
         String addDate = dateFormats.format(etjDateChooser4.getDate());
         pst.setString(8, addDate);
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
            
        }
        
    }
      
      // DELETE
    public void etdelete()
    {
         try{
        String sql = "DELETE FROM etat_de_besoin WHERE ID=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,etid.getText());
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
    }
      //UPDATE
     public void etupdate_materiaux()
    {
         try{
                        
        String sql = "UPDATE etat_de_besoin SET DET=?,QTY=?,PU=?,PT=? WHERE ID =?";
        
         pst = con.prepareStatement(sql);
         pst.setString(5,etid.getText());
         pst.setString(1,etdet.getText());
          pst.setString(2,etqty.getText());
        pst.setString(3,etpu.getText());
             pst.setString(4,etpt.getText());
             
                       
         pst.executeUpdate();
         JOptionPane.showMessageDialog(null,"updaded");    
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
    }        
    
     
       public void etCall_ID_MAX()
    {
         {
        try{
            String sql="select MAX(NUM) from etat_de_besoin ";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("MAX(NUM)");
                  etroll1.setText(sum);
                  etrecherches.setText(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
        
        
        //SELECT IN JTABLE
        
         public void etselect_jTable()
   {
        try{
          int row= etjTable3.getSelectedRow();
          String Table_click = (etjTable3.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM etat_de_besoin WHERE ID= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              String add1 = rs.getString("ID");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              etid.setText(add1);
               // String add2 = rs.getString("SUP");
              //sup.setText(add2);
                //String add3 = rs.getString("CHANT");
              //chat.setText(add3);
                //String add4 = rs.getString("NUM");
              //roll.setText(add4);
                //String add5 = rs.getString("DET");
              //det.setText(add5);
                //String add6 = rs.getString("QTY");
              //qty.setText(add6);
               //String add7 = rs.getString("PU");
              //pu.setText(add7);
               //String add8 = rs.getString("PT");
             // pt.setText(add8);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }
        
     //SEARCH AND ADDREADER
     
      public void etReadData(String sql)
    {
            Statement st;
            try{
                
                st=con.createStatement();
                ResultSet rs;
                rs=st.executeQuery(sql);
                if(rs.first()){
                    String Data[]=new String[9];
                    do{
                        for(int i=0;i<9;i++){
                            Data[i]=rs.getString(i+1);
                        }
                    
                    mode.addRow(Data);
                }while(rs.next());
            }
                rs.close();
            }catch(Exception ex){
                
            }
    }
    
    public void etAddModel(){
        mode=new DefaultTableModel();
        mode.addColumn("ID");    
        mode.addColumn("NOM DE SUPERVISEUR");
        mode.addColumn("CHATIER");
        mode.addColumn("NUMERO EB");
        mode.addColumn("DETAILS");
        mode.addColumn("QTY");
        mode.addColumn("PU");
        mode.addColumn("PT");
        mode.addColumn("DATE");
        etjTable3.setModel(mode);
        
        
        TableColumn col0=etjTable3.getColumnModel().getColumn(0);
        TableColumn col1=etjTable3.getColumnModel().getColumn(1);
        TableColumn col2=etjTable3.getColumnModel().getColumn(2);
        TableColumn col3=etjTable3.getColumnModel().getColumn(3);
        TableColumn col4=etjTable3.getColumnModel().getColumn(4);
        TableColumn col5=etjTable3.getColumnModel().getColumn(5);
        TableColumn col6=etjTable3.getColumnModel().getColumn(6);
        TableColumn col7=etjTable3.getColumnModel().getColumn(7);
        TableColumn col9=etjTable3.getColumnModel().getColumn(8);
       
       col0.setPreferredWidth(2);
       col1.setPreferredWidth(130);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(130);
       col5.setPreferredWidth(2);
       col6.setPreferredWidth(2);
       col7.setPreferredWidth(2);
       col9.setPreferredWidth(50);
    }
      
      public void etremove(){
          while(etjTable3.getRowCount()>0){
              mode.removeRow(0);
          }
      }
              
             public void etsearch()
             {
                  String st=etchat.getText().trim();
    etremove();
    etReadData("select * from etat_de_besoin where CHANT like '"+st+"'");
   
             }
             
              public void etsearchS()
             {
                  String st=etrecherche.getText().trim();
    etremove();
    etReadData("select * from etat_de_besoin where NUM like '"+st+"%'");
   
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
        etjComboBox6 = new javax.swing.JComboBox<>();
        etsup1 = new javax.swing.JTextField();
        etchat = new javax.swing.JTextField();
        etroll1 = new javax.swing.JTextField();
        etjDateChooser4 = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        etjTable3 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        etdet = new javax.swing.JEditorPane();
        etremove = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        etadd = new javax.swing.JButton();
        etqty = new javax.swing.JTextField();
        etpu = new javax.swing.JTextField();
        etpt = new javax.swing.JTextField();
        etid = new javax.swing.JTextField();
        etrecherche = new javax.swing.JTextField();
        etrecherches = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        etjComboBox6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etjComboBox6.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                etjComboBox6PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        etsup1.setBackground(new java.awt.Color(204, 204, 255));
        etsup1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etsup1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                etsup1KeyReleased(evt);
            }
        });

        etchat.setBackground(new java.awt.Color(204, 204, 255));
        etchat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etchat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etchatActionPerformed(evt);
            }
        });
        etchat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                etchatKeyReleased(evt);
            }
        });

        etroll1.setEditable(false);
        etroll1.setBackground(new java.awt.Color(204, 204, 255));
        etroll1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        etjDateChooser4.setAutoscrolls(true);
        etjDateChooser4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etjComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etsup1)
                    .addComponent(etchat)
                    .addComponent(etroll1)
                    .addComponent(etjDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etjComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(etsup1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(etchat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(etroll1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(etjDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        etjTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        etjTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                etjTable3MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(etjTable3);

        jPanel2.setBackground(new java.awt.Color(153, 255, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        etdet.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane1.setViewportView(etdet);

        etremove.setBackground(new java.awt.Color(255, 51, 51));
        etremove.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etremove.setText("REMOVE");
        etremove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etremoveActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("QTY");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("PT");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("PU");

        etadd.setBackground(new java.awt.Color(51, 255, 51));
        etadd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etadd.setText("ADD");
        etadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etaddActionPerformed(evt);
            }
        });

        etqty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                etqtyKeyTyped(evt);
            }
        });

        etpu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etpu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                etpuKeyTyped(evt);
            }
        });

        etpt.setEditable(false);
        etpt.setBackground(new java.awt.Color(204, 204, 255));
        etpt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        etid.setEditable(false);
        etid.setBackground(new java.awt.Color(153, 255, 153));
        etid.setForeground(new java.awt.Color(153, 255, 153));
        etid.setBorder(null);

        etrecherche.setEditable(false);
        etrecherche.setBackground(new java.awt.Color(153, 255, 153));
        etrecherche.setForeground(new java.awt.Color(153, 255, 153));
        etrecherche.setBorder(null);
        etrecherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                etrechercheKeyReleased(evt);
            }
        });

        etrecherches.setEditable(false);
        etrecherches.setBackground(new java.awt.Color(153, 255, 153));
        etrecherches.setForeground(new java.awt.Color(153, 255, 153));
        etrecherches.setBorder(null);
        etrecherches.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                etrecherchesKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(etqty, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etpu, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etpt, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(etadd, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etremove, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(etrecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(24, 24, 24)
                        .addComponent(etid, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(etrecherches, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etremove, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(etadd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(etqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(etpu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(etpt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)
                        .addComponent(etid, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(etrecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(etrecherches, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextField1.setText("                                                            ETAT DE BESOIN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jTextField1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void etchatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etchatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_etchatActionPerformed

    private void etjComboBox6PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_etjComboBox6PopupMenuWillBecomeInvisible

etroll1.setText(""); 
etcombobox1_from_materiel();
etsearch();// TODO add your handling code here:
    }//GEN-LAST:event_etjComboBox6PopupMenuWillBecomeInvisible

    private void etaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etaddActionPerformed
Float a = Float.parseFloat(etqty.getText());
Float b = Float.parseFloat(etpu.getText());
//float e = Float.parseFloat(pt.getText());
Float c;
String d;
c=a*b;
d= String.format("%.2f",c);
etpt.setText(d);


etsavema_attendance();


etCall_ID_MAX();
etsearch();
 etdet.setText("");
 etqty.setText("");
 etpu.setText("");
 etpt.setText("0");
         // TODO add your handling code here:
    }//GEN-LAST:event_etaddActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
etAddModel();
etReadData("select * from etat_de_besoin"); 
//if_nothing();// TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void etrechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etrechercheKeyReleased
      // TODO add your handling code here:
    }//GEN-LAST:event_etrechercheKeyReleased

    private void etrecherchesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etrecherchesKeyReleased
       // TODO add your handling code here:
    }//GEN-LAST:event_etrecherchesKeyReleased

    private void etchatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etchatKeyReleased
etsearch();        // TODO add your handling code here:
    }//GEN-LAST:event_etchatKeyReleased

    private void etsup1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etsup1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_etsup1KeyReleased

    private void etjTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etjTable3MouseClicked
etselect_jTable();
 // TODO add your handling code here:
    }//GEN-LAST:event_etjTable3MouseClicked

    private void etremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etremoveActionPerformed
etdelete(); 
etsearch();// TODO add your handling code here:
    }//GEN-LAST:event_etremoveActionPerformed

    private void etqtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etqtyKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}        // TODO add your handling code here:
    }//GEN-LAST:event_etqtyKeyTyped

    private void etpuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_etpuKeyTyped
char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE ||c==KeyEvent.VK_PERIOD )){
evt.consume(); 
}        // TODO add your handling code here:
    }//GEN-LAST:event_etpuKeyTyped

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
            java.util.logging.Logger.getLogger(impetatdebesoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(impetatdebesoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(impetatdebesoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(impetatdebesoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new impetatdebesoin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton etadd;
    private javax.swing.JTextField etchat;
    private javax.swing.JEditorPane etdet;
    private javax.swing.JTextField etid;
    private javax.swing.JComboBox<String> etjComboBox6;
    private com.toedter.calendar.JDateChooser etjDateChooser4;
    private javax.swing.JTable etjTable3;
    private javax.swing.JTextField etpt;
    private javax.swing.JTextField etpu;
    private javax.swing.JTextField etqty;
    private javax.swing.JTextField etrecherche;
    private javax.swing.JTextField etrecherches;
    private javax.swing.JButton etremove;
    private javax.swing.JTextField etroll1;
    private javax.swing.JTextField etsup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
