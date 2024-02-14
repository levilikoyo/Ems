/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package journals;
import canalplus.cell.TableActionCellEditor_print;
import canalplus.cell.TableActionCellRender_print;
import canalplus.cell.TableActionEvent_print;
import com.raven.chart.ModelChart;
import ems_client_levi.EmslService1;
import ems_client_levi.ResponseModel;
import ems_client_levi.Stock;
import intreprisemanagementsystem.JavaDbConnect;
import intreprisemanagementsystem.wait;
import java.awt.Color;
import static java.awt.Color.black;
import static java.awt.Color.gray;
import static java.awt.Color.green;
import static java.awt.Color.red;
import static java.awt.Color.white;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.print.PrinterException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import raven.dynamicjasper.template.PageFormat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static sample.message.add_on_stock.scroll;
import sample.message.add_on_stock_bin;
import sample.notification.Item;
import sample.notification.ItemPacking;
import sample.notification.call_rfq;
import sample.notification.call_rfq1;
import sample.notification.count_list;
import sample.notification.num_entre_stock;
import sample.notification.num_sortie_stock;
import sample.notification.quotation;
import sample.notification.select_store;
import sample.swing.ModernScrollBarUI;

/**
 *
 * @author Doshe PC
 */
public class store_setting extends javax.swing.JInternalFrame {
Connection con=null;
PreparedStatement pst=null;
ResultSet rs= null;
DefaultTableModel mode;
EmslService1 api; 
String ID_TOKEN=null;
 List <ems_client_levi.Stock> pickinglist = new ArrayList();

private PageFormat pageFormat = new PageFormat(PageType.A4,0,0,PageOrientation.PORTRAIT);
   public store_setting() {
        initComponents();
        con=JavaDbConnect.dbConnect();
        dashboar();
        api = ems_client_levi.RetrofitClient.getAPIService();
               TableActionEvent_print event = new TableActionEvent_print(){
     

            @Override
            public void ondelete(int row) {
int rows= jTable10.getSelectedRow();
String    num = (jTable10.getModel().getValueAt(rows,1). toString());
      con=JavaDbConnect.dbConnect();   
  try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"UMCO_fiche_ind.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
             // String sql="select * from inventairemtr WHERE NUM_ID='"+num.getText()+"'";
              String sql="select * from  stock_mvm  WHERE ref='"+num+"'";
              //select * from  inventairemtr INNER JOIN materiaux_in ON inventairemtr.NUM=materiaux_in.NUM_ID
              
     HashMap param= new HashMap();
    param.put("nom", "BON D'ENTREE STOCK NO:");
    param.put("four",demandeur.getText());
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 
                
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
                 
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }        
            }
        };
    jTable10.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRender_print());
    jTable10.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditor_print(event));
        call();
        call_fiche();
    }
   public void call_fiche(){
jPanel56.removeAll();
jPanel56.repaint();
jPanel56.revalidate();
        setOpaque(false);
        JScrollBar sb = scroll.getVerticalScrollBar();
        sb.setOpaque(false);
        sb.setForeground(new Color(33, 140, 206));
        sb.setPreferredSize(new Dimension(8, 8));
        sb.setUI(new ModernScrollBarUI());
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);
        jPanel56.setLayout(new MigLayout("inset 0, fillx, wrap", "[fill]"));

        try{

            String sql="SELECT  * FROM  stock_rfq group by NUM ORDER BY DATE";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 String num_fiche = rs.getString("NUM");
                String fournisseur = rs.getString("STOCK_VEHICULE");
                String pt = rs.getString("Supply");
                String datescmd = rs.getString("DATE");

                // C:\Users\Doshe PC\Documents\NetBeansProjects\hand_book\src\sample\notification\fiche_medicale.jpg
               jPanel56.add(new Item(new ImageIcon(getClass().getResource("/sample/notification/download (1).png")), num_fiche,fournisseur,pt,datescmd));
            }
           //  con.close();
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
        
jPanel58.removeAll();
jPanel58.repaint();
jPanel58.revalidate();
        setOpaque(false);
        JScrollBar sbs = scroll.getVerticalScrollBar();
        sbs.setOpaque(false);
        sbs.setForeground(new Color(33, 140, 206));
        sbs.setPreferredSize(new Dimension(8, 8));
        sbs.setUI(new ModernScrollBarUI());
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);
        jPanel58.setLayout(new MigLayout("inset 0, fillx, wrap", "[fill]"));

        try{

            String sql="SELECT  * FROM stock_rfq where RFQNUM !='' and statut ='Pending' group by RFQNUM order by date";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 String num_fiche = rs.getString("RFQNUM");
                String fournisseur = rs.getString("STOCK_VEHICULE");
                String pt = rs.getString("Supply");
                String datescmd = rs.getString("DATE");

                // C:\Users\Doshe PC\Documents\NetBeansProjects\hand_book\src\sample\notification\fiche_medicale.jpg
            jPanel58.add(new ItemPacking(new ImageIcon(getClass().getResource("/sample/notification/download (1).png")), num_fiche,fournisseur,pt,datescmd));
            }
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
        
jPanel59.removeAll();
jPanel59.repaint();
jPanel59.revalidate();
        setOpaque(false);
       JScrollBar sbss = scroll.getVerticalScrollBar();
        sbss.setOpaque(false);
        sbss.setForeground(new Color(33, 140, 206));
        sbss.setPreferredSize(new Dimension(8, 8));
        sbss.setUI(new ModernScrollBarUI());
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);
        jPanel59.setLayout(new MigLayout("inset 0, fillx, wrap", "[fill]"));

        try{
             String sql="SELECT  * FROM  stock_mvm where QTY_C='0.00' group by NUM order by dates";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 String num_fiche = rs.getString("NUM");
                String fournisseur = rs.getString("RESP");
                String pt = rs.getString("NOMS");
                String datescmd = rs.getString("DATES");

          jPanel59.add(new num_entre_stock(new ImageIcon(getClass().getResource("/sample/notification/download (1).png")),num_fiche,fournisseur,pt,datescmd));
            }
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
        
jPanel51.removeAll();
jPanel51.repaint();
jPanel51.revalidate();
        setOpaque(false);
      // JScrollBar sbss = scroll.getVerticalScrollBar();
        sbss.setOpaque(false);
        sbss.setForeground(new Color(33, 140, 206));
        sbss.setPreferredSize(new Dimension(8, 8));
        sbss.setUI(new ModernScrollBarUI());
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);
        jPanel51.setLayout(new MigLayout("inset 0, fillx, wrap", "[fill]"));
        
          try{
             String sql="SELECT  * FROM  stock_mvm where QTY_D='0.00' group by NUM order by dates";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 String num_fiche = rs.getString("NUM");
                String fournisseur = rs.getString("RESP");
                String pt = rs.getString("NOMS");
                String datescmd = rs.getString("DATES");

          jPanel51.add(new num_sortie_stock(new ImageIcon(getClass().getResource("/sample/notification/download (1).png")),num_fiche,fournisseur,pt,datescmd));
            }
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
          
jPanel61.removeAll();
jPanel61.repaint();
jPanel61.revalidate();
        setOpaque(false);
     //   JScrollBar sbs = scroll.getVerticalScrollBar();
        sbs.setOpaque(false);
        sbs.setForeground(new Color(33, 140, 206));
        sbs.setPreferredSize(new Dimension(8, 8));
        sbs.setUI(new ModernScrollBarUI());
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);
        jPanel61.setLayout(new MigLayout("inset 0, fillx, wrap", "[fill]"));

        try{

            String sql="SELECT  NUM_FICHE,NAME,SUM(PT),DATE FROM  recu where TRANSACTION='QUOTATION' group by NUM_FICHE order by date";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 String num_fiche = rs.getString("NUM_FICHE");
                String fournisseur = rs.getString("NAME");
                String pt = rs.getString("SUM(PT)");
                String datescmd = rs.getString("DATE");

                // C:\Users\Doshe PC\Documents\NetBeansProjects\hand_book\src\sample\notification\fiche_medicale.jpg
            jPanel61.add(new quotation(new ImageIcon(getClass().getResource("/sample/notification/download (1).png")), num_fiche,fournisseur,pt,datescmd));
            }
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
      
jPanel69.removeAll();
jPanel69.repaint();
jPanel69.revalidate();
        setOpaque(false);
     //   JScrollBar sbs = scroll.getVerticalScrollBar();
        sbs.setOpaque(false);
        sbs.setForeground(new Color(33, 140, 206));
        sbs.setPreferredSize(new Dimension(8, 8));
        sbs.setUI(new ModernScrollBarUI());
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);
        jPanel69.setLayout(new MigLayout("inset 0, fillx, wrap", "[fill]"));

        try{

            String sql="SELECT  * FROM   stock_count  group by DESCRIPTION order by date";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 String num_fiche = rs.getString("DESCRIPTION");
                String fournisseur = rs.getString("STORE");
                String pt = rs.getString("FABRICANT");
                String datescmd = rs.getString("DATE");

                // C:\Users\Doshe PC\Documents\NetBeansProjects\hand_book\src\sample\notification\fiche_medicale.jpg
            jPanel69.add(new count_list(new ImageIcon(getClass().getResource("/sample/notification/download (1).png")), num_fiche,fournisseur,pt,datescmd));
            }
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}  

jPanel72.removeAll();
jPanel72.repaint();
jPanel72.revalidate();
        setOpaque(false);
       // JScrollBar sb = scroll.getVerticalScrollBar();
        sb.setOpaque(false);
        sb.setForeground(new Color(33, 140, 206));
        sb.setPreferredSize(new Dimension(8, 8));
        sb.setUI(new ModernScrollBarUI());
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);
       jPanel72.setLayout(new MigLayout("inset 0, fillx, wrap", "[fill]"));

        try{

            String sql="SELECT  * FROM  stock_rfq  where RFQNUM != '' group by RFQNUM ORDER BY DATE";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 String num_fiche = rs.getString("RFQNUM");
                String fournisseur = rs.getString("STOCK_VEHICULE");
                String pt = rs.getString("Supply");
                String datescmd = rs.getString("DATE");

                // C:\Users\Doshe PC\Documents\NetBeansProjects\hand_book\src\sample\notification\fiche_medicale.jpg
             jPanel72.add(new call_rfq1(new ImageIcon(getClass().getResource("/sample/notification/download (1).png")), num_fiche,fournisseur,pt,datescmd));
            }
           //  con.close();
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}     
   } 
    
public void save(){
    try{
          String sql="select * from stock_db where NUM='"+item_id.getText()+"' ";
    
            pst=con.prepareStatement(sql);
         //   pst.setString(1,item_id.getText());
            rs=pst.executeQuery();
          if(rs.next()){
              
            try{
             
    String sql1="UPDATE stock_db SET NOM=?,FABRICANT=?,PRIXACHAT=?,PRIXVENTE=?,ALERT=?,MAX_ALERT=?,PARTCODE=?,CATEGORY=?,OLDNUM=? WHERE  NUM ='"+item_id.getText()+"'";
     
    pst=con.prepareStatement(sql1);
    pst.setString(1,nom.getText());
    pst.setString(2,fabr.getText());
    pst.setString(3,prixachat.getText());
    pst.setString(4,prixvente.getText());
    pst.setString(5,alert_min.getText());
    pst.setString(6,Max_alert.getText());
    pst.setString(7,sapcode.getText());
    pst.setString(8,cat.getSelectedItem().toString());
    pst.setString(9,oldpart.getText());
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}    
          }else{
  try{
    String sqls="INSERT INTO stock_db(NUM, NOM,FABRICANT,PRIXACHAT,PRIXVENTE,ALERT,MAX_ALERT,PARTCODE,CATEGORY,OLDNUM) VALUES (?,?,?,?,?,?,?,?,?,?)";
     
    pst=con.prepareStatement(sqls);
    pst.setString(1,item_id.getText());
    pst.setString(2,nom.getText());
    pst.setString(3,fabr.getText());
    pst.setString(4,prixachat.getText());
    pst.setString(5,prixvente.getText());
    pst.setString(6,alert_min.getText());
    pst.setString(7,Max_alert.getText());
     pst.setString(8,sapcode.getText());
    pst.setString(9,cat.getSelectedItem().toString());
    pst.setString(10,oldpart.getText());
   
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
          }  
    

call();

}catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
}
//File file = showFileChooser();

public void dashboar(){
   chart.addLegend("Income", new Color(12, 84, 175), new Color(0, 108, 247));
        chart.addLegend("Expense", new Color(54, 4, 143), new Color(104, 49, 200));
        chart.addLegend("Profit", new Color(5, 125, 0), new Color(95, 209, 69));
        chart.addLegend("Cost", new Color(186, 37, 37), new Color(241, 100, 120));
        chart.addData(new ModelChart("January", new double[]{500, 200, 80, 89}));
        chart.addData(new ModelChart("February", new double[]{600, 750, 90, 150}));
        chart.addData(new ModelChart("March", new double[]{200, 350, 460, 900}));
        chart.addData(new ModelChart("April", new double[]{480, 150, 750, 700}));
        chart.addData(new ModelChart("May", new double[]{350, 540, 300, 150}));
        chart.addData(new ModelChart("June", new double[]{190, 280, 81, 200}));
        chart.start();
        lineChart.addLegend("Income", new Color(12, 84, 175), new Color(0, 108, 247));
        lineChart.addLegend("Expense", new Color(54, 4, 143), new Color(104, 49, 200));
        lineChart.addLegend("Profit", new Color(5, 125, 0), new Color(95, 209, 69));
        lineChart.addLegend("Cost", new Color(186, 37, 37), new Color(241, 100, 120));
        lineChart.addData(new ModelChart("January", new double[]{500, 200, 80, 89}));
        lineChart.addData(new ModelChart("February", new double[]{600, 750, 90, 150}));
        lineChart.addData(new ModelChart("March", new double[]{200, 350, 460, 900}));
        lineChart.addData(new ModelChart("April", new double[]{480, 150, 750, 700}));
        lineChart.addData(new ModelChart("May", new double[]{350, 540, 300, 150}));
        lineChart.addData(new ModelChart("June", new double[]{190, 280, 81, 200}));
        lineChart.start();
        progress1.start();
        progress2.start();
        progress3.start();
}
public void call(){
    
    try{
          String sql="SELECT * FROM caisse_dispacting where access='yes' and cat1='depot' and NAME='"+Home_page.home.user.getText()+"'";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("CAT");
             storemenu.setText(sums);
            
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
 
        try{

            String sql="SELECT NUM AS 'References Numbers',NOM as 'Descptions' FROM  stock_db order by nom";  
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();

            TableColumn col0=jTable1.getColumnModel().getColumn(0);
            TableColumn col1=jTable1.getColumnModel().getColumn(1);
            
            
            col0.setPreferredWidth(100);
            col1.setPreferredWidth(150);
            

        }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);}
        
        try{

            String sql="SELECT BIN as 'Bin Location',NUM as 'References Numbers',NOM as 'Descptions',STORE as 'Store Location',RANGES as Rank,ETAGERE AS Shelves,Ligne,Bins,PROFONDEUR AS Deep,FABRICANT as 'Maker/Fabricant' FROM  stock_dispacting WHERE STORE='"+storemenu.getText()+"' order by nom";  
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();

            

        }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);}
         
        try{

            String sql="SELECT NUM as 'References Numabers',NOM as 'Descptions' FROM  stock_db  group by NUM order by nom";  
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

            jTable4.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();

            

        }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);}
        
        try{

            String sql="SELECT distinct (NUM) as 'References Numbers',NOM as 'Descptions' FROM  stock_db  order by nom";  
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
           // tb_itms_cmd.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();

            

        }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);}
        try{

            String sql="SELECT BIN as 'Bin location', NUM as 'References Numbers',NOM as 'Descptions' FROM  stock_dispacting where STORE='"+storemenu.getText()+"' group by NUM order by nom";  
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

          //  jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            tb_itms_cmd.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();
 }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);}
         try{

            String sql="SELECT BIN as 'Bin location', NUM as 'References Numbers',NOM as 'Descptions' FROM  stock_dispacting where STORE='"+storemenu.getText()+"' group by NUM order by nom";  
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

          //  jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            tb_itms_cmd1.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();
 }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);}
        
 /*       
  try{
                                        //`BIN`, `REF`, `NOM`, `DEPOT`, `QTY_D`, `NUM`
            String sql="SELECT Bin as 'Bin location',REF as 'References Numbers',NOM as 'Descptions',(SUM(QTY_D)-SUM(QTY_C)) as Quantities,Fabricant,DEPOT as Store FROM  stock_mvm  GROUP BY REF order by nom";  
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();

           

            TableColumn col0=jTable7.getColumnModel().getColumn(0);
            TableColumn col1=jTable7.getColumnModel().getColumn(1);
            TableColumn col2=jTable7.getColumnModel().getColumn(2);
            TableColumn col3=jTable7.getColumnModel().getColumn(3);
            TableColumn col4=jTable7.getColumnModel().getColumn(4);
            TableColumn col5=jTable7.getColumnModel().getColumn(5);
            
            
            col0.setPreferredWidth(100);
            col1.setPreferredWidth(150);
             col2.setPreferredWidth(150);
            col3.setPreferredWidth(100);
             col4.setPreferredWidth(150);
             col5.setPreferredWidth(150);
            

        }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);} 
        */
   try{
       String sql="SELECT REF as 'References Numbers',NOM as 'Descptions' FROM  stock_mvm  where DEPOT='"+storemenu.getText()+"' GROUP BY REF order by nom";  
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

            jTable11.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();

           

            TableColumn col0=jTable11.getColumnModel().getColumn(0);
            TableColumn col1=jTable11.getColumnModel().getColumn(1);
            
            
            col0.setPreferredWidth(100);
            col1.setPreferredWidth(150);
             

        }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);}  
        
try{
          String sql="SELECT * FROM stock_bin_location where cat='depot' order by Nom";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("Nom");
             
              store_setting.depot.addItem(sums);
              depots.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
try{
          String sql="SELECT * FROM stock_bin_location where cat='Category' order by Nom";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                //String sum=rs.getString("nom");
                String sums=rs.getString("Nom");
             
              store_setting.cat.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }

 
  try{
      
          String sql="SELECT * FROM ohada where SUBSTR='40' order by NAME";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                String sums=rs.getString("NAME");
             
              respo.addItem(sums);
              fourn_cmd.addItem(sums);
               fourn_cmd2.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }  
  
   try{
       client.removeAllItems();
       client.addItem("- Client -");
       client.addItem("Self");
       client.addItem("Main Store");
       client.addItem("Sub Store");
    String sql="SELECT * FROM ohada where SUBSTR='41' order by NAME";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
            while(rs.next()){
                String sums=rs.getString("NAME");
             
              client.addItem(sums);
              fourn_cmd1.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); } 
   
   
      try{
         
          String sql = "SELECT count(num) FROM  stock_db ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        jLabel12.setText("Total Product: "+rs.getString("count(num)"));
        jLabel103.setText("Total Product: "+rs.getString("count(num)"));
        }
         
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
      
         
         
         //Select * from rapprochement_banque INNER JOIN bank ON rapprochement_banque.BANK=bank.REF
             try{
                 String sql = "SELECT BIN as 'Bin Loc',REF as 'Ref. Numbers',stock_mvm.NOM as 'Descriptions',DEPOT as 'Store Location', Format(SUM(QTY_D)-SUM(QTY_C),2) AS Quantity,Format(PRIXACHAT,2) as 'Cost price',Format((SUM(QTY_D)-SUM(QTY_C))*PRIXACHAT,2) as 'Total value' ,stock_mvm.FABRICANT as 'Fabricant/Maker' FROM  stock_mvm inner join stock_db on stock_mvm.REF=stock_db.NUM GROUP BY REF";
          pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

            jTable9.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();
         
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
             
  
             
  
       try{
        //  String sql="SELECT `ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin` WHERE  `ORIENTATION`='FINANCE' AND ORIENTATION2='Fin' and buss='"+journal1.buss.getText()+"'";   pst = con.prepareStatement(sql);
           
        PreparedStatement ps = con.prepareStatement("Select * from  stock_mvm ");
        ResultSet rs=ps.executeQuery();
        DefaultTableModel tm = (DefaultTableModel)jTable10.getModel();
        tm.setRowCount(0);

      while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
            Object o[] = {rs.getString("BIN"),rs.getString("REF")};
            tm.addRow(o);



        }
      mode=new DefaultTableModel();

           

            TableColumn col0=jTable10.getColumnModel().getColumn(0);
            TableColumn col1=jTable10.getColumnModel().getColumn(1);
            TableColumn col2=jTable10.getColumnModel().getColumn(2);
            
            
            col0.setPreferredWidth(150);
            col1.setPreferredWidth(150);
             col2.setPreferredWidth(50);
            
   
    }
    catch(Exception e){

        JOptionPane.showMessageDialog(null,"Error in Employee Grid View..... "+e);
    }
  
       
}

public void call_SEARCH(){
 
        try{

            String sql="SELECT NUM AS REFERENCE,NOM AS DESCRIPTION FROM  stock_db where NUM LIKE '"+textField1.getText()+"%' OR NOM LIKE '"+textField1.getText()+"%' OR CATEGORY LIKE '"+textField1.getText()+"%' order by nom";  
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();

            TableColumn col0=jTable1.getColumnModel().getColumn(0);
            TableColumn col1=jTable1.getColumnModel().getColumn(1);
            
            
            col0.setPreferredWidth(100);
            col1.setPreferredWidth(150);
            

        }catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, ex);
        }
        
       
}
public void selecttable(){
 int row= jTable1.getSelectedRow();
          String Table_click = (jTable1.getModel().getValueAt(row,0). toString());
//          jLabel12.setText(Table_click);
        try{
         
          String sql = "SELECT * FROM stock_db WHERE NUM= '"+Table_click+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              String add1 = rs.getString("NUM");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              item_id.setText(add1);
              String APPROV= rs.getString("NOM");
              nom.setText(APPROV); 
              
              String APPROVs= rs.getString("FABRICANT");
              fabr.setText(APPROVs); 
              String APPROVx= rs.getString("PRIXACHAT");
             prixachat.setText(APPROVx); 
              String APPROVd= rs.getString("PRIXVENTE");
              prixvente.setText(APPROVd); 
               String APPROVw= rs.getString("ALERT");
              alert_min.setText(APPROVw);
              String APPROVe= rs.getString("MAX_ALERT");
              Max_alert.setText(APPROVe); 
              
              
              //`PARTCODE`, `CATEGORY`, `OLDNUM`
                  String APPROVQ= rs.getString("PARTCODE");
             sapcode.setText(APPROVQ); 
               String APPROVQQ= rs.getString("CATEGORY");
             cat.setSelectedItem(APPROVQQ);
              String APPROVQQQ= rs.getString("OLDNUM");
             oldpart.setText(APPROVQQQ);
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }

}

 public void delete(){
 
     try{
        String sql = "DELETE FROM  stock_db WHERE 	NUM=?";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,item_id.getText());
         pst.executeUpdate();
              
     }catch(SQLException | HeadlessException ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
 call();
 }
 
 //>>>>>>>>>>>import
   public void imports(){

        CAISSE_Model();
       DefaultTableModel excels= (DefaultTableModel)jTable1.getModel(); 
        
        FileInputStream excelFILS=null;
        BufferedInputStream excelBIS=null;
        XSSFWorkbook excelImportWorkbook;
        
        
        String curentDirectiryPath="C:\\Users\\Dosh\\Desktop";
        JFileChooser excelFileChooserImport = new JFileChooser(curentDirectiryPath);
     // excelFileChooserImport.showOpenDialog(null);
      
      int excelchooser=excelFileChooserImport.showOpenDialog(null);
      
      if(excelchooser== JFileChooser.APPROVE_OPTION){
      try{
      File exceleFile =excelFileChooserImport.getSelectedFile();
      excelFILS=new FileInputStream(exceleFile);
      excelBIS= new BufferedInputStream(excelFILS);
       excelImportWorkbook = new XSSFWorkbook(excelBIS);
      XSSFSheet excelSheet = excelImportWorkbook.getSheetAt(0);
      
     
      for(int i=1;i<excelSheet.getLastRowNum(); i++){
      
      XSSFRow excelRow = excelSheet.getRow(i);
      XSSFCell cell = excelRow.getCell(0);
      XSSFCell cell1 = excelRow.getCell(1);
      XSSFCell cell2 = excelRow.getCell(2);
      XSSFCell cell3 = excelRow.getCell(3);
      XSSFCell cell4 = excelRow.getCell(4);
      XSSFCell cell5 = excelRow.getCell(5);
      XSSFCell cell6 = excelRow.getCell(6);
      XSSFCell cell7 = excelRow.getCell(7);
      XSSFCell cell8 = excelRow.getCell(8);
      XSSFCell cell9 = excelRow.getCell(9);
      XSSFCell cell10 = excelRow.getCell(10);
      XSSFCell cell11 = excelRow.getCell(11);
      XSSFCell cell12 = excelRow.getCell(12);
       XSSFCell cell13 = excelRow.getCell(13);
       
        XSSFCell cell14 = excelRow.getCell(14);
      XSSFCell cell15 = excelRow.getCell(15);
       XSSFCell cell16 = excelRow.getCell(16);
     
     excels.addRow(new Object[]{cell,cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10,cell11,cell12,cell13,cell14,cell15,cell16}); 
      }
      
      }catch(FileNotFoundException ex){
      ex.printStackTrace();
      }     catch (IOException ex) {
               ex.printStackTrace();
            }
      
      }

}
  public void CAISSE_Model(){
        mode=new DefaultTableModel();
        mode.addColumn("BIN");    
        mode.addColumn("REF");
        mode.addColumn("DESC");
        mode.addColumn("STRE");
        mode.addColumn("RAG");
        mode.addColumn("ETA");
        mode.addColumn("LN");
        mode.addColumn("BIN");
        mode.addColumn("PROF");
        mode.addColumn("FAB");
        mode.addColumn("PA");
        mode.addColumn("PV");
        mode.addColumn("ALRT");
        mode.addColumn("MAX_ALERT");
        mode.addColumn("SAP");
        mode.addColumn("CAT");
        mode.addColumn("OLD");
   jTable1.setModel(mode);

  } 
public void Save_CAISSE_usd(){
DefaultTableModel excels= (DefaultTableModel)jTable1.getModel(); 
   String Bin= null,Ref = null, Nom = null,Sap=null,Cat=null,Old=null,Store= null ,Ranges= null ,Etagere= null,Ligne= null,Bins= null,Profondeur= null,Prixachat= null,Prixvente= null,Fabricant= null,Alert= null,Max_Alert= null;
   
    for(int i=0; i < excels.getRowCount();i++){
      Bin = excels.getValueAt(i,0).toString();
      Ref= excels.getValueAt(i,1). toString();
      Nom = excels.getValueAt(i,2). toString();
      Store = excels.getValueAt(i,3). toString();
      Ranges = excels.getValueAt(i,4). toString();
      Etagere= excels.getValueAt(i,5). toString();
      Ligne = excels.getValueAt(i,6). toString();
      Bins= excels.getValueAt(i,7). toString();
      Profondeur = excels.getValueAt(i,8). toString();
      Fabricant = excels.getValueAt(i,9). toString();
      Prixachat = excels.getValueAt(i,10). toString();
      Prixvente = excels.getValueAt(i,11). toString();
      
      Alert = excels.getValueAt(i,12). toString();
       Max_Alert = excels.getValueAt(i,13). toString();
       
    Sap = excels.getValueAt(i,14). toString();
    Cat = excels.getValueAt(i,15). toString();
    Old = excels.getValueAt(i,16). toString();
  
              try {
       String sqls="INSERT INTO stock_dispacting (BIN,NUM,NOM,STORE,RANGES,ETAGERE,LIGNE,BINS,PROFONDEUR,FABRICANT) VALUES (?,?,?,?,?,?,?,?,?,?)";
        pst = con.prepareStatement(sqls);  
        pst.setString(1, Bin);
        pst.setString(2,Ref);
         pst.setString(3,Nom);
         pst.setString(4,Store);
         pst.setString(5,Ranges);
         pst.setString(6,Etagere);
         pst.setString(7,Ligne);
         pst.setString(8,Bins);
         pst.setString(9,Profondeur);
         pst.setString(10,Fabricant);
         
          pst.executeUpdate();
    } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}

try{
         
          String sql = "SELECT * FROM stock_db WHERE NUM= '"+Ref+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
             
          }else{
            try {
       String sqls="INSERT INTO stock_db (NUM,NOM,FABRICANT,PRIXACHAT,PRIXVENTE,ALERT,MAX_ALERT,PARTCODE,CATEGORY,OLDNUM) VALUES (?,?,?,?,?,?,?,?,?,?)";
        pst = con.prepareStatement(sqls);  
        pst.setString(1,Ref);
         pst.setString(2,Nom);
          pst.setString(3,Fabricant);
         pst.setString(4,Prixachat);
         pst.setString(5,Prixvente);
         pst.setString(6,Alert);
         pst.setString(7,Max_Alert);
         
           pst.setString(8,Sap);
         pst.setString(9,Cat);
         pst.setString(10,Old);
       
         
          pst.executeUpdate();
    } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());} 
          }
         
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
              
  
    } //END FOR
     JOptionPane.showMessageDialog(null,"Tranction Saved");
    }

    public void selectontable() {

        TableModel model1 = jTable4.getModel();
        int indexs[] = jTable4.getSelectedRows();
        Object[] row = new Object[6];
        DefaultTableModel model2 = (DefaultTableModel) jTable3.getModel();
        
     
        
        
        for (int i = 0; i < indexs.length; i++) {
         String num =  jTable4.getValueAt(i, 0).toString(); 
         String Bin = null,Store = null,QTY = null,statut=null;   
         
         try{
          String sql = "SELECT * from caisse_dispacting  WHERE NAME= '"+Home_page.home.user.getText()+"' and CAT='"+storemenu.getText()+"' AND ACCESS='Yes'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
            statut = rs.getString("STATUT");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
         }
         
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);} 
     //    JOptionPane.showMessageDialog(null,statut);
         if(statut.equals("Main Store")){
         try{
         
          String sql = "SELECT BIN,DEPOT,(SUM(QTY_D)-SUM(QTY_C)) AS QTYS FROM stock_mvm WHERE REF= '"+model1.getValueAt(indexs[i], 0)+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              Bin = rs.getString("BIN");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
             // item_id.setText(add1);
              Store= rs.getString("DEPOT");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              QTY= rs.getString("QTYS");  
          }
         
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
         }else if(statut.equals("Sub Store")){
         try{
         
          String sql = "SELECT STOCK_SUB_BIN,DEPOT,(SUM(STOCK_SUB)-SUM(STOCK_SUBC)) AS QTYS FROM stock_mvm WHERE REF= '"+model1.getValueAt(indexs[i], 0)+"' AND DEPOT='"+storemenu.getText()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              Bin = rs.getString("STOCK_SUB_BIN");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
             // item_id.setText(add1);
              Store= rs.getString("DEPOT");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              QTY= rs.getString("QTYS");  
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         }
              
            
            row[0] = Bin;
            row[1] = model1.getValueAt(indexs[i], 0);
            row[2] = model1.getValueAt(indexs[i], 1);
            row[3] = QTY; 
            row[4] = "0.0";
            row[5]= Store;
//        row[3]= model1.getValueAt(indexs[i],3);
            model2.addRow(row);

            TableColumn col0 = jTable3.getColumnModel().getColumn(0);
            TableColumn col1 = jTable3.getColumnModel().getColumn(1);
            TableColumn col2 = jTable3.getColumnModel().getColumn(2);
            TableColumn col3 = jTable3.getColumnModel().getColumn(3);
            TableColumn col4=jTable3.getColumnModel().getColumn(4);
            TableColumn col5=jTable3.getColumnModel().getColumn(5);

            col0.setPreferredWidth(100);
            col1.setPreferredWidth(100);
            col2.setPreferredWidth(200);
            col3.setPreferredWidth(50);
            col4.setPreferredWidth(50);
            col5.setPreferredWidth(100);
        }

    }

    public void selectontablemoin() {

        int indexs[] = jTable3.getSelectedRows();

        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        for (int i = 0; i < indexs.length; i++) {

            int getSelectedRowsForDeletion = jTable3.getSelectedRow();
            model.removeRow(getSelectedRowsForDeletion);

        }

    }

 
    
    
    // IMPORT ENTREE STOCK
    
    
    //>>>>>>>>>>>import
    String rolls=null;
    public void etroll()
     {
         try{
            String sql="SELECT NUM FROM  stock_mvm where QTY_C = '0.00' and STOCK_SUB='0.0' ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,3);
                 String snum=rl.substring(3,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum ;
          
             }else{
                 rolls="13-1000000001";
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }

     }
   public void importENTREE(){

         CAISSE_Model_entree();
       DefaultTableModel excels= (DefaultTableModel)jTable6.getModel(); 
        
        FileInputStream excelFILS=null;
        BufferedInputStream excelBIS=null;
        XSSFWorkbook excelImportWorkbook;
        
        
        String curentDirectiryPath="C:\\Users\\Dosh\\Desktop";
        JFileChooser excelFileChooserImport = new JFileChooser(curentDirectiryPath);
     // excelFileChooserImport.showOpenDialog(null);
      
      int excelchooser=excelFileChooserImport.showOpenDialog(null);
      
      if(excelchooser== JFileChooser.APPROVE_OPTION){
      try{
      File exceleFile =excelFileChooserImport.getSelectedFile();
      excelFILS=new FileInputStream(exceleFile);
      excelBIS= new BufferedInputStream(excelFILS);
       excelImportWorkbook = new XSSFWorkbook(excelBIS);
      XSSFSheet excelSheet = excelImportWorkbook.getSheetAt(0);
      
     
      for(int i=1;i<excelSheet.getLastRowNum(); i++){
      
      XSSFRow excelRow = excelSheet.getRow(i);
      XSSFCell cell = excelRow.getCell(0);
      XSSFCell cell1 = excelRow.getCell(1);
      XSSFCell cell2 = excelRow.getCell(2);
      XSSFCell cell3 = excelRow.getCell(3);
      XSSFCell cell4 = excelRow.getCell(4);
      XSSFCell cell5 = excelRow.getCell(5);
      XSSFCell cell6 =  excelRow.getCell(6);
      XSSFCell cell7 =  excelRow.getCell(7);
      XSSFCell cell8 =  excelRow.getCell(8);
      XSSFCell cell9 =  excelRow.getCell(9);
      XSSFCell cell10 = excelRow.getCell(10);
      XSSFCell cell11 = excelRow.getCell(11);
      XSSFCell cell12 = excelRow.getCell(12);
      XSSFCell cell13 = excelRow.getCell(13);
      XSSFCell cell14 = excelRow.getCell(14);
      XSSFCell cell15 = excelRow.getCell(15);
      XSSFCell cell16 = excelRow.getCell(16);
      XSSFCell cell17 = excelRow.getCell(17);
      XSSFCell cell18 = excelRow.getCell(18);
      XSSFCell cell19 = excelRow.getCell(19);
      XSSFCell cell20 = excelRow.getCell(20);
     
     excels.addRow(new Object[]{cell,cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10,cell11,cell12,cell13,cell14,cell15,cell16,cell17,cell18,cell19,cell20}); 
      }
      
      }catch(FileNotFoundException ex){
      ex.printStackTrace();
      }     catch (IOException ex) {
               ex.printStackTrace();
            }
      
      }

}
  public void CAISSE_Model_entree(){
        mode=new DefaultTableModel();
        mode.addColumn("FICHE");    
        mode.addColumn("FOUR");
        mode.addColumn("CONTACT");
        mode.addColumn("BIN");
        mode.addColumn("REF");
        mode.addColumn("DESCR");
        mode.addColumn("DEPOT");
        mode.addColumn("RANGE");
        mode.addColumn("ETAGERE");
        mode.addColumn("LIGNE");
        mode.addColumn("BINS");
        mode.addColumn("PROF");
        mode.addColumn("QTY MAIN");
        mode.addColumn("MAKER");
        mode.addColumn("DATE");
        mode.addColumn("NOM");
        mode.addColumn("SAP");
        mode.addColumn("CAT");
        mode.addColumn("OLD");
        mode.addColumn("QTY SUB");
        mode.addColumn("BIN SUB");
   jTable6.setModel(mode);

  } 
public void Save_ENTREE(){
    etroll();
    call_idToken(); 
DefaultTableModel excels= (DefaultTableModel)jTable6.getModel(); 
   String Fiche= null,Four = null,Sap=null,Cat=null,Old=null, Contact = null,Bin= null,Bin_sub= null ,Ref= null ,Nom= null ,Depot= null ,Ranges= null ,Etagere= null,Ligne= null,Bins= null,Profondeur= null,Qty=null,Qty_sub=null,Fabricant= "",Dates= null,Noms= null;
   
    for(int i=0; i < excels.getRowCount();i++){
    Fiche = excels.getValueAt(i,0).toString();
    Four  = excels.getValueAt(i,1). toString();
    Contact = excels.getValueAt(i,2). toString();
    Bin = excels.getValueAt(i,3). toString();
    Ref = excels.getValueAt(i,4). toString();
    Nom= excels.getValueAt(i,5). toString();
    Depot  = excels.getValueAt(i,6). toString();
    Ranges = excels.getValueAt(i,7). toString();
    Etagere = excels.getValueAt(i,8). toString();
    Ligne = excels.getValueAt(i,9). toString();
    Bins = excels.getValueAt(i,10). toString();
    Profondeur = excels.getValueAt(i,11). toString();
    Qty = excels.getValueAt(i,12). toString();
   // Fabricant = excels.getValueAt(i,13). toString();
    Dates = excels.getValueAt(i,14). toString();
    Noms = excels.getValueAt(i,15). toString();
    
   // Sap = excels.getValueAt(i,16). toString();
  // Cat = excels.getValueAt(i,17). toString();
   Old = excels.getValueAt(i,18). toString();
   Qty_sub = excels.getValueAt(i,19). toString();
   Bin_sub = excels.getValueAt(i,20). toString();

           try{
         
          String sql = "SELECT * FROM stock_db WHERE NUM= '"+Ref+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              Cat = rs.getString("CATEGORY");
              Sap = rs.getString("PARTCODE");
              Fabricant = rs.getString("FABRICANT");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              
          }else{
           Cat = "";
           Sap = "";
           Fabricant = "";
          }
         
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
              try {
       String sqls="INSERT INTO stock_mvm (NUM_FICHE,RESP,CONTACT,BIN,REF,NOM,DEPOT,RANGER,ETAGERE,LIGNE,BINS,PROFONDEUR,QTY_D,QTY_C,NUM,FABRICANT,DATES,NOMS,PARTCODE,CATEGORY,OLDNUM,STOCK_SUB,STOCK_SUB_BIN,RN,PR,PO,QTY_R) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst = con.prepareStatement(sqls);  
        pst.setString(1, Fiche);
        pst.setString(2,Four);
         pst.setString(3,Contact);
         pst.setString(4,Bin);
         pst.setString(5,Ref);
         pst.setString(6,Nom);
         pst.setString(7,Depot);
         pst.setString(8,Ranges);
         pst.setString(9,Etagere);
         pst.setString(10,Ligne);
         pst.setString(11,Bins);
         pst.setString(12,Profondeur);
         pst.setString(13,Qty);
         pst.setString(14,"0.00");
         pst.setString(15,rolls);
         pst.setString(16,Fabricant);
         pst.setString(17,Dates);
         pst.setString(18,Noms);
         
         pst.setString(19,Sap);
         pst.setString(20,Cat);
         pst.setString(21,Old);
         pst.setString(22,Qty_sub);
         pst.setString(23,Bin_sub);
         
         pst.setString(24,ID_TOKEN);
         pst.setString(25,"");
         pst.setString(26,"");
         pst.setString(27,"");
         
          pst.executeUpdate();
    } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
 
  
  
    } //END FOR
     JOptionPane.showMessageDialog(null,"Tranction Saved");
    }
/// import excel
    public void openFile(String file){
        try{
            File path = new File(file);
            Desktop.getDesktop().open(path);
        }catch(IOException ioe){
            System.out.println(ioe);
        }
    } 
      
 public void call_idToken(){     
            try{
         
         String sql="SELECT * FROM  ID_TOKEN  where id=1";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
         ID_TOKEN = rs.getString("ORGANIZATION"); }
            }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
 }
 
 public void SendAndSavePickingList(){
  call_idToken();  
    String  Partcode="-",Category="-",Oldnum="-";
      try{
          String sqlS="SELECT * FROM caisse_dispacting where access='yes' and cat1='depot' and NAME='"+Home_page.home.user.getText()+"' and Statut='Main store'";
             pst = con.prepareStatement(sqlS);rs=pst.executeQuery();
           if(rs.next()){
 try{
            String sql="SELECT NUM FROM  stock_mvm where QTY_D = '0.00' and STOCK_SUBC='0.00' ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,3);
                 String snum=rl.substring(3,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum ;
          
             }else{
                 rolls="12-1000000001";
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
 
DefaultTableModel excels= (DefaultTableModel)jTable3.getModel(); 



     for(int i=0; i < excels.getRowCount();i++){
  String  Bin = excels.getValueAt(i,0).toString();
  String  Ref  = excels.getValueAt(i,1). toString();
  String Nom = excels.getValueAt(i,2). toString();
  String Qty = excels.getValueAt(i,4). toString();
  String Depot = excels.getValueAt(i,5). toString();
 // String Depot = excels.getValueAt(i,5). toString();
  String Ranges ="-",Bins ="-",Etagere ="-",Ligne ="-",Profondeur ="-",Fabricant="-",Bin_sub="-";
  
  
        try{
         
          String sql = "SELECT * FROM stock_mvm WHERE REF= '"+Ref+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        Ranges = rs.getString("RANGER");
        Etagere = rs.getString("ETAGERE");
        Ligne = rs.getString("LIGNE");
        Bins = rs.getString("BINS");
        Profondeur = rs.getString("PROFONDEUR");
        Fabricant = rs.getString("FABRICANT");
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         try{
         
          String sql = "SELECT * FROM stock_dispacting WHERE NUM= '"+Ref+"' and STORE='"+jLabel91.getText()+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
       Bin_sub = rs.getString("BIN");
            
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
          try{
         
          String sql = "SELECT * FROM stock_db WHERE NUM= '"+Ref+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          while(rs.next()){
       Partcode = rs.getString("PARTCODE");
       Category = rs.getString("CATEGORY");
       Oldnum = rs.getString("OLDNUM");
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
  try {
       String sqls="INSERT INTO stock_mvm (NUM_FICHE,RESP,CONTACT,BIN,REF,NOM,DEPOT,RANGER,ETAGERE,LIGNE,BINS,PROFONDEUR,QTY_D,QTY_C,NUM,FABRICANT,DATES,NOMS,RN,PR,PO,STOCK_SUB,STOCK_SUB_BIN,PARTCODE,CATEGORY,OLDNUM,QTY_R) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst = con.prepareStatement(sqls);  
        pst.setString(1, demandeur.getText());
        pst.setString(2,client.getSelectedItem().toString());
         pst.setString(3,site.getText());
         pst.setString(4,Bin);
         pst.setString(5,Ref);
         pst.setString(6,Nom);
         pst.setString(7,jLabel91.getText());
         pst.setString(8,Ranges);
         
         pst.setString(9,Etagere);
         pst.setString(10,Ligne);
         pst.setString(11,Bins);
         pst.setString(12,Profondeur);
         pst.setString(14,Qty);
         pst.setString(13,"0.00");
         pst.setString(15,rolls);
          pst.setString(16,Fabricant);
           pst.setString(17,dates1.getText());
            pst.setString(18,Home_page.home.user.getText());
            pst.setString(19,rn.getText());
            pst.setString(20,pr.getText());
            pst.setString(21,po.getText());
            pst.setString(22,Qty);
            pst.setString(23,Bin_sub); 
            pst.setString(24,Partcode);
            pst.setString(25,Category);
            pst.setString(26,Oldnum);
            pst.setString(27,ID_TOKEN);
         
         pst.executeUpdate(); 
          
    } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
 //online

  
}
}
      }catch(Exception e){JOptionPane.showMessageDialog(null, e);  }
 
 }
  public void SendAndSavePickingonline(){
  call_idToken();  
    String  Partcode="-",Category="-",Oldnum="-";
      try{
          String sqlS="SELECT * FROM caisse_dispacting where access='yes' and cat1='depot' and NAME='"+Home_page.home.user.getText()+"' and Statut='Main store'";
             pst = con.prepareStatement(sqlS);rs=pst.executeQuery();
           if(rs.next()){
 try{
            String sql="SELECT NUM FROM  stock_mvm where QTY_D = '0.00' and STOCK_SUBC='0.00' ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,3);
                 String snum=rl.substring(3,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum ;
          
             }else{
                 rolls="12-1000000001";
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
 
DefaultTableModel excels= (DefaultTableModel)jTable3.getModel(); 

String Bin="-",Ref="-",Nom="-",Qty="-",Depot="-",Ranges ="-",Bins ="-",Etagere ="-",Ligne ="-",Profondeur ="-",Fabricant="-",Bin_sub="-";

     for(int i=0; i < excels.getRowCount();i++){
  Bin = excels.getValueAt(i,0).toString();
   Ref  = excels.getValueAt(i,1). toString();
  Nom = excels.getValueAt(i,2). toString();
 Qty = excels.getValueAt(i,4). toString();
 Depot = excels.getValueAt(i,5). toString();
 // String Depot = excels.getValueAt(i,5). toString();
  
  
  
        try{
         
          String sql = "SELECT * FROM stock_mvm WHERE REF= '"+Ref+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        Ranges = rs.getString("RANGER");
        Etagere = rs.getString("ETAGERE");
        Ligne = rs.getString("LIGNE");
        Bins = rs.getString("BINS");
        Profondeur = rs.getString("PROFONDEUR");
        Fabricant = rs.getString("FABRICANT");
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         try{
         
          String sql = "SELECT * FROM stock_dispacting WHERE NUM= '"+Ref+"' and STORE='"+jLabel91.getText()+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
       Bin_sub = rs.getString("BIN");
            
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
          try{
         
          String sql = "SELECT * FROM stock_db WHERE NUM= '"+Ref+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          while(rs.next()){
       Partcode = rs.getString("PARTCODE");
       Category = rs.getString("CATEGORY");
       Oldnum = rs.getString("OLDNUM");
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
     
 //online
 
ems_client_levi.Stock stocktoadd = new ems_client_levi.Stock(
     
         0, 
        demandeur.getText(), 
        client.getSelectedItem().toString(),
        site.getText(),      
        Bin,
        Ref,   
        Nom,
        jLabel91.getText(),
        "RANGER",
        "ETAGERE",
        "LIGNE",
        "BINS",
        "PROFONDEUR",
        "0.00",
        "0.00",
        rolls,
        Fabricant,
        dates1.getText(),
        Home_page.home.user.getText(),
        rn.getText(),
        "PR",
        "PO",
        ID_TOKEN,
        "0.00",
        "0.00",
        Bin_sub,
        Partcode,
        Category,
        Oldnum,
        Qty,
        "YES",
         "No"); 
   pickinglist.add(stocktoadd);
     }

}
      }catch(Exception e){JOptionPane.showMessageDialog(null, e);  }
 
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
        dateChooser2 = new com.raven.datechooser.DateChooser();
        dateChooser3 = new com.raven.datechooser.DateChooser();
        dateChooser4 = new com.raven.datechooser.DateChooser();
        dateChooser5 = new com.raven.datechooser.DateChooser();
        dateChooser6 = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        chart = new com.raven.chart.Chart();
        roundPanel1 = new com.raven.swing.RoundPanel();
        jPanel38 = new javax.swing.JPanel();
        progress1 = new com.raven.swing.progress.Progress();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        progress2 = new com.raven.swing.progress.Progress();
        jLabel51 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        progress3 = new com.raven.swing.progress.Progress();
        jLabel52 = new javax.swing.JLabel();
        jPanel73 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        lineChart = new com.raven.chart.LineChart();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        item_id = new Palette.MyTextField();
        jLabel10 = new javax.swing.JLabel();
        nom = new Palette.MyTextField();
        jLabel11 = new javax.swing.JLabel();
        textField1 = new Palette.TextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        fabr = new Palette.MyTextField();
        jLabel30 = new javax.swing.JLabel();
        prixachat = new Palette.MyTextField();
        jLabel31 = new javax.swing.JLabel();
        prixvente = new Palette.MyTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        alert_min = new Palette.MyTextField();
        jLabel66 = new javax.swing.JLabel();
        Max_alert = new Palette.MyTextField();
        jLabel100 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        textField2 = new Palette.TextField();
        jPanel18 = new javax.swing.JPanel();
        oldpart = new Palette.MyTextField();
        jLabel92 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        sapcode = new Palette.MyTextField();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel41 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        textField9 = new Palette.TextField();
        jScrollPane24 = new javax.swing.JScrollPane();
        jPanel69 = new javax.swing.JPanel();
        jPanel46 = new javax.swing.JPanel();
        textField10 = new Palette.TextField();
        jScrollPane18 = new javax.swing.JScrollPane();
        tb_itms_cmd = new javax.swing.JTable();
        jPanel47 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        date_cmd = new Palette.MyTextField();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        textField11 = new Palette.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel56 = new javax.swing.JPanel();
        purchase = new javax.swing.JPanel();
        jPanel67 = new javax.swing.JPanel();
        jPanel68 = new javax.swing.JPanel();
        textField16 = new Palette.TextField();
        jScrollPane13 = new javax.swing.JScrollPane();
        jPanel65 = new javax.swing.JPanel();
        jPanel70 = new javax.swing.JPanel();
        item_id3 = new Palette.MyTextField();
        jLabel68 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel82 = new javax.swing.JLabel();
        date_cmd2 = new Palette.MyTextField();
        jScrollPane22 = new javax.swing.JScrollPane();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jPanel71 = new javax.swing.JPanel();
        textField18 = new Palette.TextField();
        jScrollPane26 = new javax.swing.JScrollPane();
        jPanel72 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        textField4 = new Palette.TextField();
        jScrollPane16 = new javax.swing.JScrollPane();
        jPanel58 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        contact = new Palette.MyTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        dates = new Palette.MyTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jButton4 = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel50 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jPanel59 = new javax.swing.JPanel();
        textField13 = new Palette.TextField();
        jPanel57 = new javax.swing.JPanel();
        textField12 = new Palette.TextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        quotation = new javax.swing.JPanel();
        jPanel60 = new javax.swing.JPanel();
        textField14 = new Palette.TextField();
        jScrollPane20 = new javax.swing.JScrollPane();
        jPanel61 = new javax.swing.JPanel();
        jPanel62 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jLabel67 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel71 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jPanel63 = new javax.swing.JPanel();
        textField15 = new Palette.TextField();
        jPanel54 = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        tb_itms_cmd1 = new javax.swing.JTable();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel24 = new javax.swing.JPanel();
        nom5 = new Palette.MyTextField();
        jLabel28 = new javax.swing.JLabel();
        demandeur = new Palette.MyTextField();
        site = new Palette.MyTextField();
        jLabel29 = new javax.swing.JLabel();
        client = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        dates1 = new Palette.MyTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        po = new Palette.MyTextField();
        rn = new Palette.MyTextField();
        pr = new Palette.MyTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        textField3 = new Palette.TextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        jPanel51 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        textField5 = new Palette.TextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel37 = new javax.swing.JPanel();
        textField8 = new Palette.TextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        jLabel90 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel53 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel64 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        depots = new javax.swing.JComboBox<>();
        jLabel42 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel36 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        textField6 = new Palette.TextField();
        jPanel34 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jLabel43 = new javax.swing.JLabel();
        textField7 = new Palette.TextField();
        jPanel42 = new javax.swing.JPanel();
        jPanel52 = new javax.swing.JPanel();
        jPanel55 = new javax.swing.JPanel();
        jDateChooser1 = new Palette.MyTextField();
        jDateChooser2 = new Palette.MyTextField();
        jButton6 = new javax.swing.JButton();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jScrollPane27 = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        storemenu = new javax.swing.JMenu();

        dateChooser1.setDateFormat("yyyy-MM-dd");
        dateChooser1.setTextRefernce(dates);

        dateChooser2.setDateFormat("yyyy-MM-dd");
        dateChooser2.setTextRefernce(dates1);

        dateChooser3.setDateFormat("yyyy-MM-dd");
        dateChooser3.setTextRefernce(date_cmd);

        dateChooser4.setDateFormat("yyyy-MM-dd");
        dateChooser4.setTextRefernce(date_cmd1);

        dateChooser5.setDateFormat("yyyy-MM-dd");
        dateChooser5.setTextRefernce(jDateChooser1);

        dateChooser6.setDateFormat("yyyy-MM-dd");
        dateChooser6.setTextRefernce(jDateChooser2);

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Sell_Stock_30px.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel38.setOpaque(false);

        progress1.setBackground(new java.awt.Color(66, 246, 84));
        progress1.setForeground(new java.awt.Color(19, 153, 32));
        progress1.setValue(60);

        jLabel49.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(220, 220, 220));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("Total Income Sold");

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(progress1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progress1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel50.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(220, 220, 220));
        jLabel50.setText("Report Monthly");
        jLabel50.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jPanel39.setOpaque(false);

        progress2.setBackground(new java.awt.Color(132, 66, 246));
        progress2.setForeground(new java.awt.Color(64, 18, 153));
        progress2.setValue(70);

        jLabel51.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(220, 220, 220));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Total Income Profit");

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progress2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progress2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel40.setOpaque(false);

        progress3.setBackground(new java.awt.Color(66, 193, 246));
        progress3.setForeground(new java.awt.Color(26, 132, 181));
        progress3.setValue(85);

        jLabel52.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(220, 220, 220));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("Total Expense");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progress3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progress3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel73.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8-dollar-reçu-48.png"))); // NOI18N
        jLabel37.setText("Invoice Pending");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jLabel37)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8-facture-d&#39;achat-48.png"))); // NOI18N
        jLabel38.setText("Supply Invoice Pending");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8-packing-list-48.png"))); // NOI18N
        jLabel39.setText("Packing List Pending");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8-vendre-les-stock-48.png"))); // NOI18N
        jLabel36.setText("Stock minimal");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel73Layout = new javax.swing.GroupLayout(jPanel73);
        jPanel73.setLayout(jPanel73Layout);
        jPanel73Layout.setHorizontalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel73Layout.setVerticalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE))
                    .addComponent(jPanel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Dash Board", jPanel13);

        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel9.setText("Ref/Item Number *");

        item_id.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        item_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                item_idKeyReleased(evt);
            }
        });

        jLabel10.setText("Name/Nom *");

        nom.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Microsoft_Excel_30px.png"))); // NOI18N
        jLabel11.setText("Import excel");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        textField1.setLabelText("Recherche");
        textField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField1KeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jLabel19.setText("...");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        fabr.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel30.setText("Maker/Fabricant *");

        prixachat.setText("0.00");
        prixachat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prixachatActionPerformed(evt);
            }
        });

        jLabel31.setText("Cost price USD *");

        prixvente.setText("0.00");

        jLabel32.setText("Sell price  *");

        jLabel40.setText("Min Alert *");

        alert_min.setText("0.00");

        jLabel66.setText("Max Alert *");

        Max_alert.setText("0.00");
        Max_alert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Max_alertActionPerformed(evt);
            }
        });

        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8-update-24.png"))); // NOI18N
        jLabel100.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel100MouseClicked(evt);
            }
        });

        jLabel103.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel103.setText("Total : ");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(item_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fabr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prixachat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Max_alert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(prixvente, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addComponent(alert_min, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel103)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel100))
                            .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(item_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fabr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prixachat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prixvente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(alert_min, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Max_alert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel100))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel103, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        ranges.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Rank/Rangé -" }));
        ranges.setToolTipText("");
        ranges.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        ranges.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                rangesPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        ranges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rangesActionPerformed(evt);
            }
        });

        etagere.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Shelves/ Etagères -" }));
        etagere.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        etagere.setMinimumSize(new java.awt.Dimension(141, 30));
        etagere.setPreferredSize(new java.awt.Dimension(141, 30));
        etagere.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                etagerePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        ligne.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Line/ Ligne -" }));
        ligne.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        ligne.setPreferredSize(new java.awt.Dimension(109, 30));
        ligne.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                lignePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-plus-24.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-plus-24.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-plus-24.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel13.setText("Rank/Rangé");

        jLabel14.setText("Shelves/ Etagères");

        jLabel15.setText("Niveau/ Level");

        depot.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Store/dépôt -" }));
        depot.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        depot.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                depotPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        depot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depotActionPerformed(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-plus-24.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel18.setText("Store/dépôt");

        jLabel16.setText("Bin location");

        bin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Bin location -" }));
        bin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        bin.setPreferredSize(new java.awt.Dimension(113, 30));
        bin.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                binPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-plus-24.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        profondeur.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        profondeur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        profondeur.setPreferredSize(new java.awt.Dimension(145, 30));
        profondeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profondeurActionPerformed(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-plus-24.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jLabel7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel7KeyPressed(evt);
            }
        });

        jLabel17.setText("Depth/ Profondeur");

        jButton3.setText("Ok");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(ligne, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(ranges, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(etagere, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(depot, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(profondeur, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addComponent(bin, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(depot)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ranges, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etagere, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ligne, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(profondeur, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        textField2.setLabelText("Recherche");
        textField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        oldpart.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel92.setText("Old PartNumber");

        jLabel96.setText("SAP PartNumber");

        sapcode.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel97.setText("Categoy");

        cat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Category-" }));
        cat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        cat.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                catPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catActionPerformed(evt);
            }
        });

        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8-plus-24.png"))); // NOI18N
        jLabel98.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel98MouseClicked(evt);
            }
        });

        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(oldpart, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(sapcode, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))))
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel97, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2)
                        .addComponent(cat, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel98, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cat)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(oldpart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sapcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(95, Short.MAX_VALUE))
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Stock registration", jPanel4);

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));
        jPanel44.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel45.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Count List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        textField9.setLabelText("Recherche");
        textField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField9KeyReleased(evt);
            }
        });

        jScrollPane24.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
        jPanel69.setLayout(jPanel69Layout);
        jPanel69Layout.setHorizontalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel69Layout.setVerticalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 269, Short.MAX_VALUE)
        );

        jScrollPane24.setViewportView(jPanel69);

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(textField9, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addComponent(textField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel46.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Items List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        textField10.setLabelText("Recherche");
        textField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField10KeyReleased(evt);
            }
        });

        tb_itms_cmd.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_itms_cmd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_itms_cmdMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(tb_itms_cmd);

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGap(0, 92, Short.MAX_VALUE)
                        .addComponent(textField10, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel47.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Request for quotation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        rfq_supply.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Part number", "Part description", "Unit", "Qty", "Qty Available", "Unit price", "Total amount", "Maker", "Store"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        rfq_supply.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rfq_supplyMouseClicked(evt);
            }
        });
        rfq_supply.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rfq_supplyKeyReleased(evt);
            }
        });
        jScrollPane15.setViewportView(rfq_supply);

        fourn_cmd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Supply / Fournisseur -" }));
        fourn_cmd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        fourn_cmd.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                fourn_cmdPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        fourn_cmd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fourn_cmdActionPerformed(evt);
            }
        });

        jLabel55.setText("Supply *");

        jLabel56.setText("Region *");

        region.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Region-" }));
        region.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Print_24px.png"))); // NOI18N
        jLabel58.setText("Print");
        jLabel58.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel58.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel58MouseClicked(evt);
            }
        });

        date_cmd.setEditable(false);
        date_cmd.setBackground(new java.awt.Color(242, 242, 241));
        date_cmd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        date_cmd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                date_cmdKeyReleased(evt);
            }
        });

        rfqnum.setEditable(false);
        rfqnum.setBackground(new java.awt.Color(242, 242, 241));
        rfqnum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rfqnumActionPerformed(evt);
            }
        });

        jLabel86.setText("RFQ No.*");

        jLabel87.setText("Stock take*");

        jLabel88.setText("Po");

        p_o.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_oActionPerformed(evt);
            }
        });

        stock_vehicule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stock_vehiculeActionPerformed(evt);
            }
        });

        jLabel59.setText("...");

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(region, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fourn_cmd, javax.swing.GroupLayout.Alignment.LEADING, 0, 169, Short.MAX_VALUE)
                            .addComponent(rfqnum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel47Layout.createSequentialGroup()
                                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel87))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(stock_vehicule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(p_o, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(date_cmd, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_cmd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fourn_cmd, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(region, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(rfqnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stock_vehicule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p_o, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );

        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText(">>");
        jLabel53.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel53MouseClicked(evt);
            }
        });

        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("<<");
        jLabel54.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel54MouseClicked(evt);
            }
        });

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("LN >");
        jLabel57.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel57MouseClicked(evt);
            }
        });

        jPanel48.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Request for quotation List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        textField11.setLabelText("Recherche");
        textField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField11KeyReleased(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel56.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 244, Short.MAX_VALUE)
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel56);

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addGap(0, 83, Short.MAX_VALUE)
                        .addComponent(textField11, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addComponent(textField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel47, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel44Layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel44Layout.createSequentialGroup()
                        .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Rfq Supply", jPanel41);

        purchase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                purchaseMouseClicked(evt);
            }
        });

        jPanel67.setBackground(new java.awt.Color(255, 255, 255));
        jPanel67.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel68.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rfq List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        textField16.setLabelText("Recherche");
        textField16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField16KeyReleased(evt);
            }
        });

        jScrollPane13.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
        jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel65Layout.setVerticalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 506, Short.MAX_VALUE)
        );

        jScrollPane13.setViewportView(jPanel65);

        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
        jPanel68.setLayout(jPanel68Layout);
        jPanel68Layout.setHorizontalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel68Layout.createSequentialGroup()
                        .addGap(0, 82, Short.MAX_VALUE)
                        .addComponent(textField16, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel68Layout.setVerticalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addComponent(textField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel70.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Purchase order/ Bon de commande", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        fourn_cmd2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Supply / Fournisseur -" }));
        fourn_cmd2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        fourn_cmd2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                fourn_cmd2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        fourn_cmd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fourn_cmd2ActionPerformed(evt);
            }
        });

        item_id3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        item_id3.setText("0.00");
        item_id3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                item_id3KeyReleased(evt);
            }
        });

        jLabel68.setText("Supply / Fournisseur *");

        jLabel79.setText("Lead time *");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Days/Jrs" }));
        jComboBox3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Print_24px.png"))); // NOI18N
        jLabel80.setText("Print");
        jLabel80.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel80.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel80MouseClicked(evt);
            }
        });

        jLabel81.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel81.setText("Letter/ Lettre");
        jLabel81.setOpaque(true);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fr", "En" }));
        jComboBox5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jComboBox5.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox5PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel82.setBackground(new java.awt.Color(204, 204, 204));
        jLabel82.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("0.00");
        jLabel82.setOpaque(true);

        date_cmd2.setEditable(false);
        date_cmd2.setBackground(new java.awt.Color(242, 242, 241));
        date_cmd2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        date_cmd2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                date_cmd2KeyReleased(evt);
            }
        });

        jLabel60.setText("...");

        tb_bon_de_commande2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Part number", "Part description", "Unit", "Qty", "Qty Available", "Unit price", "Total amount", "Maker", "Store"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_bon_de_commande2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_bon_de_commande2MouseClicked(evt);
            }
        });
        tb_bon_de_commande2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_bon_de_commande2KeyReleased(evt);
            }
        });
        jScrollPane22.setViewportView(tb_bon_de_commande2);

        jLabel89.setText("...");

        javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
        jPanel70.setLayout(jPanel70Layout);
        jPanel70Layout.setHorizontalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel70Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel70Layout.createSequentialGroup()
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel70Layout.createSequentialGroup()
                        .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel70Layout.createSequentialGroup()
                                .addComponent(item_id3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(fourn_cmd2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date_cmd2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane22))
                .addContainerGap())
        );
        jPanel70Layout.setVerticalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel70Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_cmd2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fourn_cmd2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(item_id3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox3)
                    .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox5)
                    .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText(">>");
        jLabel83.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel83.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel83MouseClicked(evt);
            }
        });

        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("<<");
        jLabel84.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel84.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel84MouseClicked(evt);
            }
        });

        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("LN >");
        jLabel85.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel85.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel85MouseClicked(evt);
            }
        });

        jPanel71.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Purchase order List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        textField18.setLabelText("Recherche");
        textField18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField18KeyReleased(evt);
            }
        });

        jScrollPane26.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel72.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
        jPanel72.setLayout(jPanel72Layout);
        jPanel72Layout.setHorizontalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 244, Short.MAX_VALUE)
        );
        jPanel72Layout.setVerticalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 504, Short.MAX_VALUE)
        );

        jScrollPane26.setViewportView(jPanel72);

        javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
        jPanel71.setLayout(jPanel71Layout);
        jPanel71Layout.setHorizontalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel71Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addGap(0, 83, Short.MAX_VALUE)
                        .addComponent(textField18, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel71Layout.setVerticalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel71Layout.createSequentialGroup()
                .addComponent(textField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
        jPanel67.setLayout(jPanel67Layout);
        jPanel67Layout.setHorizontalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel67Layout.setVerticalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel67Layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel68, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel70, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel71, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout purchaseLayout = new javax.swing.GroupLayout(purchase);
        purchase.setLayout(purchaseLayout);
        purchaseLayout.setHorizontalGroup(
            purchaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(purchaseLayout.createSequentialGroup()
                .addComponent(jPanel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        purchaseLayout.setVerticalGroup(
            purchaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(purchaseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Purchase order/Cmd", purchase);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pending purchase order/bon de commade", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        textField4.setLabelText("Recherche");
        textField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField4KeyReleased(evt);
            }
        });

        jScrollPane16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane16.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel58.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 234, Short.MAX_VALUE)
        );

        jScrollPane16.setViewportView(jPanel58);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane16))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Stock in/ Entree stock", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        numfiche.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel25.setText("Sheet number *");

        jLabel26.setText("Supply*");

        jLabel27.setText("Contacts");

        item_id2.setEditable(false);
        item_id2.setBackground(new java.awt.Color(242, 242, 241));
        item_id2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Microsoft_Excel_30px.png"))); // NOI18N
        jLabel20.setText("Import excel");
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        jLabel21.setText("...");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });

        respo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Supply/Fournisseur -", "Main Store", "Sub Store", "Self count" }));
        respo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255), 2));

        dates.setEditable(false);
        dates.setBackground(new java.awt.Color(242, 242, 241));
        dates.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("Date *");

        jLabel44.setText("....");

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8-download-24.png"))); // NOI18N
        jLabel34.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numfiche, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(respo, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(item_id2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dates, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(numfiche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(item_id2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(respo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ref. Numbers", "Description", "Unit", "Qty", "Qty received", "Store"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable6);

        jButton4.setText("OK");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Print_24px.png"))); // NOI18N
        jLabel46.setText("Print");
        jLabel46.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel46.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel46MouseClicked(evt);
            }
        });

        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel101.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_New_24px.png"))); // NOI18N
        jLabel101.setText("New trans");
        jLabel101.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel101.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel101.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel101MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel101)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel46)
                        .addGap(0, 0, 0)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(">>");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("<<");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jPanel50.setBackground(new java.awt.Color(255, 255, 255));
        jPanel50.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Packing List/ Fiche entree stock", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jScrollPane14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel59.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 489, Short.MAX_VALUE)
        );

        jScrollPane14.setViewportView(jPanel59);

        textField13.setLabelText("Recherche");
        textField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField13KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(textField13, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addComponent(textField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel57.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item list/ Liste Iteme", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        textField12.setLabelText("Recherche");
        textField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField12KeyReleased(evt);
            }
        });

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel57Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(textField12, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Packing list", jPanel2);

        quotation.setBackground(new java.awt.Color(255, 255, 255));

        jPanel60.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quotatio/Invoice/Facture  List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        textField14.setLabelText("Recherche");
        textField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField14KeyReleased(evt);
            }
        });

        jScrollPane20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane20.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel61.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 268, Short.MAX_VALUE)
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 506, Short.MAX_VALUE)
        );

        jScrollPane20.setViewportView(jPanel61);

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(textField14, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane20))
                .addContainerGap())
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addComponent(textField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel62.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quotatio/Invoice/Facture", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tb_quotation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bin Location", "Refenrences", "Descriptions", "Qty", "Up", "Total price", "Store"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_quotation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_quotationMouseClicked(evt);
            }
        });
        tb_quotation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_quotationKeyReleased(evt);
            }
        });
        jScrollPane21.setViewportView(tb_quotation);

        fourn_cmd1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Client -" }));
        fourn_cmd1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        fourn_cmd1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                fourn_cmd1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        fourn_cmd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fourn_cmd1ActionPerformed(evt);
            }
        });

        jLabel67.setText("Client *");

        jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Print_24px.png"))); // NOI18N
        jLabel69.setText("Print");
        jLabel69.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel69.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel69MouseClicked(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel70.setText("Letter/ Lettre");
        jLabel70.setOpaque(true);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fr", "En" }));
        jComboBox4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jComboBox4.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox4PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox4PopupMenuWillBecomeVisible(evt);
            }
        });

        jLabel71.setBackground(new java.awt.Color(204, 204, 204));
        jLabel71.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setText("0.00");
        jLabel71.setOpaque(true);

        date_cmd1.setEditable(false);
        date_cmd1.setBackground(new java.awt.Color(242, 242, 241));
        date_cmd1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        date_cmd1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                date_cmd1KeyReleased(evt);
            }
        });

        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel75.setText("...");

        jLabel76.setText("PR *");

        jLabel77.setText("WO *");

        jLabel78.setText("PO *");

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                    .addGroup(jPanel62Layout.createSequentialGroup()
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel62Layout.createSequentialGroup()
                        .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pr1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel62Layout.createSequentialGroup()
                                .addComponent(rn1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(po1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fourn_cmd1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(date_cmd1, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(date_cmd1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fourn_cmd1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(po1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox4)
                    .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel63.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        textField15.setLabelText("Recherche");
        textField15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField15KeyReleased(evt);
            }
        });

        jPanel54.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tb_itms_cmd1.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_itms_cmd1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_itms_cmd1MouseClicked(evt);
            }
        });
        jScrollPane23.setViewportView(tb_itms_cmd1);

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel63Layout.createSequentialGroup()
                        .addGap(0, 117, Short.MAX_VALUE)
                        .addComponent(textField15, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addComponent(textField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel72.setText(">>");
        jLabel72.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel72.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel72MouseClicked(evt);
            }
        });

        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setText("<<");
        jLabel73.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel73.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel73MouseClicked(evt);
            }
        });

        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("LN >");
        jLabel74.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel74.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel74MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout quotationLayout = new javax.swing.GroupLayout(quotation);
        quotation.setLayout(quotationLayout);
        quotationLayout.setHorizontalGroup(
            quotationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(quotationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(quotationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        quotationLayout.setVerticalGroup(
            quotationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, quotationLayout.createSequentialGroup()
                .addContainerGap(188, Short.MAX_VALUE)
                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, quotationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(quotationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Quotation", quotation);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bin Loc.", "Ref. Numbers", "Descriptions", "Avl. Qty ", "Qty", "Store"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable3);

        nom5.setEditable(false);
        nom5.setBackground(new java.awt.Color(242, 242, 241));

        jLabel28.setText("Requester *");

        site.setText("-");

        jLabel29.setText("Site *");

        client.setEditable(true);
        client.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Client -", "Self", "Main Store", "Sub Store" }));
        client.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255), 2));
        client.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                clientPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        client.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientActionPerformed(evt);
            }
        });

        jLabel33.setText("Client *");

        dates1.setEditable(false);
        dates1.setBackground(new java.awt.Color(242, 242, 241));
        dates1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel61.setText("Rsv *");

        jLabel62.setText("PR *");

        jLabel63.setText("PO *");

        po.setEditable(false);
        po.setBackground(new java.awt.Color(242, 242, 241));
        po.setText("-");

        pr.setEditable(false);
        pr.setBackground(new java.awt.Color(242, 242, 241));
        pr.setText("-");

        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel65.setText("...");

        jLabel91.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel91.setText("...");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(site, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(demandeur, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(client, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(po, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel91, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(nom5, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(dates1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(po, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(demandeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(client, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(nom5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dates1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel65)
                            .addComponent(site, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel91, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Print_30px.png"))); // NOI18N
        jLabel24.setText("Print");
        jLabel24.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8-upload-24.png"))); // NOI18N
        jLabel45.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel45MouseClicked(evt);
            }
        });

        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel102.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_New_24px.png"))); // NOI18N
        jLabel102.setText("New trans");
        jLabel102.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel102.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel102.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel102MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel24)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel102, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder("Item List"));

        textField3.setLabelText("Recherche");
        textField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField3KeyReleased(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(0, 91, Short.MAX_VALUE)
                        .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText(">>");
        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("<<");
        jLabel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel64.setText("Picking slip/ Liste");

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane19.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 517, Short.MAX_VALUE)
        );

        jScrollPane19.setViewportView(jPanel51);

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane19)
                .addContainerGap())
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Picking slip/Liste ", jPanel21);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bin loc", "Part Number", "Old Part Number", "SAP Code", "Descriptions", "Categorie", "Main Store Qty", "Sub store Qty (LME)", "Total Qty", "Fabricant", "Store"
            }
        ));
        jTable7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable7MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTable7);

        textField5.setLabelText("Recherche");
        textField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField5KeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Total Product: 0.00");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Print_30px.png"))); // NOI18N
        jLabel35.setText("Print Table");
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel37.setBorder(javax.swing.BorderFactory.createTitledBorder("Item List"));

        textField8.setLabelText("Recherche");
        textField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField8KeyReleased(evt);
            }
        });

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable11MouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(jTable11);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(0, 129, Short.MAX_VALUE)
                        .addComponent(textField8, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel90.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Microsoft_Excel_30px.png"))); // NOI18N
        jLabel90.setText("Export");
        jLabel90.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel90MouseClicked(evt);
            }
        });

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel93.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel93.setText("Sort by");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bin loc", "Part Number", "Old Part Number", "SAP Code", "Descriptions", "Categories", "Fabricant", "Store" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A to Z", "Z to A" }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButton5.setText("OK");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel94.setText("Column");

        jLabel95.setText("Order by");

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel53Layout.createSequentialGroup()
                        .addComponent(jLabel93)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addGroup(jPanel53Layout.createSequentialGroup()
                        .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel95, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jComboBox1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel99.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8-online-30.png"))); // NOI18N
        jLabel99.setText("Sync");
        jLabel99.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel99MouseClicked(evt);
            }
        });

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel64.setBackground(new java.awt.Color(255, 255, 255));

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText(">>");
        jLabel47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel47MouseClicked(evt);
            }
        });

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("<<");
        jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel48MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
        jPanel64.setLayout(jPanel64Layout);
        jPanel64Layout.setHorizontalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel64Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        jPanel64Layout.setVerticalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel104.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8-stocks-30.png"))); // NOI18N
        jLabel104.setText("Generate Graph");
        jLabel104.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel104.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel104.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel104MouseClicked(evt);
            }
        });

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel64, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel104, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel99, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textField5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator3)))
                    .addComponent(jPanel53, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel90, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Stock on hand", jPanel10);

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel35.setBorder(javax.swing.BorderFactory.createTitledBorder("Counting Liste"));

        depots.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Store/dépôt -" }));
        depots.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255), 2));
        depots.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                depotsPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Print_24px.png"))); // NOI18N
        jLabel42.setText("Print List");
        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel42MouseClicked(evt);
            }
        });

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2));

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(depots, 0, 288, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel42))
                    .addComponent(jTextField1))
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(depots, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder("individual stock sheet"));

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Ref. Numbers", "Description", "Action"
            }
        ));
        jTable10.setRowHeight(32);
        jTable10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable10MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jTable10);

        textField6.setLabelText("Recherche");
        textField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField6KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                        .addGap(0, 125, Short.MAX_VALUE)
                        .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bin Loc.", "Ref. Numbers", "Descriptions", "Store location", "Item cost", "Quantity", "Total value", "Supply", "Maker"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false, true, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable9MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTable9);

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Print_30px.png"))); // NOI18N
        jLabel43.setText("Print Inventory");
        jLabel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel43MouseClicked(evt);
            }
        });

        textField7.setLabelText("Recherche");
        textField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField7KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textField7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Inventory", jPanel3);

        jPanel52.setBackground(new java.awt.Color(255, 255, 255));
        jPanel52.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel55.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        fourn_cmd3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Category -", "All" }));
        fourn_cmd3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        fourn_cmd3.setEnabled(false);
        fourn_cmd3.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                fourn_cmd3PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        fourn_cmd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fourn_cmd3ActionPerformed(evt);
            }
        });

        jDateChooser1.setEditable(false);
        jDateChooser1.setBackground(new java.awt.Color(242, 242, 241));
        jDateChooser1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser1.setEnabled(false);

        jDateChooser2.setEditable(false);
        jDateChooser2.setBackground(new java.awt.Color(242, 242, 241));
        jDateChooser2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jDateChooser2.setEnabled(false);

        jButton6.setText("Generate");
        jButton6.setEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel105.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Microsoft_Excel_30px.png"))); // NOI18N
        jLabel105.setText("Export excel 1");
        jLabel105.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel105MouseClicked(evt);
            }
        });

        jLabel106.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Print_30px.png"))); // NOI18N
        jLabel106.setText("Print");

        jTable13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Bin Location", "Part Number", "Partcode", "Part Description", "01st", "02nd", "03rd", "04th", "05th", "06th", "07th", "08th", "09th", "10th", "11th", "12th", "13th", "14th", "15th", "16th", "17th", "18th", "19th", "20th", "21st", "22nd", "23rd", "24th", "25th", "26th", "27th", "28th", "29th", "30th", "31st", "Month"
            }
        ));
        jScrollPane27.setViewportView(jTable13);

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel55Layout.createSequentialGroup()
                        .addComponent(jScrollPane27)
                        .addContainerGap())
                    .addGroup(jPanel55Layout.createSequentialGroup()
                        .addComponent(fourn_cmd3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(435, 435, 435))))
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel55Layout.createSequentialGroup()
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel106))
                    .addGroup(jPanel55Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(fourn_cmd3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane27, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Graph /Repport", jPanel42);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jMenu1.setText("X");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("File");

        jMenuItem1.setText("Stock count");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Store Allocation");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        storemenu.setText("Magazin principal");
        storemenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuBar1.add(storemenu);

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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField1KeyReleased
call_SEARCH();        // TODO add your handling code here:
    }//GEN-LAST:event_textField1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed



   try{
          String sql="SELECT * FROM caisse_dispacting where access='yes' and cat1='depot' and NAME='"+Home_page.home.user.getText()+"' and Statut='Main store'";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
           if(rs.next()){
             if(item_id.getText().equals("")||nom.getText().equals("")||fabr.getText().equals("")||prixachat.getText().equals("")||prixachat.getText().equals("0.00")||prixvente.getText().equals("")||prixvente.getText().equals("0.00")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
        save();
   item_id.setText("") ; 
nom.setText("") ;  
fabr.setText("") ;  
prixachat.setText("0.00") ;  
prixvente.setText("0.00") ;  
alert_min.setText("0.00") ;  
sapcode.setText("") ;  
oldpart.setText("") ;  
}
            }else{
           JOptionPane.showMessageDialog(null,"You do not have credential","Error",JOptionPane.WARNING_MESSAGE);
           }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }       
        
      // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
selecttable();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
          String sql="SELECT * FROM caisse_dispacting where access='yes' and cat1='depot' and NAME='"+Home_page.home.user.getText()+"' and Statut='Main store'";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
           if(rs.next()){
            delete(); 
            }else{
           JOptionPane.showMessageDialog(null,"You do not have credential","Error",JOptionPane.WARNING_MESSAGE);
           }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
              // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
add_on_stock_bin m = new add_on_stock_bin();
add_on_stock_bin.jLabel1.setText("depot");
 try{
        PreparedStatement ps = con.prepareStatement("Select * FROM stock_bin_location where cat='depot' order by Nom");
        ResultSet rs=ps.executeQuery();
        DefaultTableModel tm = (DefaultTableModel)add_on_stock_bin.jTable1.getModel();
        tm.setRowCount(0);

      while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
            Object o[] = {rs.getString("NOM")};
            tm.addRow(o);

      }

       }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
            m.show();
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
add_on_stock_bin m = new add_on_stock_bin();
add_on_stock_bin.jLabel1.setText("ranges");
try{
        PreparedStatement ps = con.prepareStatement("Select * FROM stock_bin_location where cat='ranges' and depot='"+depot.getSelectedItem()+"'order by Nom");
        ResultSet rs=ps.executeQuery();
        DefaultTableModel tm = (DefaultTableModel)add_on_stock_bin.jTable1.getModel();
        tm.setRowCount(0);

      while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
            Object o[] = {rs.getString("NOM")};
            tm.addRow(o);

      }

       }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
            m.show();
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
add_on_stock_bin m = new add_on_stock_bin();
add_on_stock_bin.jLabel1.setText("etagere");
try{
        PreparedStatement ps = con.prepareStatement("Select * FROM stock_bin_location where cat='etagere' and depot='"+depot.getSelectedItem()+"' and ranges='"+ranges.getSelectedItem()+"' order by Nom");
       //PreparedStatement ps = con.prepareStatement("Select * FROM stock_bin_location where  ranges='"+ranges.getSelectedItem()+"' order by Nom");
        ResultSet rs=ps.executeQuery();
        DefaultTableModel tm = (DefaultTableModel)add_on_stock_bin.jTable1.getModel();
        tm.setRowCount(0);

      while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
            Object o[] = {rs.getString("NOM")};
            tm.addRow(o);

      }

       }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
            m.show();
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
add_on_stock_bin m = new add_on_stock_bin();
add_on_stock_bin.jLabel1.setText("ligne");
try{
        PreparedStatement ps = con.prepareStatement("Select * FROM stock_bin_location where cat='ligne' and depot='"+depot.getSelectedItem()+"' and ranges='"+ranges.getSelectedItem()+"' and etagere='"+etagere.getSelectedItem()+"' order by Nom");
        ResultSet rs=ps.executeQuery();
        DefaultTableModel tm = (DefaultTableModel)add_on_stock_bin.jTable1.getModel();
        tm.setRowCount(0);

      while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
            Object o[] = {rs.getString("NOM")};
            tm.addRow(o);

      }

       }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
            m.show();
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
add_on_stock_bin m = new add_on_stock_bin();
add_on_stock_bin.jLabel1.setText("bin");
try{
        PreparedStatement ps = con.prepareStatement("Select * FROM stock_bin_location where cat='bin' and depot='"+depot.getSelectedItem()+"' and ranges='"+ranges.getSelectedItem()+"' and etagere='"+etagere.getSelectedItem()+"' and ligne='"+ligne.getSelectedItem()+"' order by Nom");
        ResultSet rs=ps.executeQuery();
        DefaultTableModel tm = (DefaultTableModel)add_on_stock_bin.jTable1.getModel();
        tm.setRowCount(0);

      while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
            Object o[] = {rs.getString("NOM")};
            tm.addRow(o);

      }

       }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
            m.show();
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel7KeyPressed
       // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7KeyPressed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
add_on_stock_bin m = new add_on_stock_bin();
add_on_stock_bin.jLabel1.setText("profondeur");
try{
        PreparedStatement ps = con.prepareStatement("Select * FROM stock_bin_location where cat='profondeur' and depot='"+depot.getSelectedItem()+"' and ranges='"+ranges.getSelectedItem()+"' and etagere='"+etagere.getSelectedItem()+"' and ligne='"+ligne.getSelectedItem()+"' and bin='"+bin.getSelectedItem()+"' order by Nom");
        ResultSet rs=ps.executeQuery();
        DefaultTableModel tm = (DefaultTableModel)add_on_stock_bin.jTable1.getModel();
        tm.setRowCount(0);

      while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
            Object o[] = {rs.getString("NOM")};
            tm.addRow(o);

      }

       }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
            m.show();
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void depotPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_depotPopupMenuWillBecomeInvisible
try{
          String sql="SELECT * FROM stock_bin_location where  cat='ranges' and depot='"+depot.getSelectedItem()+"' order by Nom";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
               store_setting.ranges.removeAllItems();
          store_setting.ranges.addItem("- Rank/Rangé -");
            while(rs.next()){
                String sums=rs.getString("NOM");
             
              ranges.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
         // TODO add your handling code here:
    }//GEN-LAST:event_depotPopupMenuWillBecomeInvisible

    private void rangesPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_rangesPopupMenuWillBecomeInvisible
try{
          String sql="SELECT * FROM stock_bin_location where  cat='etagere' and depot='"+depot.getSelectedItem()+"' and ranges='"+ranges.getSelectedItem()+"' order by Nom";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
          store_setting.etagere.removeAllItems();
          store_setting.etagere.addItem("- Shelves/ Etagères -");
            while(rs.next()){
                String sums=rs.getString("Nom");
  
              store_setting.etagere.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }        // TODO add your handling code here:
    }//GEN-LAST:event_rangesPopupMenuWillBecomeInvisible

    private void etagerePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_etagerePopupMenuWillBecomeInvisible
    try{
          String sql="SELECT * FROM stock_bin_location where cat='ligne' and depot='"+depot.getSelectedItem()+"' and ranges='"+ranges.getSelectedItem()+"' and etagere='"+etagere.getSelectedItem()+"' order by Nom";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
             store_setting.ligne.removeAllItems();
          store_setting.ligne.addItem("- Line/ Ligne -");
            while(rs.next()){
                String sums=rs.getString("Nom");
             
              store_setting.ligne.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
        // TODO add your handling code here:
    }//GEN-LAST:event_etagerePopupMenuWillBecomeInvisible

    private void lignePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_lignePopupMenuWillBecomeInvisible
  try{
          String sql="SELECT * FROM stock_bin_location where cat='bin' and depot='"+depot.getSelectedItem()+"' and ranges='"+ranges.getSelectedItem()+"' and etagere='"+etagere.getSelectedItem()+"' and ligne='"+ligne.getSelectedItem()+"' order by Nom";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
               store_setting.bin.removeAllItems();
          store_setting.bin.addItem("- Bin location -");
            while(rs.next()){
                String sums=rs.getString("Nom");
             
              store_setting.bin.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }        // TODO add your handling code here:
    }//GEN-LAST:event_lignePopupMenuWillBecomeInvisible

    private void binPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_binPopupMenuWillBecomeInvisible
 try{
          String sql="SELECT * FROM stock_bin_location where cat='profondeur' and depot='"+depot.getSelectedItem()+"' and ranges='"+ranges.getSelectedItem()+"' and etagere='"+etagere.getSelectedItem()+"' and ligne='"+ligne.getSelectedItem()+"' and bin='"+bin.getSelectedItem()+"' order by Nom";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
               store_setting.profondeur.removeAllItems();
      //  //    store_setting.profondeur.addItem("- Deep/ Profondeur -");
            while(rs.next()){
                String sums=rs.getString("Nom");
             
              store_setting.profondeur.addItem(sums);
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }        // TODO add your handling code here:
    }//GEN-LAST:event_binPopupMenuWillBecomeInvisible

    private void depotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_depotActionPerformed

    private void rangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rangesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rangesActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
      try{
          String sql="SELECT * FROM caisse_dispacting where access='yes' and cat1='depot' and NAME='"+Home_page.home.user.getText()+"' and Statut='Main store'";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
           if(rs.next()){
                   if(jLabel19.getText().equals("File imported")){
Save_CAISSE_usd();
jLabel19.setText("...");
call();
}else{
imports();
jLabel19.setText("File imported");
}
            }else{
           JOptionPane.showMessageDialog(null,"You do not have credential","Error",JOptionPane.WARNING_MESSAGE);
           }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }


        
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
jLabel19.setText("...");        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel19MouseClicked

    private void textField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField2KeyReleased
 try{
String sql="SELECT BIN as 'Bin Location',NUM as 'References Numbers',NOM as 'Descptions',STORE as 'Store Location',RANGES as Rank,ETAGERE AS Shelves,Ligne,Bins,PROFONDEUR AS Deep,FABRICANT AS 'Maker/Fabricant' FROM  stock_dispacting where  store='"+storemenu.getText()+"' and BIN like '"+textField2.getText()+"' or NUM like '"+textField2.getText()+"%' or NOM like '"+textField2.getText()+"%' or STORE like '"+textField2.getText()+"%' or RANGES like '"+textField2.getText()+"%' or FABRICANT like '"+textField2.getText()+"%'   order by nom";  
            
           // String sql="SELECT BIN as 'Bin Location',NUM as 'References Numbers',NOM as 'Descptions',QTY,UNITE,MAKE,SUPPLY as 'Store Location' FROM  stock_dispacting  where BIN like '"+textField2.getText()+"' or NUM like '"+textField2.getText()+"%' or NOM like '"+textField2.getText()+"%' or MAKE like '"+textField2.getText()+"%' or SUPPLY like '"+textField2.getText()+"%'  order by nom";  
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();

            

        }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);}        // TODO add your handling code here:
    }//GEN-LAST:event_textField2KeyReleased

    private void textField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField3KeyReleased

try{

            String sql="SELECT distinct (NUM) as 'References Numbers',NOM as 'Descptions' FROM  stock_db   where  NUM like '"+textField3.getText()+"%' or NOM like '"+textField3.getText()+"%' or CATEGORY like '"+textField3.getText()+"%'   order by nom";  
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

            jTable4.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();

            

        }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);}          // TODO add your handling code here:
    }//GEN-LAST:event_textField3KeyReleased

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
selectontablemoin();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel23MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
 selectontable(); 



// TODO add your handling code here:
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
if(depot.getSelectedItem().equals("- Store/dépôt -")||ranges.getSelectedItem().equals("- Rank/Rangé -")||etagere.getSelectedItem().equals("- Shelves/ Etagères -")||ligne.getSelectedItem().equals("- Line/ Ligne -")||bin.getSelectedItem().equals("- Bin location -")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
        int row= jTable1.getSelectedRow();
         // String rows =jTable1.getName()
          String Ref = (jTable1.getModel().getValueAt(row,0). toString());
          String Nom = (jTable1.getModel().getValueAt(row,1). toString());
        try {
       String sqls="INSERT INTO stock_dispacting (BIN,NUM,NOM,STORE,RANGES,ETAGERE,LIGNE,BINS,PROFONDEUR,FABRICANT) VALUES (?,?,?,?,?,?,?,?,?,?)";
        pst = con.prepareStatement(sqls);  
        pst.setString(1,ranges.getSelectedItem().toString()+etagere.getSelectedItem().toString()+ligne.getSelectedItem().toString()+bin.getSelectedItem().toString()+profondeur.getSelectedItem().toString());
        pst.setString(2,Ref);
         pst.setString(3,Nom);
         pst.setString(4,depot.getSelectedItem().toString());
         pst.setString(5,ranges.getSelectedItem().toString());
         pst.setString(6,etagere.getSelectedItem().toString());
         pst.setString(7,ligne.getSelectedItem().toString());
         pst.setString(8,bin.getSelectedItem().toString());
         pst.setString(9,profondeur.getSelectedItem().toString());
         pst.setString(10,fabr.getText());
         
          pst.executeUpdate();
    } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());} 
        JOptionPane.showMessageDialog(null, "Data saved");
        call();
       //depot.setSelectedItem("- Store/dépôt -");
      // ranges.setSelectedItem("- Rank/Rangé -");
      // etagere.setSelectedItem("- Shelves/ Etagères -");
      // ligne.setSelectedItem("- Line/ Ligne -");
      // bin.setSelectedItem("- Bin location -");
      // profondeur.setSelectedItem("- Deep/ Profondeur -");  
}
// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void textField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField4KeyReleased
try{

            String sql="SELECT distinct (NUM) as 'References Numbers',NOM as 'Descptions' FROM  stock_dispacting  WHERE NUM like '"+textField4.getText()+"%' or NOM like '"+textField4.getText()+"%'  order by nom";  
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();

            

        }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);}        // TODO add your handling code here:
    }//GEN-LAST:event_textField4KeyReleased

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable5MouseClicked

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable6MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
 TableModel model1 = jTable5.getModel();
        int indexs[] = jTable5.getSelectedRows();
        Object[] row = new Object[6];
        DefaultTableModel model2 = (DefaultTableModel) jTable6.getModel();
        for (int i = 0; i < indexs.length; i++) {
         String num =  jTable5.getValueAt(i, 0).toString(); 
         String Bin = null,Store = null;
        try{
         
          String sql = "SELECT * FROM stock_dispacting WHERE NUM= '"+model1.getValueAt(indexs[i], 0)+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              Bin = rs.getString("BIN");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
             // item_id.setText(add1);
            //  Store= rs.getString("STORE");//SUP,CHANT,NUM,DET,QTY,PU,PT,DATES
              //nom.setText(APPROV);    
          }
         
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}

            
            
            row[0] =  model1.getValueAt(indexs[i], 0);
            row[1] = model1.getValueAt(indexs[i], 1);
            row[2] ="EA";
            row[3] = "0.0";
            row[4] = "0.0";
            row[5] = storemenu.getText();
           //  row[6] = Store;
           // here
//        row[2]= model1.getValueAt(indexs[i],2);
//        row[3]= model1.getValueAt(indexs[i],3);
            model2.addRow(row);

            TableColumn col0 = jTable6.getColumnModel().getColumn(0);
            TableColumn col1 = jTable6.getColumnModel().getColumn(1);
            TableColumn col2 = jTable6.getColumnModel().getColumn(2);
            TableColumn col3 = jTable6.getColumnModel().getColumn(3);
            TableColumn col4=  jTable6.getColumnModel().getColumn(4);
            TableColumn col5 = jTable6.getColumnModel().getColumn(5);
          //  TableColumn col6=  jTable6.getColumnModel().getColumn(6);

            col0.setPreferredWidth(100);
            col1.setPreferredWidth(250);
            col2.setPreferredWidth(20);
            col3.setPreferredWidth(50);
            col4.setPreferredWidth(50);
            col5.setPreferredWidth(100);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
int indexs[] = jTable6.getSelectedRows();

        DefaultTableModel model = (DefaultTableModel) jTable6.getModel();
        for (int i = 0; i < indexs.length; i++) {

            int getSelectedRowsForDeletion = jTable6.getSelectedRow();
            model.removeRow(getSelectedRowsForDeletion);

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable7MouseClicked

    private void textField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField5KeyReleased
try{
         String sql="SELECT Bin as 'Bin loc',REF as 'Ref. Numbers',NOM as 'Descriptions',(SUM(QTY_D)-SUM(QTY_C)) as 'Main Store Qty',(SUM(STOCK_SUB)-SUM(STOCK_SUBC)) as 'Sub Store (LME)',(SUM(QTY_D)-SUM(QTY_C))+(SUM(STOCK_SUB)-SUM(STOCK_SUBC)) as 'Total Qty',Fabricant,DEPOT as Store FROM  stock_mvm  WHERE Bin like '"+textField5.getText()+"%' or REF like '"+textField5.getText()+"%' or NOM like '"+textField5.getText()+"%' or DEPOT like '"+textField5.getText()+"%'  GROUP BY REF order by nom";  
         
         //   String sql="SELECT Bin as 'Bin location',REF as 'References Numbers',NOM as 'Descptions',(SUM(QTY_D)-SUM(QTY_C)) as Quantities,Fabricant,DEPOT as Store FROM  stock_mvm  WHERE Bin like '"+textField5.getText()+"%' or REF like '"+textField5.getText()+"%' or NOM like '"+textField5.getText()+"%' or DEPOT like '"+textField5.getText()+"%'  GROUP BY REF order by nom";  
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();

            mode=new DefaultTableModel();

            mode=new DefaultTableModel();

            TableColumn col0=jTable7.getColumnModel().getColumn(0);
            TableColumn col1=jTable7.getColumnModel().getColumn(1);
            TableColumn col2=jTable7.getColumnModel().getColumn(2);
            TableColumn col3=jTable7.getColumnModel().getColumn(3);
            TableColumn col4=jTable7.getColumnModel().getColumn(4);
             TableColumn col5=jTable7.getColumnModel().getColumn(5);
              TableColumn col6=jTable7.getColumnModel().getColumn(6);
            TableColumn col7=jTable7.getColumnModel().getColumn(7);
            TableColumn col8=jTable7.getColumnModel().getColumn(8);
            TableColumn col9=jTable7.getColumnModel().getColumn(9);
             TableColumn col10=jTable7.getColumnModel().getColumn(10);
            
            
            
           col0.setPreferredWidth(100);
            col1.setPreferredWidth(100);
             col2.setPreferredWidth(100);
            col3.setPreferredWidth(100);
             col4.setPreferredWidth(250);
             col5.setPreferredWidth(100);
              col6.setPreferredWidth(50);
             col7.setPreferredWidth(50);
            col8.setPreferredWidth(50);
             col9.setPreferredWidth(100);
             col10.setPreferredWidth(100);
            

        }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);}         // TODO add your handling code here:
    }//GEN-LAST:event_textField5KeyReleased

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
if(jLabel21.getText().equals("File imported")){
Save_ENTREE();
   try{
            String sqls="select max(num) from  stock_mvm where QTY_C='0.00'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
            item_id2.setText(rs.getString("max(num)"));
            }
            }
     
        catch(Exception ex){
          JOptionPane.showMessageDialog(null, ex);    
        }
jLabel21.setText("...");
 try{

           // String sql="SELECT BIN as 'Bin Location' , REF as 'References Numbers',NOM as 'Descptions',QTY_D as 'Qty' FROM  stock_mvm where Num='"+item_id2.getText()+"' order by nom";  
            String sql="SELECT BIN as 'Bin Location' , REF as 'References Numbers',NOM as 'Descptions',QTY_D as 'Qty',Depot as Store FROM  stock_mvm  order by nom";  
           
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

            jTable6.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();

            TableColumn col0 = jTable6.getColumnModel().getColumn(0);
            TableColumn col1 = jTable6.getColumnModel().getColumn(1);
            TableColumn col2 = jTable6.getColumnModel().getColumn(2);
            TableColumn col3 = jTable6.getColumnModel().getColumn(3);
            TableColumn col4=  jTable6.getColumnModel().getColumn(4);
          //  TableColumn col5=  jTable6.getColumnModel().getColumn(4);

            col0.setPreferredWidth(50);
            col1.setPreferredWidth(100);
            col2.setPreferredWidth(200);
            col3.setPreferredWidth(50);
            col4.setPreferredWidth(100);
           /// col.setPreferredWidth(100);
            

        }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);}
        
}else{
importENTREE();
jLabel21.setText("File imported");
}        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
call_idToken();
     try{
          String sqlsss="SELECT * FROM caisse_dispacting where access='yes' and cat1='depot' and NAME='"+Home_page.home.user.getText()+"' and Statut='Main store'";
             pst = con.prepareStatement(sqlsss);rs=pst.executeQuery();
           if(rs.next()){
if(numfiche.getText().equals("")||respo.getSelectedItem().equals("- Supply/Fournisseur -")||contact.getText().equals("")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
        etroll();
DefaultTableModel excels= (DefaultTableModel)jTable6.getModel(); 
        for(int i=0; i < excels.getRowCount();i++){
  String  Ref = excels.getValueAt(i,0).toString();
  String  Nom  = excels.getValueAt(i,1). toString();
  //String Nom = excels.getValueAt(i,2). toString();
  String Qty = excels.getValueAt(i,3). toString();
  String Qty_r = excels.getValueAt(i,4). toString();
  String Depot = excels.getValueAt(i,5). toString();
  String Ranges =null,Bins =null,Etagere =null,Ligne =null,Profondeur =null,Fabricant=null,Bin=null,Sapcode=null,Cat=null,Oldnum=null;
  
  int row= jTable6.getSelectedRow();
          String Table_click = (jTable6.getModel().getValueAt(row,0). toString());
         // jLabel12.setText(Table_click);
        try{
         
          String sql = "SELECT * FROM stock_dispacting WHERE NUM= '"+Ref+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        Ranges = rs.getString("RANGES");
        Etagere = rs.getString("ETAGERE");
        Ligne = rs.getString("LIGNE");
        Bins = rs.getString("BINS");
        Bin = rs.getString("BIN");
        Profondeur = rs.getString("PROFONDEUR");
      //  Fabricant = rs.getString("FABRICANT");
          }
         }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
        
          try{
         
          String sql = "SELECT * FROM stock_db WHERE NUM= '"+Ref+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              //Sapcode,Cat,Oldnum
        Sapcode = rs.getString("PARTCODE");
       Cat = rs.getString("CATEGORY");
       Oldnum = rs.getString("OLDNUM");
       Fabricant = rs.getString("FABRICANT");
       
          }
         }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
  
              try {
       String sqls="INSERT INTO stock_mvm (NUM_FICHE,RESP,CONTACT,BIN,REF,NOM,DEPOT,RANGER,ETAGERE,LIGNE,BINS,PROFONDEUR,QTY_D,QTY_C,NUM,FABRICANT,DATES,NOMS,QTY_R,PARTCODE,CATEGORY,OLDNUM,RN,PR,PO,STOCK_SUB,STOCK_SUBC) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst = con.prepareStatement(sqls);  
        pst.setString(1, numfiche.getText());
        pst.setString(2,respo.getSelectedItem().toString());
         pst.setString(3,contact.getText());
         pst.setString(4,Bin);
         pst.setString(5,Ref);
         pst.setString(6,Nom);
         pst.setString(7,Depot);
         pst.setString(8,Ranges);
         
         pst.setString(9,Etagere);
         pst.setString(10,Ligne);
         pst.setString(11,Bins);
         pst.setString(12,Profondeur);
         pst.setString(13,Qty_r);
         pst.setString(14,"0.00");
         pst.setString(15,rolls);
         pst.setString(16,Fabricant);
         pst.setString(17,dates.getText());
         pst.setString(18,Home_page.home.user.getText());
         pst.setString(19,ID_TOKEN);
         //Sapcode,Cat,Oldnum
          pst.setString(20,Sapcode);
         pst.setString(21,Cat);
         pst.setString(22,Oldnum);
         pst.setString(23,"-");
           pst.setString(24,"-");
           pst.setString(25,"-");
           pst.setString(26,"0.00");
           pst.setString(27,"0.00");
         
         
          pst.executeUpdate();
    } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
                try{
            String sqls="select max(num) from  stock_mvm where QTY_C='0.00' and STOCK_SUB='0.00'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
            item_id2.setText(rs.getString("max(num)"));
            }
            }
     
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 
 try{
            String sqls="select (QTY_R-qty_D) from  stock_mvm where  NUM='"+item_id2.getText()+"'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
         Double   sold = rs.getDouble("(QTY_R-qty_D)");
         if(sold.equals(0.0)){
          try{
             
    String sql1="UPDATE stock_rfq SET STATUT=? WHERE  RFQNUM ='"+jLabel44.getText()+"' and REF='"+Ref+"'";
     
    pst=con.prepareStatement(sql1);
    pst.setString(1,"OK");
 
    pst.executeUpdate();
     // JOptionPane.showMessageDialog(null,"Datas Saved");
    }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
         }
            }
            }
     
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
  
    }
       


//END FOR
 JOptionPane.showMessageDialog(null,"Tranction Saved");
  
  numfiche.setText("");
  respo.setSelectedItem("- Supply/Fournisseur -");
  contact.setText("");}
  DefaultTableModel dm= (DefaultTableModel)jTable6.getModel();
while (dm.getRowCount()>0){dm.removeRow(0);}
  call_fiche();
  call();
            }else{
              
if(numfiche.getText().equals("")||respo.getSelectedItem().equals("- Supply/Fournisseur -")||contact.getText().equals("")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
try{
            String sql="SELECT NUM FROM  stock_mvm where QTY_C = '0.00' and STOCK_SUBC='0.00' ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,3);
                 String snum=rl.substring(3,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum ;
          
             }else{
                 rolls="13-1000000001";
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
DefaultTableModel excels= (DefaultTableModel)jTable6.getModel(); 
        for(int i=0; i < excels.getRowCount();i++){
  String  Ref = excels.getValueAt(i,0).toString();
  String  Nom  = excels.getValueAt(i,1). toString();
  //String Nom = excels.getValueAt(i,2). toString();
  String Qty = excels.getValueAt(i,3). toString();
  String Qty_r = excels.getValueAt(i,4). toString();
  String Depot = excels.getValueAt(i,5). toString();
  String Ranges ="-",Bins ="-",Etagere ="-",Ligne ="-",Profondeur ="-",Fabricant="-",Bin="-",Sapcode="-",Cat="-",Oldnum="-";
  
  int row= jTable6.getSelectedRow();
          String Table_click = (jTable6.getModel().getValueAt(row,0). toString());
         // jLabel12.setText(Table_click);
        try{
         
          String sql = "SELECT * FROM stock_dispacting WHERE NUM= '"+Ref+"'  and STORE='"+storemenu.getText()+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        Ranges = rs.getString("RANGES");
        Etagere = rs.getString("ETAGERE");
        Ligne = rs.getString("LIGNE");
        Bins = rs.getString("BINS");
        Bin = rs.getString("BIN");
        Profondeur = rs.getString("PROFONDEUR");
        }
         }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
        
          try{
         
          String sql = "SELECT * FROM stock_db WHERE NUM= '"+Ref+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              //Sapcode,Cat,Oldnum
        Sapcode = rs.getString("PARTCODE");
       Cat = rs.getString("CATEGORY");
       Oldnum = rs.getString("OLDNUM");
        Fabricant = rs.getString("FABRICANT");
       
          }
         }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
  
              try {
       String sqls="INSERT INTO stock_mvm (NUM_FICHE,RESP,CONTACT,BIN,REF,NOM,DEPOT,RANGER,ETAGERE,LIGNE,BINS,PROFONDEUR,QTY_D,QTY_C,NUM,FABRICANT,DATES,NOMS,QTY_R,PARTCODE,CATEGORY,OLDNUM,STOCK_SUB,STOCK_SUB_BIN,RN,PR,PO,MS_SYNCHRONISED) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst = con.prepareStatement(sqls);  
        pst.setString(1, numfiche.getText());
        pst.setString(2,respo.getSelectedItem().toString());
         pst.setString(3,contact.getText());
         pst.setString(4,"-");
         pst.setString(5,Ref);
         pst.setString(6,Nom);
         pst.setString(7,Depot);
         pst.setString(8,"-");
         
         pst.setString(9,"-");
         pst.setString(10,"-");
         pst.setString(11,"-");
         pst.setString(12,"-");
         pst.setString(13,"0.00");
         pst.setString(14,"0.00");
         pst.setString(15,rolls);
         pst.setString(16,Fabricant);
         pst.setString(17,dates.getText());
         pst.setString(18,Home_page.home.user.getText());
         pst.setString(19,ID_TOKEN);
         //Sapcode,Cat,Oldnum
          pst.setString(20,Sapcode);
         pst.setString(21,Cat);
         pst.setString(22,Oldnum);
          pst.setString(23,Qty_r);
           pst.setString(24,Bin);
           pst.setString(25,"-");
           pst.setString(26,"-");
           pst.setString(27,"-");
           pst.setString(28,"YES");
         
         
          pst.executeUpdate();
    } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
                try{
            String sqls="select max(num) from  stock_mvm where QTY_C='0.00' and STOCK_SUB='0.00'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
            item_id2.setText(rs.getString("max(num)"));
            }
            }
     
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 

  
    }
       


//END FOR
 JOptionPane.showMessageDialog(null,"Tranction Saved");
  
  numfiche.setText("");
  respo.setSelectedItem("- Supply/Fournisseur -");
  contact.setText("");}
           }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
        
        
         // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
this.dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked

    private void prixachatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prixachatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prixachatActionPerformed

    private void clientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientActionPerformed

    private void item_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_item_idKeyReleased
try{
          String sql="SELECT * FROM stock_db where  NUM='"+item_id.getText()+"'";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
              
            while(rs.next()){
           nom.setText(rs.getString("NOM"));
           fabr.setText(rs.getString("FABRICANT"));
          prixachat.setText(rs.getString("PRIXACHAT"));
          prixvente.setText(rs.getString("PRIXVENTE"));
          alert_min.setText(rs.getString("ALERT"));
            
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }        // TODO add your handling code here:
    }//GEN-LAST:event_item_idKeyReleased

    private void jTable9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable9MouseClicked

    private void jTable10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable10MouseClicked

    private void textField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField6KeyReleased
try{
        //  String sql="SELECT `ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin` WHERE  `ORIENTATION`='FINANCE' AND ORIENTATION2='Fin' and buss='"+journal1.buss.getText()+"'";   pst = con.prepareStatement(sql);
           
        PreparedStatement ps = con.prepareStatement("Select * from  stock_mvm WHERE  REF LIKE '"+textField6.getText()+"%'");
        ResultSet rs=ps.executeQuery();
        DefaultTableModel tm = (DefaultTableModel)jTable10.getModel();
        tm.setRowCount(0);

      while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
            Object o[] = {rs.getString("BIN"),rs.getString("REF")};
            tm.addRow(o);



        }
    mode=new DefaultTableModel();

           

            TableColumn col0=jTable10.getColumnModel().getColumn(0);
            TableColumn col1=jTable10.getColumnModel().getColumn(1);
            TableColumn col2=jTable10.getColumnModel().getColumn(2);
            
            
            col0.setPreferredWidth(150);
            col1.setPreferredWidth(150);
             col2.setPreferredWidth(50);
    }
    catch(Exception e){

        JOptionPane.showMessageDialog(null,"Error in Employee Grid View..... "+e);
    }
          // TODO add your handling code here:
    }//GEN-LAST:event_textField6KeyReleased

    private void textField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField7KeyReleased
   try{
      // BIN like '"+textField7.getText+"' or REF like '"+textField7.getText+"' or stock_mvm.NOM like '"+textField7.getText+"' or DEPOT like '"+textField7.getText+"' or stock_mvm.FABRICANT like '"+textField7.getText+"'
      String sql = "SELECT BIN as 'Bin Loc',REF as 'Ref. Numbers',stock_mvm.NOM as 'Descriptions',DEPOT as 'Store Location', Format(SUM(QTY_D)-SUM(QTY_C),2) AS Quantity,Format(PRIXACHAT,2) as 'Cost price',Format((SUM(QTY_D)-SUM(QTY_C))*PRIXACHAT,2) as 'Total value' ,stock_mvm.FABRICANT as 'Fabricant/Maker' FROM  stock_mvm inner join stock_db on stock_mvm.REF=stock_db.NUM where BIN like '"+textField7.getText()+"%' or REF like '"+textField7.getText()+"%'  or stock_mvm.NOM like '"+textField7.getText()+"%'  or DEPOT like '"+textField7.getText()+"%'  or stock_mvm.FABRICANT like '"+textField7.getText()+"%'   GROUP BY REF";
          pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

            jTable9.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();
         
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}        // TODO add your handling code here:
    }//GEN-LAST:event_textField7KeyReleased

    private void jLabel46MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel46MouseClicked

          try{
          String sqls="SELECT * FROM caisse_dispacting where access='yes' and cat1='depot' and NAME='"+Home_page.home.user.getText()+"' and Statut='Main store'";
             pst = con.prepareStatement(sqls);rs=pst.executeQuery();
           if(rs.next()){
 try{
                 
                 
                   String sqlss="Select path from pathn";
           
            pst=con.prepareStatement(sqlss);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"UMCO_bonstock.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
             // String sql="select * from inventairemtr WHERE NUM_ID='"+num.getText()+"'";
              String sql="select * from  stock_mvm  WHERE NUM='"+item_id2.getText()+"'";
              //select * from  inventairemtr INNER JOIN materiaux_in ON inventairemtr.NUM=materiaux_in.NUM_ID
              
     HashMap param= new HashMap();
    param.put("nom", "BON D'ENTREE STOCK NO:");
    param.put("four", "Service acheteur");
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 
                
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
                 
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }  
            }else{
            try{
                 
                 
                   String sqlss="Select path from pathn";
           
            pst=con.prepareStatement(sqlss);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"UMCO_bonstock_1.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
             // String sql="select * from inventairemtr WHERE NUM_ID='"+num.getText()+"'";
              String sql="select * from  stock_mvm  WHERE NUM='"+item_id2.getText()+"'";
              //select * from  inventairemtr INNER JOIN materiaux_in ON inventairemtr.NUM=materiaux_in.NUM_ID
              
     HashMap param= new HashMap();
    param.put("nom", "BON D'ENTREE STOCK NO:");
    param.put("four", "Service acheteur");
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 
                
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
                 
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             } 
           }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
          // TODO add your handling code here:
    }//GEN-LAST:event_jLabel46MouseClicked

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
MessageFormat header = new MessageFormat("Picking List");   
 MessageFormat footer = new MessageFormat("Page{0,number,integer}"); 
 try{
jTable3.print(JTable.PrintMode.FIT_WIDTH,header,footer);
 }catch(PrinterException ex){
          JOptionPane.showMessageDialog(null, ex);    
        }  
                // TODO add your handling code here:
    }//GEN-LAST:event_jLabel24MouseClicked

    private void jLabel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseClicked
try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"UMCO_fiche_invt.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
          // String sql = "SELECT BIN as 'Bin Loc',REF as 'Ref. Numbers',stock_mvm.NOM as 'Descriptions',DEPOT as 'Store Location', Format(SUM(QTY_D)-SUM(QTY_C),2) AS Quantity,Format((PRIXACHAT*SUM(QTY_D)-SUM(QTY_C)),2) as 'Total value' ,stock_mvm.FABRICANT as 'Fabricant/Maker' FROM  stock_mvm inner join stock_db on stock_mvm.REF=stock_db.NUM GROUP BY REF";
      String sql = "SELECT BIN as 'Bin Loc',REF as 'Ref. Numbers',stock_mvm.NOM as 'Descriptions',DEPOT as 'Store Location', Format(SUM(QTY_D)-SUM(QTY_C),2) AS Quantity,PRIXACHAT,stock_mvm.FABRICANT as 'Fabricant/Maker' FROM  stock_mvm inner join stock_db on stock_mvm.REF=stock_db.NUM group by REF";
      
      //SELECT BIN as 'Bin Loc',REF as 'Ref. Numbers',stock_mvm.NOM as 'Descriptions',DEPOT as 'Store Location', Format(SUM(QTY_D)-SUM(QTY_C),2) AS Quantity,PRIXACHAT,stock_mvm.FABRICANT as 'Fabricant/Maker' FROM  stock_mvm inner join stock_db on stock_mvm.REF=stock_db.NUM group by REF
      
     HashMap param= new HashMap();
    param.put("nom", "BON D'ENTREE STOCK NO:");
    param.put("four",demandeur.getText());
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 
                
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
                 
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }          // TODO add your handling code here:
    }//GEN-LAST:event_jLabel43MouseClicked

    private void depotsPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_depotsPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_depotsPopupMenuWillBecomeInvisible

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
jLabel21.setText("...");          // TODO add your handling code here:
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
MessageFormat header = new MessageFormat("Search result");   
 MessageFormat footer = new MessageFormat("Page{0,number,integer}"); 
 try{
jTable7.print(JTable.PrintMode.FIT_WIDTH,header,footer);
 }catch(PrinterException ex){
          JOptionPane.showMessageDialog(null, ex);    
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel35MouseClicked

    private void textField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField8KeyReleased
 try{
       String sql="SELECT REF as 'References Numbers',NOM as 'Descptions' FROM stock_mvm WHERE REF like '"+textField8.getText()+"%' or NOM like '"+textField8.getText()+"%' or FABRICANT like '"+textField8.getText()+"%' or CATEGORY like '"+textField8.getText()+"%' GROUP BY REF order by nom";  
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

            jTable11.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();

           

            TableColumn col0=jTable11.getColumnModel().getColumn(0);
            TableColumn col1=jTable11.getColumnModel().getColumn(1);
            
            
            col0.setPreferredWidth(100);
            col1.setPreferredWidth(150);
             

        }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);}          // TODO add your handling code here:
    }//GEN-LAST:event_textField8KeyReleased

    private void jTable11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable11MouseClicked

    private void jLabel47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel47MouseClicked
TableModel model1 = jTable11.getModel(); 
        int indexs[] = jTable11.getSelectedRows();
        Object[] row = new Object[11];
        DefaultTableModel model2 = (DefaultTableModel) jTable7.getModel();
        for (int i = 0; i < indexs.length; i++) {
        
         String Bin_Location = null,Sapcode=null,Oldcode=null,Cat=null,References_Numbers = null,Descptions=null,Fabricant=null,Store=null,Bin_Locationlme=null;
         Double Quantities=null,Quantitiesub=null,Quantitietot=null;
        try{
         
          String sql="SELECT Bin,STOCK_SUB_BIN,REF,NOM,(SUM(QTY_D)-SUM(QTY_C)),(SUM(STOCK_SUB)-SUM(STOCK_SUBC)),Fabricant,DEPOT,PARTCODE,CATEGORY,OLDNUM FROM  stock_mvm  WHERE REF='"+model1.getValueAt(indexs[i], 0)+"' GROUP BY REF order by nom";  
           
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              Bin_Location = rs.getString("BIN");
              Bin_Locationlme = rs.getString("STOCK_SUB_BIN");
             // References_Numbers = rs.getString("REF");
             // Descptions = rs.getString("NOM");
              Quantities = rs.getDouble("(SUM(QTY_D)-SUM(QTY_C))");
              Quantitiesub = rs.getDouble("(SUM(STOCK_SUB)-SUM(STOCK_SUBC))");
              Quantitietot =Quantities+Quantitiesub ;
              Fabricant = rs.getString("FABRICANT");
              Store = rs.getString("DEPOT"); 
              Sapcode = rs.getString("PARTCODE"); 
              Cat = rs.getString("CATEGORY"); 
              Oldcode = rs.getString("OLDNUM"); 
              
          }
         
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}

            
            
            row[0] =   Bin_Location;
            row[1] = model1.getValueAt(indexs[i], 0);
            row[2] = Oldcode;
            row[3] = Sapcode;
            row[4] = model1.getValueAt(indexs[i], 1);
            row[5] = Cat;
            row[6] = Quantities;
            
            // Sapcode,,
            
            row[7] = Quantitiesub;
            row[8] = Quantitietot;
            row[9] = Fabricant;
            row[10] = Store;
            model2.addRow(row);

             mode=new DefaultTableModel();

            TableColumn col0=jTable7.getColumnModel().getColumn(0);
            TableColumn col1=jTable7.getColumnModel().getColumn(1);
            TableColumn col2=jTable7.getColumnModel().getColumn(2);
            TableColumn col3=jTable7.getColumnModel().getColumn(3);
            TableColumn col4=jTable7.getColumnModel().getColumn(4);
             TableColumn col5=jTable7.getColumnModel().getColumn(5);
              TableColumn col6=jTable7.getColumnModel().getColumn(6);
            TableColumn col7=jTable7.getColumnModel().getColumn(7);
            TableColumn col8=jTable7.getColumnModel().getColumn(8);
            TableColumn col9=jTable7.getColumnModel().getColumn(9);
             TableColumn col10=jTable7.getColumnModel().getColumn(10);
            
            
            
           col0.setPreferredWidth(100);
            col1.setPreferredWidth(100);
             col2.setPreferredWidth(100);
            col3.setPreferredWidth(100);
             col4.setPreferredWidth(250);
             col5.setPreferredWidth(100);
              col6.setPreferredWidth(50);
             col7.setPreferredWidth(50);
            col8.setPreferredWidth(50);
             col9.setPreferredWidth(100);
             col10.setPreferredWidth(100);
           //  col8.setPreferredWidth(150);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel47MouseClicked

    private void jLabel48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel48MouseClicked
   int indexs[] = jTable7.getSelectedRows();

        DefaultTableModel model = (DefaultTableModel) jTable7.getModel();
        for (int i = 0; i < indexs.length; i++) {

            int getSelectedRowsForDeletion = jTable7.getSelectedRow();
            model.removeRow(getSelectedRowsForDeletion); }       // TODO add your handling code here:
    }//GEN-LAST:event_jLabel48MouseClicked

    private void textField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField9KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField9KeyReleased

    private void textField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField10KeyReleased
    try{

            String sql="SELECT BIN as 'Bin location', NUM as 'References Numbers',NOM as 'Descptions' FROM  stock_dispacting where NUM like '"+textField10.getText()+"%' or NOM like '"+textField10.getText()+"%' or FABRICANT like '%"+textField10.getText()+"%'  group by NUM order by nom";  
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

          //  jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            tb_itms_cmd.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();
 }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);}
               // TODO add your handling code here:
    }//GEN-LAST:event_textField10KeyReleased

    private void fourn_cmdPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_fourn_cmdPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_fourn_cmdPopupMenuWillBecomeInvisible

    private void fourn_cmdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fourn_cmdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fourn_cmdActionPerformed

    private void textField11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField11KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField11KeyReleased

    private void jLabel57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel57MouseClicked

     
         try{
          String sql="SELECT * FROM caisse_dispacting where access='yes' and cat1='depot' and NAME='"+Home_page.home.user.getText()+"' and Statut='Main store'";
             pst = con.prepareStatement(sql);rs=pst.executeQuery();
           if(rs.next()){
  int indexs[] = {1};

        Object[] row = new Object[6];
        DefaultTableModel model2 = (DefaultTableModel) rfq_supply.getModel();
        for (int i = 0; i < indexs.length; i++) {
            row[0] = "Bin location...";
            row[1] = "References...";
            row[2] = "Description...";
            row[3] = "0.0";
            row[4]= "0.0";
            row[5]= "0.0";
            model2.addRow(row);

            // jTable3.setModel(DbUtils.resultSetToTableModel(rs));
            TableColumn col0 = rfq_supply.getColumnModel().getColumn(0);
            TableColumn col1 = rfq_supply.getColumnModel().getColumn(1);
            TableColumn col2 = rfq_supply.getColumnModel().getColumn(2);
            TableColumn col3 = rfq_supply.getColumnModel().getColumn(3);
             TableColumn col4= rfq_supply.getColumnModel().getColumn(4);
             TableColumn col5= rfq_supply.getColumnModel().getColumn(4);

            col0.setPreferredWidth(50);
            col1.setPreferredWidth(100);
            col2.setPreferredWidth(150);
            col3.setPreferredWidth(50);
            col4.setPreferredWidth(50);
            col5.setPreferredWidth(50);
            //
        } 
            }else{
           JOptionPane.showMessageDialog(null,"You do not have credential","Error",JOptionPane.WARNING_MESSAGE);
           }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
        
               // TODO add your handling code here:
    }//GEN-LAST:event_jLabel57MouseClicked

    private void jLabel58MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel58MouseClicked
 try {
         SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MMM");
            String addDateS = dateFormatsS.format(new Date());   

            String sql = "SELECT num FROM stock_rfq  ORDER BY num DESC LIMIT 1";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String rl = rs.getString("num");
                int ln = rl.length();
                String stxt = rl.substring(0, 3);
                String snum_fiche = rl.substring(3, ln);
                int n = Integer.parseInt(snum_fiche);
                n++;
                snum_fiche = Integer.toString(n);
                if (n < 10) {
                    rolls = stxt + "00" + snum_fiche;//001 ou 009
                } else if (n < 100) {
                    rolls = stxt + "0" + snum_fiche;//010 ou 099
                } else if (n > 100) {
                    rolls = stxt + snum_fiche;// 100 ou 199
                } else if (n > 1000) {
                    rolls = stxt + snum_fiche;// 1000 ou 1999
                }
            } else {
                rolls = "75-230803"; //CMD - 2024-JUN - 001

            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
      //  num.setText(rolls);
        
        
        int rows = rfq_supply.getRowCount();

        for (int row = 0; row < rows; row++) {

          //  String libelle;
            //  ReplaceString = libelle.replace("'", "''");
            String REF = (String) rfq_supply.getValueAt(row, 0);
            String NOM = (String) rfq_supply.getValueAt(row, 1);
            String UNIT = (String) rfq_supply.getValueAt(row, 2);
            String QTY_REQ = (String) rfq_supply.getValueAt(row, 3);
             String QTY_AV = "0.00";
            String UP_PRICE = "0.00";
           String TOTAL = "0.00";
           String MAKER = (String)rfq_supply.getValueAt(row, 7);
           
           String STORE = (String)rfq_supply.getValueAt(row, 8);
          
                   
       
            try {
 PreparedStatement pst = con.prepareStatement("INSERT INTO `stock_rfq`( `REF`, `NOM`, `UNIT`, `QTY_REQ`, `QTY_AV`, `UP_PRICE`, `TOTAL`, `STORE`, `RFQNUM`, `DATE`, `STOCK_VEHICULE`, `PO`, `SUPPLY`, `NUM`,`REGION`,`MAKER`)"
                        + "value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
// `REF`, `NOM`, `UNIT`, `QTY_REQ`, `QTY_AV`, `UP_PRICE`, `TOTAL`, `STORE`, `RFQ_NUM`, `DATE`, `STOCK_VEHICULE`, `PO`, `SUPPLY`, `NUM`
                pst.setString(1, REF);
                pst.setString(2, NOM);
                pst.setString(3, UNIT);
                pst.setString(4, QTY_REQ);
                 pst.setString(5, QTY_AV);
                pst.setString(6, UP_PRICE);
                pst.setString(7, TOTAL);
                pst.setString(8,STORE);
                pst.setString(9, "");
                pst.setString(10, date_cmd.getText());
                pst.setString(11, stock_vehicule.getText());
                pst.setString(12, p_o.getText());
                pst.setString(13, fourn_cmd.getSelectedItem().toString());
                pst.setString(14, rolls);
                pst.setString(15, region.getSelectedItem().toString());
                pst.setString(16, MAKER);

                pst.executeUpdate();

                //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }
        JOptionPane.showMessageDialog(null, "Transaction Saved"); 
  try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"UMCO_request_for_quotation.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
             // String sql="select * from inventairemtr WHERE NUM_ID='"+num.getText()+"'";
              String sql="select * from  stock_rfq  WHERE NUM='"+rolls+"'";
              //select * from  inventairemtr INNER JOIN materiaux_in ON inventairemtr.NUM=materiaux_in.NUM_ID
              
     HashMap param= new HashMap();
    //param.put("nom", "BON D'ENTREE STOCK NO:");
    param.put("four", "Service acheteur");
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 
                
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
                 
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             } 
DefaultTableModel dm= (DefaultTableModel)rfq_supply.getModel();
while (dm.getRowCount()>0){dm.removeRow(0);}
call_fiche();
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel58MouseClicked

    private void date_cmdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_date_cmdKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_date_cmdKeyReleased

    private void Max_alertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Max_alertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Max_alertActionPerformed

    private void jLabel53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel53MouseClicked
/*
        int indexs[] = tb_itms_cmd.getSelectedRows();
        Object[] row = new Object[6];
        DefaultTableModel model2 = (DefaultTableModel) tb_bon_de_commande.getModel();
        for (int i = 0; i < indexs.length; i++) {
         String aaa =  tb_itms_cmd.getValueAt(i, 0).toString();
         JOptionPane.showMessageDialog(null,aaa);
         String Bin = null,prixachats = null,noms=null;
        try{
         String sql = "SELECT * FROM  stock_dispacting WHERE NUM= '"+aaa+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              Bin = rs.getString("BIN");
              noms = rs.getString("NOM");
                //Bin = rs.getString("BIN");
        }
     }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
            try{
         String sql = "SELECT * FROM  stock_db WHERE NUM= '"+aaa+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              prixachats = rs.getString("PRIXACHAT");
        }
     }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}

            
         JOptionPane.showMessageDialog(null,aaa+" - "+noms);   
            row[0] = Bin;
            row[1] = aaa;
            row[2] = noms;
            row[3] = "0";
             row[4] = prixachats;
         row[5]= "0";
            model2.addRow(row);

            TableColumn col0 = tb_bon_de_commande.getColumnModel().getColumn(0);
            TableColumn col1 = tb_bon_de_commande.getColumnModel().getColumn(1);
            TableColumn col2 = tb_bon_de_commande.getColumnModel().getColumn(2);
            TableColumn col3 = tb_bon_de_commande.getColumnModel().getColumn(3);
             TableColumn col4= tb_bon_de_commande.getColumnModel().getColumn(4);
             TableColumn col5= tb_bon_de_commande.getColumnModel().getColumn(4);

            col0.setPreferredWidth(50);
            col1.setPreferredWidth(100);
            col2.setPreferredWidth(150);
            col3.setPreferredWidth(50);
            col4.setPreferredWidth(50);
            col5.setPreferredWidth(50);
            
        }  
       */     
   
     try{
          String sqls="SELECT * FROM caisse_dispacting where access='yes' and cat1='depot' and NAME='"+Home_page.home.user.getText()+"' and Statut='Main store'";
             pst = con.prepareStatement(sqls);rs=pst.executeQuery();
           if(rs.next()){
TableModel model1 = tb_itms_cmd.getModel();       
   int indexs[] = tb_itms_cmd.getSelectedRows();
        Object[] row = new Object[9];
        DefaultTableModel model2 = (DefaultTableModel)rfq_supply.getModel();
        for (int i = 0; i < indexs.length; i++) {
      String prixachats=null,store=null,maker=null;
          String num =  tb_itms_cmd.getValueAt(i, 1).toString(); 
        try{
         
          String sql="SELECT * FROM  stock_db  WHERE NUM='"+model1.getValueAt(indexs[i], 1)+"'";  
           
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              prixachats = rs.getString("PRIXACHAT"); 
              // prixachats = rs.getString("PRIXACHAT"); // stock_dispacting
          }
         
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
        try{
         
          String sql="SELECT * FROM  stock_dispacting  WHERE NUM='"+model1.getValueAt(indexs[i], 1)+"'";  
           
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              store = rs.getString("STORE"); 
               maker = rs.getString("FABRICANT"); // stock_dispacting
          }
         
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}

            
           
            row[0] = model1.getValueAt(indexs[i], 1);
            row[1] = model1.getValueAt(indexs[i], 2);
            row[2] = "EA";
            row[3] = "0.00";
            row[4] = "0.00";
            row[5] = "0.00";
            row[6] = "0.00";
            row[7] = maker;
            row[8] = store;
            model2.addRow(row);

            TableColumn col0 = rfq_supply.getColumnModel().getColumn(0);
            TableColumn col1 = rfq_supply.getColumnModel().getColumn(1);
            TableColumn col2 = rfq_supply.getColumnModel().getColumn(2);
            TableColumn col3 = rfq_supply.getColumnModel().getColumn(3);
             TableColumn col4= rfq_supply.getColumnModel().getColumn(4);
             TableColumn col5= rfq_supply.getColumnModel().getColumn(5);
             TableColumn col6= rfq_supply.getColumnModel().getColumn(6);
              TableColumn col7= rfq_supply.getColumnModel().getColumn(7);
             TableColumn col8= rfq_supply.getColumnModel().getColumn(8);

            col0.setPreferredWidth(100);
            col1.setPreferredWidth(150);
            col2.setPreferredWidth(20);
            col3.setPreferredWidth(50);
            col4.setPreferredWidth(50);
            col5.setPreferredWidth(50);
            col6.setPreferredWidth(100);
             col7.setPreferredWidth(100);
            col8.setPreferredWidth(100);
        }   
            }else{
           JOptionPane.showMessageDialog(null,"You do not have credential","Error",JOptionPane.WARNING_MESSAGE);
           }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
            
                  
               // TODO add your handling code here:
    }//GEN-LAST:event_jLabel53MouseClicked

    private void jLabel54MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel54MouseClicked
int indexs[] = rfq_supply.getSelectedRows();

        DefaultTableModel model = (DefaultTableModel) rfq_supply.getModel();
        for (int i = 0; i < indexs.length; i++) {

            int getSelectedRowsForDeletion = rfq_supply.getSelectedRow();
            model.removeRow(getSelectedRowsForDeletion); }          // TODO add your handling code here:
    }//GEN-LAST:event_jLabel54MouseClicked

    private void tb_itms_cmdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_itms_cmdMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_itms_cmdMouseClicked

    private void textField12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField12KeyReleased
try{

            String sql="SELECT distinct (NUM) as 'References Numbers',NOM as 'Descptions' FROM  stock_db   where  NUM like '"+textField12.getText()+"%' or NOM like '"+textField12.getText()+"%' or FABRICANT like '"+textField12.getText()+"%'  or CATEGORY like '"+textField12.getText()+"%'   order by nom";  
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();

            

        }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);} 
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_textField12KeyReleased

    private void textField13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField13KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField13KeyReleased

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable4MouseClicked

    private void textField14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField14KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField14KeyReleased

    private void tb_quotationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_quotationMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_quotationMouseClicked

    private void tb_quotationKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_quotationKeyReleased
int rowcount = tb_quotation.getRowCount();
  NumberFormat nf = new DecimalFormat("#,###.00");
      double sum = 0;
        for (int i = 0; i < rowcount; i++) {
           String strgqty =  tb_quotation.getValueAt(i, 3).toString();
           String strgpu =  tb_quotation.getValueAt(i, 4).toString();

         double qty = Double.parseDouble(strgqty);
        double pu = Double.parseDouble(strgpu);
         double b = qty * pu;
         
         
//String Table_click1  = amount.getText();
//String replaceString=Table_click1.replace(",", "");
 //Double c= Double.parseDouble(replaceString);
String fn = nf.format(b);

          tb_quotation.getModel().setValueAt(fn, i, 5);
          String pt =  tb_quotation.getValueAt(i, 5).toString();
         String replaceString=pt.replace(",", ""); 
          
           sum = sum + Double.parseDouble(replaceString);
        }
        String fn = nf.format(sum);
        jLabel71.setText(fn);
       String aa=  StringUtils.substringBefore(jLabel71.getText(), ".");
       String bb=  StringUtils.substringAfter(jLabel71.getText(), ".");
        
        String replaceStrings=aa.replace(",", ""); 
        Long a = Long.parseLong(replaceStrings);
        Long b = Long.parseLong(bb);
        if(jComboBox4.getSelectedItem().equals("Fr")){
        jLabel70.setText(""+ FrenchNumberToWords.convert(a)+" point "+FrenchNumberToWords.convert(b)); 
        }else{
        jLabel70.setText(""+ EnglishNumberToWord.convert(a)+" dot "+FrenchNumberToWords.convert(b));
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tb_quotationKeyReleased

    private void fourn_cmd1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_fourn_cmd1PopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_fourn_cmd1PopupMenuWillBecomeInvisible

    private void fourn_cmd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fourn_cmd1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fourn_cmd1ActionPerformed

    private void jLabel69MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel69MouseClicked
 try {
         SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MMM");
            String addDateS = dateFormatsS.format(new Date());   

            String sql = "SELECT num_fiche FROM recu where projet='Quotation' and transaction='Quotation' ORDER BY num_fiche DESC LIMIT 1";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String rl = rs.getString("num_fiche");
                int ln = rl.length();
                String stxt = rl.substring(0, 17);
                String snum_fiche = rl.substring(17, ln);
                int n = Integer.parseInt(snum_fiche);
                n++;
                snum_fiche = Integer.toString(n);
                if (n < 10) {
                    rolls = stxt + "00" + snum_fiche;//001 ou 009
                } else if (n < 100) {
                    rolls = stxt + "0" + snum_fiche;//010 ou 099
                } else if (n > 100) {
                    rolls = stxt + snum_fiche;// 100 ou 199
                } else if (n > 1000) {
                    rolls = stxt + snum_fiche;// 1000 ou 1999
                }
            } else {
                rolls = "CMD - " + addDateS + " - 001"; //CMD - 2024-JUN - 001

            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
      //  num.setText(rolls);
        
        
        int rows = tb_quotation.getRowCount();

        for (int row = 0; row < rows; row++) {

          //  String libelle;
            //  ReplaceString = libelle.replace("'", "''");
            String BIN = (String) tb_quotation.getValueAt(row, 0);
            String REF = (String) tb_quotation.getValueAt(row, 1);
            String DESCR = (String) tb_quotation.getValueAt(row, 2);
            String Qty = (String) tb_quotation.getValueAt(row, 3);
            String Up = (String) tb_quotation.getValueAt(row, 4);
           String stores = (String)tb_quotation.getValueAt(row, 6);
           // libelle = Docc.replace("'", "''");
            Double qty = Double.parseDouble(Qty);
            Double up = Double.parseDouble(Up);
            Double pt = qty * up;

            // Pts = Ptss.replace("'", "''");
            try {
                //                                                               `DESCR`, `QTY`, `PU`, `PT`, `NUM`, `NAME`, `LETTRE`, `DATE`, `TRANSACTION`, `NAME_TO`, `MONAIS`, `PROJET`, `NUM_FICHE`   
                PreparedStatement pst = con.prepareStatement("INSERT INTO `recu`( `DESCR`, `QTY`, `PU`, `PT`, `NUM`, `NAME`, `LETTRE`, `DATE`, `TRANSACTION`, `NAME_TO`, `MONAIS`, `PROJET`, `NUM_FICHE`,QTY_C,STATUT,STORE)"
                        + "value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                //`DESCR`, `QTY`, `PU`, `PT`, `NUM`, `NAME`, `LETTRE`, `DATE`, `TRANSACTION`, `NAME_TO`, `MONAIS`, `PROJET`, `NUM_FICHE`          
                pst.setString(1, DESCR);
                pst.setDouble(2, qty);
                pst.setDouble(3, up);
                pst.setDouble(4, pt);
                pst.setString(5, BIN);
                pst.setString(6, fourn_cmd1.getSelectedItem().toString());
                pst.setString(7, jLabel70.getText());
                pst.setString(8,date_cmd1.getText());
                pst.setString(9, "Quotation");
                pst.setString(10, REF);
                pst.setString(11, "USD");
                pst.setString(12, "Quotation");
                pst.setString(13, rolls);
                pst.setString(14, "0.0");
                pst.setString(15,"Pending");
                pst.setString(16,stores);

                pst.executeUpdate();

                //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }
        JOptionPane.showMessageDialog(null, "Transaction Saved");  
       try{
            String sqls="select max(num_fiche) from  RECU where TRANSACTION= 'Quotation'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
            jLabel75.setText(rs.getString("max(num_fiche)"));
            }
            }
     
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
 try{
                 
                 
                   String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"UMCO_quotation.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
             // String sql="select * from inventairemtr WHERE NUM_ID='"+num.getText()+"'";
              String sql="select * from  recu  WHERE NUM_fiche='"+jLabel75.getText()+"'";
              //select * from  inventairemtr INNER JOIN materiaux_in ON inventairemtr.NUM=materiaux_in.NUM_ID
              
     HashMap param= new HashMap();
   // param.put("nom", "BON D'ENTREE STOCK NO:");
   // param.put("four", "Service acheteur");
    
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
 
 jLabel75.setText("");
 pr1.setText("");
 rn1.setText("");
 po1.setText("");
 
 jLabel70.setText("Letter/ Lettre");
jLabel71.setText("0.00");
// po1.setText("");
  fourn_cmd1.setSelectedItem("-Client -");
  //contact.setText("");
  DefaultTableModel dm= (DefaultTableModel)tb_quotation.getModel();
while (dm.getRowCount()>0){dm.removeRow(0);}
  call_fiche();
 
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel69MouseClicked

    private void date_cmd1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_date_cmd1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_date_cmd1KeyReleased

    private void textField15KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField15KeyReleased
  try{

            String sql="SELECT BIN as 'Bin location', NUM as 'References Numbers',NOM as 'Descptions' FROM  stock_dispacting where NUM like '"+textField15.getText()+"%' or NOM like '"+textField15.getText()+"%' or FABRICANT like '%"+textField15.getText()+"%'  group by NUM order by nom";  
            pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

          //  jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            tb_itms_cmd1.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();
 }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);}        // TODO add your handling code here:
    }//GEN-LAST:event_textField15KeyReleased

    private void jLabel72MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel72MouseClicked
  try{
          String sqls="SELECT * FROM caisse_dispacting where access='yes' and cat1='depot' and NAME='"+Home_page.home.user.getText()+"' and Statut='Main store'";
             pst = con.prepareStatement(sqls);rs=pst.executeQuery();
           if(rs.next()){
TableModel model1 = tb_itms_cmd1.getModel();       
   int indexs[] = tb_itms_cmd1.getSelectedRows();
        Object[] row = new Object[9];
        DefaultTableModel model2 = (DefaultTableModel)tb_quotation.getModel();
        for (int i = 0; i < indexs.length; i++) {
      String prixachats=null,store=null,maker=null,BINS=null;
          String num =  tb_itms_cmd1.getValueAt(i, 1).toString(); 
        try{
         
          String sql="SELECT * FROM  stock_db  WHERE NUM='"+model1.getValueAt(indexs[i], 1)+"'";  
           
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              prixachats = rs.getString("PRIXACHAT"); 
              // prixachats = rs.getString("PRIXACHAT"); // stock_dispacting
          }
         
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
        try{
         
          String sql="SELECT * FROM  stock_dispacting  WHERE NUM='"+model1.getValueAt(indexs[i], 1)+"'";  
           
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              store = rs.getString("STORE"); 
               maker = rs.getString("FABRICANT");
             BINS = rs.getString("BIN");// stock_dispacting
          }
         
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}

            
           
            row[0] = model1.getValueAt(indexs[i], 0);
            row[1] = model1.getValueAt(indexs[i], 1);
            row[2] = model1.getValueAt(indexs[i], 2);
            row[3] = "0.00";
            row[4] = "0.00";
            row[5] = "0.00";
            row[6] = store;
            model2.addRow(row);

            TableColumn col0 = tb_quotation.getColumnModel().getColumn(0);
            TableColumn col1 = tb_quotation.getColumnModel().getColumn(1);
            TableColumn col2 = tb_quotation.getColumnModel().getColumn(2);
            TableColumn col3 = tb_quotation.getColumnModel().getColumn(3);
             TableColumn col4= tb_quotation.getColumnModel().getColumn(4);
             TableColumn col5= tb_quotation.getColumnModel().getColumn(5);
             TableColumn col6= tb_quotation.getColumnModel().getColumn(6);
              

            col0.setPreferredWidth(100);
            col1.setPreferredWidth(100);
            col2.setPreferredWidth(150);
            col3.setPreferredWidth(50);
            col4.setPreferredWidth(50);
            col5.setPreferredWidth(50);
            col6.setPreferredWidth(100);
             
        }   
            }else{
           JOptionPane.showMessageDialog(null,"You do not have credential","Error",JOptionPane.WARNING_MESSAGE);
           }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
                    // TODO add your handling code here:
    }//GEN-LAST:event_jLabel72MouseClicked

    private void jLabel73MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel73MouseClicked
int indexs[] = tb_quotation.getSelectedRows();

        DefaultTableModel model = (DefaultTableModel) tb_quotation.getModel();
        for (int i = 0; i < indexs.length; i++) {

            int getSelectedRowsForDeletion = tb_quotation.getSelectedRow();
            model.removeRow(getSelectedRowsForDeletion); }         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel73MouseClicked

    private void jLabel74MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel74MouseClicked
  int indexs[] = {1};

        Object[] row = new Object[6];
        DefaultTableModel model2 = (DefaultTableModel) tb_quotation.getModel();
        for (int i = 0; i < indexs.length; i++) {
            row[0] = "N/A";
            row[1] = "N/A";
            row[2] = "Description...";
            row[3] = "0.0";
            row[4]= "0.0";
            row[5]= "0.0";
            model2.addRow(row);

            // jTable3.setModel(DbUtils.resultSetToTableModel(rs));
            TableColumn col0 = tb_quotation.getColumnModel().getColumn(0);
            TableColumn col1 = tb_quotation.getColumnModel().getColumn(1);
            TableColumn col2 = tb_quotation.getColumnModel().getColumn(2);
            TableColumn col3 = tb_quotation.getColumnModel().getColumn(3);
             TableColumn col4= tb_quotation.getColumnModel().getColumn(4);
             TableColumn col5= tb_quotation.getColumnModel().getColumn(4);

            col0.setPreferredWidth(50);
            col1.setPreferredWidth(100);
            col2.setPreferredWidth(150);
            col3.setPreferredWidth(50);
            col4.setPreferredWidth(50);
            col5.setPreferredWidth(50);
            //
        }               // TODO add your handling code here:
    }//GEN-LAST:event_jLabel74MouseClicked

    private void textField16KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField16KeyReleased
 
jPanel65.removeAll();
jPanel65.repaint();
jPanel65.revalidate();
        setOpaque(false);
        JScrollBar sb = scroll.getVerticalScrollBar();
        sb.setOpaque(false);
        sb.setForeground(new Color(33, 140, 206));
        sb.setPreferredSize(new Dimension(8, 8));
        sb.setUI(new ModernScrollBarUI());
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);
       jPanel65.setLayout(new MigLayout("inset 0, fillx, wrap", "[fill]"));

        try{

            String sql="SELECT  * FROM  stock_rfq  where num like '"+textField16.getText()+"%' group by NUM ORDER BY DATE";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                 String num_fiche = rs.getString("NUM");
                String fournisseur = rs.getString("STOCK_VEHICULE");
                String pt = rs.getString("Supply");
                String datescmd = rs.getString("DATE");

                // C:\Users\Doshe PC\Documents\NetBeansProjects\hand_book\src\sample\notification\fiche_medicale.jpg
              jPanel65.add(new call_rfq(new ImageIcon(getClass().getResource("/sample/notification/download (1).png")), num_fiche,fournisseur,pt,datescmd));
            }
           //  con.close();
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}        // TODO add your handling code here:
    }//GEN-LAST:event_textField16KeyReleased

    private void fourn_cmd2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_fourn_cmd2PopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_fourn_cmd2PopupMenuWillBecomeInvisible

    private void fourn_cmd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fourn_cmd2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fourn_cmd2ActionPerformed

    private void item_id3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_item_id3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_item_id3KeyReleased

    private void jLabel80MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel80MouseClicked

          try{
          String sqls="SELECT * FROM caisse_dispacting where access='yes' and cat1='depot' and NAME='"+Home_page.home.user.getText()+"' and Statut='Main store'";
             pst = con.prepareStatement(sqls);rs=pst.executeQuery();
           if(rs.next()){
 String Rolls=null;
        try {  

            String sql = "SELECT RFQNUM FROM stock_rfq   ORDER BY RFQNUM DESC LIMIT 1";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
             // JOptionPane.showMessageDialog(null,"HERE");
            if (rs.next()) {
                String rl = rs.getString("RFQNUM");
                if(rl.equals("")){
                 Rolls = "78-230803";
                }else{
                int ln = rl.length();
                String stxt = rl.substring(0, 3);
                String snum_fiche = rl.substring(3, ln);
                int n = Integer.parseInt(snum_fiche);
                n++;
                snum_fiche = Integer.toString(n);
                if (n < 10) {
                    Rolls = stxt + "00" + snum_fiche;//001 ou 009
                } else if (n < 100) {
                    Rolls = stxt + "0" + snum_fiche;//010 ou 099
                } else if (n > 100) {
                    Rolls = stxt + snum_fiche;// 100 ou 199
                } else if (n > 1000) {
                    Rolls = stxt + snum_fiche;// 1000 ou 1999
                }}
            } else {
                Rolls = "78-230803"; //CMD - 2024-JUN - 001
}
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
//JOptionPane.showMessageDialog(null,Rolls);
        int rows = tb_bon_de_commande2.getRowCount();
        for (int row = 0; row < rows; row++) {
             String Ref= (String) tb_bon_de_commande2.getValueAt(row, 0);
             String QTY_AV = (String) tb_bon_de_commande2.getValueAt(row, 4);
             String UP_PRICE = (String) tb_bon_de_commande2.getValueAt(row, 5);
           String TOTAL = tb_bon_de_commande2.getValueAt(row,6). toString().replace(",","");
          /*   
             Float qty = Float.parseFloat(QTY_AV);
            Float up = Float.parseFloat(UP_PRICE);
           Float pt = qty * up;*/
             //JOptionPane.showMessageDialog(null,TOTAL);
     if(jLabel89.getText().equals("RFQ")) {
      try {
 PreparedStatement pst = con.prepareStatement("UPDATE stock_rfq SET QTY_AV=?, UP_PRICE=?,TOTAL=?,RFQNUM=? WHERE REF='"+Ref+"' and  NUM='"+jLabel60.getText()+"'");

                 pst.setString(1, QTY_AV);
                pst.setString(2, UP_PRICE);
                pst.setString(3, TOTAL);
                pst.setString(4,Rolls);
              

                pst.executeUpdate();

                //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
      //jLabel60.setText(Rolls);
      //jLabel89.setText(Rolls);
     }else{
      try {
 PreparedStatement pst = con.prepareStatement("UPDATE stock_rfq SET QTY_AV=?, UP_PRICE=?,TOTAL=? WHERE REF='"+Ref+"' and  RFQNUM='"+jLabel60.getText()+"'");

                 pst.setString(1, QTY_AV);
                pst.setString(2, UP_PRICE);
                pst.setString(3, TOTAL);
              //  pst.setString(4,Rolls);
              

                pst.executeUpdate();

                //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
    //  jLabel89.setText("CMD");
     }  
           

        }
        jLabel60.setText(Rolls);
        JOptionPane.showMessageDialog(null, "Transaction Saved"); 
  try{
                 
                 
                   String sqlss="Select path from pathn";
           
            pst=con.prepareStatement(sqlss);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"UMCO_request_for_quotation_1.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
             // String sql="select * from inventairemtr WHERE NUM_ID='"+num.getText()+"'";
              String sql="select * from  stock_rfq  WHERE RFQNUM='"+jLabel60.getText()+"'";
              //select * from  inventairemtr INNER JOIN materiaux_in ON inventairemtr.NUM=materiaux_in.NUM_ID
              
     HashMap param= new HashMap();
    //param.put("nom", "BON D'ENTREE STOCK NO:");
    param.put("four", "Service acheteur");
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 
                
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
                 
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             } 
  DefaultTableModel dm= (DefaultTableModel)tb_bon_de_commande2.getModel();
while (dm.getRowCount()>0){dm.removeRow(0);}
  call_fiche();
            }else{
           JOptionPane.showMessageDialog(null,"You do not have credential","Error",JOptionPane.WARNING_MESSAGE);
           }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
          
             // TODO add your handling code here:
    }//GEN-LAST:event_jLabel80MouseClicked

    private void date_cmd2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_date_cmd2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_date_cmd2KeyReleased

    private void jLabel83MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel83MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel83MouseClicked

    private void jLabel84MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel84MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel84MouseClicked

    private void jLabel85MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel85MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel85MouseClicked

    private void textField18KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField18KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_textField18KeyReleased

    private void rfqnumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rfqnumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rfqnumActionPerformed

    private void p_oActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_oActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_oActionPerformed

    private void stock_vehiculeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stock_vehiculeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stock_vehiculeActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
store_count.home m= new store_count.home();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void rfq_supplyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rfq_supplyKeyReleased

    }//GEN-LAST:event_rfq_supplyKeyReleased

    private void rfq_supplyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rfq_supplyMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rfq_supplyMouseClicked

    private void tb_bon_de_commande2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_bon_de_commande2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_bon_de_commande2MouseClicked

    private void tb_bon_de_commande2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_bon_de_commande2KeyReleased
      NumberFormat nf = new DecimalFormat("#,###.00");

        int rowcount = tb_bon_de_commande2.getRowCount();
     float sum = 0;
        for (int i = 0; i < rowcount; i++) {
           String strgqty =  tb_bon_de_commande2.getValueAt(i, 4).toString();
           String strgpu =  tb_bon_de_commande2.getValueAt(i, 5).toString();
//         double qty = Double.parseDouble(strgqty);
//         double pu = Double.parseDouble(strgpu);
         
         float qty = Float.parseFloat(strgqty);
        float pu = Float.parseFloat(strgpu);
         float b = qty * pu;
String fn = nf.format(b);


 //Double c= Double.parseDouble(replaceString);
//String fn = nf.format(c);
//int row= jTable1.getSelectedRow();
        //  String Ref = (jTable1.getModel().getValueAt(row,0). toString());
        tb_bon_de_commande2.getModel().setValueAt(fn, i, 6);
       String replaceString = tb_bon_de_commande2.getValueAt(i, 6).toString().replace(",", "");
      float c= Float.parseFloat(replaceString);
           sum = sum + c;
        }
        String fnsum = nf.format(sum);
       jLabel82.setText(fnsum);
      String replaceString = jLabel82.getText().replace(",", ""); 
      String entier =StringUtils.substringBefore(replaceString, ".");
      String decima =StringUtils.substringAfter(replaceString, ".");
        Long Entier = Long.parseLong(entier);
        Long Decima = Long.parseLong(decima);
        if(jComboBox5.getSelectedItem().equals("Fr")){
        jLabel81.setText(""+ FrenchNumberToWords.convert(Entier)+" point "+FrenchNumberToWords.convert(Decima)); 
        }else{
         jLabel81.setText(""+ EnglishNumberToWord.convert(Entier)+" point "+EnglishNumberToWord.convert(Decima));
        }
         // TODO add your handling code here:
    }//GEN-LAST:event_tb_bon_de_commande2KeyReleased

    private void jLabel90MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel90MouseClicked
/* File file = showFileChooser();
        if (file != null) {
            try {
               File f = new journals.PrintTable().printTables(jTable7.getModel(), "Test Report", pageFormat).exportToExcel(file);
               
                Desktop.getDesktop().open(f);
            } catch (IOException | DRException e){
                e.printStackTrace();
            }
        }
       
    */   

 try{
           JFileChooser jFileChooser = new JFileChooser();
           jFileChooser.showSaveDialog(this);
           File saveFile = jFileChooser.getSelectedFile();
           
           if(saveFile != null){
               saveFile = new File(saveFile.toString()+".xlsx");
               Workbook wb = new XSSFWorkbook();
               Sheet sheet = wb.createSheet("customer");
               
               Row rowCol = sheet.createRow(0);
               for(int i=0;i<jTable7.getColumnCount();i++){
                   Cell cell = rowCol.createCell(i);
                   cell.setCellValue(jTable7.getColumnName(i));
               }
               
               for(int j=0;j<jTable7.getRowCount();j++){
                   Row row = sheet.createRow(j+1);
                   for(int k=0;k<jTable7.getColumnCount();k++){
                       Cell cell = row.createCell(k);
                       if(jTable7.getValueAt(j, k)!=null){
                           cell.setCellValue(jTable7.getValueAt(j, k).toString());
                       }
                   }
               }
               FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
               wb.write(out);
            //   wb.close();
               out.close();
               openFile(saveFile.toString());
            //    JOptionPane.showMessageDialog(null,"Tranction Saved");
           }else{
               JOptionPane.showMessageDialog(null,"Error al generar archivo");
           }
       }catch(FileNotFoundException e){
           System.out.println(e);
       }catch(IOException io){
           System.out.println(io);
       }
    
            // TODO add your handling code here:
    }//GEN-LAST:event_jLabel90MouseClicked

    private void clientPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_clientPopupMenuWillBecomeInvisible
if(client.getSelectedItem().equals("Sub Store")){
select_store m= new select_store();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE); 

}else{
    
    }       // TODO add your handling code here:
    }//GEN-LAST:event_clientPopupMenuWillBecomeInvisible

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
store_dispact m= new store_dispact();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
String column=null,ord=null;
        if(jComboBox1.getSelectedItem().equals("Bin loc")){
column="Bin";
}else if(jComboBox1.getSelectedItem().equals("Part Number")){
column="REF";
}else if(jComboBox1.getSelectedItem().equals("Descriptions")){
column="NOM";
}else if(jComboBox1.getSelectedItem().equals("Fabricant")){
column="FABRICANT";
}else if(jComboBox1.getSelectedItem().equals("Store")){
column="DEPOT";
}else if(jComboBox1.getSelectedItem().equals("Old Part Number")){
column="OLDNUM";
}else if(jComboBox1.getSelectedItem().equals("SAP Code")){
column="PARTCODE";
}else if(jComboBox1.getSelectedItem().equals("Categories")){
column="CATEGORY";
}
  //`PARTCODE``CATEGORY``OLDNUM`
if(jComboBox2.getSelectedItem().equals("A to Z")){
ord="ASC";
}else if(jComboBox2.getSelectedItem().equals("Z to A")){
ord="DESC";
}
  //A to Z, Z to A      
        
        try{
              String sql="SELECT Bin as 'Bin loc',REF as 'Part Number',OLDNUM as 'Old Part Number',PARTCODE as 'SAP Code',NOM as 'Descriptions',CATEGORY as 'Categories',(SUM(QTY_D)-SUM(QTY_C)) as 'Main Store Qty',(SUM(STOCK_SUB)-SUM(STOCK_SUBC)) as 'Sub Store (LME)',(SUM(QTY_D)-SUM(QTY_C))+(SUM(STOCK_SUB)-SUM(STOCK_SUBC)) as 'Total Qty',Fabricant,DEPOT as Store FROM  stock_mvm  GROUP BY REF order by "+column+" "+ord+"";  
       pst = con.prepareStatement(sql);
            rs= pst.executeQuery();

            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
            mode=new DefaultTableModel();

            mode=new DefaultTableModel();

            TableColumn col0=jTable7.getColumnModel().getColumn(0);
            TableColumn col1=jTable7.getColumnModel().getColumn(1);
            TableColumn col2=jTable7.getColumnModel().getColumn(2);
            TableColumn col3=jTable7.getColumnModel().getColumn(3);
            TableColumn col4=jTable7.getColumnModel().getColumn(4);
             TableColumn col5=jTable7.getColumnModel().getColumn(5);
              TableColumn col6=jTable7.getColumnModel().getColumn(6);
            TableColumn col7=jTable7.getColumnModel().getColumn(7);
            TableColumn col8=jTable7.getColumnModel().getColumn(8);
            TableColumn col9=jTable7.getColumnModel().getColumn(9);
             TableColumn col10=jTable7.getColumnModel().getColumn(10);
            
            
            
           col0.setPreferredWidth(100);
            col1.setPreferredWidth(100);
             col2.setPreferredWidth(100);
            col3.setPreferredWidth(100);
             col4.setPreferredWidth(250);
             col5.setPreferredWidth(100);
              col6.setPreferredWidth(50);
             col7.setPreferredWidth(50);
            col8.setPreferredWidth(50);
             col9.setPreferredWidth(100);
             col10.setPreferredWidth(100);
            

        }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);}          // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void purchaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchaseMouseClicked
//JOptionPane.showMessageDialog(null, "Works");       // TODO add your handling code here:
    }//GEN-LAST:event_purchaseMouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
//here        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jComboBox5PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox5PopupMenuWillBecomeInvisible

      String replaceString = jLabel82.getText().replace(",", ""); 
      String entier =StringUtils.substringBefore(replaceString, ".");
      String decima =StringUtils.substringAfter(replaceString, ".");
        Long Entier = Long.parseLong(entier);
        Long Decima = Long.parseLong(decima);
        if(jComboBox5.getSelectedItem().equals("Fr")){
        jLabel81.setText(""+ FrenchNumberToWords.convert(Entier)+" point "+FrenchNumberToWords.convert(Decima)); 
        }else{
         jLabel81.setText(""+ EnglishNumberToWord.convert(Entier)+" point "+EnglishNumberToWord.convert(Decima));
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5PopupMenuWillBecomeInvisible

    private void jComboBox4PopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox4PopupMenuWillBecomeVisible
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4PopupMenuWillBecomeVisible

    private void jComboBox4PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox4PopupMenuWillBecomeInvisible
 String aa=  StringUtils.substringBefore(jLabel71.getText(), ".");
       String bb=  StringUtils.substringAfter(jLabel71.getText(), ".");
        
        String replaceStrings=aa.replace(",", ""); 
        Long a = Long.parseLong(replaceStrings);
        Long b = Long.parseLong(bb);
        if(jComboBox4.getSelectedItem().equals("Fr")){
        jLabel70.setText(""+ FrenchNumberToWords.convert(a)+" point "+FrenchNumberToWords.convert(b)); 
        }else{
        jLabel70.setText(""+ EnglishNumberToWord.convert(a)+" dot "+EnglishNumberToWord.convert(b));
        }          // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4PopupMenuWillBecomeInvisible

    private void tb_itms_cmd1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_itms_cmd1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_itms_cmd1MouseClicked

    private void profondeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profondeurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profondeurActionPerformed

    private void catPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_catPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_catPopupMenuWillBecomeInvisible

    private void catActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_catActionPerformed

    private void jLabel98MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel98MouseClicked
add_on_stock_bin m = new add_on_stock_bin();
add_on_stock_bin.jLabel1.setText("Category");
 try{
        PreparedStatement ps = con.prepareStatement("Select * FROM stock_bin_location where cat='Category' order by Nom");
        ResultSet rs=ps.executeQuery();
        DefaultTableModel tm = (DefaultTableModel)add_on_stock_bin.jTable1.getModel();
        tm.setRowCount(0);

      while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
            Object o[] = {rs.getString("NOM")};
            tm.addRow(o);

      }

       }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); }
            m.show();
            m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel98MouseClicked

    private void jLabel99MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel99MouseClicked
 call_idToken();
    wait mw = new wait();

   mw.show();
 mw.msg2.setText(""); 
     mw. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        try{
          String sqlS="SELECT * FROM caisse_dispacting where access='yes' and cat1='depot' and NAME='"+Home_page.home.user.getText()+"' and Statut='Main store'";
             pst = con.prepareStatement(sqlS);rs=pst.executeQuery();
           if(rs.next()){ // Main Store 
           api.synchroniseddataFromsub_store(ID_TOKEN).enqueue(new Callback<ResponseModel<List<Stock>>>(){
 
    @Override
    public void onResponse(Call<ResponseModel<List<Stock>>> call, Response<ResponseModel<List<Stock>>> rspns) {
    if(rspns.body().isSuccess()==true){
        if(rspns.body().getData().isEmpty()){
       mw.msg.setText("No Data found"); 
       mw.msg2.setText("0 line synchronized");
       ImageIcon image = new ImageIcon(getClass().getResource( "icons8-synchronizing-48.png"));
            if (image != null) {
                mw.msg.setIcon(image);
             //   imageLabel.setText("");
            } else {
              mw.msg.setText("not");
            }   
        }else{
        //on recupere le donne et on les enregistre dans la basse de donnee local 
        for(Stock stock:rspns.body().getData()){
           try {
       String sqls="INSERT INTO stock_mvm (NUM_FICHE,RESP,CONTACT,BIN,REF,NOM,DEPOT,RANGER,ETAGERE,LIGNE,BINS,PROFONDEUR,QTY_D,QTY_C,NUM,FABRICANT,DATES,NOMS,RN,PR,PO,STOCK_SUB,STOCK_SUBc,STOCK_SUB_BIN,PARTCODE,CATEGORY,OLDNUM,QTY_R) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst = con.prepareStatement(sqls);  
        pst.setString(1,stock.getNum_fiche());
        pst.setString(2,stock.getResp());
         pst.setString(3,stock.getContact());
        pst.setString(4,stock.getBin());
         pst.setString(5,stock.getRef());
         pst.setString(6,stock.getNom());
         pst.setString(7,jLabel91.getText());
         pst.setString(8,stock.getRange());
         
         pst.setString(9,stock.getEtagere());
         pst.setString(10,stock.getLigne());
         pst.setString(11,stock.getBins());
         pst.setString(12,stock.getProfondeur());
         pst.setString(14,stock.getQty_d());
         pst.setString(13,stock.getQty_c());
         pst.setString(15,stock.getNum());
          pst.setString(16,stock.getFabricant());
           pst.setString(17,stock.getDates());
            pst.setString(18,stock.getNoms());
            pst.setString(19,stock.getRn());
            pst.setString(20,stock.getPr());
            pst.setString(21,stock.getPo());
            pst.setString(22,stock.getStock_sub());
            pst.setString(23,stock.getStock_subc());
            
            pst.setString(24,stock.getStock_sub_bin()); 
            pst.setString(25,stock.getPartcode());
            pst.setString(26,stock.getCategory());
            pst.setString(27,stock.getOldname());
            pst.setString(28,ID_TOKEN);
      
         
          pst.executeUpdate();
    } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
            
        }
         mw.msg.setText("Transaction Done"); 
       mw.msg2.setText(rspns.body().getData().size()+"  line synchronized");
       ImageIcon image = new ImageIcon(getClass().getResource( "icons8-synchronizing-48.png"));
            if (image != null) {
                mw.msg.setIcon(image);
             //   imageLabel.setText("");
            } else {
              mw.msg.setText("not");
            }   
       
        }
    }else{
    //erreur serveur
     mw.msg.setForeground(red);
       mw.msg2.setForeground(red);
       mw.msg.setText("Transaction failed"); 
       ImageIcon image = new ImageIcon(getClass().getResource( "icons8-synchronizing-48.png"));
            if (image != null) {
                mw.msg.setIcon(image);
             //   imageLabel.setText("");
            } else {
              mw.msg.setText("not");
            }   
       mw.msg2.setText("Error Serveur "+rspns.body().getErrorMessage());
    //JOptionPane.showMessageDialog(null, "erreur serveeur");
    } 
    }

    @Override
    public void onFailure(Call<ResponseModel<List<Stock>>> call, Throwable thrwbl) {
     //erreur ems-l  
      mw.msg.setForeground(red);
       mw.msg2.setForeground(red);
       mw.msg.setText("Transaction failed"); 
       ImageIcon image = new ImageIcon(getClass().getResource( "icons8-synchronizing-48.png"));
            if (image != null) {
                mw.msg.setIcon(image);
             //   imageLabel.setText("");
            } else {
              mw.msg.setText("not");
            }   
       mw.msg2.setText("Error EMS-L "+thrwbl.getMessage());
    }

});
//  Sub Store -------------------------------------------------------------------------------------------------------------------           
           }else{ // Sub Store
         List <ems_client_levi.Stock> syncglist = new ArrayList();      
             try{
            String sql="SELECT  * FROM  stock_mvm  where MS_SYNCHRONISED ='No'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
        while(rs.next()){
          ems_client_levi.Stock stocktoadd = new ems_client_levi.Stock(
       0,
          rs.getString("NUM_FICHE"),
          rs.getString("RESP"),
          rs.getString("CONTACT"),
          rs.getString("BIN"),
          rs.getString("REF"),
          rs.getString("NOM"),
          rs.getString("DEPOT"),
          rs.getString("RANGER"),
          rs.getString("ETAGERE"),
          rs.getString("LIGNE"),
          rs.getString("BINS"),
          rs.getString("PROFONDEUR"),
          rs.getString("QTY_D"),
          rs.getString("QTY_C"),
          rs.getString("NUM"),
          rs.getString("FABRICANT"),
          rs.getString("DATES"),
          rs.getString("NOMS"),
          rs.getString("RN"),
          rs.getString("PR"),
          rs.getString("PO"),
          ID_TOKEN,
          rs.getString("STOCK_SUB"),
          rs.getString("STOCK_SUBC"),
          rs.getString("STOCK_SUB_BIN"),
          rs.getString("PARTCODE"),
          rs.getString("CATEGORY"),
          rs.getString("OLDNUM"),
          "",
          "No",
         "No"
                
                  /*
          0,
          "texte",
          "texte",
          "texte",
          "texte",
          "texte",
          "texte",
          "texte",
          "texte",
          "texte",
          "texte",
          "texte",
          "texte",
          "texte",
          "texte",
          "texte",
          "texte",
           "texte",
           "texte",
           "texte",
           "texte",
           "texte",
           "texte",
           "texte",
           "texte",
           "texte",
           "texte",
           "texte",
           "texte",
           "texte",
           "texte",
            "texte"
         */
          );
          
       syncglist.add(stocktoadd); 
    
         }
        api.envoyerPickingList(syncglist).enqueue(new Callback<ResponseModel<Boolean>>() {
            //JOptionPane.showMessageDialog(null, syncglist);
                @Override
                public void onResponse(Call<ResponseModel<Boolean>> call, Response<ResponseModel<Boolean>> rspns) {
                mw.msg.setText("Transaction Done"); 
               try {String sqls="UPDATE  stock_mvm set MS_SYNCHRONISED='Yes'"; pst = con.prepareStatement(sqls); pst.executeUpdate(); } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
      ImageIcon image = new ImageIcon(getClass().getResource( "icons8-synchronizing-48.png"));
            if (image != null) {
                mw.msg.setIcon(image);
             //   imageLabel.setText("");
            } else {
              mw.msg.setText("not");
            }   
                }

                @Override
                public void onFailure(Call<ResponseModel<Boolean>> call, Throwable thrwbl) {
                mw.msg.setForeground(red);
       mw.msg2.setForeground(red);
       mw.msg.setText("Transaction failed"); 
       ImageIcon image = new ImageIcon(getClass().getResource( "icons8-synchronizing-48.png"));
            if (image != null) {
                mw.msg.setIcon(image);
             //   imageLabel.setText("");
            } else {
              mw.msg.setText("not");
            }   
       mw.msg2.setText("Error EMS-L "+thrwbl.getMessage());    
                }
            });
         
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
              
            
                  
                
     
           }
     } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
        
        
    }//GEN-LAST:event_jLabel99MouseClicked

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
 try{
    String sql="SELECT * FROM stock_mvm where NUM_FICHE='"+numfiche.getText()+"'";
     pst = con.prepareStatement(sql);rs=pst.executeQuery();
    if(rs.next()){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);         
            }else{
    call_idToken();
        mode = (DefaultTableModel) jTable6.getModel();
     wait mw = new wait();

   mw.show();
 mw.msg2.setText(""); 
     mw. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
api.recevoirPackinglist(numfiche.getText(),ID_TOKEN).enqueue(new Callback<ResponseModel<List<Stock>>>() {
    @Override
    public void onResponse(Call<ResponseModel<List<Stock>>> call, Response<ResponseModel<List<Stock>>> rspns) {
        //if(rspns.body().getData()==true) {
        if(rspns.body().isSuccess()==true){
        for(Stock stock:   rspns.body().getData()){
                    mode.insertRow(
                            mode.getRowCount(), 
                           // table.setModel(DbUtils.resultSetToTableModel(rs));
                            new Object[]{stock.getRef(), stock.getNom(),"EA",stock.getQty_send(),"0",stock.getDepot()}
                         
      
                    );
     // mode=new DefaultTableModel();

      TableColumn col0 = jTable6.getColumnModel().getColumn(0);
            TableColumn col1 = jTable6.getColumnModel().getColumn(1);
            TableColumn col2 = jTable6.getColumnModel().getColumn(2);
            TableColumn col3 = jTable6.getColumnModel().getColumn(3);
            TableColumn col4=  jTable6.getColumnModel().getColumn(4);
            TableColumn col5 = jTable6.getColumnModel().getColumn(5);
          //  TableColumn col6=  jTable6.getColumnModel().getColumn(6);

            col0.setPreferredWidth(100);
            col1.setPreferredWidth(250);
            col2.setPreferredWidth(20);
            col3.setPreferredWidth(50);
            col4.setPreferredWidth(50);
            col5.setPreferredWidth(100);
                } 

       mw.msg.setText("Transaction saved"); 
       ImageIcon image = new ImageIcon(getClass().getResource( "icons8-synchronizing-48.png"));
            if (image != null) {
                mw.msg.setIcon(image);
             //   imageLabel.setText("");
            } else {
              mw.msg.setText("not");
            }   
      //  JOptionPane.showMessageDialog(null,rspns.body().isSuccess());   
        }else{
            mw.msg.setForeground(red);
       mw.msg2.setForeground(red);
       mw.msg.setText("Transaction failed"); 
       ImageIcon image = new ImageIcon(getClass().getResource( "icons8-synchronizing-48.png"));
            if (image != null) {
                mw.msg.setIcon(image);
             //   imageLabel.setText("");
            } else {
              mw.msg.setText("not");
            }   
       mw.msg2.setText("Error EMS-L "+rspns.body().getErrorMessage()); // erreur ems-l
       //mw.waitbnt.setBackground(red);
       //mw.waitbnt.setForeground(white);
      //  JOptionPane.showMessageDialog(null, "Error Server "+rspns.body().getErrorMessage()); 
        }
            
    }

    @Override
    public void onFailure(Call<ResponseModel<List<Stock>>> call, Throwable thrwbl) {
     mw.msg.setForeground(red);
       mw.msg2.setForeground(red);
       mw.msg.setText("Transaction failed"); 
       ImageIcon image = new ImageIcon(getClass().getResource( "icons8-synchronizing-48.png"));
            if (image != null) {
                mw.msg.setIcon(image);
             //   imageLabel.setText("");
            } else {
              mw.msg.setText("not");
            }   
       mw.msg2.setText("Error EMS-L "+thrwbl.getMessage()); // erreur ems-l
      // mw.waitbnt.setBackground(red);
      // mw.waitbnt.setForeground(white);    
        
     //JOptionPane.showMessageDialog(null, "Error EMS-L "+thrwbl.getMessage());   
    }
});
        
    }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex); } 
        
    
        /*
  try{
       Connection cononline=JavaDbConnectonline.dbConnect();     

        PreparedStatement ps = cononline.prepareStatement("Select * from  stock_mvm where NUM ='"+numfiche.getText()+"'");  
        ResultSet rs=ps.executeQuery();
        DefaultTableModel tm = (DefaultTableModel)store_setting.jTable6.getModel();
        tm.setRowCount(0);

      while(rs.next()){
          Object o[] = {rs.getString("REF"),rs.getString("NOM"),"EA",rs.getString("QTY_SEND"),"",rs.getString("DEPOT")};
            tm.addRow(o);
 }
      mode=new DefaultTableModel();

      TableColumn col0 = jTable6.getColumnModel().getColumn(0);
            TableColumn col1 = jTable6.getColumnModel().getColumn(1);
            TableColumn col2 = jTable6.getColumnModel().getColumn(2);
            TableColumn col3 = jTable6.getColumnModel().getColumn(3);
            TableColumn col4=  jTable6.getColumnModel().getColumn(4);
            TableColumn col5 = jTable6.getColumnModel().getColumn(5);
          //  TableColumn col6=  jTable6.getColumnModel().getColumn(6);

            col0.setPreferredWidth(100);
            col1.setPreferredWidth(250);
            col2.setPreferredWidth(20);
            col3.setPreferredWidth(50);
            col4.setPreferredWidth(50);
            col5.setPreferredWidth(100);
     
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null, ex);}
*/
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel34MouseClicked

    private void jLabel100MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel100MouseClicked
DefaultTableModel excels= (DefaultTableModel)jTable1.getModel(); 
   String Bin= null,Desc= null;
    for(int i=0; i < excels.getRowCount();i++){
      Bin = excels.getValueAt(i,1).toString();
      Desc = excels.getValueAt(i,2).toString();
     try{
    
          String sql = "SELECT * FROM  stock_db where  NUM = '"+Bin+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
       
        }else{
         JOptionPane.showMessageDialog(null,Bin+" - "+Desc) ;
          }
         
    }catch(SQLException ex ){JOptionPane.showMessageDialog(null,ex);}
    }
    JOptionPane.showMessageDialog(null,"Done");
               // TODO add your handling code here:
    }//GEN-LAST:event_jLabel100MouseClicked

    private void jLabel102MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel102MouseClicked
  DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
 while(model.getRowCount()>0){
 model.removeRow(0);
 }  
 demandeur.setText("");
 site.setText("");
 rn.setText("");
 client.setSelectedItem("- Client -");
 nom5.setText("");
 jLabel65.setText("...");
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel102MouseClicked

    private void jLabel101MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel101MouseClicked
  DefaultTableModel model = (DefaultTableModel) jTable6.getModel();
 while(model.getRowCount()>0){
 model.removeRow(0);
 }  
 numfiche.setText("");
 contact.setText("");
item_id2.setText("");
respo.setSelectedItem("- Supply/Fournisseur -");
 //nom5.setText("");
 jLabel21.setText("...");
jLabel44.setText("...");// TODO add your handling code here:
    }//GEN-LAST:event_jLabel101MouseClicked

    private void jLabel104MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel104MouseClicked
graph m= new graph();
        m.show();
        m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel104MouseClicked

    private void fourn_cmd3PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_fourn_cmd3PopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_fourn_cmd3PopupMenuWillBecomeInvisible

    private void fourn_cmd3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fourn_cmd3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fourn_cmd3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
      try{
            String sql = "TRUNCATE stock_graph2";
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
        }catch(SQLException  ex ){ JOptionPane.showMessageDialog(null,ex); }

        String REF=null,NOM=null,BIN=null;
        String MOIS =jDateChooser1.getText().substring(5,7);

        int indexs[]=store_setting.jTable7.getSelectedRows();

        for(int i=0; i < indexs.length;i++){
            BIN = (store_setting.jTable7.getModel().getValueAt(indexs[i],0). toString());
            REF= (store_setting.jTable7.getModel().getValueAt(indexs[i],1). toString());
            NOM = (store_setting.jTable7.getModel().getValueAt(indexs[i],3). toString());
            String from = jDateChooser1.getText();
            String to = jDateChooser2.getText();

            try {
                 pst = con.prepareStatement("INSERT INTO `stock_graph2`(`REF`, `BIN`, `NOM`, `J01`, `J02`, `J03`,`J04`, `J05`, `J06`, `J07`, `J08`,`J09`, `J10`, `J11`, `J12`, `J13`, `J14`, `J15`, `J16`, `J17`, `J18`, `J19`, `J20`, `J21`, `J22`, `J23`, `J24`, `J25`, `J26`, `J27`, `J28`, `J29`, `J30`, `J31`, `MOIS`)"+
                    " VALUES ('"+REF+"','"+BIN+"','"+NOM+"','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','"+MOIS+"')");
                pst.executeUpdate();
            } catch (SQLException ex) {JOptionPane.showMessageDialog(null, ex);}

            Date d_from = null,d_to=null;
            try {
                d_from = new SimpleDateFormat("yyyy-MM-dd").parse(from);
                d_to = new SimpleDateFormat("yyyy-MM-dd").parse(to);

            } catch (ParseException ex) {
                Logger.getLogger(graph.class.getName()).log(Level.SEVERE, null, ex);
            }

            long t1=d_from.getTime();
            long t2=d_to.getTime();
            SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");

            SimpleDateFormat jrs=new SimpleDateFormat("dd");

            if(t1<t2)
            {

                //1 = 1000
                for(long ii=t1;ii<=t2;ii+=86400000)
                {

                    String dates=date.format(ii);
                    String datess=jrs.format(ii);
                    String Datess ="J"+datess;

                    // JOptionPane.showMessageDialog(null,"DATA: "+dates+" COLUNM: "+Datess);
                    String qty="0";

                    try{
                        String sqls="select * from stock_mvm where REF='"+REF+"' AND DATES='"+dates+"'";

                        pst=con.prepareStatement(sqls);
                        rs=pst.executeQuery();
                        if(rs.next()){
                            try{
                                String  sqlss="select (SUM(QTY_C)-SUM(STOCK_SUB))+SUM(STOCK_SUBC) as QTY from stock_mvm where REF='"+REF+"' AND DATES='"+dates+"'";
                            pst=con.prepareStatement(sqlss);
                                rs=pst.executeQuery();
                                if(rs.next()){
                                    qty=rs.getString("QTY");
                                }
                            }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
                        }else{
                            qty="0";
                        }
                    }catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}

                    try {
                        PreparedStatement pst = con.prepareStatement("UPDATE `stock_graph2` SET `"+Datess+"`=? where REF='"+REF+"'");
                        pst.setString(1,qty );
                        pst.executeUpdate();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }

            //    }

        // }catch(HeadlessException | SQLException ex){JOptionPane.showMessageDialog(null,ex);}

        }
        JOptionPane.showMessageDialog(null,"Transaction Done");
        
try{
        //  String sql="SELECT `ID` AS NUM,`DET`, `QTY`, `PU`, `PT`, `DATES`, `APPROUVATION` FROM `etat_de_besoin` WHERE  `ORIENTATION`='FINANCE' AND ORIENTATION2='Fin' and buss='"+journal1.buss.getText()+"'";   pst = con.prepareStatement(sql);
           
        PreparedStatement ps = con.prepareStatement("Select * from  stock_graph2");
        ResultSet rs=ps.executeQuery();
        DefaultTableModel tm = (DefaultTableModel)jTable13.getModel();
        tm.setRowCount(0);

      while(rs.next()){//``, ``, ``, `                                                                                    `, ``, ``, ``, ``, ``, `DATEFABRI`, ``, `DATE`
            Object o[] = {rs.getString("BIN"),rs.getString("REF"),rs.getString("NOM"),rs.getString("J01"),rs.getString("J02"),rs.getString("J03"),rs.getString("J04"),rs.getString("J05"),rs.getString("J06"),rs.getString("J07"),rs.getString("J08"),rs.getString("J09"),rs.getString("J10"),
            
                          rs.getString("J11"),rs.getString("J12"),rs.getString("J13"),rs.getString("J14"),rs.getString("J15"),rs.getString("J16"),rs.getString("J17"),rs.getString("J18"),rs.getString("J19"),rs.getString("J20"),
                          rs.getString("J21"),rs.getString("J22"),rs.getString("J23"),rs.getString("J24"),rs.getString("J25"),rs.getString("J26"),rs.getString("J27"),rs.getString("J28"),rs.getString("J29"),rs.getString("J30"),
                          rs.getString("J31"),rs.getString("MOIS")};
            tm.addRow(o);



        }
    
    }
    catch(Exception e){

        JOptionPane.showMessageDialog(null,"Error in Employee Grid View..... "+e);
    }// TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jLabel105MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel105MouseClicked
 try{
           JFileChooser jFileChooser = new JFileChooser();
           jFileChooser.showSaveDialog(this);
           File saveFile = jFileChooser.getSelectedFile();
           
           if(saveFile != null){
               saveFile = new File(saveFile.toString()+".xlsx");
               Workbook wb = new XSSFWorkbook();
               Sheet sheet = wb.createSheet("customer");
               
               Row rowCol = sheet.createRow(0);
               for(int i=0;i<jTable13.getColumnCount();i++){
                   Cell cell = rowCol.createCell(i);
                   cell.setCellValue(jTable13.getColumnName(i));
               }
               
               for(int j=0;j<jTable13.getRowCount();j++){
                   Row row = sheet.createRow(j+1);
                   for(int k=0;k<jTable13.getColumnCount();k++){
                       Cell cell = row.createCell(k);
                       if(jTable13.getValueAt(j, k)!=null){
                           cell.setCellValue(jTable13.getValueAt(j, k).toString());
                       }
                   }
               }
               FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
               wb.write(out);
            //   wb.close();
               out.close();
               openFile(saveFile.toString());
            //    JOptionPane.showMessageDialog(null,"Tranction Saved");
           }else{
               JOptionPane.showMessageDialog(null,"Error al generar archivo");
           }
       }catch(FileNotFoundException e){
           System.out.println(e);
       }catch(IOException io){
           System.out.println(io);
       }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel105MouseClicked

    private void jLabel45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel45MouseClicked
if(demandeur.getText().equals("")||client.getSelectedItem().equals("- Client -")||site.getText().equals("")){
JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
   

if(client.getSelectedItem().equals("Sub Store")){
   wait mw = new wait();

   mw.show();
 mw.msg2.setText(""); 
     mw. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
     pickinglist.clear();
     SendAndSavePickingonline();
  api.envoyerPickingList(pickinglist).enqueue(new Callback<ResponseModel<Boolean>>() {
      
     @Override
     public void onResponse(Call<ResponseModel<Boolean>> call, Response<ResponseModel<Boolean>> rspns) {
     if(rspns.body().getData()==true) {
         
      mw.msg.setText("Transaction Done");
      pickinglist.clear();
     SendAndSavePickingList();
      try{
            
            String sqls="select max(num) from  stock_mvm where QTY_D='0.00' and STOCK_SUBC='0.00'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
            nom5.setText(rs.getString("max(num)"));
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
      
      ImageIcon image = new ImageIcon(getClass().getResource( "icons8-synchronizing-48.png"));
            if (image != null) {
                mw.msg.setIcon(image);
             //   imageLabel.setText("");
            } else {
              mw.msg.setText("not");
            }   
     }else{
      mw.msg.setForeground(red);
      mw.msg2.setForeground(red);
      mw.msg.setText("Transaction failed");
      ImageIcon image = new ImageIcon(getClass().getResource( "icons8-synchronizing-48.png"));
            if (image != null) {
                mw.msg.setIcon(image);
             //   imageLabel.setText("");
            } else {
              mw.msg.setText("not");
            }   
       mw.msg2.setText("Error Server "+rspns.body().getErrorMessage());// eureur serveur

          try{
        String sql = "DELETE FROM stock_mvm WHERE NUM=? ";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,nom5.getText());
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
    call_fiche();
     } 
     }   
         
 try{
            String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"UMCO_bonstock_IN.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
         String sql="select * from  stock_mvm  WHERE NUM='"+nom5.getText()+"'";          
     HashMap param= new HashMap();
    param.put("nom", "BON D'ENTREE STOCK NO:");
    param.put("four",demandeur.getText());
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 
                
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
                 
             }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}   
     }


     @Override
     public void onFailure(Call<ResponseModel<Boolean>> call, Throwable thrwbl) {
    mw.msg.setForeground(red);
       mw.msg2.setForeground(red);
       mw.msg.setText("Transaction failed"); 
       
       ImageIcon image = new ImageIcon(getClass().getResource("icons8-synchronizing-48.png"));
            if (image != null) {
                mw.msg.setIcon(image);
       } else {
              mw.msg.setText("not");
            }      
       mw.msg2.setText("Error EMS-L "+thrwbl.getMessage()); // erreur ems-l
     try{
            
            String sqls="select max(num) from  stock_mvm where QTY_D='0.00' and STOCK_SUBC='0.00'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
            nom5.setText(rs.getString("max(num)"));
            }
            }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
          try{
        String sql = "DELETE FROM stock_mvm WHERE NUM=? ";
        
         pst = con.prepareStatement(sql);
         pst.setString(1,nom5.getText());
         pst.executeUpdate();

     }catch(SQLException | HeadlessException ex ){
    call_fiche();
     } 
     }
 });
}else{
    call_idToken(); 
     String  Partcode=null,Category=null,Oldnum=null;
    
      try{
          String sqlS="SELECT * FROM caisse_dispacting where access='yes' and cat1='depot' and NAME='"+Home_page.home.user.getText()+"' and Statut='Main store'";
             pst = con.prepareStatement(sqlS);rs=pst.executeQuery();
           if(rs.next()){
 try{
            String sql="SELECT NUM FROM  stock_mvm where QTY_D = '0.00' and STOCK_SUBC='0.00' ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,3);
                 String snum=rl.substring(3,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum ;
          
             }else{
                 rolls="12-1000000001";
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
 
DefaultTableModel excels= (DefaultTableModel)jTable3.getModel(); 




        for(int i=0; i < excels.getRowCount();i++){
  String  Bin = excels.getValueAt(i,0).toString();
  String  Ref  = excels.getValueAt(i,1). toString();
  String Nom = excels.getValueAt(i,2). toString();
  String Qty = excels.getValueAt(i,4). toString();
  String Depot = excels.getValueAt(i,5). toString();
 // String Depot = excels.getValueAt(i,5). toString();
  String Ranges =null,Bins =null,Etagere =null,Ligne =null,Profondeur =null,Fabricant=null,Bin_sub=null;
 
  
  
        try{
         
          String sql = "SELECT * FROM stock_mvm WHERE REF= '"+Ref+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        Ranges = rs.getString("RANGER");
        Etagere = rs.getString("ETAGERE");
        Ligne = rs.getString("LIGNE");
        Bins = rs.getString("BINS");
        Profondeur = rs.getString("PROFONDEUR");
       
            
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         try{
         
          String sql = "SELECT * FROM stock_dispacting WHERE NUM= '"+Ref+"' and STORE='"+jLabel91.getText()+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
       Bin_sub = rs.getString("BIN");
            
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
          try{
         
          String sql = "SELECT * FROM stock_db WHERE NUM= '"+Ref+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          while(rs.next()){
       Partcode = rs.getString("PARTCODE");
       Category = rs.getString("CATEGORY");
       Oldnum = rs.getString("OLDNUM");
        Fabricant = rs.getString("FABRICANT");
     //    Partcode,Category,Oldnum 
     //PARTCODE,CATEGORY,OLDNUM
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
   try {
       String sqls="INSERT INTO stock_mvm (NUM_FICHE,RESP,CONTACT,BIN,REF,NOM,DEPOT,RANGER,ETAGERE,LIGNE,BINS,PROFONDEUR,QTY_D,QTY_C,NUM,FABRICANT,DATES,NOMS,RN,PR,PO,PARTCODE,CATEGORY,OLDNUM,QTY_R) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst = con.prepareStatement(sqls);  
        pst.setString(1, demandeur.getText());
        pst.setString(2,client.getSelectedItem().toString());
         pst.setString(3,site.getText());
         pst.setString(4,Bin);
         pst.setString(5,Ref);
         pst.setString(6,Nom);
         pst.setString(7,Depot);
         pst.setString(8,Ranges);
         
         pst.setString(9,Etagere);
         pst.setString(10,Ligne);
         pst.setString(11,Bins);
         pst.setString(12,Profondeur);
         pst.setString(14,Qty);
         pst.setString(13,"0.00");
         pst.setString(15,rolls);
          pst.setString(16,Fabricant);
           pst.setString(17,dates1.getText());
            pst.setString(18,Home_page.home.user.getText());
            pst.setString(19,rn.getText());
            pst.setString(20,pr.getText());
            pst.setString(21,po.getText());
          pst.setString(22,Partcode);
            pst.setString(23,Category);
            pst.setString(24,Oldnum);
             pst.setString(25,ID_TOKEN);
         
          pst.executeUpdate();
    } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
  
        }
        
        try{
            String sqls="select max(num) from  stock_mvm where QTY_D='0.00' and STOCK_SUBC='0.00'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
            nom5.setText(rs.getString("max(num)"));
            }
            }
     
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}        
        
 JOptionPane.showMessageDialog(null,"Tranction Saved");
 try{
            String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"UMCO_bonstock_IN.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
             // String sql="select * from inventairemtr WHERE NUM_ID='"+num.getText()+"'";
              String sql="select * from  stock_mvm  WHERE NUM='"+nom5.getText()+"'";
              //select * from  inventairemtr INNER JOIN materiaux_in ON inventairemtr.NUM=materiaux_in.NUM_ID
              
     HashMap param= new HashMap();
    param.put("nom", "BON D'ENTREE STOCK NO:");
    param.put("four",demandeur.getText());
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 
                
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
                 
             }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);} 
           }else{
           
          try{
            String sql="SELECT NUM FROM  stock_mvm where QTY_D = '0.00' and STOCK_SUB='0.00' ORDER BY NUM DESC LIMIT 1";
             pst=con.prepareStatement(sql);
             rs=pst.executeQuery();
             if(rs.next()){
                 String rl=rs.getString("NUM");
                 int ln=rl.length();
                 String stxt=rl.substring(0,3);
                 String snum=rl.substring(3,ln);
                 int n = Integer.parseInt(snum);
                 n++;
                 snum=Integer.toString(n);
                 rolls=stxt+snum ;
          
             }else{
                 rolls="12-2000000001";
             }
                   }catch(Exception e){
             JOptionPane.showMessageDialog(null, e); 
             
         }
 
DefaultTableModel excels= (DefaultTableModel)jTable3.getModel(); 
        for(int i=0; i < excels.getRowCount();i++){
  String  Bin = excels.getValueAt(i,0).toString();
  String  Ref  = excels.getValueAt(i,1). toString();
  String Nom = excels.getValueAt(i,2). toString();
  String Qty = excels.getValueAt(i,4). toString();
  String Depot = excels.getValueAt(i,5). toString();
 // String Depot = excels.getValueAt(i,5). toString();
  String Ranges =null,Bins =null,Etagere =null,Ligne =null,Profondeur =null,Fabricant=null,Bin_sub=null;
  
  
        try{
         
          String sql = "SELECT * FROM stock_mvm WHERE REF= '"+Ref+"'";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
        Ranges = rs.getString("RANGER");
        Etagere = rs.getString("ETAGERE");
        Ligne = rs.getString("LIGNE");
        Bins = rs.getString("BINS");
        Profondeur = rs.getString("PROFONDEUR");
            
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
         try{
         
          String sql = "SELECT * FROM stock_dispacting WHERE NUM= '"+Ref+"' and STORE='"+jLabel91.getText()+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
       Bin_sub = rs.getString("BIN");
            
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
          try{
         
          String sql = "SELECT * FROM stock_db WHERE NUM= '"+Ref+"' ";
          pst = con.prepareStatement(sql);
          rs=pst.executeQuery();
          while(rs.next()){
       Partcode = rs.getString("PARTCODE");
       Category = rs.getString("CATEGORY");
       Oldnum = rs.getString("OLDNUM");
       
        Fabricant = rs.getString("FABRICANT");
     //    Partcode,Category,Oldnum 
     //PARTCODE,CATEGORY,OLDNUM
          }
         
    }catch(SQLException ex ){
         JOptionPane.showMessageDialog(null,ex);
     }
 try {
       String sqls="INSERT INTO stock_mvm (NUM_FICHE,RESP,CONTACT,BIN,REF,NOM,DEPOT,RANGER,ETAGERE,LIGNE,BINS,PROFONDEUR,QTY_D,QTY_C,NUM,FABRICANT,DATES,NOMS,RN,PR,PO,STOCK_SUB,STOCK_SUBC,STOCK_SUB_BIN,PARTCODE,CATEGORY,OLDNUM,QTY_R) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst = con.prepareStatement(sqls);  
        pst.setString(1, demandeur.getText());
        pst.setString(2,client.getSelectedItem().toString());
         pst.setString(3,site.getText());
         pst.setString(4,Bin);
         pst.setString(5,Ref);
         pst.setString(6,Nom);
         pst.setString(7,storemenu.getText());
         pst.setString(8,Ranges);
         
         pst.setString(9,Etagere);
         pst.setString(10,Ligne);
         pst.setString(11,Bins);
         pst.setString(12,Profondeur);
         pst.setString(14,"0.00");
         pst.setString(13,"0.00");
         pst.setString(15,rolls);
          pst.setString(16,Fabricant);
           pst.setString(17,dates1.getText());
            pst.setString(18,Home_page.home.user.getText());
            pst.setString(19,rn.getText());
            pst.setString(20,pr.getText());
            pst.setString(21,po.getText());
            pst.setString(22,"0.00");
             pst.setString(23,Qty);
            pst.setString(24,Bin);
            
             pst.setString(25,Partcode);
            pst.setString(26,Category);
            pst.setString(27,Oldnum);
             pst.setString(28,ID_TOKEN);
         
         
          pst.executeUpdate();
    } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
 
             
 
  
  
    } //END FOR
        
 try{
            String sqls="select max(num) from  stock_mvm where QTY_D='0.00' and STOCK_SUB='0.00'";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
            nom5.setText(rs.getString("max(num)"));
            }
            }
     
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}        
        
 JOptionPane.showMessageDialog(null,"Tranction Saved");
  try{
            String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"UMCO_bonstock_IN_1.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
               //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
             // String sql="select * from inventairemtr WHERE NUM_ID='"+num.getText()+"'";
              String sql="select * from  stock_mvm  WHERE NUM='"+nom5.getText()+"'";
              //select * from  inventairemtr INNER JOIN materiaux_in ON inventairemtr.NUM=materiaux_in.NUM_ID
              
     HashMap param= new HashMap();
    param.put("nom", "BON D'ENTREE STOCK NO:");
    param.put("four",demandeur.getText());
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 
                
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
                 
             }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}  
           
           
           }
   }catch(Exception e){JOptionPane.showMessageDialog(null, e); }        
}    
    
       
 
}
call_fiche();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel45MouseClicked

    private void jLabel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseClicked
  try{
            String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"Conmpte_take.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                
                  String rl=jTextField1.getText();
                 int mn=rl.length();
                 String n =jTextField1.getText().substring(0,mn);
               //(SUBSTRING(phone,1,3) = '399'  SUBSTR
               //JOptionPane.showMessageDialog(null, n+" "+jTextField1.getText().substring(0,n)); 
               //Select * from stock_dispacting INNER JOIN stock_mvm ON stock_dispacting.BIN=tock_mvm.BIN
               //Select * from stock_dispacting INNER JOIN stock_mvm ON stock_dispacting.BIN=Stock_mvm.BIN
// S239
               // String sql="select * from  stock_dispacting  WHERE SUBSTR(BIN,1,"+mn+") ='"+n+"' and Store='"+depots.getSelectedItem()+"'";
    String sql="Select stock_dispacting.BIN,stock_dispacting.NUM,stock_dispacting.NOM,STORE,(SUM(QTY_D)-SUM(QTY_C))AS QTYS from stock_dispacting INNER JOIN stock_mvm ON stock_dispacting.BIN=stock_mvm.BIN WHERE SUBSTR(stock_dispacting.BIN,1,"+mn+") ='"+n+"' and Store='"+depots.getSelectedItem()+"'  group by stock_dispacting.BIN ";
              
     HashMap param= new HashMap();
    param.put("nom", "BON D'ENTREE STOCK NO:");
    param.put("four",demandeur.getText());
    
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
                
                
               
                 JasperReport jr =JasperCompileManager.compileReport(jd);
                 JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
                 JasperViewer.viewReport(jp,false);
                 
                 
                 
                 JasperViewer m= new JasperViewer(jp);
                 
                
                 m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
                 
             }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}     // TODO add your handling code here:
    }//GEN-LAST:event_jLabel42MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Palette.MyTextField Max_alert;
    private Palette.MyTextField alert_min;
    public static final javax.swing.JComboBox<String> bin = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> cat = new javax.swing.JComboBox<>();
    private com.raven.chart.Chart chart;
    private javax.swing.JComboBox<String> client;
    private Palette.MyTextField contact;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private com.raven.datechooser.DateChooser dateChooser3;
    private com.raven.datechooser.DateChooser dateChooser4;
    private com.raven.datechooser.DateChooser dateChooser5;
    private com.raven.datechooser.DateChooser dateChooser6;
    private Palette.MyTextField date_cmd;
    public static final Palette.MyTextField date_cmd1 = new Palette.MyTextField();
    private Palette.MyTextField date_cmd2;
    private Palette.MyTextField dates;
    private Palette.MyTextField dates1;
    private Palette.MyTextField demandeur;
    public static final javax.swing.JComboBox<String> depot = new javax.swing.JComboBox<>();
    private javax.swing.JComboBox<String> depots;
    public static final javax.swing.JComboBox<String> etagere = new javax.swing.JComboBox<>();
    private Palette.MyTextField fabr;
    public static final javax.swing.JComboBox<String> fourn_cmd = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> fourn_cmd1 = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> fourn_cmd2 = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> fourn_cmd3 = new javax.swing.JComboBox<>();
    private Palette.MyTextField item_id;
    public static final Palette.MyTextField item_id2 = new Palette.MyTextField();
    private Palette.MyTextField item_id3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private Palette.MyTextField jDateChooser1;
    private Palette.MyTextField jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
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
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    public static final javax.swing.JLabel jLabel44 = new javax.swing.JLabel();
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    public static final javax.swing.JLabel jLabel59 = new javax.swing.JLabel();
    private javax.swing.JLabel jLabel6;
    public static final javax.swing.JLabel jLabel60 = new javax.swing.JLabel();
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    public static final javax.swing.JLabel jLabel65 = new javax.swing.JLabel();
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    public static final javax.swing.JLabel jLabel75 = new javax.swing.JLabel();
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    public static final javax.swing.JLabel jLabel89 = new javax.swing.JLabel();
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    public static final javax.swing.JLabel jLabel91 = new javax.swing.JLabel();
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
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
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    public static final javax.swing.JTable jTable11 = new javax.swing.JTable();
    public static final javax.swing.JTable jTable13 = new javax.swing.JTable();
    private javax.swing.JTable jTable2;
    public static final javax.swing.JTable jTable3 = new javax.swing.JTable();
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    public static final javax.swing.JTable jTable6 = new javax.swing.JTable();
    public static final javax.swing.JTable jTable7 = new javax.swing.JTable();
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField1;
    public static final javax.swing.JComboBox<String> ligne = new javax.swing.JComboBox<>();
    private com.raven.chart.LineChart lineChart;
    private Palette.MyTextField nom;
    private Palette.MyTextField nom5;
    public static final Palette.MyTextField numfiche = new Palette.MyTextField();
    private Palette.MyTextField oldpart;
    public static final Palette.MyTextField p_o = new Palette.MyTextField();
    private Palette.MyTextField po;
    public static final Palette.MyTextField po1 = new Palette.MyTextField();
    private Palette.MyTextField pr;
    public static final Palette.MyTextField pr1 = new Palette.MyTextField();
    private Palette.MyTextField prixachat;
    private Palette.MyTextField prixvente;
    public static final javax.swing.JComboBox<String> profondeur = new javax.swing.JComboBox<>();
    private com.raven.swing.progress.Progress progress1;
    private com.raven.swing.progress.Progress progress2;
    private com.raven.swing.progress.Progress progress3;
    private javax.swing.JPanel purchase;
    private javax.swing.JPanel quotation;
    public static final javax.swing.JComboBox<String> ranges = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> region = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> respo = new javax.swing.JComboBox<>();
    public static final javax.swing.JTable rfq_supply = new javax.swing.JTable();
    public static final Palette.MyTextField rfqnum = new Palette.MyTextField();
    private Palette.MyTextField rn;
    public static final Palette.MyTextField rn1 = new Palette.MyTextField();
    private com.raven.swing.RoundPanel roundPanel1;
    private Palette.MyTextField sapcode;
    private Palette.MyTextField site;
    public static final Palette.MyTextField stock_vehicule = new Palette.MyTextField();
    private javax.swing.JMenu storemenu;
    public static final javax.swing.JTable tb_bon_de_commande2 = new javax.swing.JTable();
    private javax.swing.JTable tb_itms_cmd;
    private javax.swing.JTable tb_itms_cmd1;
    public static final javax.swing.JTable tb_quotation = new javax.swing.JTable();
    private Palette.TextField textField1;
    private Palette.TextField textField10;
    private Palette.TextField textField11;
    private Palette.TextField textField12;
    private Palette.TextField textField13;
    private Palette.TextField textField14;
    private Palette.TextField textField15;
    private Palette.TextField textField16;
    private Palette.TextField textField18;
    private Palette.TextField textField2;
    private Palette.TextField textField3;
    private Palette.TextField textField4;
    private Palette.TextField textField5;
    private Palette.TextField textField6;
    private Palette.TextField textField7;
    private Palette.TextField textField8;
    private Palette.TextField textField9;
    // End of variables declaration//GEN-END:variables

private File showFileChooser(){

JFileChooser ch = new JFileChooser();
int opt = ch.showSaveDialog(this);
if(opt==JFileChooser.APPROVE_OPTION){
return ch.getSelectedFile();
}else{
return null;
}
}

}

