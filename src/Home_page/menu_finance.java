/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Home_page;

import ems_client.EmslService;
import ems_client.RetrofitClient;
import intreprisemanagementsystem.JavaDbConnect;
import intreprisemanagementsystem.avance_sur_salaire1;
import intreprisemanagementsystem.bilans;
import intreprisemanagementsystem.budget_shows;
import intreprisemanagementsystem.homme;
import intreprisemanagementsystem.new_project;
import intreprisemanagementsystem.ohada;
import intreprisemanagementsystem.reconciliation_bank;
import intreprisemanagementsystem.t_f_r;
import intreprisemanagementsystem.wait;
import java.awt.HeadlessException;
import java.beans.PropertyVetoException;
import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import journals.home_Table;
import journals.home_etat_de_besoin;
import journals.journal1;
import static journals.journal1.boody;
import journals.print;
import journals.verser_au_tiers;
import model.Budget_trans;
import model.Ohada_trans;
import model.Resultat_sync;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @author Doshe PC
 */
public class menu_finance extends javax.swing.JPanel {
 Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls;

EmslService api;
// List <ems_client_levi.Stock> pickinglist = new ArrayList();
    public menu_finance() {
        con=JavaDbConnect.dbConnect();
        initComponents();
        
       // api=RetrofitClient.getAPIService();
        
    }
String imgPath=null; 
public void broswer(){
  int indexs[]=home_Table.jTable2.getSelectedRows();
  for(int i=0; i < indexs.length;i++){
   String piece = (home_Table.jTable2.getModel().getValueAt(indexs[i],7). toString()); 
if(piece.equals("No")){
  JFileChooser chooser = new JFileChooser();
       chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
       FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","gif","png","*.pdf","pdf");
       chooser.addChoosableFileFilter(filter);
       int result = chooser.showSaveDialog(null);
       if(result == JFileChooser.APPROVE_OPTION ){
           File selectedFile = chooser.getSelectedFile();
           String path = selectedFile.getAbsolutePath();
           imgPath = path;
        csave();
       }
       else if(result ==  JFileChooser.CANCEL_OPTION){
           System.out.println("No Fli Selected");
       }     
    
}else if(piece.equals("Yes")){
    
   csave();  
}}    
    
    
  
     }   
   public void roll()
     {
         String NUMS = null;
         int SUBSTRING = 0;
          try{
          String sql="SELECT * FROM  transaction_number WHERE PROJET='"+journal1.buss.getText()+"' and NAME='ARCHIVE'";
          
             
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
                //String sum=rs.getString("nom");
                NUMS=rs.getString("NUM");
                SUBSTRING=rs.getInt("SUBSTR");
              //  jLabel6.setText(NUMS);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
         
         
         
         try{
            String sql="SELECT NUM FROM archive WHERE PROJET='"+journal1.buss.getText()+"' ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,SUBSTRING);
                 String snum=rl.substring(SUBSTRING,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum;
                 
                 
                
             }else{
                 //rolls="FICHE/EB/2018/1";
                 rolls=NUMS;
             }
                 
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
      String paths1=null;
      
       public void CALL_PATHS()
    {
       
        
         
        try{
            String sql="SELECT PATHS FROM   path_docs where ID=1";
          //  con = DriverManager.getConnection(url);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
               paths1=rs.getString("PATHS");
                //buss.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
       
           public void upload(){
          roll();
      CALL_PATHS();
          File sourceFile = new File ( imgPath);//("C:\\Users\\DOSHE\\Documents\\Congo.docx"); 
        // File file = new File("C:\\Users\\Mayank\\Desktop\\1.txt");
File destinationFile = new File (paths1+rolls+".pdf");
//file.d;
try{
  Files.copy(sourceFile.toPath(),destinationFile.toPath());
  sourceFile.delete();
}catch(Exception ex ){
         JOptionPane.showMessageDialog(null,ex);
}
      
      }
   
public void attache(){
  JFileChooser chooser = new JFileChooser();
       chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
       FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","gif","png","*.pdf","pdf");
       chooser.addChoosableFileFilter(filter);
       int result = chooser.showSaveDialog(null);
       if(result == JFileChooser.APPROVE_OPTION ){
           File selectedFile = chooser.getSelectedFile();
           String path = selectedFile.getAbsolutePath();
           imgPath = path;
       roll();
    CALL_PATHS();
    upload(); 
    int indexs[]=home_Table.jTable2.getSelectedRows();
     for(int i=0; i < indexs.length;i++){
   String REF = (home_Table.jTable2.getModel().getValueAt(indexs[i],0). toString());
   String OBJET = (home_Table.jTable2.getModel().getValueAt(indexs[i],2). toString());
          try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `archive`(`NAME`, `NUMBRE`, `PAR`, `A`, `DATER`, `DATEC`, `OBJET`, `LINK`, `BATCH`, `PHYSQUE`, `NUM`,REF,PROJET)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,"-");
        pst.setString(2, "-");
         pst.setString(3,"-");
         pst.setString(4,"-");
         pst.setString(5,"-");
         pst.setString(6,"-");
         pst.setString(7,OBJET);
         pst.setString(9,"-");
         pst.setString(8,paths1+rolls+".pdf");
         pst.setString(10,"-");
         pst.setString(11,rolls);
          pst.setString(12,REF);
          pst.setString(13,journal1.buss.getText());
 pst.executeUpdate();
             
    } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage()); }
    
     try {
        PreparedStatement pst = con.prepareStatement("UPDATE `ohada_trans` SET `ARCHIVE`='Yes' where NUM='"+REF+"' and BUSS='"+journal1.buss.getText()+"'");
     pst.executeUpdate();
   } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.getMessage());  }
     }
       }
       else if(result ==  JFileChooser.CANCEL_OPTION){
           System.out.println("No Fli Selected");
       }     

} 

public void no_attache(){
         int response = JOptionPane.showConfirmDialog(null, "voulez-vous supprimer cette pièce jointe ?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
             int indexs[]=home_Table.jTable2.getSelectedRows();   
                for(int i=0; i < indexs.length;i++){
   String REF = (home_Table.jTable2.getModel().getValueAt(indexs[i],0). toString());
   String OBJET = (home_Table.jTable2.getModel().getValueAt(indexs[i],2). toString());
  try{
        String sql = "DELETE FROM archive WHERE REF='"+REF+"' and PROJET='"+journal1.buss.getText()+"'";
        
         pst = con.prepareStatement(sql);
         //pst.setString(1,nums);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
     // JOptionPane.showMessageDialog(null, ex.getMessage());
     } 
     try {
        PreparedStatement pst = con.prepareStatement("UPDATE `ohada_trans` SET `ARCHIVE`='No' where NUM='"+REF+"' and BUSS='"+journal1.buss.getText()+"'");
      pst.executeUpdate();
  } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());} 
                }
            }else{
           
            } 

}
     public void csave(){
  int indexs[]=home_Table.jTable2.getSelectedRows();
  for(int i=0; i < indexs.length;i++){
   String REF = (home_Table.jTable2.getModel().getValueAt(indexs[i],0). toString());
   String OBJET = (home_Table.jTable2.getModel().getValueAt(indexs[i],2). toString());
   String piece = (home_Table.jTable2.getModel().getValueAt(indexs[i],7). toString()); 
if(piece.equals("No")){
    roll();
    CALL_PATHS();
    upload(); 
     
          try {
        PreparedStatement pst = con.prepareStatement("INSERT INTO `archive`(`NAME`, `NUMBRE`, `PAR`, `A`, `DATER`, `DATEC`, `OBJET`, `LINK`, `BATCH`, `PHYSQUE`, `NUM`,REF,PROJET)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,"-");
        pst.setString(2, "-");
         pst.setString(3,"-");
         pst.setString(4,"-");
         pst.setString(5,"-");
         pst.setString(6,"-");
         pst.setString(7,OBJET);
         pst.setString(9,"-");
         pst.setString(8,paths1+rolls+".pdf");
         pst.setString(10,"-");
         pst.setString(11,rolls);
          pst.setString(12,REF);
          pst.setString(13,journal1.buss.getText());
         
      
       
          
         
          pst.executeUpdate();
        
                
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
         
    }
    
     try {
        PreparedStatement pst = con.prepareStatement("UPDATE `ohada_trans` SET `ARCHIVE`='Yes' where NUM='"+REF+"' and BUSS='"+journal1.buss.getText()+"'");
        
      
         
          pst.executeUpdate();
        
                
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());   
    }
      
       }else if(piece.equals("Yes")){
                int response = JOptionPane.showConfirmDialog(null, "voulez-vous supprimer cette pièce jointe ?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
  try{
        String sql = "DELETE FROM archive WHERE REF='"+REF+"' and PROJET='"+journal1.buss.getText()+"'";
        
         pst = con.prepareStatement(sql);
         //pst.setString(1,nums);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
     // JOptionPane.showMessageDialog(null, ex.getMessage());
     } 
     try {
        PreparedStatement pst = con.prepareStatement("UPDATE `ohada_trans` SET `ARCHIVE`='No' where NUM='"+REF+"' and BUSS='"+journal1.buss.getText()+"'");
      pst.executeUpdate();
  } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}  
            }else{
           
            } 
        
          }
   
        // for}
JOptionPane.showMessageDialog(null, "Saved");


            }
}  
       public void csavdelete(){
    roll();
    CALL_PATHS();
    upload(); 
     
      int row= home_Table.jTable2.getSelectedRow();
         
    int indexs[]=home_Table.jTable2.getSelectedRows();
         
        for(int i=0; i < indexs.length;i++){
             String REF = (home_Table.jTable2.getModel().getValueAt(indexs[i],0). toString());
          String OBJET = (home_Table.jTable2.getModel().getValueAt(indexs[i],2). toString());
  
     try{
        String sql = "DELETE FROM archive WHERE REF='"+REF+"' and PROJET='"+journal1.buss.getText()+"'";
        
         pst = con.prepareStatement(sql);
         //pst.setString(1,nums);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
     // JOptionPane.showMessageDialog(null, ex.getMessage());
     } 
     try {
        PreparedStatement pst = con.prepareStatement("UPDATE `ohada_trans` SET `ARCHIVE`='No' where NUM='"+REF+"' and BUSS='"+journal1.buss.getText()+"'");
      pst.executeUpdate();
  } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());}
        }
JOptionPane.showMessageDialog(null, "Saved");

      // }

}  
       
       
 public void send_data_budget_trans(){
     wait m = new wait();
     wait.msg.setText("Sychronization ......");
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 api=RetrofitClient.getAPIService();
 List <Budget_trans> list_budgeTrans= new ArrayList<Budget_trans>(); 
 con=JavaDbConnect.dbConnect();
  try{
      String  sqls="SELECT * FROM budget_trans where  projet ='"+journal1.buss.getText()+"' and Online='No'"; 
           pst = con.prepareStatement(sqls);          
         rs=pst.executeQuery();
         while (rs.next()){
              Budget_trans bt = new Budget_trans(
               rs.getString(2),
               rs.getString(3),         
               rs.getString(4),
               rs.getString(5),
               rs.getString(6),      
               rs.getString(7),
               rs.getString(8),
               rs.getString(9),
               rs.getString(10),
               rs.getString(11),
               rs.getString(12),       
               rs.getString(13),
               rs.getString(14),
               rs.getString(15),         
               rs.getString(16),
               rs.getString(17)
           //    rs.getString(18)
              );
         list_budgeTrans.add(bt);
           }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
//wait.msg.setText("Uploading dane");
  //JOptionPane.showMessageDialog(null, "Done1:"); 
 api.ajouter_table_budget_trans(list_budgeTrans, journal1.buss.getText(),journal1.batchs.getSelectedItem().toString()).enqueue(new Callback<Resultat_sync>() {
 //JOptionPane.showMessageDialog(null, "Done2:"); 
     @Override
    public void onResponse(Call<Resultat_sync> call, Response<Resultat_sync> rspns) {
      //  JOptionPane.showMessageDialog(null, "Done2:"); 
       System.out.println(rspns.body().getData().size());
      // JOptionPane.showMessageDialog(null, "Done: " + rspns.body().getData().size()); 
       
       
     //  JOptionPane.showMessageDialog(null, "Done3:"); 
     //  wait.msg.setText("Downloading......");
      
//----Delete
 try{
        String sql = "DELETE FROM budget_trans WHERE  where  projet='"+journal1.buss.getText()+"' and ONLINE='No'";
        
         pst = con.prepareStatement(sql);
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
     } 
 
       try{
             //JOptionPane.showMessageDialog(null, "Done4:");
           con.setAutoCommit(false); 
    String sql="INSERT INTO `budget_trans`(`ITEM`, `DEBIT`, `CREDIT`, `SOLD`, `PROJET`, `CODE`, `NUM`, `CAT`, `DATES`, `MOIS`, `ANNEE`,SUB_CAT,CODE_CAT,CODE_SUB_CAT,ITEMS,BATCH,ONLINE) "+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst=con.prepareStatement(sql);
    
    for(int i=0; i<rspns.body().getData().size();i++){
        Budget_trans bt = rspns.body().getData().get(i);
        pst.setString(1,bt.getItem());
    pst.setString(2,bt.getDebit());
    pst.setString(3,bt.getCredit());
    pst.setString(4,bt.getSold());
    pst.setString(5,bt.getProjet());
    pst.setString(6,bt.getCode());
    pst.setString(7,bt.getNum());
    pst.setString(8,bt.getCat());
         pst.setString(9, bt.getDates());
         pst.setString(10, bt.getMois());
         pst.setString(11, bt.getAnnee());
    pst.setString (12,bt.getSub_cat());
    
     pst.setString(13,bt.getCode_cat());
      pst.setString(14,bt.getCode_sub_cat());
      
        pst.setString(15,bt.getItems());
        pst.setString(16,bt.getBatch());
    pst.setString(17,"Yes");
    
    
    pst.addBatch();;
    }
    
    pst.executeBatch();
    con.commit();
    
    
  //   wait.msg.setText("Downloading Done");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
        
//---->Update 
     
      try {
        PreparedStatement pst = con.prepareStatement("UPDATE `budget_trans` SET `ONLINE`='Yes' where projet='"+journal1.buss.getText()+"' and BATCH='"+journal1.batchs.getSelectedItem()+"'and ONLINE='No'");
      pst.executeUpdate();
  } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());} 
 wait.msg.setText("Done");
       
       
    }

    @Override
    public void onFailure(Call<Resultat_sync> call, Throwable thrwbl) {
        System.out.print(thrwbl);
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     wait.msg.setText("Error");
    }
});
 
   // JOptionPane.showMessageDialog(null, "Done5:"); 
 }

 public void send_data_ohada_trans(){
wait m = new wait();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
 api=RetrofitClient.getAPIService();
 List <Ohada_trans> list_ohadaTrans= new ArrayList<Ohada_trans>(); 

 SimpleDateFormat dateFormatsjour = new SimpleDateFormat("yyyy-MM-dd");
         String addDatejour = dateFormatsjour.format(journal1.jDateChooser1.getDate());
          SimpleDateFormat dateFormatsmois = new SimpleDateFormat("yyyy-MM");
         String addDatemois = dateFormatsmois.format(journal1.jDateChooser1.getDate());
          SimpleDateFormat dateFormatsyear = new SimpleDateFormat("yyyy");
         String addDateyear = dateFormatsyear.format(journal1.jDateChooser1.getDate());
         String sql=null;
       if(journal1.batchs1.getSelectedItem().equals("Day")){
       sql="SELECT * FROM ohada_trans where  buss ='"+journal1.buss.getText()+"' and DATES='"+addDatejour+"' and Online='No'";
       }else if(journal1.batchs1.getSelectedItem().equals("Month")){
      sql="SELECT * FROM ohada_trans where  buss ='"+journal1.buss.getText()+"'and SUBSTR(DATES,1,7)='"+addDatemois+"' and Online='No'"; 
       }else if(journal1.batchs1.getSelectedItem().equals("Year")){
       sql="SELECT * FROM ohada_trans where  buss ='"+journal1.buss.getText()+"' and SUBSTR(DATES,1,4)='"+addDateyear+"' and Online='No'"; 
     }
       
 con=JavaDbConnect.dbConnect();
  try{
   //   String  sqls="SELECT * FROM ohada_trans where  buss ='"+journal1.buss.getText()+"' and SUBSTR(DATES,1,4)='"+addDateyear+"' and Online='No'"; 
      
           pst = con.prepareStatement(sql);          
         rs=pst.executeQuery();
         while (rs.next()){
              Ohada_trans bt = new Ohada_trans(
              // rs.getString(1),
               rs.getString(2),
               rs.getString(3),         
               rs.getString(4),
               rs.getString(5),
               rs.getString(6),      
               rs.getString(7),
               rs.getString(8),
               rs.getString(9),
               rs.getString(10),
               rs.getString(11),
               rs.getString(12),       
               rs.getString(13),
               rs.getString(14),
               rs.getString(15),         
               rs.getString(16),
               rs.getString(17),
               rs.getString(18),
               rs.getString(19),
               rs.getString(20),
               rs.getString(21),
               rs.getString(22),
               rs.getString(23)
              );
         list_ohadaTrans.add(bt);
           }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 
 api.ajouter_table_ohada_trans(list_ohadaTrans).enqueue(new Callback<Resultat_sync>() {
    @Override
    public void onResponse(Call<Resultat_sync> call, Response<Resultat_sync> rspns) {
     //  JOptionPane.showMessageDialog(null, "Done: " + list_ohadaTrans.size()); 
  
     wait.msg.setText("Done: " + list_ohadaTrans.size());
    }

    @Override
    public void onFailure(Call<Resultat_sync> call, Throwable thrwbl) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
});
 
 
 } 
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));

        projet.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        projet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Course_16px.png"))); // NOI18N
        projet.setText("Nouveau Projet");
        projet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                projetMouseClicked(evt);
            }
        });

        ohada.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ohada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Accounting_16px.png"))); // NOI18N
        ohada.setText("Compte comptable");
        ohada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ohadaMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Documents_16px.png"))); // NOI18N
        jLabel2.setText("Documents comptable");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Sheets_16px.png"))); // NOI18N
        jLabel16.setText("OP. Fiche de paie");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        avc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        avc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Cash_in_Hand_16px.png"))); // NOI18N
        avc.setText("Avance sur Salaire");
        avc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                avcMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Customs_Officer_16px.png"))); // NOI18N
        jLabel4.setText("S. Verser aux tiers");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        archi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        archi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Attach_24px.png"))); // NOI18N
        archi.setText("Attache des Pieces");
        archi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                archiMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bill_16px.png"))); // NOI18N
        jLabel6.setText("Rec. Bancaire");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home_page/icons8-budget-30.png"))); // NOI18N
        jc.setText("Jounaux Comptable");
        jc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcMouseClicked(evt);
            }
        });

        budget.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        budget.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home_page/icons8-budget-40.png"))); // NOI18N
        budget.setText("Budgétisation");
        budget.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                budgetMouseClicked(evt);
            }
        });

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Calculator_16px.png"))); // NOI18N
        jLabel9.setText("TFT");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bill_16px.png"))); // NOI18N
        jLabel10.setText("TFR");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Documents_16px.png"))); // NOI18N
        jLabel11.setText("Bilan");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("↑");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Home_page/icons8-online-80.png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        etat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        etat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bulleted_List_16px.png"))); // NOI18N
        etat.setText("Etats des besoins/Req");
        etat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                etatMouseClicked(evt);
            }
        });

        archi1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        archi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_No_Attach_24px.png"))); // NOI18N
        archi1.setText("Annuler  Attache");
        archi1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                archi1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ohada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(projet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16)
                    .addComponent(avc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(archi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(archi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(budget, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 186, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etat)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator4)
            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(avc, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(projet, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ohada, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(budget))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(archi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(archi1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(etat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12))
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void projetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_projetMouseClicked
try{
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select * from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Nouveau Projet' and ACESS='Yes'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){ 
            if(projet.getText().equals("Nouveau Projet")){
 new_project m = new new_project ();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        projet.setText("Nouveau Projet *");
}
            }else{
                JOptionPane.showMessageDialog(null,"Acces limité","Avertissement",JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex){

            JOptionPane.showMessageDialog(null, ex); }

        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }   
        
               // TODO add your handling code here:
    }//GEN-LAST:event_projetMouseClicked

    private void ohadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ohadaMouseClicked
try{
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select * from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Compte comptable' and ACESS='Yes'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){ 
            if(ohada.getText().equals("Compte comptable")){
 ohada m = new ohada();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        ohada.setText("Compte comptable *");
}
            }else{
                JOptionPane.showMessageDialog(null,"Acces limité","Avertissement",JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex){

            JOptionPane.showMessageDialog(null, ex); }

        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        } 
        

               // TODO add your handling code here:
    }//GEN-LAST:event_ohadaMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
try{
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select * from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Rec Bank' and ACESS='Yes'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){ 
          reconciliation_bank m= new reconciliation_bank();
       home.desktoppane.add(m);
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 

            }else{
                JOptionPane.showMessageDialog(null,"Acces limité","Avertissement",JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex){

            JOptionPane.showMessageDialog(null, ex); }

        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }
                // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
try{
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select * from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='T_F_R' and ACESS='Yes'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){ 
        t_f_r m = new t_f_r();
 home.desktoppane.add(m);
 try {
        m.setMaximum(true);
    } catch (PropertyVetoException ex) {
        Logger.getLogger(homme.class.getName()).log(Level.SEVERE, null, ex);
    }
   m.show();
   m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
            }else{
                JOptionPane.showMessageDialog(null,"Acces limité","Avertissement",JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex){

            JOptionPane.showMessageDialog(null, ex); }

        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }  
        
              // TODO add your handling code here:
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
try{
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select * from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Bilan' and ACESS='Yes'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){ 
       bilans m = new bilans();
home.desktoppane.add(m);
 try {
        m.setMaximum(true);
    } catch (PropertyVetoException ex) {
        Logger.getLogger(homme.class.getName()).log(Level.SEVERE, null, ex);
    }
   m.show();
   m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }else{
                JOptionPane.showMessageDialog(null,"Acces limité","Avertissement",JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex){

            JOptionPane.showMessageDialog(null, ex); }

        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }  
                  // TODO add your handling code here:
    }//GEN-LAST:event_jLabel11MouseClicked

    private void avcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_avcMouseClicked
try{
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select * from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Avance sur Salaire' and ACESS='Yes'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){ 
       if(avc.getText().equals("Avance sur Salaire")){
            // JOptionPane.showMessageDialog(null, "ici");
  avance_sur_salaire1 m = new avance_sur_salaire1();
       m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        avc.setText("Avance sur Salaire *");
 }

            }else{
                JOptionPane.showMessageDialog(null,"Acces limité","Avertissement",JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex){

            JOptionPane.showMessageDialog(null, ex); }

        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }     
        
               // TODO add your handling code here:
    }//GEN-LAST:event_avcMouseClicked

    private void budgetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_budgetMouseClicked
try{
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select * from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Budgétisation' and ACESS='Yes'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){ 
      if(budget.getText().equals("Budgétisation")){
budget_shows m= new budget_shows();
   home.desktoppane.add(m);
    try {
        m.setMaximum(true);
    } catch (PropertyVetoException ex) {
        Logger.getLogger(homme.class.getName()).log(Level.SEVERE, null, ex);
    }
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        budget.setText("Budgétisation *");
}

            }else{
                JOptionPane.showMessageDialog(null,"Acces limité","Avertissement",JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex){

            JOptionPane.showMessageDialog(null, ex); }

        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }  
        
                   // TODO add your handling code here:
    }//GEN-LAST:event_budgetMouseClicked

    private void jcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcMouseClicked
try{
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select * from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Jounaux Comptable' and ACESS='Yes'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){ 
          if(jc.getText().equals("Jounaux Comptable")){
 journal1 m= new journal1();
        home.desktoppane.add(m);
    try {
        m.setMaximum(true);
    } catch (PropertyVetoException ex) {
        Logger.getLogger(homme.class.getName()).log(Level.SEVERE, null, ex);
    }
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
       jc.setText("Jounaux Comptable *");
}
            }else{
                JOptionPane.showMessageDialog(null,"Acces limité","Avertissement",JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex){

            JOptionPane.showMessageDialog(null, ex); }

        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }
        
        
        
       
             // TODO add your handling code here:
    }//GEN-LAST:event_jcMouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        home.body_menu.removeAll();
         home.body_menu.repaint();
         home.body_menu.revalidate();// TODO add your handling code here:
    }//GEN-LAST:event_jLabel12MouseClicked

    private void archiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archiMouseClicked
try{
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select * from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Archivage des Pieces' and ACESS='Yes'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){ 
         
             
attache();
  boody.removeAll();
  boody.add(new home_Table());
  boody.repaint();
  boody.revalidate();
                }else{
                JOptionPane.showMessageDialog(null,"Acces limité","Avertissement",JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex){

            JOptionPane.showMessageDialog(null, ex); }

        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }       
       
             // TODO add your handling code here:
    }//GEN-LAST:event_archiMouseClicked

    private void etatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etatMouseClicked
try{
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select * from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Etats des besoins_Fin' and ACESS='Yes'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){ 
                  if(etat.getText().equals("Etats des besoins/Req")){
home_etat_de_besoin m = new home_etat_de_besoin();
home.desktoppane.add(m);
   m.show();
   m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
   etat.setText("Etats des besoins/Req *");
} 

            }else{
                JOptionPane.showMessageDialog(null,"Acces limité","Avertissement",JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex){

            JOptionPane.showMessageDialog(null, ex); }

        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }       
      // TODO add your handling code here:
    }//GEN-LAST:event_etatMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
/*
        try{
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select * from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Bon_de_commande' and ACESS='Yes'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){ 
           bon_de_comande m= new  bon_de_comande();
      
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            }else{
                JOptionPane.showMessageDialog(null,"Acces limité","Avertissement",JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex){

            JOptionPane.showMessageDialog(null, ex); }

        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored *//*}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored *//*}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored *//*}
            }
        }
*/
print m = new print ();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
try{
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select * from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='-' and ACESS='Yes'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){ 
           //HREE

            }else{
                JOptionPane.showMessageDialog(null,"Acces limité","Avertissement",JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex){

            JOptionPane.showMessageDialog(null, ex); }

        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }             // TODO add your handling code here:
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
try{
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select * from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Verser aux tiers' and ACESS='Yes'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){ 
          verser_au_tiers m= new  verser_au_tiers();
      
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            }else{
                JOptionPane.showMessageDialog(null,"Acces limité","Avertissement",JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex){

            JOptionPane.showMessageDialog(null, ex); }

        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }               // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
try{
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select * from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='-' and ACESS='Yes'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){ 
           //HREE

            }else{
                JOptionPane.showMessageDialog(null,"Acces limité","Avertissement",JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex){

            JOptionPane.showMessageDialog(null, ex); }

        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }               // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked

  
  
    }//GEN-LAST:event_jLabel13MouseClicked

    private void archi1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archi1MouseClicked
try{
            con=JavaDbConnect.dbConnect();
            try{
                String sql="select * from user_write where NAME='"+Home_page.home.user.getText()+"' and CHAMP='Archivage des Pieces' and ACESS='Yes'";

                pst=con.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){ 
         
             
no_attache();
  boody.removeAll();
  boody.add(new home_Table());
  boody.repaint();
  boody.revalidate();
                }else{
                JOptionPane.showMessageDialog(null,"Acces limité","Avertissement",JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex){

            JOptionPane.showMessageDialog(null, ex); }

        }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) { /* Ignored */}
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) { /* Ignored */}
            }
        }           // TODO add your handling code here:
    }//GEN-LAST:event_archi1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JLabel archi = new javax.swing.JLabel();
    public static final javax.swing.JLabel archi1 = new javax.swing.JLabel();
    public static final javax.swing.JLabel avc = new javax.swing.JLabel();
    public static final javax.swing.JLabel budget = new javax.swing.JLabel();
    public static final javax.swing.JLabel etat = new javax.swing.JLabel();
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    public static final javax.swing.JLabel jc = new javax.swing.JLabel();
    public static final javax.swing.JLabel ohada = new javax.swing.JLabel();
    public static final javax.swing.JLabel projet = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}
