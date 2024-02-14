/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package journals;

import intreprisemanagementsystem.JavaDbConnect;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Dosh
 */
public class printrecus extends javax.swing.JPanel {

     Connection con=null;
    Connection conof=null;
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
 String rolls;
    public printrecus() {
        initComponents();
       con=JavaDbConnect.dbConnect();
       //   setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons.png")));
        call();
      //  testDatas();
    }
public void call(){
 try{
		String sql=("SELECT LIBELLE FROM OHADA_TRANS WHERE SUBSTR=41 and BUSS='"+journal1.buss.getText()+"'");
                pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
                  jTable5.setModel(DbUtils.resultSetToTableModel(rs));
    }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 try{
		String sql=("SELECT num_fiche as FACTURE,name FROM  recu WHERE projet='"+journal1.buss.getText()+"'  AND TRANSACTION='FACTURE' GROUP BY num_fiche");
                pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
                  jTable7.setModel(DbUtils.resultSetToTableModel(rs));
    }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 try{
		String sql=("SELECT num_fiche as FACTURE,NAME FROM RECU WHERE PROJET='"+journal1.buss.getText()+"' AND TRANSACTION='FACTURE' GROUP BY num_fiche");
                pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
                 jTable9.setModel(DbUtils.resultSetToTableModel(rs));
    }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
 
  
 
 try{
		String sql=("SELECT num_fiche as FACTURE,NAME FROM RECU WHERE PROJET='"+journal1.buss.getText()+"'  and TRANSACTION='PRO FORMAT' GROUP BY num_fiche");
                pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
                 jTable10.setModel(DbUtils.resultSetToTableModel(rs));
    }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  try{
          String sql="SELECT * FROM currency";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("APPR");
             
               jComboBox1.addItem(sums);
               jComboBox2.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }

  try{
		String sql=("SELECT num_fiche as FACTURE,NAME FROM RECU WHERE PROJET='"+journal1.buss.getText()+"'  AND TRANSACTION='FACTURE' GROUP BY num_fiche");
                pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
                 jTable12.setModel(DbUtils.resultSetToTableModel(rs));
    }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
  
   try{
            String sql=("SELECT num_fiche as Numéro,name as 'Client/Four' FROM recu WHERE transaction='Bon de Livraison' and projet='"+journal1.buss.getText()+"' group by num_fiche");
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();
            jTable13.setModel(DbUtils.resultSetToTableModel(rs));
            TableColumn col0= jTable13.getColumnModel().getColumn(0);
            TableColumn col1= jTable13.getColumnModel().getColumn(1);

            col0.setPreferredWidth(55);
            col1.setPreferredWidth(100);

        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }

}
public void selectontable_vide(){
    
//        TableModel model1 =doc.getModel();
        int indexs[]={1};
        
        Object[] row = new Object[4];
        DefaultTableModel model2 = (DefaultTableModel) jTable6.getModel();
        for(int i=0; i < indexs.length;i++){
        row[0]= "";
        row[1]= "0";
        row[2]= "0";
        row[3]= "0";
//        row[2]= model1.getValueAt(indexs[i],2);
//        row[3]= model1.getValueAt(indexs[i],3);
        model2.addRow(row);
        
        // jTable3.setModel(DbUtils.resultSetToTableModel(rs));
      
       
       
        TableColumn col0=jTable6.getColumnModel().getColumn(0);
        TableColumn col1=jTable6.getColumnModel().getColumn(1);
        TableColumn col2=jTable6.getColumnModel().getColumn(2);
       TableColumn col3=jTable6.getColumnModel().getColumn(3);
       // TableColumn col4=jTable1.getColumnModel().getColumn(4);
           
        col0.setPreferredWidth(500);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
      // col4.setPreferredWidth(50);
//        
        }
        
    }

   public void selectontable(){
    
        TableModel model1 =jTable5.getModel();
        int indexs[]=jTable5.getSelectedRows();
        Object[] row = new Object[4];
        DefaultTableModel model2 = (DefaultTableModel) jTable6.getModel();
        for(int i=0; i < indexs.length;i++){
        row[0]= model1.getValueAt(indexs[i],0);
        row[1]= "1";
        row[2]= "0";
        row[3]= "Pcs";
//        row[2]= model1.getValueAt(indexs[i],2);
//        row[3]= model1.getValueAt(indexs[i],3);
        model2.addRow(row);
        
       
         TableColumn col0=jTable6.getColumnModel().getColumn(0);
        TableColumn col1=jTable6.getColumnModel().getColumn(1);
        TableColumn col2=jTable6.getColumnModel().getColumn(2);
       TableColumn col3=jTable6.getColumnModel().getColumn(3);
       // TableColumn col4=jTable1.getColumnModel().getColumn(4);
           
        col0.setPreferredWidth(500);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
        }
        
    }
   
       public void selectontablemoin(){
     
       
        int indexs[]=jTable6.getSelectedRows();
        
        DefaultTableModel model = (DefaultTableModel) jTable6.getModel();
        for(int i=0; i < indexs.length;i++){
          
    int getSelectedRowsForDeletion = jTable6.getSelectedRow();
        model.removeRow(getSelectedRowsForDeletion);
        
        }
        
    }
       public void calculateraw(){
       int sum=0;
       for(int i=0;i<jTable6.getRowCount();i++){
       sum=sum+Integer.parseInt(jTable6.getValueAt(1,3).toString());
       }
       //jTextField1.setText(Integer.toString(sum));
       }
        public void Rolls(){
          try{
                  SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM");
         String addDateS = dateFormatsS.format(journal1.jDateChooser1.getDate());
         
         String sql="SELECT num_fiche FROM FACTURE where projet='"+journal1.buss.getText()+"' and transaction='Facture' ORDER BY num_fiche DESC LIMIT 1";
		   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("num_fiche");
                 int ln=rl.length();
                 String stxt=rl.substring(0,8);
                 String snum_fiche=rl.substring(8,ln);
                 int n = Integer.parseInt(snum_fiche);
                 n++;
                     snum_fiche=Integer.toString(n);
                 if(n < 10){
                 rolls=stxt+"00"+snum_fiche;//001 ou 009
                 }else if(n < 100){
                 rolls=stxt+"0"+snum_fiche;//010 ou 099
                 }else if(n > 100){
                 rolls=stxt+snum_fiche;// 100 ou 199
                 }  else if(n > 1000){
                 rolls=stxt+snum_fiche;// 1000 ou 1999
                 }
             }else{
               rolls= ""+addDateS+"-001";
                
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }
          num.setText(rolls);
         }
       public void save(){
  Rolls();
  int rows=jTable6.getRowCount();

  

  for(int row = 0; row<rows; row++)
  {   
  
    String libelle;
  //  ReplaceString = libelle.replace("'", "''");
    String Docc = (String)jTable6.getValueAt(row, 0);
    String Qty = (String)jTable6.getValueAt(row, 1);
    String Up = (String)jTable6.getValueAt(row, 2);
    String Unite = (String)jTable6.getValueAt(row, 3);
   libelle = Docc.replace("'", "''");
   Double qty= Double.parseDouble(Qty);
   Double up= Double.parseDouble(Up);
   Double pt=qty*up;
   
  // Pts = Ptss.replace("'", "''");
 try {
         //                                                               `DESCR`, `QTY`, `PU`, `PT`, `NUM`, `NAME`, `LETTRE`, `DATE`, `TRANSACTION`, `NAME_TO`, `MONAIS`, `PROJET`, `NUM_FICHE`   
        PreparedStatement pst = con.prepareStatement("INSERT INTO `recu`( `DESCR`, `QTY`, `PU`, `PT`, `NUM`, `NAME`, `LETTRE`, `DATE`, `TRANSACTION`, `NAME_TO`, `MONAIS`, `PROJET`, `NUM_FICHE`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?)");
 //`DESCR`, `QTY`, `PU`, `PT`, `NUM`, `NAME`, `LETTRE`, `DATE`, `TRANSACTION`, `NAME_TO`, `MONAIS`, `PROJET`, `NUM_FICHE`          
        pst.setString(1,libelle);
         pst.setDouble(2,qty);
       pst.setDouble(3,up);
         pst.setDouble(4,pt);
         pst.setString(5,rolls);
         pst.setString(6,client.getText());
         pst.setString(7,"LETTRE");
        pst.setString(8,journal1.jDateChooser1.getText());
        pst.setString(9,"Facture");
        pst.setString(10,rolls);
        pst.setString(11,jComboBox1.getSelectedItem().toString());
        pst.setString(12,journal1.buss.getText());
        pst.setString(13,num.getText());
         
          pst.executeUpdate();
        
              //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    } 

  }
        JOptionPane.showMessageDialog(null,"Transaction Saved");
        
       }
       
        public void report()
                           
     {
    // Rolls();
         String PrintName=null;
          try{
          String sql="SELECT * FROM new_buss WHERE DENO1='"+journal1.buss.getText()+"'";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                PrintName=rs.getString("FACTURE");
             
              
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
             try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+PrintName+".jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                String sql="Select * from recu INNER JOIN new_buss ON facture.startup=new_buss.deno1 where roll='"+num.getText()+"' and buss='"+journal1.buss.getText()+"' and startup='"+journal1.buss.getText()+"'";

                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
              JRViewer m= new JRViewer(jp);
              
        print_show ms= new  print_show();
        ms.show();
        ms. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
   print_show.boody.removeAll();
     print_show.boody.setLayout(new BorderLayout());
     print_show.boody.add(m);
     
            }
                 
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
     
           
     }
         public void report_PRO()
                           
     {
    // Rolls();
           String PrintName=null,Fact=null;
          try{
          String sql="SELECT * FROM new_buss WHERE DENO1='"+journal1.buss.getText()+"'";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                Fact=rs.getString("FACTURE");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    }
         switch (Fact) {
             case "facture":
                 PrintName="facture_1";
                 break;
             case "facture_2":
                 PrintName="proforma_2";
                 break;  
             default:
                 PrintName="proforma_2_1";
                 break;
         }


             try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+PrintName+".jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
             // String sql="Select * from ohada_trans INNER JOIN projet ON ohada_trans.BUSS=projet.PROJET_num_fiche where buss='"+tmp+" ' and DATES BETWEEN '"+date1+"' AND '"+date2+"'AND CODE ='"+journal_caisse.jComboBox1.getSelectedItem()+"'  order by DATES,ohada_trans.num_fiche";
              String sql="Select * from facture INNER JOIN new_buss ON facture.startup=new_buss.deno1 where roll='"+num.getText()+"' and buss='"+journal1.buss.getText()+"' and startup='"+journal1.buss.getText()+"' and docs='PRO FORMAT'";
//      HashMap param= new HashMap();
//    param.put("date1", jDateChooser1.getText());
//     param.put("date2", jDateChooser2.getText());
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
              JRViewer m= new JRViewer(jp);
              
        print_show ms= new  print_show();
        ms.show();
        ms. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
   print_show.boody.removeAll();
     print_show.boody.setLayout(new BorderLayout());
     print_show.boody.add(m);
     
            }
                 
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
     
           
     }
         
     public void report_LIVRAISON()
                           
     {
    // Rolls();
        /*   String PrintName=null,Fact=null;
          try{
          String sql="SELECT * FROM new_buss WHERE DENO1='"+journal1.buss.getText()+"'";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                Fact=rs.getString("FACTURE");
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    }
         switch (Fact) {
             case "facture":
                 PrintName="facture_1";
                 break;
             case "facture_2":
                 PrintName="proforma_2";
                 break;  
             default:
                 PrintName="proforma_2_1";
                 break;
         }

*/
             try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"docs.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
             // String sql="Select * from ohada_trans INNER JOIN projet ON ohada_trans.BUSS=projet.PROJET_num_fiche where buss='"+tmp+" ' and DATES BETWEEN '"+date1+"' AND '"+date2+"'AND CODE ='"+journal_caisse.jComboBox1.getSelectedItem()+"'  order by DATES,ohada_trans.num_fiche";
              String sql="Select * from facture INNER JOIN new_buss ON facture.startup=new_buss.deno1 where roll='"+num.getText()+"' and buss='"+journal1.buss.getText()+"' and startup='"+journal1.buss.getText()+"' and docs='Bon de Livraison'";
//      HashMap param= new HashMap();
//    param.put("date1", jDateChooser1.getText());
//     param.put("date2", jDateChooser2.getText());
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
              JRViewer m= new JRViewer(jp);
              
        print_show ms= new  print_show();
        ms.show();
        ms. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
   print_show.boody.removeAll();
     print_show.boody.setLayout(new BorderLayout());
     print_show.boody.add(m);
     
            }
                 
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
     
           
     }     
        
             public void clic_attCall_IN_LIST()
    { 
   int row= jTable7.getSelectedRow();
          String Table_click = (jTable7.getModel().getValueAt(row,0). toString());
 
        try{
          String sql="SELECT LIBELLE,QTY,UP,UNITE FROM FACTURE where num_fiche ='"+Table_click+"' and buss='"+journal1.buss.getText()+"' and startup='"+journal1.buss.getText()+"' AND transaction='FACTURE'";
       pst = con.prepareStatement(sql);          rs=pst.executeQuery();
           jTable6.setModel(DbUtils.resultSetToTableModel(rs));
           
           
         TableColumn col0=jTable6.getColumnModel().getColumn(0);
        TableColumn col1=jTable6.getColumnModel().getColumn(1);
        TableColumn col2=jTable6.getColumnModel().getColumn(2);
       TableColumn col3=jTable6.getColumnModel().getColumn(3);
       // TableColumn col4=jTable1.getColumnModel().getColumn(4);
           
        col0.setPreferredWidth(500);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        try{
          String sql="SELECT CLIENT,num_fiche,DEVICE,SUM(PT) FROM FACTURE where num_fiche ='"+Table_click+"' and buss='"+journal1.buss.getText()+"' and startup='"+journal1.buss.getText()+"' AND transaction='FACTURE'";
        pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            while(rs.next()){
                
               String rolll=rs.getString("CLIENT");
           client.setText(rolll);
               
               String clients=rs.getString("roll");
              num.setText(clients);
              
              String clientsA=rs.getString("SUM(PT)");
              jLabel5.setText(clientsA);
              
              String clientsS=rs.getString("DEVICE");
              jLabel6.setText(clientsS);
              
              jComboBox1.setSelectedItem(clientsS);
               
          }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }  
                    public void clic_attCall_IN_LISTS()
    { 
   int row= jTable10.getSelectedRow();
          String Table_click = (jTable10.getModel().getValueAt(row,0). toString());
 
        try{
          String sql="SELECT LIBELLE,QTY,UP,PT FROM FACTURE where num_fiche ='"+Table_click+"' and buss='"+journal1.buss.getText()+"' and startup='"+journal1.buss.getText()+"'";
       pst = con.prepareStatement(sql);          rs=pst.executeQuery();
           jTable11.setModel(DbUtils.resultSetToTableModel(rs));
           
           
         TableColumn col0=jTable11.getColumnModel().getColumn(0);
        TableColumn col1=jTable11.getColumnModel().getColumn(1);
        TableColumn col2=jTable11.getColumnModel().getColumn(2);
       TableColumn col3=jTable11.getColumnModel().getColumn(3);
       // TableColumn col4=jTable1.getColumnModel().getColumn(4);
           
        col0.setPreferredWidth(500);
       col1.setPreferredWidth(50);
       col2.setPreferredWidth(50);
       col3.setPreferredWidth(50);
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
        try{
          String sql="SELECT CLIENT,num_fiche,DEVICE,SUM(PT) FROM FACTURE where num_fiche ='"+Table_click+"' and buss='"+journal1.buss.getText()+"' and startup='"+journal1.buss.getText()+"' AND transaction='PRO FORMAT'";
        pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            while(rs.next()){
                
               String rolll=rs.getString("CLIENT");
           client1.setText(rolll);
               
               String clients=rs.getString("roll");
              num.setText(clients);
              
//              String clientsA=rs.getString("SUM(PT)");
//              jLabel.setText(clientsA);
              
              String clientsS=rs.getString("DEVICE");
              jLabel6.setText(clientsS);
              
              jComboBox1.setSelectedItem(clientsS);
               
          }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }  
             
                 public void Rollss(){
          try{
                  SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM");
         String addDateS = dateFormatsS.format(journal1.jDateChooser1.getDate());
         
         String sql="SELECT num_fiche FROM RECUS where STARTUP='"+journal1.buss.getText()+"' where transaction='Recus' ORDER BY num_fiche DESC LIMIT 1";
		   pst = con.prepareStatement(sql);          rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("num_fiche");
                 int ln=rl.length();
                 String stxt=rl.substring(0,8);
                 String snum_fiche=rl.substring(8,ln);
                 int n = Integer.parseInt(snum_fiche);
                 n++;
                     snum_fiche=Integer.toString(n);
                 if(n < 10){
                 rolls=stxt+"00"+snum_fiche;//001 ou 009
                 }else if(n < 100){
                 rolls=stxt+"0"+snum_fiche;//010 ou 099
                 }else if(n > 100){
                 rolls=stxt+snum_fiche;// 100 ou 199
                 }  else if(n > 1000){
                 rolls=stxt+snum_fiche;// 1000 ou 1999
                 }
             }else{
               rolls= ""+addDateS+"-001";
                
             }
         }catch(NumberFormatException | SQLException e){
             JOptionPane.showMessageDialog(null, e);  
         }
          num.setText(rolls);
         } 
                 
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jButton19 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable14 = new javax.swing.JTable();
        jButton20 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTable15 = new javax.swing.JTable();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTable16 = new javax.swing.JTable();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTable17 = new javax.swing.JTable();
        jButton21 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTable18 = new javax.swing.JTable();
        jPanel31 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTable19 = new javax.swing.JTable();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTable20 = new javax.swing.JTable();
        jButton22 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        jLabel7.setText("jLabel7");

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseClicked(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jTable5.setGridColor(new java.awt.Color(204, 204, 204));
        jTable5.setRowHeight(30);
        jTable5.setShowHorizontalLines(true);
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Ecritures", jPanel2);

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable7.setGridColor(new java.awt.Color(204, 204, 204));
        jTable7.setRowHeight(30);
        jTable7.setShowHorizontalLines(true);
        jTable7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable7MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable7);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Base de donnée", jPanel3);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(">>");
        jLabel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("<<");
        jLabel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("LN >>");
        jLabel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        client.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        client.setText("Client:");
        client.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......." }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Libelle", "Qty", "Up", "Utes"
            }
        ));
        jTable6.setGridColor(new java.awt.Color(204, 204, 204));
        jTable6.setRowHeight(30);
        jTable6.setShowHorizontalLines(true);
        jTable6.setShowVerticalLines(true);
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable6);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("Recus");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Device");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("PT");

        num.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        num.setText("Num:");

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(242, 242, 241));
        jTextField5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText("Factures/Recus");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(client)
                                .addGap(0, 0, 0)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addComponent(num)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(314, 314, 314)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
            .addComponent(jTextField5)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(client)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1)
                                .addComponent(jButton2))
                            .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Factures/Recus", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "......"
            }
        ));
        jTable9.setGridColor(new java.awt.Color(204, 204, 204));
        jTable9.setRowHeight(30);
        jTable9.setShowHorizontalLines(true);
        jTable9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable9MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jTable9);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("", jPanel11);

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable10.setGridColor(new java.awt.Color(204, 204, 204));
        jTable10.setRowHeight(30);
        jTable10.setShowHorizontalLines(true);
        jTable10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable10MouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(jTable10);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Base de donnée", jPanel22);

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......." }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        client1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        client1.setText("Client:");
        client1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Libelle", "Qty", "Up", "Utes"
            }
        ));
        jTable11.setGridColor(new java.awt.Color(204, 204, 204));
        jTable11.setRowHeight(30);
        jTable11.setShowHorizontalLines(true);
        jTable11.setShowVerticalLines(true);
        jTable11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable11MouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(jTable11);

        jButton19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton19.setText("Save");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        num1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        num1.setText("Num:");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("LN >>");
        jLabel33.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("<<");
        jLabel34.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
        });

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(242, 242, 241));
        jTextField4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText("Facture Proformat");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(num1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton19))
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(client1)
                        .addGap(0, 0, 0)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jTextField4)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(client1))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton19)
                            .addComponent(num1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTabbedPane3))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Facture Proformat", jPanel5);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        jTable12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "......"
            }
        ));
        jTable12.setGridColor(new java.awt.Color(204, 204, 204));
        jTable12.setRowHeight(30);
        jTable12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable12MouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(jTable12);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("", jPanel24);

        jTable13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable13.setGridColor(new java.awt.Color(204, 204, 204));
        jTable13.setRowHeight(30);
        jTable13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable13MouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(jTable13);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("Base de donnée", jPanel25);

        jComboBox3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......." }));
        jComboBox3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        client2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        client2.setText("Client:");
        client2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable14.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Libelle", "Qty", "Up", "Utes"
            }
        ));
        jTable14.setGridColor(new java.awt.Color(204, 204, 204));
        jTable14.setRowHeight(30);
        jTable14.setShowHorizontalLines(true);
        jTable14.setShowVerticalLines(true);
        jTable14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable14MouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(jTable14);

        jButton20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton20.setText("Save");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        num2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        num2.setText("Num:");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("LN >>");
        jLabel35.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("<<");
        jLabel36.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(num2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton20))
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(client2)
                        .addGap(0, 0, 0)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(client2)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton20)
                            .addComponent(num2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTabbedPane4))
                .addContainerGap())
        );

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(242, 242, 241));
        jTextField3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("Bon de livraison");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextField3)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Bon de Livraison ", jPanel19);

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));

        jTable15.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "......"
            }
        ));
        jTable15.setGridColor(new java.awt.Color(204, 204, 204));
        jTable15.setRowHeight(30);
        jTable15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable15MouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(jTable15);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("", jPanel27);

        jTable16.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable16.setGridColor(new java.awt.Color(204, 204, 204));
        jTable16.setRowHeight(30);
        jTable16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable16MouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(jTable16);

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Base de donnée", jPanel28);

        jComboBox4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......." }));
        jComboBox4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        client3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        client3.setText("Client:");
        client3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable17.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Libelle", "Qty", "Up", "Utes"
            }
        ));
        jTable17.setGridColor(new java.awt.Color(204, 204, 204));
        jTable17.setRowHeight(30);
        jTable17.setShowHorizontalLines(true);
        jTable17.setShowVerticalLines(true);
        jTable17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable17MouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(jTable17);

        jButton21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton21.setText("Save");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        num3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        num3.setText("Num:");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("LN >>");
        jLabel37.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("<<");
        jLabel38.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel38MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(num3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton21))
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(client3)
                        .addGap(0, 0, 0)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(client3)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton21)
                            .addComponent(num3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTabbedPane5))
                .addContainerGap())
        );

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(242, 242, 241));
        jTextField2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("Bon de Réception");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextField2)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Bon de Réception", jPanel20);

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));

        jTable18.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "......"
            }
        ));
        jTable18.setGridColor(new java.awt.Color(204, 204, 204));
        jTable18.setRowHeight(30);
        jTable18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable18MouseClicked(evt);
            }
        });
        jScrollPane20.setViewportView(jTable18);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );

        jTabbedPane6.addTab("", jPanel30);

        jTable19.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable19.setGridColor(new java.awt.Color(204, 204, 204));
        jTable19.setRowHeight(30);
        jTable19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable19MouseClicked(evt);
            }
        });
        jScrollPane21.setViewportView(jTable19);

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );

        jTabbedPane6.addTab("Base de donnée", jPanel31);

        jComboBox5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......." }));
        jComboBox5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        client4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        client4.setText("Client:");
        client4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable20.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Libelle", "Qty", "Up", "Utes"
            }
        ));
        jTable20.setGridColor(new java.awt.Color(204, 204, 204));
        jTable20.setRowHeight(30);
        jTable20.setShowHorizontalLines(true);
        jTable20.setShowVerticalLines(true);
        jTable20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable20MouseClicked(evt);
            }
        });
        jScrollPane22.setViewportView(jTable20);

        jButton22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton22.setText("Save");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        num4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        num4.setText("Num:");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("LN >>");
        jLabel39.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel39MouseClicked(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("<<");
        jLabel40.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(num4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton22))
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(client4)
                        .addGap(0, 0, 0)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(client4)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton22)
                            .addComponent(num4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTabbedPane6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(242, 242, 241));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Bon de commande ");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextField1)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Bon de commande ", jPanel18);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel38MouseClicked

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel37MouseClicked

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jTable17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable17MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable17MouseClicked

    private void jTable16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable16MouseClicked

    private void jTable15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable15MouseClicked

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
 int indexs[]=jTable14.getSelectedRows();

        DefaultTableModel model = (DefaultTableModel) jTable14.getModel();
        for(int i=0; i < indexs.length;i++){

            int getSelectedRowsForDeletion = jTable14.getSelectedRow();
            model.removeRow(getSelectedRowsForDeletion);

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel36MouseClicked

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
 if(journal1.buss.getText().equals("Start Up") || client2.getText().equals("") || client2.getText().equals("Client:")){
            JOptionPane.showMessageDialog(null,"Unknown Start up  or Client","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            if(num.getText().equals("Num:")){
                try{
                    SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM");
                    String addDateS = dateFormatsS.format(journal1.jDateChooser1.getDate());

                    String sql="SELECT num_fiche FROM FACTURE where BUSS='"+journal1.buss.getText()+"' and STARTUP='"+journal1.buss.getText()+"' and transaction='Bon de Livraison' ORDER BY num_fiche DESC LIMIT 1";
                    pst = con.prepareStatement(sql);          rs=pst.executeQuery();
                    if(rs.next()){
                        String rl=rs.getString("num_fiche");
                        int ln=rl.length();
                        String stxt=rl.substring(0,8);
                        String snum_fiche=rl.substring(8,ln);
                        int n = Integer.parseInt(snum_fiche);
                        n++;
                        snum_fiche=Integer.toString(n);
                        if(n < 10){
                            rolls=stxt+"00"+snum_fiche;//001 ou 009
                        }else if(n < 100){
                            rolls=stxt+"0"+snum_fiche;//010 ou 099
                        }else if(n > 100){
                            rolls=stxt+snum_fiche;// 100 ou 199
                        }  else if(n > 1000){
                            rolls=stxt+snum_fiche;// 1000 ou 1999
                        }
                    }else{
                        rolls= ""+addDateS+"-001";

                    }
                }catch(NumberFormatException | SQLException e){
                    JOptionPane.showMessageDialog(null, e);
                }
                num.setText(rolls);
                int rows=jTable14.getRowCount();

                for(int row = 0; row<rows; row++)
                {

                    String libelle;
                    //  ReplaceString = libelle.replace("'", "''");
                    String Docc = (String)jTable14.getValueAt(row, 0);
                    String Qty = (String)jTable14.getValueAt(row, 1);
                    String Up = (String)jTable14.getValueAt(row, 2);
                    String Unite = (String)jTable14.getValueAt(row, 3);
                    libelle = Docc.replace("'", "''");
                    Double qty= Double.parseDouble(Qty);
                    Double up= Double.parseDouble(Up);
                    Double pt=qty*up;

                    // Pts = Ptss.replace("'", "''");
                    try {

                        PreparedStatement pst = con.prepareStatement("INSERT INTO `FACTURE`( `LIBELLE`, `QTY`, `UP`, `PT`, `num_fiche`, `BUSS`,`STARTUP`,`CLIENT`,`DEVICE`,DATE,transaction,UNITE)"
                            +"value(?,?,?,?,?,?,?,?,?,?,?,?)");

                        pst.setString(1,libelle);
                        pst.setDouble(2,qty);
                        pst.setDouble(3,up);
                        pst.setDouble(4,pt);
                        pst.setString(5,rolls);
                        pst.setString(6,journal1.buss.getText());
                        pst.setString(7,journal1.buss.getText());
                        pst.setString(8,client2.getText());
                        pst.setString(9,jComboBox3.getSelectedItem().toString());
                        pst.setString(10,journal1.jDateChooser1.getText());
                        pst.setString(11,"Bon de Livraison");
                        pst.setString(12,Unite);

                        pst.executeUpdate();

                        //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }

                }
                JOptionPane.showMessageDialog(null,"Transaction Saved");
                report_LIVRAISON();
             //   here
                call();
            }else{

                report_LIVRAISON();
                call();
            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jTable14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable14MouseClicked

    private void jTable13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable13MouseClicked
  int row= jTable13.getSelectedRow();
        String Table_click = (jTable13.getModel().getValueAt(row,0). toString());
        try{
            String sql="SELECT CLIENT,num_fiche FROM FACTURE where num_fiche ='"+Table_click+"' and buss='"+journal1.buss.getText()+"' and startup='"+journal1.buss.getText()+"'";
            pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            while(rs.next()){

                String rolll=rs.getString("CLIENT");
                client2.setText(rolll);
                String rolllS=rs.getString("num_fiche");
                num.setText(rolllS);

            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        try{
            String sqls="SELECT `LIBELLE`, `QTY`, `UP`,`PT`,`UNITE` FROM facture where num_fiche='"+Table_click+"' and BUSS='"+journal1.buss.getText()+"' and STARTUP='"+journal1.buss.getText()+"' and docs='Bon de Livraison'";
            pst = con.prepareStatement(sqls);
            rs= pst.executeQuery();

            jTable14.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
            TableColumn col0= jTable14.getColumnModel().getColumn(0);
            TableColumn col1= jTable14.getColumnModel().getColumn(1);
            jTable14.getColumnModel().getColumn(1).setCellRenderer(centre);
            TableColumn col2=jTable14.getColumnModel().getColumn(2);
            jTable14.getColumnModel().getColumn(2).setCellRenderer(centre);
            TableColumn col3= jTable14.getColumnModel().getColumn(3);
            jTable14.getColumnModel().getColumn(3).setCellRenderer(centre);
            TableColumn col4= jTable14.getColumnModel().getColumn(4);
            jTable14.getColumnModel().getColumn(4).setCellRenderer(centre);

            col0.setPreferredWidth(500);
            col1.setPreferredWidth(50);
            col2.setPreferredWidth(50);
            col3.setPreferredWidth(50);
            col4.setPreferredWidth(50);

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);}           // TODO add your handling code here:
    }//GEN-LAST:event_jTable13MouseClicked

    private void jTable12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable12MouseClicked
   int row= jTable12.getSelectedRow();
        String Table_click = (jTable12.getModel().getValueAt(row,0). toString());

        try{
            String sql="SELECT LIBELLE,QTY,UP,UNITE FROM FACTURE where num_fiche ='"+Table_click+"' and buss='"+journal1.buss.getText()+"' and startup='"+journal1.buss.getText()+"' AND transaction='FACTURE'";
            pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            jTable14.setModel(DbUtils.resultSetToTableModel(rs));

            TableColumn col0=jTable14.getColumnModel().getColumn(0);
            TableColumn col1=jTable14.getColumnModel().getColumn(1);
            TableColumn col2=jTable14.getColumnModel().getColumn(2);
            TableColumn col3=jTable14.getColumnModel().getColumn(3);
            // TableColumn col4=jTable1.getColumnModel().getColumn(4);

            col0.setPreferredWidth(500);
            col1.setPreferredWidth(50);
            col2.setPreferredWidth(50);
            col3.setPreferredWidth(50);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }              // TODO add your handling code here:
    }//GEN-LAST:event_jTable12MouseClicked

    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel40MouseClicked

    private void jLabel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel39MouseClicked

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jTable20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable20MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable20MouseClicked

    private void jTable19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable19MouseClicked

    private void jTable18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable18MouseClicked

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked

        int indexs[]=jTable11.getSelectedRows();

        DefaultTableModel model = (DefaultTableModel) jTable11.getModel();
        for(int i=0; i < indexs.length;i++){

            int getSelectedRowsForDeletion = jTable11.getSelectedRow();
            model.removeRow(getSelectedRowsForDeletion);

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel34MouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        int indexs[]={1};

        Object[] row = new Object[4];
        DefaultTableModel model2 = (DefaultTableModel) jTable11.getModel();
        for(int i=0; i < indexs.length;i++){
            row[0]= "";
            row[1]= "0";
            row[2]= "0";
            row[3]= "0";
            //        row[2]= model1.getValueAt(indexs[i],2);
            //        row[3]= model1.getValueAt(indexs[i],3);
            model2.addRow(row);

            // jTable3.setModel(DbUtils.resultSetToTableModel(rs));

            TableColumn col0=jTable11.getColumnModel().getColumn(0);
            TableColumn col1=jTable11.getColumnModel().getColumn(1);
            TableColumn col2=jTable11.getColumnModel().getColumn(2);
            TableColumn col3=jTable11.getColumnModel().getColumn(3);
            // TableColumn col4=jTable1.getColumnModel().getColumn(4);

            col0.setPreferredWidth(500);
            col1.setPreferredWidth(50);
            col2.setPreferredWidth(50);
            col3.setPreferredWidth(50);
            // col4.setPreferredWidth(50);
            //
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel33MouseClicked

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        if(journal1.buss.getText().equals("Start Up") || client1.getText().equals("") || client1.getText().equals("Client:")){
            JOptionPane.showMessageDialog(null,"Unknown Start up  or Client","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            if(num.getText().equals("Num:")){
                try{
                    SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM");
                    String addDateS = dateFormatsS.format(journal1.jDateChooser1.getDate());

                    String sql="SELECT num_fiche FROM FACTURE where BUSS='"+journal1.buss.getText()+"' and STARTUP='"+journal1.buss.getText()+"' and transaction='PRO FORMAT' ORDER BY num_fiche DESC LIMIT 1";
                    pst = con.prepareStatement(sql);          rs=pst.executeQuery();
                    if(rs.next()){
                        String rl=rs.getString("num_fiche");
                        int ln=rl.length();
                        String stxt=rl.substring(0,8);
                        String snum_fiche=rl.substring(8,ln);
                        int n = Integer.parseInt(snum_fiche);
                        n++;
                        snum_fiche=Integer.toString(n);
                        if(n < 10){
                            rolls=stxt+"00"+snum_fiche;//001 ou 009
                        }else if(n < 100){
                            rolls=stxt+"0"+snum_fiche;//010 ou 099
                        }else if(n > 100){
                            rolls=stxt+snum_fiche;// 100 ou 199
                        }  else if(n > 1000){
                            rolls=stxt+snum_fiche;// 1000 ou 1999
                        }
                    }else{
                        rolls= ""+addDateS+"-001";

                    }
                }catch(NumberFormatException | SQLException e){
                    JOptionPane.showMessageDialog(null, e);
                }
                num.setText(rolls);
                int rows=jTable11.getRowCount();

                for(int row = 0; row<rows; row++)
                {

                    String libelle;
                    //  ReplaceString = libelle.replace("'", "''");
                    String Docc = (String)jTable11.getValueAt(row, 0);
                    String Qty = (String)jTable11.getValueAt(row, 1);
                    String Up = (String)jTable11.getValueAt(row, 2);
                    String Unite = (String)jTable11.getValueAt(row, 3);
                    libelle = Docc.replace("'", "''");
                    Double qty= Double.parseDouble(Qty);
                    Double up= Double.parseDouble(Up);
                    Double pt=qty*up;

                    // Pts = Ptss.replace("'", "''");
                    try {

                        PreparedStatement pst = con.prepareStatement("INSERT INTO `FACTURE`( `LIBELLE`, `QTY`, `UP`, `PT`, `num_fiche`, `BUSS`,`STARTUP`,`CLIENT`,`DEVICE`,DATE,transaction,UNITE)"
                            +"value(?,?,?,?,?,?,?,?,?,?,?,?)");

                        pst.setString(1,libelle);
                        pst.setDouble(2,qty);
                        pst.setDouble(3,up);
                        pst.setDouble(4,pt);
                        pst.setString(5,rolls);
                        pst.setString(6,journal1.buss.getText());
                        pst.setString(7,journal1.buss.getText());
                        pst.setString(8,client1.getText());
                        pst.setString(9,jComboBox2.getSelectedItem().toString());
                        pst.setString(10,journal1.jDateChooser1.getText());
                        pst.setString(11,"PRO FORMAT");
                        pst.setString(12,Unite);

                        pst.executeUpdate();

                        //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }

                }
                JOptionPane.showMessageDialog(null,"Transaction Saved");
                report_PRO();
                call();
            }else{

                report_PRO();
                call();
            }

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jTable11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable11MouseClicked

    private void jTable10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable10MouseClicked
        int row= jTable10.getSelectedRow();
        String Table_click = (jTable10.getModel().getValueAt(row,0). toString());

        try{
            String sql="SELECT LIBELLE,QTY,UP,UNITE FROM FACTURE where num_fiche ='"+Table_click+"' and buss='"+journal1.buss.getText()+"' and startup='"+journal1.buss.getText()+"' and transaction='PRO FORMAT'";
            pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            jTable11.setModel(DbUtils.resultSetToTableModel(rs));

            TableColumn col0=jTable11.getColumnModel().getColumn(0);
            TableColumn col1=jTable11.getColumnModel().getColumn(1);
            TableColumn col2=jTable11.getColumnModel().getColumn(2);
            TableColumn col3=jTable11.getColumnModel().getColumn(3);
            // TableColumn col4=jTable1.getColumnModel().getColumn(4);

            col0.setPreferredWidth(500);
            col1.setPreferredWidth(50);
            col2.setPreferredWidth(50);
            col3.setPreferredWidth(50);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        try{
            String sql="SELECT CLIENT,num_fiche,DEVICE,SUM(PT) FROM FACTURE where num_fiche ='"+Table_click+"' and buss='"+journal1.buss.getText()+"' and startup='"+journal1.buss.getText()+"' AND transaction='PRO FORMAT'";
            pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            while(rs.next()){

                String rolll=rs.getString("CLIENT");
                client1.setText(rolll);

                String clients=rs.getString("roll");
                num.setText(clients);

                //              String clientsA=rs.getString("SUM(PT)");
                //              jLabel5.setText(clientsA);
                //
                String clientsS=rs.getString("DEVICE");
                jLabel6.setText(clientsS);

                jComboBox2.setSelectedItem(clientsS);

            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTable10MouseClicked

    private void jTable9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable9MouseClicked
        int row= jTable9.getSelectedRow();
        String Table_click = (jTable9.getModel().getValueAt(row,0). toString());

        try{
            String sql="SELECT LIBELLE,QTY,UP,UNITE  FROM FACTURE where num_fiche ='"+Table_click+"' and buss='"+journal1.buss.getText()+"' and startup='"+journal1.buss.getText()+"' AND transaction='FACTURE'";
            pst = con.prepareStatement(sql);          rs=pst.executeQuery();
            jTable11.setModel(DbUtils.resultSetToTableModel(rs));

            TableColumn col0=jTable11.getColumnModel().getColumn(0);
            TableColumn col1=jTable11.getColumnModel().getColumn(1);
            TableColumn col2=jTable11.getColumnModel().getColumn(2);
            TableColumn col3=jTable11.getColumnModel().getColumn(3);
            // TableColumn col4=jTable1.getColumnModel().getColumn(4);

            col0.setPreferredWidth(500);
            col1.setPreferredWidth(50);
            col2.setPreferredWidth(50);
            col3.setPreferredWidth(50);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTable9MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     //   recu m= new  recu  ();
      //  m.show();
    //    m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(journal1.buss.getText().equals("Start Up") || client.getText().equals("") || client.getText().equals("Client:")){
            JOptionPane.showMessageDialog(null,"Unknown Start up  or Client","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            if(num.getText().equals("Num:")){
                save();
                report();
                call();
            }else{
                report();
                call();
            }

        }

        //calculateraw();//        savealltransactionS();
        //        save();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable6MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        selectontable_vide();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        selectontablemoin();       // selectontablemoin();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        selectontable();       // selectontable();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked
        clic_attCall_IN_LIST();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable7MouseClicked

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable5MouseClicked

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked
jTabbedPane2.addChangeListener(new ChangeListener() {
    public void stateChanged(ChangeEvent e) {
        System.out.println("Tab index: " + jTabbedPane2.getSelectedIndex());
    }
});        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JTextField client = new javax.swing.JTextField();
    public static final javax.swing.JTextField client1 = new javax.swing.JTextField();
    public static final javax.swing.JTextField client2 = new javax.swing.JTextField();
    public static final javax.swing.JTextField client3 = new javax.swing.JTextField();
    public static final javax.swing.JTextField client4 = new javax.swing.JTextField();
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    public static final javax.swing.JComboBox<String> jComboBox1 = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> jComboBox2 = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> jComboBox3 = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> jComboBox4 = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> jComboBox5 = new javax.swing.JComboBox<>();
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    public static final javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable12;
    private javax.swing.JTable jTable13;
    private javax.swing.JTable jTable14;
    private javax.swing.JTable jTable15;
    private javax.swing.JTable jTable16;
    private javax.swing.JTable jTable17;
    private javax.swing.JTable jTable18;
    private javax.swing.JTable jTable19;
    private javax.swing.JTable jTable20;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    public static final javax.swing.JLabel num = new javax.swing.JLabel();
    public static final javax.swing.JLabel num1 = new javax.swing.JLabel();
    public static final javax.swing.JLabel num2 = new javax.swing.JLabel();
    public static final javax.swing.JLabel num3 = new javax.swing.JLabel();
    public static final javax.swing.JLabel num4 = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}
