/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DOSHE
 */
public class regritement extends javax.swing.JInternalFrame {

    DefaultTableModel modes;
  DefaultTableModel mode;
  DefaultTableModel etmode;
  DefaultTableModel attmode;
 DefaultTableModel modetable3;
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String rolls;
String etrolls;
String salaryrolls;
    public regritement() {
        initComponents();
              con=JavaDbConnect.dbConnect();
//        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
//        BasicInternalFrameUI bui= (BasicInternalFrameUI) this.getUI();
//       bui.setNorthPane(null);
       
       call_in_table();
    }
String imgPath= "D:\\logos\\tetes.JPG"; 
     public void photophatom()     
     {
       images.setIcon(ResizeImage(imgPath,null));
     }
     
     public ImageIcon ResizeImage(String ImagePath, byte[] pic)
    {
        ImageIcon MyImage = null;
        if(ImagePath !=null)
        {
            MyImage = new ImageIcon(ImagePath);
        }else
        {
            MyImage = new ImageIcon (ImagePath);
        }
        Image img =MyImage.getImage();
        Image newImg= img.getScaledInstance(images.getWidth(),images.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    //BROUSWER
     
     public void broswer()
     {
        // this.setAlwaysOnTop(false);
          JFileChooser chooser = new JFileChooser();
       chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
       FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","gif","png");
       chooser.addChoosableFileFilter(filter);
       int result = chooser.showSaveDialog(null);
       if(result == JFileChooser.APPROVE_OPTION ){
           File selectedFile = chooser.getSelectedFile();
           String path = selectedFile.getAbsolutePath();
           images.setIcon(ResizeImage(path,null));
           imgPath = path;
           paths.setText(imgPath);
       }
       else if(result ==  JFileChooser.CANCEL_OPTION){
           System.out.println("No Fli Selected");
       }       
     }
     
     public void call_in_table(){
     
      try{
           
             String sql="SELECT `NAME`, `LNAME`, `ROLLNUM`, `TITRE`, `TEL`, `EMAIL`, `SUP`, `STATUT`, `DEPARTMENT` FROM employee ";
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
       col1.setPreferredWidth(150);
       col2.setPreferredWidth(80);
       col3.setPreferredWidth(100);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(100);
       col6.setPreferredWidth(50);
       col7.setPreferredWidth(50);
       col8.setPreferredWidth(50);
      
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     
     }
     
      public void call_in_tablesearch(){
     
      try{
           
             String sql="SELECT `NAME`, `LNAME`, `ROLLNUM`, `TITRE`, `TEL`, `EMAIL`, `SUP`, `STATUT`, `DEPARTMENT` FROM employee where "+jComboBox2.getSelectedItem()+" like '"+search.getText()+"%' ";
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
       col1.setPreferredWidth(150);
       col2.setPreferredWidth(80);
       col3.setPreferredWidth(100);
       col4.setPreferredWidth(50);
       col5.setPreferredWidth(100);
       col6.setPreferredWidth(50);
       col7.setPreferredWidth(50);
       col8.setPreferredWidth(50);
      
      
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
     
     }
      public void Refresh(){
      
       empname.setText("");
        emplname.setText("");
        emaddress.setText("");
        dob.setText("");
        rollnum.setText("");
        jobtitle.setText("");
        tel.setText("");
        mail.setText("");
        course.setText("");
        degree.setText("");
         
         //`STATUT`, `DEPARTEMENT`, `POB`
        nationalite.setText("");
        gender.setSelectedItem("");
        sup.setText("");
         
         
         
        pname.setText("");
        jobtitle.setText("");
        padresse.setText("");
        relation.setText("");
        ptel.setText("");
        pmail.setText("");
         
        contrattype.setText("");
        pob.setText("");
       departement.setText("");
       numid.setText("");
       rollnum.enable(true);
       paths.setText("");
         
         
      photophatom();
      
      }
      
      
       public void show_photo_from_db()
               
   {
       rollnum.enable(false);
       String Roll=null;
            try{
          int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,2). toString());
          String sql = "SELECT * FROM  employee WHERE ROLLNUM = '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
             Roll=rs.getString("ROLLNUM");
             rollnum.setText(Roll);
             numid.setText(Roll);
          
             String sum=rs.getString("NAME");
                 empname.setText(sum);
                 
                 String sum1=rs.getString("LNAME");
                 emplname.setText(sum1);
                 
                 String sum2=rs.getString("ADRESSE");
                 emaddress.setText(sum2);
                 
                 String sum3=rs.getString("DOB");
                 dob.setText(sum3);
                 
                 String sum4=rs.getString("POB");
                 pob.setText(sum4);
                 
                 String sum5=rs.getString("TITRE");
                jobtitle.setText(sum5);
                
                String sum55=rs.getString("TEL");
                tel.setText(sum55);
                 
                 String sum6=rs.getString("EMAIL");
                 mail.setText(sum6);
                 
                 String sum7=rs.getString("DEGREE");
                 degree.setText(sum7);
                 
                 String sum73=rs.getString("COURSE");
                 course.setText(sum73);
                 
                 String sum8=rs.getString("NATIONALITE");
                 nationalite.setText(sum8);
                 
                 String sum9=rs.getString("SUP");
                 sup.setText(sum9);
                 
                 String sum10=rs.getString("ETAT_CIVIL");
                 mariage.setSelectedItem(sum10);
                 
                 String sum11=rs.getString("NOMS");
                 pname.setText(sum11);
                 
                 String sum12=rs.getString("ADRESSE1");
                 padresse.setText(sum12);
                 
                 String sum13=rs.getString("RELATION");
                 relation.setText(sum13);
                 
                 
                 String sum14=rs.getString("TEL1");
                 ptel.setText(sum14);
                 
                 String sum15=rs.getString("EMAIL1");
                pmail.setText(sum15);
                 
                 String sum16=rs.getString("STATUT");
                 contrattype.setText(sum16);
                 
                  String sum166=rs.getString("DEPARTMENT");
                 departement.setText(sum166);
                 
                   String sum16S=rs.getString("GENDER");
                 gender.setSelectedItem(sum16S);
                 
                 String sum16SD=rs.getString("ETAT_CIVIL");
                 mariage.setSelectedItem(sum16SD);
                 
                
                 
                 
               
                 
                
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
      
       
       try {
                
            
              Statement st = con.createStatement();
              ResultSet rs = st.executeQuery("select * from employee where ROLLNUM= '"+Roll+"'");
              if(rs.next()){
                  byte[] img = rs.getBytes("PHOTOS");
                  ImageIcon image =new ImageIcon(img);
                  Image im = image.getImage();
                  Image myIm = im.getScaledInstance(images.getWidth(),images.getHeight(),Image.SCALE_SMOOTH);
                  ImageIcon newImage= new ImageIcon(myIm);
                  images.setIcon(newImage);
              }
                }
             catch (HeadlessException | SQLException ex) {
                
            } 
   }
       
       
         public void Count_emp(){
   
     try{
            String sqls="select Count(GENDER) from  Employee ";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("Count(GENDER)");
              rh.jLabel27.setText(add1);
            }
            }
        catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);}
     
      try{
            String sqls="select Count(GENDER) from  Employee where Gender='Male'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("Count(GENDER)");
              rh.jLabel28.setText(add1);
            }
            }
        catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);}
      
       try{
            String sqls="select Count(GENDER) from  Employee where Gender='Female'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("Count(GENDER)");
              rh.jLabel29.setText(add1);
            }
            }
        catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);}
    
    }

       
       public void save(){
           if(numid.getText().isEmpty()){
               saveS();
           }else{
               update();
           }
       Count_emp();
       call_in_table();
       Refresh();
       }
       
       public void deletes(){
       delete();
               Count_emp();
       call_in_table();
       Refresh();
       }
       
       public void saveS(){
       
         try {
         
        PreparedStatement pst = con.prepareStatement("INSERT INTO `employee`(`NAME`, `LNAME`, `ADRESSE`, `DOB`, `ROLLNUM`, `TITRE`, `TEL`, `EMAIL`, `COURSE`, `DEGREE`, `NATIONALITE`, `GENDER`, `SUP`, `PHOTOS`, `ETAT_CIVIL`, `NOMS`, `ADRESSE1`, `RELATION`, `TEL1`, `EMAIL1`,`STATUT`, `POB`,`DEPARTMENT`) "
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
        pst.setString(1, empname.getText());
         pst.setString(2, emplname.getText());
         pst.setString(3, emaddress.getText());
         pst.setString(4, dob.getText());
         pst.setString(5, rollnum.getText());
          pst.setString(6, jobtitle.getText());
         pst.setString(7, tel.getText());
         pst.setString(8, mail.getText());
         pst.setString(9,course.getText());
         pst.setString(10,degree.getText());
         
         //`STATUT`, `DEPARTEMENT`, `POB`
          pst.setString(11, nationalite.getText());
         pst.setString(12, gender.getSelectedItem().toString());
         pst.setString(13, sup.getText());
         
          InputStream images = new FileInputStream(new File(imgPath));
            pst.setBlob(14, images);
         
         pst.setString(15, mariage.getSelectedItem().toString());
          pst.setString(16, pname.getText());
         pst.setString(17, padresse.getText());
         pst.setString(18, relation.getText());
         pst.setString(19,ptel.getText());
         pst.setString(20,pmail.getText());
         
         pst.setString(21, contrattype.getText());
         pst.setString(22, pob.getText());
         pst.setString(23, departement.getText());
         
         
       
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
       
//            try {
//         
//        PreparedStatement pst = con.prepareStatement("INSERT INTO `active_employee`(`NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`) "
//        +"value(?,?,?,?,?,?,?,?,?,?,?)");
// //`NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `LOCK`      
//        pst.setString(1, empname.getText());
//         pst.setString(2, emplname.getText());
//          pst.setString(3, rollnum.getText());
//         pst.setString(4, emaddress.getText());
//        
//        
//        
//         pst.setString(5, tel.getText());
//         pst.setString(6, mail.getText());
//          pst.setString(7, gender.getSelectedItem().toString());
//            pst.setString(8, jobtitle.getText());
//            pst.setString(9, departement.getText());
//               InputStream images = new FileInputStream(new File(imgPath));
//            pst.setBlob(10, images);
//         pst.setString(11,""); 
//          pst.executeUpdate();
//        
//            //     JOptionPane.showMessageDialog(null,"Data Saved");
//    } catch (Exception ex) {
//         JOptionPane.showMessageDialog(null, ex.getMessage());
//    }
         
         
       }
       
       
        public void update(){
       
if(paths.getText().equals("")){

         try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE employee SET NAME=?,LNAME=?,ADRESSE=?,DOB=?,ROLLNUM=?,TITRE=?,TEL=?,EMAIL=?,COURSE=?,DEGREE=?,NATIONALITE=?,GENDER=?,SUP=?,ETAT_CIVIL=?,NOMS=?,ADRESSE1=?,RELATION=?,TEL1=?,EMAIL1=?,STATUT=?,POB=?,DEPARTMENT=? where ROLLNUM='"+numid.getText()+"'");
       
        pst.setString(1, empname.getText());
         pst.setString(2, emplname.getText());
         pst.setString(3, emaddress.getText());
         pst.setString(4, dob.getText());
         pst.setString(5, rollnum.getText());
          pst.setString(6, jobtitle.getText());
         pst.setString(7, tel.getText());
         pst.setString(8, mail.getText());
         pst.setString(9,course.getText());
         pst.setString(10,degree.getText());
         
         //`STATUT`, `DEPARTEMENT`, `POB`
          pst.setString(11, nationalite.getText());
         pst.setString(12, gender.getSelectedItem().toString());
         pst.setString(13, sup.getText());
         
//          InputStream images = new FileInputStream(new File(imgPath));
//            pst.setBlob(14, images);
         
         pst.setString(14, mariage.getSelectedItem().toString());
          pst.setString(15, pname.getText());
         pst.setString(16, padresse.getText());
         pst.setString(17, relation.getText());
         pst.setString(18,ptel.getText());
         pst.setString(19,pmail.getText());
         
         pst.setString(20, contrattype.getText());
         pst.setString(21, pob.getText());
         pst.setString(22, departement.getText());
        
         
       
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
}else{
         try {
         
        PreparedStatement pst = con.prepareStatement("UPDATE employee SET NAME=?,LNAME=?,ADRESSE=?,DOB=?,ROLLNUM=?,TITRE=?,TEL=?,EMAIL=?,COURSE=?,DEGREE=?,NATIONALITE=?,GENDER=?,SUP=?,PHOTOS=?,ETAT_CIVIL=?,NOMS=?,ADRESSE1=?,RELATION=?,TEL1=?,EMAIL1=?,STATUT=?,POB=?,DEPARTMENT=? where ROLLNUM='"+numid.getText()+"'");
       
        pst.setString(1, empname.getText());
         pst.setString(2, emplname.getText());
         pst.setString(3, emaddress.getText());
         pst.setString(4, dob.getText());
         pst.setString(5, rollnum.getText());
          pst.setString(6, jobtitle.getText());
         pst.setString(7, tel.getText());
         pst.setString(8, mail.getText());
         pst.setString(9,course.getText());
         pst.setString(10,degree.getText());
         
         //`STATUT`, `DEPARTEMENT`, `POB`
          pst.setString(11, nationalite.getText());
         pst.setString(12, gender.getSelectedItem().toString());
         pst.setString(13, sup.getText());
         
          InputStream images = new FileInputStream(new File(imgPath));
            pst.setBlob(14, images);
         
         pst.setString(15, mariage.getSelectedItem().toString());
          pst.setString(16, pname.getText());
         pst.setString(17, padresse.getText());
         pst.setString(18, relation.getText());
         pst.setString(19,ptel.getText());
         pst.setString(20,pmail.getText());
         
         pst.setString(21, contrattype.getText());
         pst.setString(22, pob.getText());
         pst.setString(23, departement.getText());
         
         
       
         
          pst.executeUpdate();
        
                 JOptionPane.showMessageDialog(null,"Data Saved");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }
}
//       //  SSSS
//                  try{
//            String sqls="select * from  active_employee where ROLL='"+rollnum.getText()+"'";
//           
//            pst=con.prepareStatement(sqls);
//            rs=pst.executeQuery();
//           if(rs.next()){
//                try {
//         
//        PreparedStatement pst = con.prepareStatement("UPDATE active_employee SET NOMS=?,LNAME=?,ROLL=?,ADDRESS=?,TEL=?,MAIL=?,GENDER=?,TITLE=?,DEPARTEMENT=?,PHOTOS=? WHERE ROLL='"+numid.getText()+"'");
// //`NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `LOCK`      
//        pst.setString(1, empname.getText());
//         pst.setString(2, emplname.getText());
//          pst.setString(3, rollnum.getText());
//         pst.setString(4, emaddress.getText());
//        
//        
//        
//         pst.setString(5, tel.getText());
//         pst.setString(6, mail.getText());
//          pst.setString(7, gender.getSelectedItem().toString());
//            pst.setString(8, jobtitle.getText());
//            pst.setString(9, departement.getText());
//               InputStream images = new FileInputStream(new File(imgPath));
//            pst.setBlob(10, images);
// 
//          pst.executeUpdate();
//        
//               //  JOptionPane.showMessageDialog(null,"Data Saved");
//    } catch (Exception ex) {
//         JOptionPane.showMessageDialog(null, ex.getMessage());
//    }
//            }else{
//           
//           try {
//         
//        PreparedStatement pst = con.prepareStatement("INSERT INTO `active_employee`(`NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `PHOTOS`, `LOCKS`) "
//        +"value(?,?,?,?,?,?,?,?,?,?,?)");
// //`NOMS`, `LNAME`, `ROLL`, `ADDRESS`, `TEL`, `MAIL`, `GENDER`, `TITLE`, `DEPARTEMENT`, `LOCK`      
//        pst.setString(1, empname.getText());
//         pst.setString(2, emplname.getText());
//          pst.setString(3, rollnum.getText());
//         pst.setString(4, emaddress.getText());
//        
//        
//        
//         pst.setString(5, tel.getText());
//         pst.setString(6, mail.getText());
//          pst.setString(7, gender.getSelectedItem().toString());
//            pst.setString(8, jobtitle.getText());
//            pst.setString(9, departement.getText());
//               InputStream images = new FileInputStream(new File(imgPath));
//            pst.setBlob(10, images);
//         pst.setString(11,""); 
//          pst.executeUpdate();
//        
//            //     JOptionPane.showMessageDialog(null,"Data Saved");
//    } catch (Exception ex) {
//         JOptionPane.showMessageDialog(null, ex.getMessage());
//    }
//         
//           }
//            }
//        catch(Exception ex){
//         JOptionPane.showMessageDialog(null, ex);}
//          
       
       }
       
       
       
       
        public void delete()
    {
//        this.setAlwaysOnTop(true);
         try{
        String sql = "DELETE FROM employee WHERE ROLLNUM=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,rollnum.getText());
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
//           try{
//        String sql = "DELETE FROM employee WHERE ROLLNUM=?";
//        
//         pst = con.prepareStatement(sql);
//         pst.setString(1,rollnum.getText());
//         pst.executeUpdate();
//        // JOptionPane.showMessageDialog(null,"delete");
//              
//     }catch(SQLException | HeadlessException ex ){
//         JOptionPane.showMessageDialog(null,ex);
//     }
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
        emplname = new javax.swing.JTextField();
        empname = new javax.swing.JTextField();
        emaddress = new javax.swing.JTextField();
        pob = new javax.swing.JTextField();
        rollnum = new javax.swing.JTextField();
        tel = new javax.swing.JTextField();
        mail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nationalite = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        groupesaguin = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        gender = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        mariage = new javax.swing.JComboBox<>();
        dob = new com.alee.extended.date.WebDateField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        degree = new javax.swing.JTextField();
        course = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jobtitle = new javax.swing.JTextField();
        sup = new javax.swing.JTextField();
        contrattype = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        salrange = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        departement = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        paths = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        images = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        pname = new javax.swing.JTextField();
        relation = new javax.swing.JTextField();
        padresse = new javax.swing.JTextField();
        ptel = new javax.swing.JTextField();
        pmail = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        search = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        numid = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Add_User_Group_Man_Man_16px.png"))); // NOI18N
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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personel Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        emplname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        emplname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emplnameActionPerformed(evt);
            }
        });

        empname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        empname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empnameActionPerformed(evt);
            }
        });

        emaddress.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        emaddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emaddressActionPerformed(evt);
            }
        });

        pob.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pobActionPerformed(evt);
            }
        });

        rollnum.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rollnum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rollnumActionPerformed(evt);
            }
        });

        tel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telActionPerformed(evt);
            }
        });

        mail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Names:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Last Name:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Address:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("D.O.B:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("P.O.B:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Roll_Num:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Tel:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("E-Mail:");

        nationalite.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Nationality:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Blood Groupe:");

        groupesaguin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("Gender:");

        gender.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Autres" }));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("Mariage?:");

        mariage.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mariage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Married", "Single" }));

        dob.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(groupesaguin, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mariage, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(nationalite)
                    .addComponent(mail)
                    .addComponent(empname)
                    .addComponent(emplname)
                    .addComponent(emaddress)
                    .addComponent(pob)
                    .addComponent(rollnum)
                    .addComponent(tel)
                    .addComponent(dob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(empname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emplname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emaddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(dob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rollnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nationalite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(groupesaguin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel24)
                    .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(mariage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setRowHeight(24);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Employee Registration Form");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Education", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        degree.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        course.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Degree:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Course:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(degree, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(course))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(degree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(course, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Job Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jobtitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        sup.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supActionPerformed(evt);
            }
        });

        contrattype.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Job Title:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Sup:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Contract Type:");

        salrange.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        salrange.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0-100", "101-300", "301-600", "601-800", "801-1000", "1001-1500", "1501-2000", "2001-3000" }));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Salary Range:");

        departement.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Departement:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(departement)
                    .addComponent(contrattype)
                    .addComponent(sup)
                    .addComponent(jobtitle, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(salrange, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jobtitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contrattype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salrange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(departement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Upload Profile  Photo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        paths.setEditable(false);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paths)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paths, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel27.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        images.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Urgent Personel to Contact", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        pname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        relation.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        padresse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        ptel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        pmail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Name:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Relationship:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Address:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Tel:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("Mail:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(padresse, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(ptel)
                    .addComponent(pmail)
                    .addComponent(relation)
                    .addComponent(pname))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(relation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(padresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ptel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search By:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NAME", "LNAME", "ROLLNUM", "TITRE", "TEL", "EMAIL", "SUP", "STATUT", "DEPARTEMENT" }));

        search.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Fingerprint_Scan_80px.png"))); // NOI18N

        numid.setEditable(false);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numid, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(images, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(numid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(images, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
        );

        jMenu4.setText("X");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("New");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Save");
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

        jMenu2.setText("Print");

        jMenuItem4.setText("Employee CV Sheet");
        jMenuItem4.setEnabled(false);
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Employees List");
        jMenuItem5.setEnabled(false);
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void emplnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emplnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emplnameActionPerformed

    private void empnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empnameActionPerformed

    private void emaddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emaddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emaddressActionPerformed

    private void pobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pobActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pobActionPerformed

    private void rollnumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rollnumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rollnumActionPerformed

    private void telActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telActionPerformed

    private void supActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
 photophatom() ;        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameOpened

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
call_in_tablesearch();        // TODO add your handling code here:
    }//GEN-LAST:event_searchKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
broswer();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
show_photo_from_db();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
Refresh();
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
save();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
deletes();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField contrattype;
    private javax.swing.JTextField course;
    private javax.swing.JTextField degree;
    private javax.swing.JTextField departement;
    private com.alee.extended.date.WebDateField dob;
    private javax.swing.JTextField emaddress;
    private javax.swing.JTextField emplname;
    private javax.swing.JTextField empname;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JTextField groupesaguin;
    private javax.swing.JLabel images;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
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
    private javax.swing.JTextField jobtitle;
    private javax.swing.JTextField mail;
    private javax.swing.JComboBox<String> mariage;
    private javax.swing.JTextField nationalite;
    private javax.swing.JTextField numid;
    private javax.swing.JTextField padresse;
    private javax.swing.JTextField paths;
    private javax.swing.JTextField pmail;
    private javax.swing.JTextField pname;
    private javax.swing.JTextField pob;
    private javax.swing.JTextField ptel;
    private javax.swing.JTextField relation;
    private javax.swing.JTextField rollnum;
    private javax.swing.JComboBox<String> salrange;
    private javax.swing.JTextField search;
    private javax.swing.JTextField sup;
    private javax.swing.JTextField tel;
    // End of variables declaration//GEN-END:variables
}
