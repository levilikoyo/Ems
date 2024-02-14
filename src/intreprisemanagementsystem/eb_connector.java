/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import com.alee.laf.WebLookAndFeel;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dosh
 */
public class eb_connector extends javax.swing.JFrame {

   Connection con=null;
 Connection cononline=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
 private static HttpURLConnection connection;
    public eb_connector() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
        cononline=JavaDbConnectonline.dbConnect();
        con=JavaDbConnect.dbConnect();
        Groupe1();

      load();
    }
     public void Groupe1(){
ButtonGroup bg1 = new ButtonGroup();
bg1.add(upload);
bg1.add(dowload);
     }
public void showEBData(){ 
   // SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
    // String add = dateFormats.format (jDateChooser1.setDate(new Date()));
     try{
       
         String sql="SELECT  NUM as 'Numero',BUSS as 'Projet',SUP AS 'Demmandeur',DET as 'Indices',APPROUVATION as 'Approuvation' FROM `etat_de_besoin` WHERE APPROUVATION =''  group by num";
          pst = cononline.prepareStatement(sql);
        rs= pst.executeQuery();
        jTableebcon2.setModel(DbUtils.resultSetToTableModel(rs));
        
          TableColumn col0=jTableebcon2.getColumnModel().getColumn(0);
        TableColumn col1=jTableebcon2.getColumnModel().getColumn(1);
        TableColumn col2=jTableebcon2.getColumnModel().getColumn(2);
        TableColumn col3=jTableebcon2.getColumnModel().getColumn(3);
        TableColumn col4=jTableebcon2.getColumnModel().getColumn(4);
       col0.setPreferredWidth(80);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(200);
       col4.setPreferredWidth(50);
       
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}    
}
public void load(){ 
   // SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
    // String add = dateFormats.format (jDateChooser1.setDate(new Date()));
     try{
       
          String sql="SELECT  NUM as 'Numero' FROM `etat_de_besoin` WHERE  ORIENTATION='Finance' and print='' and EXCECUTE is not null and TRIM(EXCECUTE)<>'' and APPROUVATION =''and upload='No'  group by num";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable_ebcon1.setModel(DbUtils.resultSetToTableModel(rs));
        
       
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}  
      try{
            String sql="select * from PROJET";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("PROJET_NUM");
                  buss.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
      try{
            String sql="select * from  id_token";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
              orgs.setText(rs.getString("ORGANIZATION"));
           
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
}

public void loadonline(){ 
   // SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
    // String add = dateFormats.format (jDateChooser1.setDate(new Date()));
     try{
       
          String sql="SELECT  NUM as 'Numero' FROM `etat_de_besoin` WHERE  APPROUVATION <>'' and dowload='No' and organisation='"+orgs.getText()+"' group by num";
          pst = cononline.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable_ebcon1.setModel(DbUtils.resultSetToTableModel(rs));
        
       
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}    
}
public void showEBDataOFF(){ 
    int row= jTable_ebcon1.getSelectedRow();
          String Table_clicks = (jTable_ebcon1.getModel().getValueAt(row,0). toString());
     try{
       
          String sql="SELECT  NUM_ID as 'No.',SUP AS 'Demmandeur',DET as 'Indices',QTY,UNITE,PU,PT,DEVICE FROM `etat_de_besoin` WHERE NUM='"+Table_clicks+"' AND  ORIENTATION='Finance' and print='' and APPROUVATION =''  ";//group by num";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTableebcon2.setModel(DbUtils.resultSetToTableModel(rs));
        
          TableColumn col0=jTableebcon2.getColumnModel().getColumn(0);
        TableColumn col1=jTableebcon2.getColumnModel().getColumn(1);
        TableColumn col2=jTableebcon2.getColumnModel().getColumn(2);
        TableColumn col3=jTableebcon2.getColumnModel().getColumn(3);
        TableColumn col4=jTableebcon2.getColumnModel().getColumn(4);
         TableColumn col5=jTableebcon2.getColumnModel().getColumn(5);
         TableColumn col6=jTableebcon2.getColumnModel().getColumn(6);
         TableColumn col7=jTableebcon2.getColumnModel().getColumn(7);
       col0.setPreferredWidth(1);
       col1.setPreferredWidth(80);
       col2.setPreferredWidth(300);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
        col5.setPreferredWidth(50);
         col6.setPreferredWidth(50);
          col7.setPreferredWidth(50);
       
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
} 
      try{
       
          String sql="SELECT  * FROM `etat_de_besoin` WHERE NUM='"+Table_clicks +"'";
          pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            String   SUP =rs.getString("DATES");
            jLabel1.setText(SUP);
            }
       
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}    
}
public void showEBDataON(){ 
    int row= jTable_ebcon1.getSelectedRow();
          String Table_clicks = (jTable_ebcon1.getModel().getValueAt(row,0). toString());
     try{
       
          String sql="SELECT  NUM_ID as 'No.',SUP AS 'Demmandeur',DET as 'Indices',QTY,UNITE,PU,PT,DEVICE FROM `etat_de_besoin` WHERE NUM='"+Table_clicks+"' AND  APPROUVATION <>''  and organisation='"+orgs.getText()+"'";//group by num";
          pst = cononline.prepareStatement(sql);
        rs= pst.executeQuery();
        jTableebcon2.setModel(DbUtils.resultSetToTableModel(rs));
        
          TableColumn col0=jTableebcon2.getColumnModel().getColumn(0);
        TableColumn col1=jTableebcon2.getColumnModel().getColumn(1);
        TableColumn col2=jTableebcon2.getColumnModel().getColumn(2);
        TableColumn col3=jTableebcon2.getColumnModel().getColumn(3);
        TableColumn col4=jTableebcon2.getColumnModel().getColumn(4);
         TableColumn col5=jTableebcon2.getColumnModel().getColumn(5);
         TableColumn col6=jTableebcon2.getColumnModel().getColumn(6);
         TableColumn col7=jTableebcon2.getColumnModel().getColumn(7);
       col0.setPreferredWidth(1);
       col1.setPreferredWidth(80);
       col2.setPreferredWidth(300);
       col3.setPreferredWidth(50);
       col4.setPreferredWidth(50);
        col5.setPreferredWidth(50);
         col6.setPreferredWidth(50);
          col7.setPreferredWidth(50);
       
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
} 
      try{
       
          String sql="SELECT  * FROM `etat_de_besoin` WHERE NUM='"+Table_clicks +"'";
          pst=cononline.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            String   SUP =rs.getString("DATES");
            jLabel1.setText(SUP);
            }
       
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}    
}
public void savedowload(){
    
  int row= jTable_ebcon1.getSelectedRow();
          String Table_clicks = (jTable_ebcon1.getModel().getValueAt(row,0). toString());
        
        int indexs[]=jTableebcon2.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){
      String NUM_ID = (jTableebcon2.getModel().getValueAt(indexs[i],0). toString());
//      String SUP = (jTable1.getModel().getValueAt(indexs[i],1). toString());
     String DET = (jTableebcon2.getModel().getValueAt(indexs[i],2). toString());
      String QTY = (jTableebcon2.getModel().getValueAt(indexs[i],3). toString());
      String PU = (jTableebcon2.getModel().getValueAt(indexs[i],5). toString());
      String PT = (jTableebcon2.getModel().getValueAt(indexs[i],6). toString());

      String  ReplaceString = DET.replace("'", "''");
         try{
 String sql = "UPDATE etat_de_besoin SET DET='"+ReplaceString+"',QTY='"+QTY+"',PU='"+PU+"',PT='"+PT+"',APPROUVATION='Approuved' WHERE  NUM_ID='"+NUM_ID+"' AND NUM ='"+Table_clicks+"'";
        
         pst = con.prepareStatement(sql);
       pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);} 
     
    
         }
         try{
 String sql = "UPDATE etat_de_besoin SET dowload='Yes' WHERE NUM ='"+Table_clicks+"'";
        
         pst = cononline.prepareStatement(sql);
       pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
        
        JOptionPane.showMessageDialog(null,"Transaction Saved"); 
        loadonline();
         jTableebcon2.setModel(new DefaultTableModel());
    
    
 
}
public void save(){
          
        int row= jTable_ebcon1.getSelectedRow();
          String Table_clicks = (jTable_ebcon1.getModel().getValueAt(row,0). toString());
        
        int indexs[]=jTableebcon2.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){
      String NUM_ID = (jTableebcon2.getModel().getValueAt(indexs[i],0). toString());
      String SUP = (jTableebcon2.getModel().getValueAt(indexs[i],1). toString());
      String DET = (jTableebcon2.getModel().getValueAt(indexs[i],2). toString());
      String QTY = (jTableebcon2.getModel().getValueAt(indexs[i],3). toString());
      String PU = (jTableebcon2.getModel().getValueAt(indexs[i],5). toString());
      String PT = (jTableebcon2.getModel().getValueAt(indexs[i],6). toString());
     // String DATES = (jTable1.getModel().getValueAt(indexs[i],0). toString());
      String DEVICE = (jTableebcon2.getModel().getValueAt(indexs[i],7). toString());
      String UNITE = (jTableebcon2.getModel().getValueAt(indexs[i],4). toString());
     try {
           // etroll();
        PreparedStatement pst = cononline.prepareStatement("INSERT INTO etat_de_besoin(`SUP`, `NUM`, `DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION`, `DEVICE`, `UNITE`, `NUM_ID`,ORGANISATION) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1, SUP);
         pst.setString(2, Table_clicks);
        pst.setString(3, DET);
         pst.setString(4, QTY);
         pst.setString(5, PU);
         pst.setString(6, PT);
         pst.setString(7, jLabel1.getText());
         pst.setString(8, "");
         // pst.setString(9,BUSS);
          pst.setString(9,DEVICE);
          pst.setString(10,UNITE);
           pst.setString(11,NUM_ID);
            pst.setString(12,orgs.getText());
          
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    } 
         
         
        
   
    
         }
        
        JOptionPane.showMessageDialog(null,"Transaction Saved");
          try{
 String sql = "UPDATE etat_de_besoin SET upload='Yes' WHERE NUM ='"+Table_clicks+"'";
        
         pst = con.prepareStatement(sql);
       pst.executeUpdate();


}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
        jTableebcon2.setModel(new DefaultTableModel());
        notification();
       }

public void select(){
 // String tmp=null; 
            if(jComboBox1.getSelectedItem().equals("Comptabilité Générale")){
            try{
       
          String sql="SELECT   ID,`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`, `NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`, `LB`, `PAY`, `DEVICE`, `ARCHIVE`, `SOLDE` FROM `ohada_trans` WHERE buss='"+buss.getSelectedItem()+"' and ONLINE='No'";//group by num";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
} 
            }else if(jComboBox1.getSelectedItem().equals("Comptabilité budgétaire")){
           try{
       
          String sql="SELECT    `ID`,`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`, `SUB_CAT`, `CODE_CAT`, `CODE_SUB_CAT`, `ITEMS`, `BATCH` FROM `budget_trans` WHERE projet='"+buss.getSelectedItem()+"' and ONLINE='No'";//group by num";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
} 
            }else if(jComboBox1.getSelectedItem().equals("Archive")){
           try{
       
          String sql="SELECT     ID,`NAME`, `NUMBRE`, `PAR`, `A`, `DATER`, `DATEC`, `OBJET`, `LINK`, `BATCH`, `PHYSQUE`, `NUM`, `REF`, `SUPRIME` FROM `Archive` where ONLINE='No'";//group by num";
          pst = con.prepareStatement(sql);
        rs= pst.executeQuery();
        jTable3.setModel(DbUtils.resultSetToTableModel(rs));
       
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
} 
            }
 
 
 
}

public void savesonlinedatas(){
          
 if(jComboBox1.getSelectedItem().equals("Comptabilité Générale")){
     String DEBIT = null ,CREDIT,DEBIT_CDF,CREDIT_CDF,LB,NUM_FACTURE;
      
  int indexs[]=jTable3.getSelectedRows();
       
        for(int i=0; i < indexs.length;i++){
      String ID = (jTable3.getModel().getValueAt(indexs[i],0). toString());
      String COMPTE_M = (jTable3.getModel().getValueAt(indexs[i],1). toString());
      String COMPTE = (jTable3.getModel().getValueAt(indexs[i],2). toString());
      String CODE_M = (jTable3.getModel().getValueAt(indexs[i],3). toString());
      String CODE = (jTable3.getModel().getValueAt(indexs[i],4). toString());
      String CLASSE = (jTable3.getModel().getValueAt(indexs[i],5). toString());
      String SUBSTR = (jTable3.getModel().getValueAt(indexs[i],6). toString());
      
      String NUM = (jTable3.getModel().getValueAt(indexs[i],9). toString());
      String DATES = (jTable3.getModel().getValueAt(indexs[i],10). toString());
      String BUSS = (jTable3.getModel().getValueAt(indexs[i],11). toString());
      String LIBELLE = (jTable3.getModel().getValueAt(indexs[i],12). toString());
      String JOURNAL = (jTable3.getModel().getValueAt(indexs[i],13). toString());
      String CODE1 = (jTable3.getModel().getValueAt(indexs[i],14). toString());
      

   
     
      String PAY = (jTable3.getModel().getValueAt(indexs[i],19). toString());
      String DEVICE = (jTable3.getModel().getValueAt(indexs[i],20). toString());
      String ARCHIVE = (jTable3.getModel().getValueAt(indexs[i],21). toString());
      String SOLDE = (jTable3.getModel().getValueAt(indexs[i],22). toString());
      
       try{
       
          String sql="SELECT  * FROM ohada_trans WHERE ID='"+ID+"'";
          pst=cononline.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                DEBIT =rs.getString("DEBIT");
                CREDIT =rs.getString("CREDIT");
                DEBIT_CDF =rs.getString("DEBIT_CDF");
                CREDIT_CDF =rs.getString("CREDIT_CDF");
                LB =rs.getString("LB");
                NUM_FACTURE=rs.getString("NUM_FACTURE");
           
            }
       
        
      }catch(SQLException ex ){
      JOptionPane.showMessageDialog(null, ex);
}  
        JOptionPane.showMessageDialog(null, ID+" "+DEBIT);
//     try {
//           // etroll();
//        PreparedStatement pst = cononline.prepareStatement("INSERT INTO ohada_trans (`COMPTE_M`, `COMPTE`, `CODE_M`, `CODE`, `CLASSE`, `SUBSTR`, `DEBIT`, `CREDIT`, `NUM`, `DATES`, `BUSS`, `LIBELLE`, `JOURNAL`, `CODE1`, `NUM_FACTURE`, `DEBIT_CDF`, `CREDIT_CDF`, `LB`, `PAY`, `DEVICE`, `ARCHIVE`, `SOLDE`) "
//        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//        
//        pst.setString(1, COMPTE_M);
//         pst.setString(2,COMPTE);
//        pst.setString(3,CODE_M);
//         pst.setString(4,CODE);
//         pst.setString(5,CLASSE);
//         pst.setString(6, SUBSTR);
//         pst.setString(7,DEBIT);
//         pst.setString(8,CREDIT);
//         pst.setString(9,NUM);
//          pst.setString(10,DATES);
//          pst.setString(11,BUSS);
//           pst.setString(12,LIBELLE);
//           pst.setString(13,JOURNAL);
//            pst.setString(14,CODE1);
//             pst.setString(15,NUM_FACTURE);
//              pst.setString(16,DEBIT_CDF);
//               pst.setString(17,CREDIT_CDF); 
//               pst.setString(18,LB);
//                pst.setString(19,PAY);
//            pst.setString(20,DEVICE);
//             pst.setString(21,ARCHIVE);
//              pst.setString(22,SOLDE);
//               
//          
//          pst.executeUpdate();
//        
//                
//    } catch (Exception ex) {
//         JOptionPane.showMessageDialog(null, ex.getMessage());
//    } 
//         
//         
//        
//   
//    
         }
//        
//        
//          try{
// String sql = "UPDATE ohada_trans SET online='Yes' WHERE buss ='"+buss.getSelectedItem()+"'";
//        
//         pst = con.prepareStatement(sql);
//       pst.executeUpdate();
//
//
//}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}  
//        jTable3.setModel(new DefaultTableModel());
//        
//        JOptionPane.showMessageDialog(null,"Transaction Saved");
 }else if(jComboBox1.getSelectedItem().equals("Comptabilité budgétaire")){
     
 } else if(jComboBox1.getSelectedItem().equals("Archive")){
     
 }       
        
       
       }
public void notification(){
      
BufferedReader reader;
String line;
StringBuffer responseContent = new StringBuffer(); 
        try{
//URL url = new URL("https://jsonplaceholder.typicode.com/albums");//200
URL url = new URL("https://fcm.googleapis.com/fcm/send");//405
    connection =(HttpURLConnection) url.openConnection();
     int row= jTable_ebcon1.getSelectedRow();
          String Table_clicks = (jTable_ebcon1.getModel().getValueAt(row,0). toString());
          String IDTAKEN = null,TOKEN=null;
           try{
            String sql="select ID_USER from ID_TOKEN WHERE ID=1";
	   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
          IDTAKEN=rs.getString("ID_USER");  
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
           try{
            String sql="select  token from  token WHERE ID='"+IDTAKEN+"'";
	   pst = cononline.prepareStatement(sql);          rs=pst.executeQuery();
            if(rs.next()){
         TOKEN=rs.getString("token");  
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
String tokens=TOKEN;
//String tokens="ecfLcSTNQQyCHHRt94Lr3A:APA91bF72Vl-LKShx4yXFJ4qreR6vmKCc8jnP665BmbH_dURwCMadvlyI8ppvdoQz_5EqiHl5EmsNw8GfpWKuq-8rFl7voE68c96A5Zn1i6o7YXlboH5x6Yn9zgeD4l1z7AKsmBWOwN_";

String contt=Table_clicks;
//String titttle="Nouvel état de besoin";
    try {
        String jsonData = "{\r\n    \"to\": \""+tokens+"\",\r\n    \"data\" : {\r\n        \"title\" : \"EMS-Likoyo\",\r\n        \"content\" : \""+contt+"\"\r\n    }\r\n\r\n}";
        //request stup
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(5000);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "key=AAAATZnF8kA:APA91bGvYNdaxqaG4F6FoqvzicEWNpu8njTh3lRw6DSTq1BgXwfUr4KpL4h5NQs6qjTv36PKa3WjMCF_6B510kU4wUXLwfd1SrmWxwtpEpKkQtW7Y9it1W7UaGGOB2iN9KQ3QeklNcSb");
        
        byte[] out = jsonData.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = connection.getOutputStream();
        stream.write(out);
        
connection.setReadTimeout(5000); 
    } catch (ProtocolException ex) {
        Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
    }


int status=connection.getResponseCode();
System.out.println(status);
if(status >299){
reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
while((line = reader.readLine()) !=null){
responseContent.append(line);
}
reader.close();
}else{
reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
while((line = reader.readLine()) !=null){
responseContent.append(line);
}
reader.close();
}
System.out.println(responseContent.toString());
}catch(MalformedURLException e){
 e.printStackTrace();
}catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
connection.disconnect();
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        buss = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableebcon2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_ebcon1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        dowload = new javax.swing.JRadioButton();
        upload = new javax.swing.JRadioButton();
        online = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        orgs = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();

        jScrollPane2.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable3.setRowHeight(26);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Comptabilité Générale", "Comptabilité budgétaire", "Archive" }));
        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Sychronisation");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buss.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One Project" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, 0, 179, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(buss, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Online Sychronized", jPanel2);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTableebcon2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableebcon2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableebcon2.setRowHeight(26);
        jTableebcon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableebcon2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableebcon2);

        jTable_ebcon1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable_ebcon1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable_ebcon1.setRowHeight(26);
        jTable_ebcon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ebcon1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_ebcon1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        dowload.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dowload.setText("Download Datas");

        upload.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        upload.setText("UpLoad Datas");

        online.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        online.setText("Online Datas");
        online.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onlineMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Message");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/saves.jpg"))); // NOI18N
        jLabel2.setText("Save Action");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        orgs.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        orgs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orgs.setText("AFPDE");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orgs, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(online, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(upload, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dowload, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(dowload, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
            .addComponent(upload, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(online, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(orgs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("EB Connector", jPanel3);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1004, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 483, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("EB Backup", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
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

    private void jTableebcon2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableebcon2MouseClicked
      // TODO add your handling code here:
    }//GEN-LAST:event_jTableebcon2MouseClicked

    private void onlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onlineMouseClicked
if(online.isSelected()){
//showEBData();
loadonline();
}else{
 load();
}        // TODO add your handling code here:
    }//GEN-LAST:event_onlineMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
// try{
// con=JavaDbConnect.dbConnect(); 
// cononline=JavaDbConnect.dbConnect(); 
// if(upload.isSelected()){
// save(); 
//  load();
//}else if(dowload.isSelected()){
//savedowload();
//loadonline();
//}
//
//}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
//} finally {
//    if (rs != null) {
//        try {
//            rs.close();
//        } catch (SQLException ex) { /* Ignored */}
//    }
//    if (pst != null) {
//        try {
//            pst.close();
//        } catch (SQLException ex) { /* Ignored */}
//    }
//    if (con != null) {
//        try {
//            con.close();
//        } catch (SQLException ex) { /* Ignored */}
//    }
//     if (cononline!= null) {
//        try {
//            cononline.close();
//        } catch (SQLException ex) { /* Ignored */}
//    }
//        
// }      
if(upload.isSelected()){
 save(); 
  load();
}else if(dowload.isSelected()){
savedowload();
loadonline();
}
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jTable_ebcon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ebcon1MouseClicked
  
  
//try{
// con=JavaDbConnect.dbConnect(); 
//  cononline=JavaDbConnect.dbConnect(); 
//         if(online.isSelected()){
////showEBData();
//showEBDataON();
//}else{
//showEBDataOFF();
//}
//}catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
//} finally {
//    if (rs != null) {
//        try {
//            rs.close();
//        } catch (SQLException ex) { /* Ignored */}
//    }
//    if (pst != null) {
//        try {
//            pst.close();
//        } catch (SQLException ex) { /* Ignored */}
//    }
//    if (con != null) {
//        try {
//            con.close();
//        } catch (SQLException ex) { /* Ignored */}
//    }
//    if ( cononline != null) {
//        try {
//             cononline.close();
//        } catch (SQLException ex) { /* Ignored */}
//    }
//} 
 if(online.isSelected()){
//showEBData();
showEBDataON();
}else{
showEBDataOFF();
}
// TODO add your handling code here:
    }//GEN-LAST:event_jTable_ebcon1MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
select();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
savesonlinedatas();        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(eb_connector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(eb_connector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(eb_connector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(eb_connector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                  WebLookAndFeel.install(true);
                new eb_connector().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> buss;
    private javax.swing.JRadioButton dowload;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable_ebcon1;
    private javax.swing.JTable jTableebcon2;
    private javax.swing.JRadioButton online;
    private javax.swing.JLabel orgs;
    private javax.swing.JRadioButton upload;
    // End of variables declaration//GEN-END:variables
}
