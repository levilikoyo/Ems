/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package journals;

import com.barcodelib.barcode.a.b.i;
import intreprisemanagementsystem.JavaDbConnect;
import intreprisemanagementsystem.JavaDbConnectonline;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Doshe PC
 */

public class synchronisation {
    
   
    Connection cononline=JavaDbConnectonline.dbConnect();
PreparedStatement pst=null;
ResultSet rs= null;
 DefaultTableModel mode;
 String rolls;  
 
 public void save(){
   DefaultTableModel excels= (DefaultTableModel)store_setting.jTable7.getModel(); 
    
        for(int i=0; i < excels.getRowCount();i++){
  String  Bin = excels.getValueAt(i,0).toString();
  String  Ref  = excels.getValueAt(i,1). toString();
  String Nom = excels.getValueAt(i,2). toString();
  String Qty = excels.getValueAt(i,4). toString();
  String Depot = excels.getValueAt(i,5). toString();

       
 try {
       String sqls="INSERT INTO stock_mvm (NUM_FICHE,RESP,CONTACT,BIN,REF,NOM,DEPOT,RANGER,ETAGERE,LIGNE,BINS,PROFONDEUR,QTY_D,QTY_C,NUM,FABRICANT,DATES,NOMS,RN,PR,PO,STOCK_SUB,STOCK_SUB_BIN,PARTCODE,GATEGORY,OLDNUM) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst = cononline.prepareStatement(sqls);  
        pst.setString(1, Bin);
        pst.setString(2,Ref);
         pst.setString(3,Nom);
         pst.setString(4,Bin);
         pst.setString(5,Ref);
         pst.setString(6,Nom);
         pst.setString(7,Depot);
         pst.setString(8,"");
         
         pst.setString(9,"");
         pst.setString(10,"");
         pst.setString(11,"");
         pst.setString(12,"");
         pst.setString(14,Qty);
         pst.setString(13,"0.00");
         pst.setString(15,"");
          pst.setString(16,"");
           pst.setString(17,"");
            pst.setString(18,Home_page.home.user.getText());
            pst.setString(19,"");
            pst.setString(20,"");
            pst.setString(21,"");
            pst.setString(22,Qty);
            pst.setString(23,""); 
            pst.setString(24,"");
            pst.setString(25,"");
            pst.setString(26,"");
         
         
          pst.executeUpdate();
    } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
 
        }
 JOptionPane.showMessageDialog(null, "saved");
 }
  

}  
             
 
  
  

    

