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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DOSHE
 */
public class warehouse_dispact_stock extends javax.swing.JPanel {
   DefaultTableModel mode;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String ID=null;
String check=null;
String rolls,rollF;
String NAME;
String NUM;
    public warehouse_dispact_stock() {
        initComponents();
              con=JavaDbConnectUMCO.dbConnect();
        call_combo();
        call_table();
    }
  //  Date date= new Date();
    DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
String date = df.format(new Date()).toString();

 DateFormat df1 = new SimpleDateFormat("yyyy");
String requeredate = df1.format(new Date()).toString();
    
     public void call_combo(){
     try{
            String sqls="select NAME from  warehouse ";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("NAME");
              store.addItem(add1);
            }
            rs.close();
            pst.close();
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
     /*
        try{
            String sqls="select NAME from  warehouse_case";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("NAME");
              cases.addItem(add1);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
           try{
            String sqls="select NAME from  warehouse_etagere";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("NAME");
              etagere.addItem(add1);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
              try{
            String sqls="select NAME from  warehouse_rayon";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("NAME");
              rayon.addItem(add1);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    */
    }
     
     public void call_table(){
      //INSERT INTO `warehouse_stock_in_main`(`SUPPLY`, `CLASS`, `CAT`, `NAME`, `EXP_DATE`, `PRICE`, `QTY`, `QTYC`, `BARRE`, `DATE`, `NUM`, `NAMES`, `PT`, `PTC`, `STATUS`)"+
           try{
           
             String sql="SELECT distinct(NAME),NAMES,NUM_STOCK FROM `warehouse_stock_in_main` ORDER BY NAME";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        
  
       
       
      
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(180);
       col2.setPreferredWidth(50);
       
      
      rs.close();
            pst.close(); 
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
     String CLAS,CAT,BARCODE,EXP;
     Double PRICE;
       public void select_jTable()
   {
       Double QTYD,QTYC,C;
        try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,2). toString());
          String sql = "SELECT sum(qty),sum(qtyc) FROM warehouse_stock_in_main WHERE NUM_STOCK= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            QTYD = rs.getDouble("sum(qty)");
             QTYC = rs.getDouble("sum(qtyc)");
             C=QTYD-QTYC;
             qtyd.setText(""+C);
          }
         rs.close();
            pst.close();
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,2). toString());
          String sql = "SELECT sum(PT),sum(PTC) FROM warehouse_stock_in_main WHERE NUM_STOCK= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            QTYD = rs.getDouble("sum(PT)");
             QTYC = rs.getDouble("sum(PTC)");
             C=QTYD-QTYC;
             ptd.setText(""+C);
            // ptc.setText(""+QTYC);
          }
         rs.close();
            pst.close();
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        
          try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,2). toString());
          String sql = "SELECT * FROM warehouse_stock_in_main WHERE NUM_STOCK= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            String add1 = rs.getString("NAMES");
            stock.setText(add1);
             NAME = rs.getString("NAME");
             NUM = rs.getString("NUM_STOCK");
          //`SUPPLY`, ``, ``, `NAME`, ``, ``, `QTY`, `QTYC`, ``, `DATE`, `NUM`, `NAMES`, `PT`, `PTC`, `STATUS`,NUM_STOCK   
              CLAS = rs.getString("CLASS");
              CAT = rs.getString("CAT");
              PRICE = rs.getDouble("PRICE");
              BARCODE = rs.getString("BARRE");
              EXP = rs.getString("EXP_DATE");
              // PU = rs.getString("EXP_DATE");
            
          }
         rs.close();
            pst.close();
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   }
       public void rollF()
     {
         try{
            String sql="SELECT NUM_FACTURE FROM  warehouse_Vente ORDER BY NUM_FACTURE DESC LIMIT 1";
            

            
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM_FACTURE");
                 int ln=rl.length();
                 String stxt=rl.substring(0,10);
                 String snum=rl.substring(10,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rollF=stxt+snum ;
                 
                 
                 
             }else{
               rollF="FACT/"+requeredate+"/1001";
             }
             rs.close();
            pst.close();
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
       public void roll()
     {
         try{
            String sql="SELECT NUM FROM  warehouse_Vente ORDER BY NUM DESC LIMIT 1";
            

            
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,11);
                 String snum=rl.substring(11,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum ;
                 
                 
                 
             }else{
               rolls="TRANS/"+requeredate+"/1001";
             }
             rs.close();
            pst.close();
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
       String MAXNUMS=null;
        public void call_maxS(){
   
          try{
            
            String sql="SELECT MAX(NUM) FROM   warehouse_Vente";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
              
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
               MAXNUMS=rs.getString("MAX(NUM)");
                  
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
   
   } 
public void save(){
    roll();
    rollF();
//INSERT INTO `warehouse_vente`(`ID`, `NAME`, `DESCR`, `LOCATION`, `NUMBER`, `MODEL`, `QTYD`, `QTYC`, `PVD`, `PVC`, `STORE`, `PUD`, `PUC`, `CLIENT`, `DEPAR`, `NUM`, `DATES`, `STATUS`)
 if(MAXNUMS==null){
 try{
        String sql="INSERT INTO `warehouse_Vente`(`NAME`, `DESCR`, `LOCATION`, `NUMBER`, `MODEL`, `QTYD`, `QTYC`, `PVD`, `PVC`, `STORE`, `PUD`, `PUC`, `CLIENT`, `DEPAR`, `NUM`, `DATES`, `STATUS`, `NUM_FACTURE`)"+
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst=con.prepareStatement(sql);
pst.setString(1,NAME);
pst.setString(2,stock.getText());
pst.setString(3,etagere.getSelectedItem().toString()+""+rayon.getSelectedItem().toString()+""+cases.getSelectedItem().toString());
pst.setString(4,NUM);
pst.setString(5,stock.getText());
pst.setString(6,qtyc.getText());
pst.setString(7,"0");
pst.setString(8,ptc.getText());
pst.setString(9,"0.00");
pst.setString(10,store.getSelectedItem().toString());

pst.setDouble(11,PRICE);
pst.setString(12,"0.00");

pst.setString(13,"Stock In");
pst.setString(14,"Stock In");

pst.setString(15,rolls);

pst.setString(16,webDateField1.getText());
pst.setString(17,"Stock In");
pst.setString(18,rollF);





    pst.executeUpdate();
    rs.close();
            pst.close();
    JOptionPane.showMessageDialog(null, "DATA SAVED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 } else{
 try{
        String sql="INSERT INTO `warehouse_Vente`(`NAME`, `DESCR`, `LOCATION`, `NUMBER`, `MODEL`, `QTYD`, `QTYC`, `PVD`, `PVC`, `STORE`, `PUD`, `PUC`, `CLIENT`, `DEPAR`, `NUM`, `DATES`, `STATUS`,NUM_FACTURE)"+
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst=con.prepareStatement(sql);
pst.setString(1,NAME);
pst.setString(2,stock.getText());
pst.setString(3,etagere.getSelectedItem().toString()+""+rayon.getSelectedItem().toString()+""+cases.getSelectedItem().toString());
pst.setString(4,NUM);
pst.setString(5,stock.getText());
pst.setString(6,qtyc.getText());
pst.setString(7,"0");
pst.setString(8,ptc.getText());
pst.setString(9,"0.00");
pst.setString(10,store.getSelectedItem().toString());

pst.setDouble(11,PRICE);
pst.setString(12,"0.00");

pst.setString(13,"Stock In");
pst.setString(14,"Stock In");

pst.setString(15,MAXNUMS);

pst.setString(16,webDateField1.getText());
pst.setString(17,"Stock In");
pst.setString(18,rollF);





    pst.executeUpdate();
    rs.close();
            pst.close();
    JOptionPane.showMessageDialog(null, "DATA SAVED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 }  
call_maxS();
  
      save_in_main();
    }


public void etroll()
     {
         try{
            String sql="SELECT NUM FROM  warehouse_stock_in_main ORDER BY NUM DESC LIMIT 1";
            

            
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,14);
                 String snum=rl.substring(14,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum ;
                 
                 
                 
             }else{
               rolls="STOCK-IN/"+requeredate+"/1001";
             }
             rs.close();
            pst.close();
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
String MAXNUM=null;
   public void call_max(){
   
          try{
            
            String sql="SELECT MAX(NUM) FROM   warehouse_stock_in_main";
            
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
              
     //   NOM,NOMS,CLASSI,CATEGORI,P_ACHAT,P_VENTE,D_UNITE,P_ACHAT_G,P_VENTE_G,G_VENTE,NUM,MARK,MARK_QTY,CONTAINER,CONTAINER_QTY,QTY_UNITE,VAL_UNITE,PV_$,PVG_$,PA_$,PAG_$        
               MAXNUM=rs.getString("MAX(NUM)");
                  
            }
             pst.close();
            rs.close();
               
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
   
   }

public void save_in_main(){
etroll();
if(MAXNUM==null){
 try{
        String sql="INSERT INTO `warehouse_stock_in_main`(`SUPPLY`, `CLASS`, `CAT`, `NAME`, `EXP_DATE`, `PRICE`, `QTY`, `QTYC`, `BARRE`, `DATE`, `NUM`, `NAMES`, `PT`, `PTC`, `STATUS`,NUM_STOCK)"+
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst=con.prepareStatement(sql);
        //,,,;
pst.setString(1,"Out Stock");
pst.setString(2,CLAS);
pst.setString(3,CAT);
pst.setString(4,NAME);
pst.setString(5,EXP);
pst.setDouble(6,PRICE);
pst.setString(7,"0");
pst.setString(8,qtyc.getText());
pst.setString(9,BARCODE);
pst.setString(10,webDateField1.getText());
pst.setString(11,rolls);
pst.setString(12,stock.getText());
pst.setDouble(13,0.00);
pst.setString(14,ptc.getText());
pst.setString(15,store.getSelectedItem().toString());
pst.setString(16,NUM);



    pst.executeUpdate();
    rs.close();
            pst.close();
 //   JOptionPane.showMessageDialog(null, "DATA SAVED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
}else{
try{
        String sql="INSERT INTO `warehouse_stock_in_main`(`SUPPLY`, `CLASS`, `CAT`, `NAME`, `EXP_DATE`, `PRICE`, `QTY`, `QTYC`, `BARRE`, `DATE`, `NUM`, `NAMES`, `PT`, `PTC`, `STATUS`,NUM_STOCK)"+
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst=con.prepareStatement(sql);
        //,,,;
pst.setString(1,"Out Stock");
pst.setString(2,CLAS);
pst.setString(3,CAT);
pst.setString(4,NAME);
pst.setString(5,EXP);
pst.setDouble(6,PRICE);
pst.setString(7,"0");
pst.setString(8,qtyc.getText());
pst.setString(9,BARCODE);
pst.setString(10,webDateField1.getText());
pst.setString(11,MAXNUM);
pst.setString(12,stock.getText());
pst.setDouble(13,0.00);
pst.setString(14,ptc.getText());
pst.setString(15,store.getSelectedItem().toString());
pst.setString(16,NUM);



    pst.executeUpdate();
    rs.close();
            pst.close();
 //   JOptionPane.showMessageDialog(null, "DATA SAVED");
        
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
}
     
call_max();
}

public void calculation(){
Double c;
Double a = Double.parseDouble(qtyc.getText());
c=a*PRICE;
ptc.setText(""+c);
}

public void refresh(){
etagere.setSelectedItem("......");
rayon.setSelectedItem("......");
cases.setSelectedItem("......");
stock.setText("");
qtyd.setText("0.00");
qtyc.setText("0.00");
ptd.setText("0.00");
ptc.setText("0.00");

}

 public void call_table_search(){
     if(jComboBox1.getSelectedItem().equals("Search By....")){
         JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
     }else{
      //INSERT INTO `warehouse_stock_in_main`(`SUPPLY`, `CLASS`, `CAT`, `NAME`, `EXP_DATE`, `PRICE`, `QTY`, `QTYC`, `BARRE`, `DATE`, `NUM`, `NAMES`, `PT`, `PTC`, `STATUS`)"+
           try{
           
             String sql="SELECT distinct(NAME),NAMES,NUM_STOCK FROM `warehouse_stock_in_main` where "+jComboBox1.getSelectedItem()+" like '"+search.getText()+"%' ORDER BY NAME";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
       
        TableColumn col0=jTable1.getColumnModel().getColumn(0);
        TableColumn col1=jTable1.getColumnModel().getColumn(1);
        TableColumn col2=jTable1.getColumnModel().getColumn(2);
        
  
       
       
      
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(180);
       col2.setPreferredWidth(50);
       
      
       
     rs.close();
            pst.close();
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        store = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        etagere = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        rayon = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cases = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        qtyd = new javax.swing.JTextField();
        qtyc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ptc = new javax.swing.JTextField();
        ptd = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        stock = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        webDateField1 = new com.alee.extended.date.WebDateField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        search = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Store Name:");

        store.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        store.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText(" Shelf (étagère)");

        etagere.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        etagere.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......" }));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText(" Aisle (Rayon)");

        rayon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rayon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Hut (Case)");

        cases.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cases.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......" }));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Quantities in Main Store");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Quantities to Allocate  Sub Store");

        qtyd.setEditable(false);
        qtyd.setBackground(new java.awt.Color(240, 240, 241));
        qtyd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qtyd.setText("0.00");

        qtyc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        qtyc.setText("0.00");
        qtyc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtycKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Amount Money Main Store");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Amount Money Sub Store");

        ptc.setEditable(false);
        ptc.setBackground(new java.awt.Color(240, 240, 241));
        ptc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ptc.setText("0.00");

        ptd.setEditable(false);
        ptd.setBackground(new java.awt.Color(240, 240, 241));
        ptd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ptd.setText("0.00");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Stock Name:");

        stock.setEditable(false);
        stock.setBackground(new java.awt.Color(240, 240, 241));
        stock.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(stock)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(ptd, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ptc, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(qtyd, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(120, 120, 120)
                                    .addComponent(qtyc, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8))
                                    .addGap(17, 17, 17)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(qtyd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qtyc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ptc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ptd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Date");

        webDateField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(store, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etagere, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rayon, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cases, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(webDateField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(store, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etagere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cases, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rayon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable1.setBackground(new java.awt.Color(240, 240, 240));
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setSelectionForeground(new java.awt.Color(198, 215, 233));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane10.setViewportView(jTable1);

        search.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Search By....", "NAME", "NUM_STOCK" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(search, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 59, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
this.show(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
  try{
        
      select_jTable();
    rs.close();
   pst.close();

    }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
                // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(stock.getText().equals("") || webDateField1.getText().equals("")|| qtyd.getText().equals("0.00") ||qtyc.getText().equals("0.00")||etagere.getSelectedItem().equals(".....") ||store.getSelectedItem().equals("......")||cases.getSelectedItem().equals(".....")||ptd.getText().equals("0.00")||ptc.getText().equals("0.00")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else if(Double.parseDouble(qtyc.getText()) > Double.parseDouble(qtyd.getText())){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
    }else{
 try{
        
      save(); 
        refresh();
    rs.close();
   pst.close();

    }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
        
}// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void qtycKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtycKeyReleased

 try{
        
    calculation();  
    rs.close();
   pst.close();

    }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
// TODO add your handling code here:
    }//GEN-LAST:event_qtycKeyReleased

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased

         try{
        
  call_table_search();   
    rs.close();
   pst.close();

    }catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }     // TODO add your handling code here:
    }//GEN-LAST:event_searchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cases;
    private javax.swing.JComboBox<String> etagere;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField ptc;
    private javax.swing.JTextField ptd;
    private javax.swing.JTextField qtyc;
    private javax.swing.JTextField qtyd;
    private javax.swing.JComboBox<String> rayon;
    private javax.swing.JTextField search;
    private javax.swing.JTextField stock;
    private javax.swing.JComboBox<String> store;
    private com.alee.extended.date.WebDateField webDateField1;
    // End of variables declaration//GEN-END:variables
}
