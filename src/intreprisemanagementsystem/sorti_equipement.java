/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import Palette.DataSearch;
import Palette.EventClick;
import Palette.PanelSearch;
import static intreprisemanagementsystem.logs.logistic_desk;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPopupMenu;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
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
 * @author DOSHE
 */
public class sorti_equipement extends javax.swing.JInternalFrame {
  Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
//DefaultTableModel mode;
 String rolls;
 String NUM_ID,CAT,NUM_IDS,SERIAL;
 DefaultTableModel mode; 
 private JPopupMenu menu;
    private PanelSearch search;
    //intreprisemanagementsystem.sorti_equipement.
    public sorti_equipement() {
        initComponents();
              con=JavaDbConnect.dbConnect();
        Call_ID_TO_BOMBOBOX2();
        Call_ID_TO_Chantier();
        webDateField1.setDate(new Date());
      call_table_num();
        
         menu = new JPopupMenu();
        search = new PanelSearch();
        menu.setBorder(BorderFactory.createLineBorder(new Color(164, 164, 164)));
        menu.add(search);
        menu.setFocusable(false);
        search.addEventClick(new EventClick() {
            @Override
            public void itemClick(DataSearch data) {
                menu.setVisible(false);
                rech.setText(data.getText());
                addStory(data.getText());
             //   System.out.println("Click Item  levi: " + data.getText());
                
                 try{
             String sql="select ITEM_ID,ITEM,DESCR,SERIAL,CAT,sum(debit),sum(credit),MODEL,STATUT from  eqipement_trans where DESCR=?";
         
            pst=con.prepareStatement(sql);
            pst.setString(1, data.getText());
            rs=pst.executeQuery();
          if(rs.next()){
                String add1 = rs.getString("ITEM_ID");
              itm_id.setText(add1);
              
              String add11 = rs.getString("ITEM");
              item.setText(add11);
              
               String add3 = rs.getString("DESCR");
               desc.setText(add3);
               
                String add33 = rs.getString("MODEL");
               model.setText(add33);
               
                String add333 = rs.getString("STATUT");
               statut.setText(add333);
               
                
               
               
               
            double a,b,c,C;   
            // ,CAT,NUM_IDS,  
               ITEM = rs.getString("ITEM");
               SERIAL = rs.getString("SERIAL");//SERIAL,ITEM_ID,CAT
            //   NUM_ID = rs.getString("ITEM_ID");
                CAT = rs.getString("CAT");
              
            
               a = rs.getDouble("sum(debit)");
               //qtyin.setText(add5);
               b = rs.getDouble("sum(credit)");
              c= a-b;
              C=c-b;
               qtyin.setText(""+c);
               
    qtyout.setText(""+C);
    if(qtyin.getText().equals("0.0")){
       qtyout.setText("-1.0");
          }else {
    qtyout.setText("0.0");
    }
              
               
               // String add7 = rs.getString("UNITY");
               //unity.setText(add7);
               
               
             
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        
         try{
               String sql="select STATUS from  ETAT where ITEM_ID=?";
         
            pst=con.prepareStatement(sql);
            pst.setString(1,itm_id.getText());
            rs=pst.executeQuery();
          if(rs.next()){
               
               
                String add333 = rs.getString("STATUS");
               statut.setText(add333);            
          }
         
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
                
            }

            @Override
            public void itemRemove(Component com, DataSearch data) {
                search.remove(com);
                removeHistory(data.getText());
                menu.setPopupSize(menu.getWidth(), (search.getItemSize() * 35) + 2);
                if (search.getItemSize() == 0) {
                    menu.setVisible(false);
                }
                System.out.println("Remove Item : " + data.getText());
            }
        });
    }
    
  
    public void Call_ID_TO_BOMBOBOX2()
    {{
      try{
            String sql="select * from employee order by NAME";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("NAME");
                  emp.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
    
     public void combobox2_from_materiel()
    {
        
     String tmp =(String)emp.getSelectedItem();
     String sql="select * from  employee where NAME=?";
     
        try{
         
          
            pst=con.prepareStatement(sql);
            pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
               String add2 = rs.getString("TITRE");
              post.setText(add2);
               
             
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
          
    } 
     
     public void Call_ID_TO_Chantier()
    {
         {
        try{
            String sql="select PROJET_NUM from projet";
           
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String sum=rs.getString("PROJET_NUM");
                  site.addItem(sum);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    }
    }
   public void combobox1_from_chantier()
    {
        
     String tmp =(String)site.getSelectedItem();
     String sql="select SUP from  projet where PROJET_NUM='"+tmp+"'";
     if(site.getSelectedItem().equals("Site")){
          showMessageDialog(null,"INALID DATA SELECTED");  
     }else{
    
        try{
         
          
            pst=con.prepareStatement(sql);
           // pst.setString(1, tmp);
            rs=pst.executeQuery();
            if(rs.next()){
                  
                  String add1 = rs.getString("SUP");
             respo.setText(add1);
           
          }
  
      }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);  
      }
        qty.enable(true);
     }   
    }    
   
     
        
         String ITEM;          
                  public void select_Jlist()
   {
       double a=0;
       double b=0;
       double c;
       double C;
      
       
        String tmp ="";
   //  String sql="select * from  materiaux where MATR_DESCR=?";
     
        try{
             String sql="select ITEM_ID,ITEM,DESCR,sum(debit),sum(credit),MODEL,STATUT from  eqipement_trans where ITEM_ID=?";
         
            pst=con.prepareStatement(sql);
            pst.setString(1, tmp);
            rs=pst.executeQuery();
          if(rs.next()){
                String add1 = rs.getString("ITEM_ID");
              itm_id.setText(add1);
              
              String add11 = rs.getString("ITEM");
              item.setText(add11);
              
               String add3 = rs.getString("DESCR");
               desc.setText(add3);
               
                String add33 = rs.getString("MODEL");
               model.setText(add33);
               
                String add333 = rs.getString("STATUT");
               statut.setText(add333);
               
                
               
               
               
               
               
               ITEM = rs.getString("ITEM");
              
            
               a = rs.getDouble("sum(debit)");
               //qtyin.setText(add5);
               b = rs.getDouble("sum(credit)");
              c= a-b;
              C=c-b;
               qtyin.setText(""+c);
               
    qtyout.setText(""+C);
    if(qtyin.getText().equals("0.0")){
       qtyout.setText("-1.0");
          }else {
    qtyout.setText("0.0");
    }
              
               
               // String add7 = rs.getString("UNITY");
               //unity.setText(add7);
               
               
             
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
        
         try{
               String sql="select STATUS from  ETAT where ITEM_ID=?";
         
            pst=con.prepareStatement(sql);
            pst.setString(1, tmp);
            rs=pst.executeQuery();
          if(rs.next()){
               
               
                String add333 = rs.getString("STATUS");
               statut.setText(add333);            
          }
         
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
        
        
   }
                  
                     public void etroll()
     {
         try{
            String sql="SELECT NUM from eqipement_trans WHERE CREDIT =1 ORDER BY NUM DESC LIMIT 1";
            

            
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,13);
                 String snum=rl.substring(13,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum;
                 
                 
                 
             }else{
                 rolls="EQ-STOCK-OUT/1001";
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
     }
                      public void numid()
     {
         try{
            String sql="SELECT NUM_ID FROM dotation ORDER BY NUM_ID DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM_ID");
                 int ln=rl.length();
                 String stxt=rl.substring(0,10);
                 String snum=rl.substring(10,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                NUM_ID=stxt+snum;
                 
                 
                 
             }else{
                 //rolls="FICHE/EB/2018/1";
                // rolls="EB1001";
                 NUM_ID="ID-EQ-OUT/1001";
             }
              }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
         }
                      public void num(){
     try{
            String sqls="select max(num) from  eqipement_trans WHERE CREDIT=1";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            while(rs.next()){
                String add1 = rs.getString("max(num)");
              id.setText(add1);
            }
            }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
    
    }
                      
                      
                       
                         public void save_TRANSs(){
 etroll();
 //numid();
 if(id.getText().isEmpty()){
 try{
      String sql="INSERT INTO eqipement_trans(ITEM_ID,ITEM,DESCR,SERIAL,MODEL,STATUT,DEBIT,CREDIT,NUM_ID,NUM,EMP,PURPOSE,DATES,SITE,CAT,DUREE,UNITY,PRICE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  pst=con.prepareStatement(sql);
    pst.setString(1,itm_id.getText());
    pst.setString(2,item.getText());
    pst.setString(3,desc.getText());
    pst.setString(4,SERIAL);
    pst.setString(5,model.getText());
     pst.setString(6,statut.getText());
     pst.setString(7,"0");
    pst.setString(8,qty.getText());
       pst.setString(9,"");
        pst.setString(10,rolls);
    pst.setString(11,emp.getSelectedItem().toString());
        pst.setString(12,purpose.getText());
        
     SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy.MM.dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
         pst.setString(13, addDate1);
        pst.setString(14,site.getSelectedItem().toString());
         pst.setString(15,CAT);
         SimpleDateFormat dateFormat11 = new SimpleDateFormat("yyyy.MM.dd");
         String addDate11 = dateFormat11.format(webDateField2.getDate());
         pst.setString(16, addDate11);
         
         pst.setString(17,unity.getText());
         pst.setString(18,"");
   
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 
 }else{
 
     try{
      String sql="INSERT INTO eqipement_trans(ITEM_ID,ITEM,DESCR,SERIAL,MODEL,STATUT,DEBIT,CREDIT,NUM_ID,NUM,EMP,PURPOSE,DATES,SITE,CAT,DUREE,UNITY,PRICE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  pst=con.prepareStatement(sql);
    pst.setString(1,itm_id.getText());
    pst.setString(2,item.getText());
    pst.setString(3,desc.getText());
    pst.setString(4,SERIAL);
    pst.setString(5,model.getText());
     pst.setString(6,statut.getText());
     pst.setString(7,"0");
    pst.setString(8,qty.getText());
       pst.setString(9,"");
        pst.setString(10,id.getText());
    pst.setString(11,emp.getSelectedItem().toString());
        pst.setString(12,purpose.getText());
        
     SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy.MM.dd");
         String addDate1 = dateFormat1.format(webDateField1.getDate());
         pst.setString(13, addDate1);
        pst.setString(14,site.getSelectedItem().toString());
         pst.setString(15,CAT);
         SimpleDateFormat dateFormat11 = new SimpleDateFormat("yyyy.MM.dd");
         String addDate11 = dateFormat11.format(webDateField2.getDate());
         pst.setString(16, addDate11);
         
         pst.setString(17,unity.getText());
         pst.setString(18,"");
   
    
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 
 }
    
 
 
 }
public void refresh(){
              itm_id.setText("");
              item.setText("");
               desc.setText("");
               model.setText("");
               statut.setText("");
               
               qtyin.setText("");
               qtyout.setText("");
               unity.setText("");
               respo.setText("");
               qty.setText("");
               
               webDateField1.setText("");
               post.setText("");
               purpose.setText("");
              id.setText("");
              
              site.setSelectedItem("Site");
              
//              images.setIcon(null);
             
               
                         
                         
                         }

public void refresh1(){
              itm_id.setText("");
              item.setText("");
               desc.setText("");
               model.setText("");
               statut.setText("");
               
               qtyin.setText("");
               qtyout.setText("");
               unity.setText("");
              // respo.setText("");
               qty.setText("1");
               
               //webDateField1.setText("");
               //post.setText("");
               //purpose.setText("");
             // id.setText("");
              
              //site.setSelectedItem("Site");
              
//              images.setIcon(null);
             
               
                         
                         
                         }


 public void call_table(){
      
           try{
           //`ITEM_ID`, `ITEM`, `DESCR`, `SERIAL`, `MODEL`, `STATUT`, `DEBIT`, `CREDIT`, `NUM_ID`, `NUM`, `EMP`, `PURPOSE`, `DATES`, `SITE`, `CAT
             String sql="SELECT `ITEM_ID`, `ITEM`, `DESCR`,`CREDIT`, `DUREE`,`EMP` FROM `eqipement_trans` WHERE NUM='"+id.getText()+"'";
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
      //  TableColumn col6=jTable1.getColumnModel().getColumn(6);
       
       
      
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(50);
      // col6.setPreferredWidth(50);
     
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
 
  public void call_table_clear(){
      
           try{
           
             String sql="SELECT `EMP_NAME`, `POST`, `EMP_CHANTIER`, `MATERIEL`,`QTY_OUT`,`DUREE` FROM `DOTATION` WHERE NUM='xxxxxxxxxxx'";
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
      //  TableColumn col6=jTable1.getColumnModel().getColumn(6);
       
       
      
       
       col0.setPreferredWidth(100);
       col1.setPreferredWidth(100);
       col2.setPreferredWidth(100);
       col3.setPreferredWidth(20);
       col4.setPreferredWidth(20);
       col5.setPreferredWidth(50);
      // col6.setPreferredWidth(50);
     
      
       
       
      // jTable1.setModel(mode);
       
     
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
      
      }
 
 
  public void report()
     {
          
     
             try{
                 String tmp=id.getText();
                 
                 
                 String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"Eqipement_out.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                 
                 
                // String report ="C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\maretiauxrepport.jrxml";
                // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\Fiche_Materiaux_out.jrxml");
                String sql="select * from eqipement_trans  where NUM='"+tmp+"'";
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
         
//setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
     }
public void call_table_num_Search(){
      
           try{
           
             String sql="SELECT distinct(NUM) FROM `eqipement_trans` where credit =1 and DESCR like '"+jTextField1.getText()+"%' or num like '"+jTextField1.getText()+"%' or emp like '"+jTextField1.getText()+"%'";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
    }catch(SQLException ex ){
     JOptionPane.showMessageDialog(null, ex);
}
    
                             } 

          public void select_jTable_small()
   {
       
       
        try{
          int row= jTable2.getSelectedRow();
         // String rows =jTable1.getName()
          String Table_click = (jTable2.getModel().getValueAt(row,0). toString());
          String sql = "SELECT * FROM eqipement_trans WHERE NUM= '"+Table_click+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        //`ITEM_ID`, `ITEM`, `DESCR`, `QTY`, `UNITY`, `DATES`, `NUM`, `NUM_ID`
               String add11= rs.getString("NUM");
         id.setText(add11);
         
          String add11S= rs.getString("EMP");
         emp.setSelectedItem(add11S);
         
         
         
         
          }
          
          
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
       
          call_table();
       }

public void call_table_num(){
      
           try{
           
             String sql="SELECT distinct(NUM) FROM `eqipement_trans` where credit <> 0";
     //  String sql="SELECT ID, `NUM_FACTURE`, `DATE_FACTURE`, `DESCRIPTION`, `DEBIT_QTY`, `DEBIT_PU`, `DEBIT_PT`, `DEBIT_TVA`, `CREDIT_QTY`, `CREDIT_PU`, `CREDIT_PT`, `CREDIT_TVA`, `DATES`, `NUM` FROM facture_fournisseur where compte='"+jComboBox6.getSelectedItem()+"'";
       pst = con.prepareStatement(sql);
      rs= pst.executeQuery();
      
       
       jTable2.setModel(DbUtils.resultSetToTableModel(rs));
       mode=new DefaultTableModel();
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);}
     } 
  
    private List<DataSearch> search(String search) {
        List<DataSearch> list = new ArrayList<>();
        // String sql="select ITEM_ID,ITEM,DESCR,sum(debit),sum(credit),MODEL,STATUT from  eqipement_trans where ITEM_ID=?";
        try {
            PreparedStatement p = con.prepareStatement("select DISTINCT DESCR, coalesce((select StoryID from story where CAT='Equipement' AND DESCR=StoryName limit 1),'') as Story from eqipement_trans where DESCR like ? order by Story DESC, DESCR limit 7");
            p.setString(1, "%" + search + "%");
            ResultSet r = p.executeQuery();
            while (r.next()) {
                String text = r.getString(1);
                boolean story = !r.getString(2).equals("");
                list.add(new DataSearch(text, story));
            }
            r.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void removeHistory(String text) {
        try {
            PreparedStatement p = con.prepareStatement("delete from story where StoryName=? and CAT='Equipement' limit 1");
            p.setString(1, text);
            p.execute();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


 

    private void addStory(String text) {
        try {
            boolean add = true;
            PreparedStatement p = con.prepareStatement("select StoryID from story where StoryName=? AND CAT='Equipement' limit 1");
            p.setString(1, text);
            ResultSet r = p.executeQuery();
            if (r.first()) {
                add = false;
            }
            r.close();
            p.close();
            if (add) {
                p = con.prepareStatement("insert into story (StoryName,CAT) values (?,?)");
                p.setString(1, text);
                p.setString(2,"Equipement");
                p.execute();
                p.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        respo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        site = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        rech = new Palette.MyTextField();
        qty = new javax.swing.JTextField();
        webDateField1 = new com.alee.extended.date.WebDateField();
        emp = new javax.swing.JComboBox<>();
        post = new javax.swing.JTextField();
        webDateField2 = new com.alee.extended.date.WebDateField();
        jScrollPane2 = new javax.swing.JScrollPane();
        purpose = new javax.swing.JEditorPane();
        id = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField1 = new Palette.TextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Maintenance_16px.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setRowHeight(30);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Eq. ID");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Eq.");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Qty_In");

        itm_id.setEditable(false);
        itm_id.setBackground(new java.awt.Color(240, 240, 241));
        itm_id.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        itm_id.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        item.setEditable(false);
        item.setBackground(new java.awt.Color(240, 240, 241));
        item.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        item.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        qtyin.setEditable(false);
        qtyin.setBackground(new java.awt.Color(240, 240, 241));
        qtyin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        qtyin.setForeground(new java.awt.Color(0, 204, 0));
        qtyin.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        qtyout.setEditable(false);
        qtyout.setBackground(new java.awt.Color(240, 240, 241));
        qtyout.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        qtyout.setForeground(new java.awt.Color(255, 51, 51));
        qtyout.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        unity.setEditable(false);
        unity.setBackground(new java.awt.Color(240, 240, 241));
        unity.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        unity.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Site");

        respo.setEditable(false);
        respo.setBackground(new java.awt.Color(240, 240, 241));
        respo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        respo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Resp");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Eq. No.");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Statut");

        model.setEditable(false);
        model.setBackground(new java.awt.Color(240, 240, 241));
        model.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        model.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        statut.setEditable(false);
        statut.setBackground(new java.awt.Color(240, 240, 241));
        statut.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        statut.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        desc.setEditable(false);
        desc.setBackground(new java.awt.Color(240, 240, 241));
        desc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        desc.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Descr.");

        site.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        site.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Site" }));
        site.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                sitePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(qtyin, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(qtyout, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unity))
                    .addComponent(respo)
                    .addComponent(site, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itm_id, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(model, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(desc)
                    .addComponent(item)
                    .addComponent(statut, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(itm_id, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(item, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(desc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(qtyin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(unity, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qtyout, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(site, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(respo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(model, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(statut, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        rech.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rech.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/Palette/search.png"))); // NOI18N
        rech.setSelectionColor(new java.awt.Color(204, 204, 204));
        rech.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rechMouseClicked(evt);
            }
        });
        rech.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rechKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rechKeyReleased(evt);
            }
        });

        qty.setEditable(false);
        qty.setBackground(new java.awt.Color(242, 242, 241));
        qty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        qty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        qty.setText("1");
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qtyKeyTyped(evt);
            }
        });

        webDateField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        emp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        emp.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        emp.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                empPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        post.setEditable(false);
        post.setBackground(new java.awt.Color(240, 240, 241));
        post.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        webDateField2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        purpose.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        purpose.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(purpose);

        id.setBackground(new java.awt.Color(0, 0, 0));
        id.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        id.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rech, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(webDateField1, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(post, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(webDateField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(id)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rech, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webDateField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(post, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(webDateField2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.setRowHeight(30);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable2);

        jTextField1.setLabelText("Recherche");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu3.setText("X");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu1.setText("File");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("New");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Save");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Delete");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenu4.setText("Print");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem4.setText("Print By Sheet");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem5.setText("Print All");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenu1.add(jMenu4);

        jMenuItem6.setText("List");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

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

        setBounds(30, 10, 932, 479);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if(itm_id.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
        }else if(item.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
        }else if(respo.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
        }else if(qty.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
        }else if(purpose.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
        }else if(post.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
        }else if(webDateField1.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
        }else if(qty.getText().equals("0")){
            JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
        }else{

            if( Double.parseDouble(qty.getText())>Double.parseDouble(qtyin.getText())){
                JOptionPane.showMessageDialog(null,"Wrong Data No enough Stock","Error",JOptionPane.ERROR_MESSAGE);

            }else{

              

                  //  save();
                  save_TRANSs();
                
                   num();
                   call_table();
                   call_table_num();
                   refresh1();
               
                }
            }
        
        
     

              // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
refresh(); 
call_table_clear();// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
report();       // report();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
    //    select_jTable();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void empPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_empPopupMenuWillBecomeInvisible

        combobox2_from_materiel();
        // TODO add your handling code here:
    }//GEN-LAST:event_empPopupMenuWillBecomeInvisible

    private void qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE)||c==KeyEvent.VK_DELETE )){
            evt.consume();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_qtyKeyTyped

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
num() ; 
call_table_num();// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
   // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void webComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_webComboBox1PopupMenuWillBecomeInvisible
//try{
//         String sqls="select num,EMP from  eqipement_trans where num='"+webComboBox1.getSelectedItem()+"'";
//          
//            pst=con.prepareStatement(sqls);
//           // pst.setString(1, tmp);
//            rs=pst.executeQuery();
//            if(rs.next()){
//                  
//                  String add1 = rs.getString("num");
//             id.setText(add1);
//              String add2 = rs.getString("EMP");
//             emp.setSelectedItem(add2);
//             
//           
//          }
//  
//      }catch(Exception ex){
//        JOptionPane.showMessageDialog(null,ex);  
//      }  
// 
//     
//        try{
//        String tmp =(String)emp.getSelectedItem();
//     String sql="select * from  employee where NAME=?"; 
//          
//            pst=con.prepareStatement(sql);
//            pst.setString(1, tmp);
//            rs=pst.executeQuery();
//            if(rs.next()){
//               String add2 = rs.getString("TITRE");
//              post.setText(add2);
//               
//             
//          }
//  
//      }catch(Exception ex){
//        JOptionPane.showMessageDialog(null,ex);  
//      }
//call_table();
// TODO add your handling code here:
    }//GEN-LAST:event_webComboBox1PopupMenuWillBecomeInvisible

    private void sitePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_sitePopupMenuWillBecomeInvisible
 combobox1_from_chantier();          // TODO add your handling code here:
    }//GEN-LAST:event_sitePopupMenuWillBecomeInvisible

    private void rechKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rechKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_UP) {
            search.keyUp();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            search.keyDown();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String text = search.getSelectedText();
            rech.setText(text);
            menu.setVisible(false);
        }           // TODO add your handling code here:
    }//GEN-LAST:event_rechKeyPressed

    private void rechKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rechKeyReleased
 if (evt.getKeyCode() != KeyEvent.VK_UP && evt.getKeyCode() != KeyEvent.VK_DOWN && evt.getKeyCode() != KeyEvent.VK_ENTER) {
            String text = rech.getText().trim().toLowerCase();
            search.setData(search(text));
            if (search.getItemSize() > 0) {
                //  * 2 top and bot border
                menu.show(rech, 0, rech.getHeight());
                menu.setPopupSize(menu.getWidth(), (search.getItemSize() * 50) + 2);
            } else {
                menu.setVisible(false);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_rechKeyReleased

    private void rechMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rechMouseClicked
rech.setText("");
        if (search.getItemSize() > 0) {
            menu.show(rech, 0, rech.getHeight());
            search.clearSelected();
        }         // TODO add your handling code here:
    }//GEN-LAST:event_rechMouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
select_jTable_small();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
call_table_num_Search();        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JTextField desc = new javax.swing.JTextField();
    private javax.swing.JComboBox<String> emp;
    private javax.swing.JTextField id;
    public static final javax.swing.JTextField item = new javax.swing.JTextField();
    public static final javax.swing.JTextField itm_id = new javax.swing.JTextField();
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private Palette.TextField jTextField1;
    public static final javax.swing.JTextField model = new javax.swing.JTextField();
    private javax.swing.JTextField post;
    private javax.swing.JEditorPane purpose;
    private javax.swing.JTextField qty;
    public static final javax.swing.JTextField qtyin = new javax.swing.JTextField();
    public static final javax.swing.JTextField qtyout = new javax.swing.JTextField();
    private Palette.MyTextField rech;
    private javax.swing.JTextField respo;
    private javax.swing.JComboBox<String> site;
    public static final javax.swing.JTextField statut = new javax.swing.JTextField();
    public static final javax.swing.JTextField unity = new javax.swing.JTextField();
    private com.alee.extended.date.WebDateField webDateField1;
    private com.alee.extended.date.WebDateField webDateField2;
    // End of variables declaration//GEN-END:variables
}
