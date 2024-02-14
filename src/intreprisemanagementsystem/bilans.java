/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
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
public class bilans extends javax.swing.JInternalFrame {
   private Connection con;
private Statement st;
PreparedStatement pst=null;
ResultSet rs= null;
    public bilans() {
        initComponents();
        con=JavaDbConnect.dbConnect();
        exp.setDate(new Date());
        exp1.setDate(new Date());
    }
    Double RESULTAT6,RESULTAT7,RESULTAT;
    String ETABLISEMENT,TERRAIN,BATIMENT,CAISSE,BANQUE,CLIENT,SUBVENTION,LOGICIEL,MATERIEL,MARCHANDISE,STOCK,PRET,PARTICIPATION, MATIER_PREMIER,EMPRUNT_DETTE_ASSIMILEE,DETTE_IMO,FISCAL,FOURNISSEUR,CAPITAL,RESERVE,REPORT,DETTE_IMMO;
public void callbilan(){
    
    
    try{   
            String sqls="select (SUM(CREDIT)-SUM(DEBIT)) AS SOLD from ohada_trans where CLASSE=7 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
          RESULTAT7=rs.getDouble("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
    try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where CLASSE=6 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
          RESULTAT6=rs.getDouble("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
     RESULTAT=RESULTAT7-RESULTAT6; 
    JOptionPane.showMessageDialog(null, RESULTAT7+"  "+RESULTAT6);
//     try{   
//            String sql="select * from ohada_trans where SUBSTR=13 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
//            pst=con.prepareStatement(sql);
//            rs=pst.executeQuery();
//           if(rs.next()){
//          try{   
//            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where CLASSE=7 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
//            pst=con.prepareStatement(sqls);
//            rs=pst.executeQuery();
//           if(rs.next()){
//          RESULTAT7=rs.getDouble("SOLD");
//                 
//            }
//            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
//          
//           try{   
//            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where CLASSE=6 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
//            pst=con.prepareStatement(sqls);
//            rs=pst.executeQuery();
//           if(rs.next()){
//          RESULTAT6=rs.getDouble("SOLD");
//                 
//            }
//            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
//            RESULTAT=RESULTAT6-RESULTAT7;     
//            }else{
//           RESULTAT=0.0;
//           }
//            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
    
    
    

 try{   
            String sql="select * from ohada_trans where SUBSTR=20 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
           try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=20 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
           ETABLISEMENT=rs.getString("SOLD");
          }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
          }else{
            ETABLISEMENT="0";
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
 try{   
            String sql="select * from ohada_trans where SUBSTR=21 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
           try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=21 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
          LOGICIEL=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           LOGICIEL="0";
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
 try{   
            String sql="select * from ohada_trans where SUBSTR=22 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
          try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=22 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
           TERRAIN=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           TERRAIN="0";
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
 try{   
            String sql="select * from ohada_trans where SUBSTR=23 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
           try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=23 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
           BATIMENT=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
            BATIMENT="0";
           
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
 try{   
            String sql="select * from ohada_trans where SUBSTR=24 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
          try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=24 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
           MATERIEL=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           MATERIEL="0";
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
 try{   
            String sql="select * from ohada_trans where SUBSTR=26 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
           try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=26 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
           PARTICIPATION=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           PARTICIPATION="0";
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
 try{   
            String sql="select * from ohada_trans where SUBSTR=27 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
           try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=27 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
          PRET=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           PRET="0";
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
 try{   
            String sql="select * from ohada_trans where SUBSTR=31 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
           try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=31 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
           MARCHANDISE=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           MARCHANDISE="0";
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
 try{   
            String sql="select * from ohada_trans where SUBSTR=32 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
           try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=32 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
           MATIER_PREMIER=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           MATIER_PREMIER="0";
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
 try{   
            String sql="select * from ohada_trans where SUBSTR=38 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
           try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=38 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
           STOCK=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           STOCK="0";
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
 try{   
            String sql="select * from ohada_trans where SUBSTR=41 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
            try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=41 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
           CLIENT=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           CLIENT="0";
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
 try{   
            String sql="select * from ohada_trans where SUBSTR=45 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
          try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=45 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
           SUBVENTION=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           SUBVENTION="0";
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
 try{   
            String sql="select * from ohada_trans where SUBSTR=57 and device='usd' and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
           try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=57 and device='usd' and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
           CAISSE=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           CAISSE="0";
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
 try{   
            String sql="select * from ohada_trans where SUBSTR=52 and device='usd' and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
            try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=52 and device='usd' and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
           BANQUE=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           BANQUE="0";
           
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
 
 
 
 try{   
            String sql="select * from ohada_trans where SUBSTR=10 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
          try{   
            String sqls="select (SUM(CREDIT)-SUM(DEBIT)) AS SOLD from ohada_trans where SUBSTR=10 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
          CAPITAL=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           CAPITAL="0";
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
  try{   
            String sql="select * from ohada_trans where SUBSTR=11 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
          try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=11 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
          RESERVE=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           RESERVE="0";
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
  try{   
            String sql="select * from ohada_trans where SUBSTR=12 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
           try{ 
               
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=12 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
          REPORT=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           REPORT="0";
           
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
 
  try{   
            String sql="select * from ohada_trans where SUBSTR=16 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
          try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=16 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
          EMPRUNT_DETTE_ASSIMILEE=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           EMPRUNT_DETTE_ASSIMILEE="0";
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
  try{   
            String sql="select * from ohada_trans where SUBSTR=40 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
          try{   
            String sqls="select (SUM(CREDIT)-SUM(DEBIT)) AS SOLD from ohada_trans where SUBSTR=40 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
          FOURNISSEUR=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           FOURNISSEUR="0";
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
  try{   
            String sql="select * from ohada_trans where SUBSTR=44 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
            try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=44 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
          FISCAL=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           FISCAL="0";
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
  try{   
            String sql="select * from ohada_trans where SUBSTR=48 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
           if(rs.next()){
          try{   
            String sqls="select (SUM(DEBIT)-SUM(CREDIT)) AS SOLD from ohada_trans where SUBSTR=48 and DATES between'"+exp.getText()+"' and '"+exp1.getText()+"'";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
           if(rs.next()){
          DETTE_IMMO=rs.getString("SOLD");
                 
            }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
                 
            }else{
           DETTE_IMMO="0";
           
           }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null, ex);}
 
}
  public void delete(){
           try{
        String sql = "TRUNCATE bilant";
        
         pst = con.prepareStatement(sql);
         
         pst.executeUpdate();
        // JOptionPane.showMessageDialog(null,"delete");
         // rs.close();
          //  pst.close();     
     }catch(SQLException  ex ){
         JOptionPane.showMessageDialog(null,ex);
     } 
        
        }
public void save(){
    delete();
    callbilan();
    JOptionPane.showMessageDialog(null,RESULTAT);
  try {
     //  ,,,,,,,LOGICIEL,MATERIEL,MARCHANDISE,,PRET,PARTICIPATION, MATIER_PREMIER,EMPRUNT_DETTE_ASSIMILEE,DETTE_IMO,FISCAL,FOURNISSEUR,CAPITAL,RESERVE,RESULTAT,REPORT,DETTE_IMMO;

         PreparedStatement pst = con.prepareStatement("INSERT INTO `bilant`(`frais_etablissement`, `Frais_du_logiciel`, `Terains`, `Batiment`, `Materiel`, `Titre_de_participation`, `Prets`, `Marchandises`, `Matier_premier`, `Stock`, `Clients`, `Subvention`, `Caisse`, `Banque`, `Capital`, `Reserves`, `Report`, `Resultat`, `Emprunts_dettes_assimile`, `Decouvert`, `Emprunts_dettes_associer`, `Fournisseur`, `Fiscal`, `Dette_Immobilisation`, `Autre_dette`)"
              // `Emprunts_dettes_assimile`, `Decouvert`, `Emprunts_dettes_associer`, `Fournisseur`, `Fiscal`, `Dette_Immobilisation`, `Autre_dette`)"
        +"value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        pst.setString(1,  ETABLISEMENT);
        pst.setString(2, LOGICIEL);
         pst.setString(3, TERRAIN);
         pst.setString(4, BATIMENT);
         pst.setString(5,MATERIEL);
         pst.setString(6,PARTICIPATION);
         pst.setString(7,PRET);
         pst.setString(8,MARCHANDISE);
         
         pst.setString(9,MATIER_PREMIER);
         pst.setString(10,STOCK);
         pst.setString(11,CLIENT);
         pst.setString(12,SUBVENTION);
          pst.setString(13,CAISSE);
           pst.setString(14,BANQUE);
            pst.setString(15,CAPITAL);
            pst.setString(16,RESERVE);
       pst.setString(17, REPORT);
       
         pst.setDouble(18,RESULTAT);
         pst.setString(19,EMPRUNT_DETTE_ASSIMILEE);
         
         pst.setString(20,"0");
         pst.setString(21,"0");
         
          pst.setString(22,FOURNISSEUR);
           pst.setString(23,FISCAL);
            pst.setString(24,DETTE_IMMO);
       pst.setString(25, "0");
      
          
         
          pst.executeUpdate();
        
              JOptionPane.showMessageDialog(null,"Transaction Done");
    } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
    }

}
 public void report()
     {
      try{
                 String sqls="Select path from pathn";
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"bilans.jrxml";
                JasperDesign jd=JRXmlLoader.load(NameFile);
  String sql="select * from bilant";
                 HashMap param= new HashMap();
    param.put("annee", jTextField1.getText());
    param.put("date1", exp.getText());
     param.put("date2", exp1.getText());
                 jPanel3.removeAll();
     jPanel3.repaint();
     jPanel3.revalidate();
                JRDesignQuery nq=new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);
      JasperReport jr =JasperCompileManager.compileReport(jd);
      JasperPrint jp=JasperFillManager.fillReport(jr,param,con);
   
    JRViewer m= new JRViewer(jp);
     jPanel3.setLayout(new BorderLayout());
     jPanel3.add(m);
     
                 
            }
             }catch(Exception ex){
                  JOptionPane.showMessageDialog(null, ex);   
             }
         
//setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
     }
 
 public void imports(){
// try {
     
     
     
     
     
// JRMapArrayDataSource dataSource = new JRMapArrayDataSource(data);
//
//JasperReport jasperReport = JasperCompileManager.compileReport(reportJRXMLSource);
//JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, dataSource);
//
//JRXlsxExporter exporter = new JRXlsxExporter();
//exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
//exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, outputFileName);
//
//       
//           exporter.exportReport();
//       } catch (JRException ex) {
//           Logger.getLogger(bilans.class.getName()).log(Level.SEVERE, null, ex);
//       }
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
        jPanel4 = new javax.swing.JPanel();
        exp = new com.alee.extended.date.WebDateField();
        exp1 = new com.alee.extended.date.WebDateField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/intreprisemanagementsystem/icons8_Accounting_16px.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Excercise", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        exp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        exp1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("From");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("To");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Load");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(240, 240, 241));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addComponent(exp, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(exp1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(exp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(exp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(637, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 375, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("X");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy");
         String addDate1 = dateFormat1.format(exp.getDate());
         jTextField1.setText(addDate1);
        save(); 
report();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
this.dispose();       // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.alee.extended.date.WebDateField exp;
    private com.alee.extended.date.WebDateField exp1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
