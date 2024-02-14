/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import com.alee.laf.WebLookAndFeel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;

public class homme extends javax.swing.JFrame {
private Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
String path;
    public homme() {
       initComponents();
         this.setExtendedState(JFrame.MAXIMIZED_BOTH);
       con=JavaDbConnect.dbConnect();
  setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
      comptabilite.setEnabled(false);
       logistique.setEnabled(false);
       rh.setEnabled(false);
       direction.setEnabled(false);
       admin.setEnabled(false);
       employee.setEnabled(false);
       guerite.setEnabled(false);
   admins.setEnabled(false);
        
        
      lock_finance();
        //  callback();
     // comptabilite.setText(GetNetworkAddress.GetAddress("ip"));
    }
    public void lock_finance(){
    
    payslip_summary.setEnabled(false);
    payday_advance.setEnabled(false);
    payroll_taxation.setEnabled(false);
    
    bills.setEnabled(false);
    invoice.setEnabled(false);
    purchase_order.setEnabled(false);
    
    new_project.setEnabled(false);
    chart_of_account.setEnabled(false);
    code_budget.setEnabled(false);
    budget_record.setEnabled(false);
    budget_show.setEnabled(false);
    
    }
    
      public void doubleclick(){
      
     images.addMouseListener(new MouseAdapter() {
    public void mousePressed(MouseEvent mouseEvent) {
       JLabel table =(JLabel) mouseEvent.getSource();
        Point point = mouseEvent.getPoint();
       // int row = table.rowAtPoint(point);
        if (mouseEvent.getClickCount() == 2 ) {
           broswers3();
         
        }
    }
});
      }
      public void callback(){
      
            try {
                
              //Connection  con= DriverManager.getConnection("jdbc:mysql://localhost/entreprise_management_system","root","");
              Statement st = con.createStatement();
              //  String sql="UPDATE `back_ground` SET `img`=? WHERE ID=1";
              ResultSet rs = st.executeQuery("select * from back_ground where  USER='"+user.getText()+"'");
              if(rs.next()){
                  byte[] img = rs.getBytes("img");
                  ImageIcon image =new ImageIcon(img);
                  Image im = image.getImage();
                  Image myIm = im.getScaledInstance(images.getWidth(),images.getHeight(),Image.SCALE_SMOOTH);
                  ImageIcon newImage= new ImageIcon(myIm);
                  images.setIcon(newImage);
                   this.background = new ImageIcon(imgPath).getImage();
       repaint();
              }
                }
             catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            } 
      
      }
      public void CALL_PATHS()
    {
       
        
         
        try{
            String sql="SELECT PATH FROM    pathn where ID=2";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
               path=rs.getString("PATH");
                //buss.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
      
      
      
    // String path="C:\\Users\\DOSHE\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\EMS-L.jpg";
    String imgPath=path; 
    private Image background;
    Graphics g;
      public void broswers3()
     {
        // this.setAlwaysOnTop(false);
          JFileChooser chooser = new JFileChooser();
       chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
       FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","gif","png","*.pdf","pdf");
       chooser.addChoosableFileFilter(filter);
       int result = chooser.showSaveDialog(null);
       if(result == JFileChooser.APPROVE_OPTION ){
           File selectedFile = chooser.getSelectedFile();
           String path = selectedFile.getAbsolutePath();
           //images.setIcon(ResizeImage(path,null));
          // imgPath = path;
//           super.paintComponents(g);
//           int with=this.getSize().width;
//           int hidth = this.getSize().height;
          // g.drawImage(this.background,0, 0,width,height,null);
       //  images.setText(imgPath);
       this.background = new ImageIcon(imgPath).getImage();
       repaint();
       }
       else if(result ==  JFileChooser.CANCEL_OPTION){
           System.out.println("No Fli Selected");
       }    
       saves();
       callback();
     }   
         public void saves(){
    try{
            String sql="SELECT * FROM  back_ground WHERE  USER='"+user.getText()+"'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
               try{
    String sqlu="UPDATE `back_ground` SET `img`=? WHERE  USER='"+user.getText()+"'";
     
    pst=con.prepareStatement(sqlu);
   
        
         InputStream img = new FileInputStream(new File(imgPath));
         pst.setBlob(1, img);
    
   
  
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
            }else{
                try{
    String sqls="INSERT INTO `back_ground`(`img`,USER) VALUES (?,?)";
     
    pst=con.prepareStatement(sqls);
   
        
         InputStream img = new FileInputStream(new File(imgPath));
         pst.setBlob(1, img);
         pst.setString(2,user.getText());
    
   
  
      
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
             
            
            }
             }
        catch(Exception ex){
      //    JOptionPane.showMessageDialog(null, ex);    
        }

    
     }
      public void CALL_PATHSs()
    {
       
        
         
        try{
              
            String sql="SELECT PATH FROM   pathn where ID=2";
            con = DriverManager.getConnection(sql);
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
               imgPath=rs.getString("PATH");
                //buss.addItem(sums);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
      public void photophatom()     
     {
      CALL_PATHS();
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
    
      
    
    
    public void centre_fram(){
      Toolkit tool= Toolkit.getDefaultToolkit();
        Dimension dim = new Dimension(tool.getScreenSize());
        int height= (int)dim.getHeight();
        int width= (int)dim.getWidth();
        setSize(width,height);
        //setLocation(width / 2 - getWidth() / 2, height / 2 - getHeight());
    }

    
     public void loading_finance(){
     
       try{
            String sql="select ACESS from user_write where NAME='"+user.getText()+"' and CHAMP='Payslip Summary'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
          payslip_summary.setEnabled(true); 
          }else{
          payslip_summary.setEnabled(false);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
       
       
        try{
            String sql="select ACESS from user_write where NAME='"+user.getText()+"' and CHAMP='Payday Advance'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
          payday_advance.setEnabled(true); 
          }else{
         payday_advance.setEnabled(false);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
        
         try{
            String sql="select ACESS from user_write where NAME='"+user.getText()+"' and CHAMP='Payroll Taxation'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
          payroll_taxation.setEnabled(true); 
          }else{
         payroll_taxation.setEnabled(false);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }  
         
         
         
            try{
            String sql="select ACESS from user_write where NAME='"+user.getText()+"' and CHAMP='Bills'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
          bills.setEnabled(true); 
          }else{
         bills.setEnabled(false);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
            
              try{
            String sql="select ACESS from user_write where NAME='"+user.getText()+"' and CHAMP='Invoice'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
          invoice.setEnabled(true); 
          }else{
         invoice.setEnabled(false);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
              
                    try{
            String sql="select ACESS from user_write where NAME='"+user.getText()+"' and CHAMP='Purchase order'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
          purchase_order.setEnabled(true); 
          }else{
         purchase_order.setEnabled(false);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
                    
                    
                       try{
            String sql="select ACESS from user_write where NAME='"+user.getText()+"' and CHAMP='New Project'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
         new_project.setEnabled(true); 
          }else{
         new_project.setEnabled(false);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
                       
                       
             try{
            String sql="select ACESS from user_write where NAME='"+user.getText()+"' and CHAMP='Chart of Account'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
         chart_of_account.setEnabled(true); 
          }else{
         chart_of_account.setEnabled(false);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); } 
             
             
             
             
          
        
         
         
          try{
            String sql="select ACESS from user_write where NAME='"+user.getText()+"' and CHAMP='Code Budget'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
         code_budget.setEnabled(true); 
          }else{
         code_budget.setEnabled(false);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
          
          
           try{
            String sql="select ACESS from user_write where NAME='"+user.getText()+"' and CHAMP='Budget Record'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
         budget_record.setEnabled(true); 
          }else{
         budget_record.setEnabled(false);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
           
            try{
            String sql="select ACESS from user_write where NAME='"+user.getText()+"' and CHAMP='Budget Show'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
        budget_show.setEnabled(true); 
          }else{
         budget_show.setEnabled(false);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
             try{
            String sql="select ACESS from user_write where NAME='"+user.getText()+"' and CHAMP='Rec Bank'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
       rc_bank.setEnabled(true); 
          }else{
       rc_bank.setEnabled(false);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
             
               try{
            String sql="select ACESS from user_write where NAME='"+user.getText()+"' and CHAMP='Report sheets'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
       rport_sheet.setEnabled(true); 
          }else{
       rport_sheet.setEnabled(false);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
               
                try{
            String sql="select ACESS from user_write where NAME='"+user.getText()+"' and CHAMP='Finance Transaction'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
          String  caisse= rs.getString("ACESS");
          if(caisse.equals("Yes")){
       fin_trans.setEnabled(true); 
          }else{
       fin_trans.setEnabled(false);
          }
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex); }
                
                
                  
     }
     
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        webMenuBar1 = new com.alee.laf.menu.WebMenuBar();
        jMenu15 = new javax.swing.JMenu();
        jMenu16 = new javax.swing.JMenu();
        jTextField1 = new javax.swing.JTextField();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        boody = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem76 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        payslip_summary = new javax.swing.JMenuItem();
        payday_advance = new javax.swing.JMenuItem();
        payroll_taxation = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        bills = new javax.swing.JMenuItem();
        invoice = new javax.swing.JMenuItem();
        purchase_order = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        new_project = new javax.swing.JMenuItem();
        chart_of_account = new javax.swing.JMenuItem();
        rc_bank = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem37 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        rport_sheet = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenu17 = new javax.swing.JMenu();
        code_budget = new javax.swing.JMenuItem();
        budget_record = new javax.swing.JMenuItem();
        budget_show = new javax.swing.JMenuItem();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenuItem81 = new javax.swing.JMenuItem();
        jMenuItem70 = new javax.swing.JMenuItem();
        jMenuItem47 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem38 = new javax.swing.JMenuItem();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem49 = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        admin = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jMenuItem66 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem36 = new javax.swing.JMenuItem();
        jMenuItem42 = new javax.swing.JMenuItem();
        jMenuItem48 = new javax.swing.JMenuItem();
        jMenuItem50 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem45 = new javax.swing.JMenuItem();
        jMenuItem46 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        fin_trans = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();

        jMenu15.setText("File");
        webMenuBar1.add(jMenu15);

        jMenu16.setText("Edit");
        webMenuBar1.add(jMenu16);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setLocationByPlatform(true);
        setType(java.awt.Window.Type.POPUP);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(204, 255, 255));
        jTextField1.setFont(new java.awt.Font("Georgia", 1, 10)); // NOI18N
        jTextField1.setText(" www.togethewecanbusnessmanagementsystem.ug.com// toghetherwecanmana@system.ug.com// levilikoyolevi@gmail.com/(+256)757776103 //(+243)816464945. Project relised by Together We can Busness Management System(TWBMS)");

        boody.setLayout(new javax.swing.BoxLayout(boody, javax.swing.BoxLayout.LINE_AXIS));

        images.setBackground(new java.awt.Color(255, 255, 255));
        images.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        images.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/banckgrounds.jpg"))); // NOI18N
        images.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        images.setOpaque(true);
        images.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imagesMousePressed(evt);
            }
        });
        boody.add(images);

        jDesktopPane1.setLayer(boody, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addComponent(boody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(boody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        user.setBackground(new java.awt.Color(0, 0, 0));
        user.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        user.setForeground(new java.awt.Color(0, 0, 153));
        user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Manager_16px.png"))); // NOI18N
        user.setText("User         ");
        user.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jMenuItem7.setText("Chat");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        user.add(jMenuItem7);

        jMenuBar1.add(user);

        comptabilite.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        comptabilite.setText("     Account/Finance");
        comptabilite.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        comptabilite.setVerifyInputWhenFocusTarget(false);
        comptabilite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comptabiliteMouseClicked(evt);
            }
        });
        comptabilite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comptabiliteActionPerformed(evt);
            }
        });

        jMenuItem76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Edit_Property_16px.png"))); // NOI18N
        jMenuItem76.setText("Journals                                                   ");
        jMenuItem76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem76ActionPerformed(evt);
            }
        });
        comptabilite.add(jMenuItem76);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Conference_16px.png"))); // NOI18N
        jMenu4.setText("Human_Resources");

        payslip_summary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Sheets_16px.png"))); // NOI18N
        payslip_summary.setText("Payslip Summary");
        payslip_summary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payslip_summaryActionPerformed(evt);
            }
        });
        jMenu4.add(payslip_summary);

        payday_advance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Cash_in_Hand_16px.png"))); // NOI18N
        payday_advance.setText("Payday Advance");
        payday_advance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payday_advanceActionPerformed(evt);
            }
        });
        jMenu4.add(payday_advance);

        payroll_taxation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Customs_Officer_16px.png"))); // NOI18N
        payroll_taxation.setText("Payroll Taxation");
        payroll_taxation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payroll_taxationActionPerformed(evt);
            }
        });
        jMenu4.add(payroll_taxation);

        comptabilite.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Receipt_16px.png"))); // NOI18N
        jMenu5.setText("Receipts");

        bills.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bill_16px.png"))); // NOI18N
        bills.setText("Bills");
        bills.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billsActionPerformed(evt);
            }
        });
        jMenu5.add(bills);

        invoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Invoice_16px.png"))); // NOI18N
        invoice.setText("Invoice");
        invoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceActionPerformed(evt);
            }
        });
        jMenu5.add(invoice);

        purchase_order.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Repository_16px.png"))); // NOI18N
        purchase_order.setText("Purchase Order");
        purchase_order.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchase_orderActionPerformed(evt);
            }
        });
        jMenu5.add(purchase_order);

        comptabilite.add(jMenu5);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Related_Companies_16px.png"))); // NOI18N
        jMenu7.setText("Management");

        new_project.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Course_16px.png"))); // NOI18N
        new_project.setText("New Project");
        new_project.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new_projectActionPerformed(evt);
            }
        });
        jMenu7.add(new_project);

        chart_of_account.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Accounting_16px.png"))); // NOI18N
        chart_of_account.setText("Chart of Account");
        chart_of_account.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chart_of_accountActionPerformed(evt);
            }
        });
        jMenu7.add(chart_of_account);

        rc_bank.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Documents_16px.png"))); // NOI18N
        rc_bank.setText("Rec Bank");
        rc_bank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rc_bankActionPerformed(evt);
            }
        });
        jMenu7.add(rc_bank);

        jMenuItem26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Attach_24px.png"))); // NOI18N
        jMenuItem26.setText("Archivage Pieces Justifs");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem26);

        jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Attach_24px.png"))); // NOI18N
        jMenu9.setText("Check Justifs");

        jMenuItem34.setText("Justifs");
        jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem34);

        jMenuItem37.setText("Third part (Delete justifs)");
        jMenuItem37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem37ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem37);

        jMenu7.add(jMenu9);

        comptabilite.add(jMenu7);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_This_Way_Up_16px.png"))); // NOI18N
        jMenu3.setText("Balance Sheets");

        jMenu6.setText("Initial Balance sheet                                   ");
        jMenu6.setEnabled(false);

        jMenuItem8.setText("Souscription                     ");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem8);

        jMenuItem9.setText("Liberation (Release)");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem9);

        jMenu3.add(jMenu6);

        rport_sheet.setText("Report sheets");
        rport_sheet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rport_sheetActionPerformed(evt);
            }
        });
        jMenu3.add(rport_sheet);

        comptabilite.add(jMenu3);

        jMenuItem28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Course_16px.png"))); // NOI18N
        jMenuItem28.setText("Binder");
        jMenuItem28.setEnabled(false);
        comptabilite.add(jMenuItem28);

        jMenu17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Data_Sheet_16px.png"))); // NOI18N
        jMenu17.setText("Budget");

        code_budget.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bulleted_List_16px.png"))); // NOI18N
        code_budget.setText("Code Budget");
        code_budget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                code_budgetActionPerformed(evt);
            }
        });
        jMenu17.add(code_budget);

        budget_record.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bulleted_List_16px.png"))); // NOI18N
        budget_record.setText("Budget Record");
        budget_record.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                budget_recordActionPerformed(evt);
            }
        });
        jMenu17.add(budget_record);

        budget_show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bulleted_List_16px.png"))); // NOI18N
        budget_show.setText("Budget Show");
        budget_show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                budget_showActionPerformed(evt);
            }
        });
        jMenu17.add(budget_show);

        comptabilite.add(jMenu17);

        jMenuItem32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Data_Sheet_16px.png"))); // NOI18N
        jMenuItem32.setText("Budgetisation");
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        comptabilite.add(jMenuItem32);

        jMenuBar1.add(comptabilite);

        logistique.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logistique.setText("Logistic");
        logistique.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        logistique.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logistiqueMouseClicked(evt);
            }
        });
        logistique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logistiqueActionPerformed(evt);
            }
        });

        jMenuItem81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Table_16px.png"))); // NOI18N
        jMenuItem81.setText("Dash Board");
        jMenuItem81.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem81ActionPerformed(evt);
            }
        });
        logistique.add(jMenuItem81);

        jMenuBar1.add(logistique);

        rh.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rh.setText("Administration/RH");
        rh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jMenuItem70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Open_Document_16px.png"))); // NOI18N
        jMenuItem70.setText("Recruitement");
        jMenuItem70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem70ActionPerformed(evt);
            }
        });
        rh.add(jMenuItem70);

        jMenuItem47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Form_16px.png"))); // NOI18N
        jMenuItem47.setText("Attendance_Management");
        jMenuItem47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem47ActionPerformed(evt);
            }
        });
        rh.add(jMenuItem47);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Customs_Officer_16px.png"))); // NOI18N
        jMenuItem5.setText("Payroll Taxation");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        rh.add(jMenuItem5);

        jMenuItem38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Message_16px.png"))); // NOI18N
        jMenuItem38.setText("Courrier");
        jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem38ActionPerformed(evt);
            }
        });
        rh.add(jMenuItem38);

        jMenuItem39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Course_16px.png"))); // NOI18N
        jMenuItem39.setText("Archives");
        jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem39ActionPerformed(evt);
            }
        });
        rh.add(jMenuItem39);

        jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Health_Book_16px.png"))); // NOI18N
        jMenuItem24.setText("Bon_de_Soin");
        jMenuItem24.setEnabled(false);
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        rh.add(jMenuItem24);

        jMenuItem49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Data_Sheet_16px.png"))); // NOI18N
        jMenuItem49.setText("R_H Dashboard");
        jMenuItem49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem49ActionPerformed(evt);
            }
        });
        rh.add(jMenuItem49);

        jMenuBar1.add(rh);

        direction.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        direction.setText("   Direction");
        direction.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jMenuItem35.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Check_16px.png"))); // NOI18N
        jMenuItem35.setText("Gestion _d'Etat_de_Besoin");
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        direction.add(jMenuItem35);

        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bulleted_List_16px.png"))); // NOI18N
        jMenuItem14.setText("Ordre de Mission");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        direction.add(jMenuItem14);

        jMenuBar1.add(direction);

        admin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        admin.setText("    Projet Management");
        admin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Unit_16px.png"))); // NOI18N
        jMenuItem10.setText("Planning_Project");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        admin.add(jMenuItem10);

        jMenuItem33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Today_16px.png"))); // NOI18N
        jMenuItem33.setText("Daily_Planning");
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        admin.add(jMenuItem33);

        jMenuItem66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bill_16px.png"))); // NOI18N
        jMenuItem66.setText("Gestion_des_Etats_de_Besoin");
        jMenuItem66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem66ActionPerformed(evt);
            }
        });
        admin.add(jMenuItem66);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Brick_Wall_16px.png"))); // NOI18N
        jMenuItem6.setText("Admin Dash Board");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        admin.add(jMenuItem6);

        jMenu10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Course_16px.png"))); // NOI18N
        jMenu10.setText("Project");

        jMenuItem36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Course_16px.png"))); // NOI18N
        jMenuItem36.setText("New Project");
        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem36);

        jMenuItem42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Accounting_16px.png"))); // NOI18N
        jMenuItem42.setText("New Task");
        jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem42ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem42);

        jMenuItem48.setText("Project Allocation");
        jMenuItem48.setEnabled(false);
        jMenu10.add(jMenuItem48);

        jMenuItem50.setText("Stop Project Process");
        jMenuItem50.setEnabled(false);
        jMenu10.add(jMenuItem50);

        admin.add(jMenu10);

        jMenu11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Data_Sheet_16px.png"))); // NOI18N
        jMenu11.setText("Budget");

        jMenuItem45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Data_Sheet_16px.png"))); // NOI18N
        jMenuItem45.setText("New Badget");
        jMenuItem45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem45ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem45);

        jMenuItem46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Data_Sheet_16px.png"))); // NOI18N
        jMenuItem46.setText("Budget Allocation");
        jMenuItem46.setEnabled(false);
        jMenu11.add(jMenuItem46);

        admin.add(jMenu11);

        jMenuBar1.add(admin);

        employee.setBorder(null);
        employee.setText("   Employees");
        employee.setAutoscrolls(true);
        employee.setBorderPainted(true);
        employee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        employee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeeMouseClicked(evt);
            }
        });
        employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeActionPerformed(evt);
            }
        });

        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Approval_16px.png"))); // NOI18N
        jMenuItem13.setText("Open");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        employee.add(jMenuItem13);

        jMenuBar1.add(employee);

        admins.setText("Admin");
        admins.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Add_User_Male_16px.png"))); // NOI18N
        jMenuItem1.setText("User Registration");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        admins.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Add_User_Group_Man_Man_16px.png"))); // NOI18N
        jMenuItem2.setText("User Dispacting");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        admins.add(jMenuItem2);

        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Add_User_Group_Man_Man_16px.png"))); // NOI18N
        jMenuItem12.setText("User Write");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        admins.add(jMenuItem12);

        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Accounting_16px.png"))); // NOI18N
        jMenuItem15.setText("Project Dispacting");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        admins.add(jMenuItem15);

        jMenuItem25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Study_16px.png"))); // NOI18N
        jMenuItem25.setText("Projet Dash");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        admins.add(jMenuItem25);

        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Password_Reset_16px.png"))); // NOI18N
        jMenuItem11.setText("Login Register");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        admins.add(jMenuItem11);

        fin_trans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Check_16px.png"))); // NOI18N
        fin_trans.setText("Finance Transaction");
        fin_trans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fin_transActionPerformed(evt);
            }
        });
        admins.add(fin_trans);

        jMenuItem20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bill_16px.png"))); // NOI18N
        jMenuItem20.setText("Number Transaction");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        admins.add(jMenuItem20);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Purchase_Order_16px.png"))); // NOI18N
        jMenuItem3.setText("Notification Mob AP");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        admins.add(jMenuItem3);

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Bulleted_List_16px.png"))); // NOI18N
        jMenu8.setText("EB");

        jMenuItem29.setText("EB Connector");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem29);

        jMenuItem30.setText("EB_Backup");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem30);

        admins.add(jMenu8);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Related_Companies_16px.png"))); // NOI18N
        jMenu2.setText("Online Datas");

        jMenuItem21.setText("Upload");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem21);

        jMenuItem22.setText("Download");
        jMenu2.add(jMenuItem22);

        admins.add(jMenu2);

        jMenuItem17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Microsoft_Excel_16px.png"))); // NOI18N
        jMenuItem17.setText("Import/Export Excel");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        admins.add(jMenuItem17);

        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_QR_Code_20px.png"))); // NOI18N
        jMenuItem18.setText("QRCode/Barcode Generator");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        admins.add(jMenuItem18);

        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Unit_16px.png"))); // NOI18N
        jMenuItem16.setText("SQL");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        admins.add(jMenuItem16);

        jMenuBar1.add(admins);

        jMenu1.setText("Programme");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        program.setText("Ouvrire");
        program.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programActionPerformed(evt);
            }
        });
        jMenu1.add(program);

        jMenuBar1.add(jMenu1);

        guerite.setText("Gerite");
        guerite.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jMenuItem19.setText("Ouvrire");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        guerite.add(jMenuItem19);

        jMenuBar1.add(guerite);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
       etat_de_besoin_direction   m= new  etat_de_besoin_direction();
        jDesktopPane1.add(m);
        

        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void logistiqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logistiqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logistiqueActionPerformed

    private void logistiqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logistiqueMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_logistiqueMouseClicked

    private void comptabiliteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comptabiliteActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_comptabiliteActionPerformed

    private void comptabiliteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comptabiliteMouseClicked
loading_finance();
    }//GEN-LAST:event_comptabiliteMouseClicked

    private void rport_sheetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rport_sheetActionPerformed
        bilan m= new bilan();
        jDesktopPane1.add(m);
         try {
        m.setMaximum(true);
    } catch (PropertyVetoException ex) {
        Logger.getLogger(homme.class.getName()).log(Level.SEVERE, null, ex);
    }
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_rport_sheetActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
      
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
               // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void chart_of_accountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chart_of_accountActionPerformed

    ohada m = new ohada();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_chart_of_accountActionPerformed

    private void purchase_orderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchase_orderActionPerformed
bon_de_comande m= new  bon_de_comande();
      
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_purchase_orderActionPerformed

    private void invoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceActionPerformed
        invoice    m= new invoice ();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_invoiceActionPerformed

    private void billsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billsActionPerformed
        RCU    m= new RCU ();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_billsActionPerformed

    private void payroll_taxationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payroll_taxationActionPerformed
        employetaxe m = new employetaxe();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_payroll_taxationActionPerformed

    private void payday_advanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payday_advanceActionPerformed
        avance_sur_salaire1 m = new avance_sur_salaire1();
       

        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_payday_advanceActionPerformed

    private void payslip_summaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payslip_summaryActionPerformed
       salary_recaputilation1 m = new salary_recaputilation1();
        jDesktopPane1.add(m);
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_payslip_summaryActionPerformed

    private void code_budgetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_code_budgetActionPerformed
 codebudget  m  = new codebudget();
        //jDesktopPane1.add(m);
        m.show();
        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);           // TODO add your handling code here:
    }//GEN-LAST:event_code_budgetActionPerformed

    private void budget_recordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_budget_recordActionPerformed
   budget m= new  budget();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);           // TODO add your handling code here:
    }//GEN-LAST:event_budget_recordActionPerformed

    private void budget_showActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_budget_showActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_budget_showActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        NewJInternalFrame   m= new NewJInternalFrame();
        jDesktopPane1.add(m);
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);       // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem66ActionPerformed
        EB m =new EB();
        jDesktopPane1.add(m);
        m.show();
        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem66ActionPerformed

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
        NewJInternalFrameplani_jour m = new NewJInternalFrameplani_jour();
        jDesktopPane1.add(m);
        m.show();
        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem81ActionPerformed
logs m = new logs();
        jDesktopPane1.add(m);
                try {
        m.setMaximum(true);
    } catch (PropertyVetoException ex) {
        Logger.getLogger(homme.class.getName()).log(Level.SEVERE, null, ex);
    }
        m.show();
        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);       // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem81ActionPerformed

    private void new_projectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_new_projectActionPerformed
 new_project m = new new_project ();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_new_projectActionPerformed

    private void imagesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagesMousePressed
//doubleclick();        // TODO add your handling code here:
    }//GEN-LAST:event_imagesMousePressed

    private void jMenuItem42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem42ActionPerformed
 project_task m  = new project_task();
        jDesktopPane1.add(m);
        m.show();
        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);          // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem42ActionPerformed

    private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
new_project m = new new_project ();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem36ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem49ActionPerformed
        ///*
        rh m= new rh();
        jDesktopPane1.add(m);
                try {
        m.setMaximum(true);
    } catch (PropertyVetoException ex) {
        Logger.getLogger(homme.class.getName()).log(Level.SEVERE, null, ex);
    }
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //      */
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem49ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem70ActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem70ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        employetaxe m = new employetaxe();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem47ActionPerformed
        pointage_electronique m= new pointage_electronique();
        // jDesktopPane1.add(m);
        //
        //jButton2.setVisible(false);
        //  NewJInternalFrameEmployee.jButton2.setVisible(true);
        // NewJInternalFrameEmployee.jList1.setEnabled(true);

        m.show();

        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem47ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
registrer m = new registrer();
   m.show();

        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);    
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
User_Write_bon m = new User_Write_bon();
   m.show();

        m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);          // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
//  WebLookAndFeel.install(true);
//        LOGINS_homme m = new LOGINS_homme();
// m.dispose(); //homme.comptabilite.setEnabled(true);
//        m.show();
//        
//        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
      user_dispact m = new user_dispact();
  //homme.comptabilite.setEnabled(true);
        m.show();
        
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);    // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
 LOGIN_TRACTOR m= new LOGIN_TRACTOR();
        jDesktopPane1.add(m);
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void fin_transActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fin_transActionPerformed
if(user.getText().equals("Levi Likoyo")){
  FINANCE_REGISTRAT m= new FINANCE_REGISTRAT();
        jDesktopPane1.add(m);
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
}else{
JOptionPane.showMessageDialog(null,"No Available"+"\n"+"Please Contact the Service Provider ","Information",JOptionPane.INFORMATION_MESSAGE);
}
               // TODO add your handling code here:
    }//GEN-LAST:event_fin_transActionPerformed

    private void jMenuItem45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem45ActionPerformed
 budget m= new  budget();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem45ActionPerformed

    private void employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeActionPerformed
                // TODO add your handling code here:
    }//GEN-LAST:event_employeeActionPerformed

    private void employeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeMouseClicked
         // TODO add your handling code here:
    }//GEN-LAST:event_employeeMouseClicked

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
 try{
            String sql="select ACESS from user_write where NAME='"+homme.user.getText()+"' and CHAMP='Kiosque'";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
         if(rs.next()){
     homme_EMPLOYEE m = new homme_EMPLOYEE();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
          }else{
       JOptionPane.showMessageDialog(null,"Acces limité","Avertissement",JOptionPane.WARNING_MESSAGE);
            }
            }catch(Exception ex){
          
          JOptionPane.showMessageDialog(null, ex); }
               // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void rc_bankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rc_bankActionPerformed
reconciliation_bank m= new reconciliation_bank();
        jDesktopPane1.add(m);
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);          // TODO add your handling code here:
    }//GEN-LAST:event_rc_bankActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
    querrys m = new querrys();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);     // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void programActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programActionPerformed
  programme m = new programme();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_programActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
chat m = new chat();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);       // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
 mvm_personnel m = new mvm_personnel();
       jDesktopPane1.add(m);
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
 project_dispact m = new project_dispact();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);     // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
if(user.getText().equals("Levi Likoyo")){
 excel m = new excel();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
}else{
JOptionPane.showMessageDialog(null,"No Available"+"\n"+"Please Contact the Service Provider ","Information",JOptionPane.INFORMATION_MESSAGE);
}
              // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
  qrbarecode m = new qrbarecode();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);       // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
  guerite m = new guerite();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
  transaction_number m = new transaction_number();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);     // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
projet_dash m= new projet_dash();
        jDesktopPane1.add(m);
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 

// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
 EB_B m = new EB_B();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
 eb_connector m = new eb_connector();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
    Achive m = new Achive();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);      // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem34ActionPerformed
Achive_check m = new Achive_check();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem34ActionPerformed

    private void jMenuItem37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem37ActionPerformed
Achive_delete_doc m = new Achive_delete_doc();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem37ActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
 budget_shows m= new budget_shows();
        jDesktopPane1.add(m);
    try {
        m.setMaximum(true);
    } catch (PropertyVetoException ex) {
        Logger.getLogger(homme.class.getName()).log(Level.SEVERE, null, ex);
    }
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
//upload();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem38ActionPerformed
courrier.courrier m = new courrier.courrier();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);      // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem38ActionPerformed

    private void jMenuItem39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem39ActionPerformed
archivages.achivage m = new archivages.achivage();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem39ActionPerformed

    private void jMenuItem76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem76ActionPerformed
journals.journal1 m = new journals.journal1();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem76ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
notification_mob_ap   m = new notification_mob_ap();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);      // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed
 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
         new homme().setVisible(true);
        
         
         
       //  new attendance().setVisible(true);
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("JTatoo".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(homme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(homme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(homme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(homme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

  

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             //    WebLookAndFeel.install(true);
                   //       WebLookAndFeel.install(true);
               //WebLookAndFeel.setDecorateAllWindows(true);
              // new homme().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JMenu admin;
    public static final javax.swing.JMenu admins = new javax.swing.JMenu();
    private javax.swing.JMenuItem bills;
    private javax.swing.JPanel boody;
    private javax.swing.JMenuItem budget_record;
    private javax.swing.JMenuItem budget_show;
    private javax.swing.JMenuItem chart_of_account;
    private javax.swing.JMenuItem code_budget;
    public static final javax.swing.JMenu comptabilite = new javax.swing.JMenu();
    public static final javax.swing.JMenu direction = new javax.swing.JMenu();
    public static final javax.swing.JMenu employee = new javax.swing.JMenu();
    private javax.swing.JMenuItem fin_trans;
    public static final javax.swing.JMenu guerite = new javax.swing.JMenu();
    public static final javax.swing.JLabel images = new javax.swing.JLabel();
    private javax.swing.JMenuItem invoice;
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu17;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem42;
    private javax.swing.JMenuItem jMenuItem45;
    private javax.swing.JMenuItem jMenuItem46;
    private javax.swing.JMenuItem jMenuItem47;
    private javax.swing.JMenuItem jMenuItem48;
    private javax.swing.JMenuItem jMenuItem49;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem50;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem66;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem70;
    private javax.swing.JMenuItem jMenuItem76;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem81;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JTextField jTextField1;
    public static final javax.swing.JMenu logistique = new javax.swing.JMenu();
    private javax.swing.JMenuItem new_project;
    private javax.swing.JMenuItem payday_advance;
    private javax.swing.JMenuItem payroll_taxation;
    private javax.swing.JMenuItem payslip_summary;
    public static final javax.swing.JMenuItem program = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem purchase_order;
    private javax.swing.JMenuItem rc_bank;
    public static final javax.swing.JMenu rh = new javax.swing.JMenu();
    private javax.swing.JMenuItem rport_sheet;
    public static final javax.swing.JMenu user = new javax.swing.JMenu();
    private com.alee.laf.menu.WebMenuBar webMenuBar1;
    // End of variables declaration//GEN-END:variables
}
