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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Doshe PC
 */
public class ajouter_employe extends javax.swing.JInternalFrame {

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
    public ajouter_employe() {
        initComponents();
             con=JavaDbConnect.dbConnect();
               call_in_table();
              // JInternalFrame internalFrame = new JInternalFrame("test",false, false, false, false);
this.setTitle("Enregistrement");
             //  photophatom();
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
           
             String sql="SELECT `NAME`, `LNAME`, `ROLLNUM`, `TITRE`, `TEL`, `EMAIL`, `SUP`, `STATUT`, `DEPARTMENT` FROM employee where `NAME` like '"+search.getText()+"%' or `LNAME` like '"+search.getText()+"%' or `ROLLNUM` like '"+search.getText()+"%' ";
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
       // gender.setText("");
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
       groupesaguin.setText("");
         
         
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
                 nationalite1.setSelectedItem(sum8);
                 
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
//       Count_emp();
       call_in_table();
       Refresh();
       }
       
       public void deletes(){
       delete();
         //      Count_emp();
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
          pst.setString(11, nationalite1.getSelectedItem().toString());
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
          pst.setString(11, nationalite1.getSelectedItem().toString());
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
          pst.setString(11, nationalite1.getSelectedItem().toString());
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
    }
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        empname = new Palette.TextField();
        emplname = new Palette.TextField();
        emaddress = new Palette.TextField();
        dob = new Palette.TextField();
        pob = new Palette.TextField();
        rollnum = new Palette.TextField();
        mail = new Palette.TextField();
        tel = new Palette.TextField();
        groupesaguin = new Palette.TextField();
        nationalite1 = new Palette.ComboBoxSuggestion();
        gender = new Palette.ComboBoxSuggestion();
        mariage = new Palette.ComboBoxSuggestion();
        jPanel4 = new javax.swing.JPanel();
        degree = new Palette.TextField();
        course = new Palette.TextField();
        jPanel5 = new javax.swing.JPanel();
        jobtitle = new Palette.TextField();
        sup = new Palette.TextField();
        contrattype = new Palette.TextField();
        salrange = new Palette.TextField();
        departement = new Palette.TextField();
        numid = new Palette.TextField();
        jLabel31 = new javax.swing.JLabel();
        images = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        pname = new Palette.TextField();
        relation = new Palette.TextField();
        padresse = new Palette.TextField();
        ptel = new Palette.TextField();
        pmail = new Palette.TextField();
        search = new Palette.TextField();
        jPanel9 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        paths = new Palette.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        dateChooser1.setTextRefernce(dob);

        setTitle("Enregistrement");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Add_User_Group_Man_Man_16px.png"))); // NOI18N
        setInheritsPopupMenu(true);
        setName(""); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informations sur l’employé", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        empname.setLabelText("Nom de l’employés");

        emplname.setLabelText("Prénom de l’employé");

        emaddress.setLabelText("Adresse de l’employé");

        dob.setLabelText("Date de naissance");

        pob.setLabelText("Lieu naissance");

        rollnum.setLabelText("Numéro matricule");
        rollnum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rollnumActionPerformed(evt);
            }
        });

        mail.setLabelText("Adresse mail");

        tel.setLabelText("Numéros téléphones");

        groupesaguin.setLabelText("Groupe sanguin");

        nationalite1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Canary Islands", "Cape Verde Islands", "Cayman islands", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Democratic Republic of the Congo ", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Federated States of Micronesia", "Fiji", "Finland", "France", "French Guyana", "French Polynesia", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Greenland", "Grenada", "Guatemala", "Guinea", "Guinea Bissau", "Guyana", "Haiti", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Israel", "Italy", "Ivory Coast/C�te d�Ivoire", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mexico", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar (Burma)", "Namibia", "Nauru", "Nepal", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn Islands", "Poland", "Portugal", "Puerto Rico", "Qatar", "Republic of Ireland", "Republic of San Marino", "Romania", "Russia", "Rwanda", "Samoa", "Sao Tome", "Saudi Arabia", "Scotland", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", "Spain", "Sri Lanka", "St. Kitts and Nevis", "St. Lucia", "St. Vincent and The Grenadines", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "The Netherlands", "The Vatican", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Wales", "West Bank and Gaza", "Western Sahara", "Yemen", "Zambia", "Zimbabwe" }));

        gender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Femelle" }));

        mariage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Célibataire", "Marier", "Veuve", "Veuf", " " }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emaddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(dob, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(empname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emplname, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rollnum, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tel, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(groupesaguin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mariage, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(nationalite1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(empname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emplname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(emaddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rollnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nationalite1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(gender, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(groupesaguin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mariage, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Information sur l’éducation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        degree.setLabelText("Niveau d’étude");
        degree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                degreeActionPerformed(evt);
            }
        });

        course.setLabelText("Faculté faite ou domaine");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(degree, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(course, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(degree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(course, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informations sur le poste", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jobtitle.setLabelText("Poste");

        sup.setLabelText("Nom du superviseur direct");

        contrattype.setLabelText("Type du contrat");

        salrange.setLabelText("Prétention salariale");

        departement.setLabelText("Departement");

        numid.setEditable(false);
        numid.setLabelText("ID");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(salrange, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(departement, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                    .addComponent(jobtitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(sup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contrattype, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jobtitle, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contrattype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salrange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Fingerprint_Scan_80px.png"))); // NOI18N

        images.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/tetes.JPG"))); // NOI18N
        images.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informations sur la personne a contacté à cas d’urgence", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        pname.setLabelText("Noms");

        relation.setLabelText("Parenté");

        padresse.setLabelText("Adresse");

        ptel.setLabelText("Numéros téléphones");

        pmail.setLabelText("Adresse mail");

        search.setLabelText("Recherche");
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(padresse, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(pname, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(relation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(ptel, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(relation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(padresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ptel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Upload Profile  Photo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        paths.setEditable(false);
        paths.setFont(new java.awt.Font("Segoe UI", 0, 1)); // NOI18N
        paths.setLabelText("Lien");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paths, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paths, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(images, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(images, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu3.setText("X");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("New");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Save");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Delete");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(130, 10, 1106, 581);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        broswer();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        show_photo_from_db();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
save();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
Refresh();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
deletes();          // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
this.dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3MouseClicked

    private void degreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_degreeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_degreeActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
 call_in_tablesearch();         // TODO add your handling code here:
    }//GEN-LAST:event_searchKeyReleased

    private void rollnumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rollnumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rollnumActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Palette.TextField contrattype;
    private Palette.TextField course;
    private com.raven.datechooser.DateChooser dateChooser1;
    private Palette.TextField degree;
    private Palette.TextField departement;
    private Palette.TextField dob;
    private Palette.TextField emaddress;
    private Palette.TextField emplname;
    private Palette.TextField empname;
    private Palette.ComboBoxSuggestion gender;
    private Palette.TextField groupesaguin;
    private javax.swing.JLabel images;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private Palette.TextField jobtitle;
    private Palette.TextField mail;
    private Palette.ComboBoxSuggestion mariage;
    private Palette.ComboBoxSuggestion nationalite1;
    private Palette.TextField numid;
    private Palette.TextField padresse;
    private Palette.TextField paths;
    private Palette.TextField pmail;
    private Palette.TextField pname;
    private Palette.TextField pob;
    private Palette.TextField ptel;
    private Palette.TextField relation;
    private Palette.TextField rollnum;
    private Palette.TextField salrange;
    private Palette.TextField search;
    private Palette.TextField sup;
    private Palette.TextField tel;
    // End of variables declaration//GEN-END:variables
}
