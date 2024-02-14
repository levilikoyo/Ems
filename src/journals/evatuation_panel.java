/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package journals;

import intreprisemanagementsystem.JavaDbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
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
 * @author Doshe PC
 */
public class evatuation_panel extends javax.swing.JPanel {

   Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    DefaultTableModel model;
    String rolls;
    public evatuation_panel() {
        initComponents();
         con = JavaDbConnect.dbConnect();
        groupe();
        try {
            String sql = ("SELECT ROLLNUM AS Matricule,NAME as 'Noms' FROM employee WHERE ACTIVE='Active' GROUP BY NAME");
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
       SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy");
            String addDateS = dateFormatsS.format(new Date());
annee.setText(addDateS);    
    }
public void groupe(){
 ButtonGroup bg1 = new ButtonGroup();
bg1.add(metier1);
bg1.add(metier2); 
bg1.add(metier3);
bg1.add(metier4);
bg1.add(metier5);



 ButtonGroup bg2 = new ButtonGroup();
bg2.add(travail1);
bg2.add(travail2); 
bg2.add(travail3);
bg2.add(travail4);
bg2.add(travail5);
 ButtonGroup bg3 = new ButtonGroup();
bg3.add(creative1);
bg3.add(creative2); 
bg3.add(creative3);
bg3.add(creative4);
bg3.add(creative5);
 ButtonGroup bg4 = new ButtonGroup();
bg4.add(ponctualite1);
bg4.add(ponctualite2); 
bg4.add(ponctualite3);
bg4.add(ponctualite4);
bg4.add(ponctualite5);
 ButtonGroup bg5 = new ButtonGroup();
bg5.add(productivite1);
bg5.add(productivite2); 
bg5.add(productivite3);
bg5.add(productivite4);
bg5.add(productivite5);

 ButtonGroup bg6 = new ButtonGroup();
bg6.add(communication1);
bg6.add(communication2); 
bg6.add(communication3);
bg6.add(communication4);
bg6.add(communication5);
 ButtonGroup bg7 = new ButtonGroup();
bg7.add(fiabilite1);
bg7.add(fiabilite2); 
bg7.add(fiabilite3);
bg7.add(fiabilite4);
bg7.add(fiabilite5);
 ButtonGroup bg8 = new ButtonGroup();
bg8.add(probleme1);
bg8.add(probleme2); 
bg8.add(probleme3);
bg8.add(probleme4);
bg8.add(probleme5);
 ButtonGroup bg9 = new ButtonGroup();
bg9.add(responsabilite1);
bg9.add(responsabilite2); 
bg9.add(responsabilite3);
bg9.add(responsabilite4);
bg9.add(responsabilite5);
 
 ButtonGroup bg10 = new ButtonGroup();
bg10.add(administration1);
bg10.add(administration2); 
bg10.add(administration3);
bg10.add(administration4);
bg10.add(administration5);

ButtonGroup ag1 = new ButtonGroup();
ag1.add(honnete1);
ag1.add(honnete2); 
ag1.add(honnete3);
ag1.add(honnete4);
ag1.add(honnete5);
 ButtonGroup ag2 = new ButtonGroup();
ag2.add(enthousiasme1);
ag2.add(enthousiasme2); 
ag2.add(enthousiasme3);
ag2.add(enthousiasme4);
ag2.add(enthousiasme5);
 ButtonGroup ag3 = new ButtonGroup();
ag3.add(attitude1);
ag3.add(attitude2); 
ag3.add(attitude3);
ag3.add(attitude4);
ag3.add(attitude5);
 ButtonGroup ag4 = new ButtonGroup();
ag4.add(coeherence1);
ag4.add(coeherence2); 
ag4.add(coeherence3);
ag4.add(coeherence4);
ag4.add(coeherence5);
 ButtonGroup ag5 = new ButtonGroup();
ag5.add(relation1);
ag5.add(relation2); 
ag5.add(relation3);
ag5.add(relation4);
ag5.add(relation5);
 ButtonGroup ag6 = new ButtonGroup();
ag6.add(collaboration1);
ag6.add(collaboration2); 
ag6.add(collaboration3);
ag6.add(collaboration4);
ag6.add(collaboration5);
 ButtonGroup ag7 = new ButtonGroup();
ag7.add(technique1);
ag7.add(technique2); 
ag7.add(technique3);
ag7.add(technique4);
ag7.add(technique5);
 ButtonGroup ag8 = new ButtonGroup();
ag8.add(cooperation1);
ag8.add(cooperation2); 
ag8.add(cooperation3);
ag8.add(cooperation4);
ag8.add(cooperation5);
 ButtonGroup ag9 = new ButtonGroup();
ag9.add(initiative1);
ag9.add(initiative2); 
ag9.add(initiative3);
ag9.add(initiative4);
ag9.add(initiative5);
 
 ButtonGroup ag10 = new ButtonGroup();
ag10.add(presence1);
ag10.add(presence2); 
ag10.add(presence3);
ag10.add(presence4);
ag10.add(presence5);

}
 int bg1=0, bg2=0,bg3=0,bg4=0,bg5=0,bg6=0,bg7=0,bg8=0,bg9=0,bg10=0,ag1=0, ag2=0,ag3=0,ag4=0,ag5=0,ag6=0,ag7=0,ag8=0,ag9=0,ag10=0;
public void selec(){
   
if(metier1.isSelected()){
bg1=1;
}else if(metier2.isSelected()){
bg1=2;
}else if(metier3.isSelected()){
bg1=3;
}else if(metier4.isSelected()){
bg1=4;
}else if(metier5.isSelected()){
bg1=5;
}

if(travail1.isSelected()){
bg2=1;
}else if(travail2.isSelected()){
bg2=2;
}else if(travail3.isSelected()){
bg2=3;
}else if(travail4.isSelected()){
bg2=4;
}else if(travail5.isSelected()){
bg2=5;
}

if(creative1.isSelected()){
bg3=1;
}else if(creative2.isSelected()){
bg3=2;
}else if(creative3.isSelected()){
bg3=3;
}else if(creative4.isSelected()){
bg3=4;
}else if(creative5.isSelected()){
bg3=5;
}

if(ponctualite1.isSelected()){
bg4=1;
}else if(ponctualite2.isSelected()){
bg4=2;
}else if(ponctualite3.isSelected()){
bg4=3;
}else if(ponctualite4.isSelected()){
bg4=4;
}else if(ponctualite5.isSelected()){
bg4=5;
}

if(productivite1.isSelected()){
bg5=1;
}else if(productivite2.isSelected()){
bg5=2;
}else if(productivite3.isSelected()){
bg5=3;
}else if(productivite4.isSelected()){
bg5=4;
}else if(productivite5.isSelected()){
bg5=5;
}
if(communication1.isSelected()){
bg6=1;
}else if(communication2.isSelected()){
bg6=2;
}else if(communication3.isSelected()){
bg6=3;
}else if(communication4.isSelected()){
bg6=4;
}else if(communication5.isSelected()){
bg6=5;
}

if(fiabilite1.isSelected()){
bg7=1;
}else if(fiabilite2.isSelected()){
bg7=2;
}else if(fiabilite3.isSelected()){
bg7=3;
}else if(fiabilite4.isSelected()){
bg7=4;
}else if(fiabilite5.isSelected()){
bg7=5;
}

if(probleme1.isSelected()){
bg8=1;
}else if(probleme2.isSelected()){
bg8=2;
}else if(probleme3.isSelected()){
bg8=3;
}else if(probleme4.isSelected()){
bg8=4;
}else if(probleme5.isSelected()){
bg8=5;
}

if(responsabilite1.isSelected()){
bg9=1;
}else if(responsabilite2.isSelected()){
bg9=2;
}else if(responsabilite3.isSelected()){
bg9=3;
}else if(responsabilite4.isSelected()){
bg9=4;
}else if(responsabilite5.isSelected()){
bg9=5;
}

if(administration1.isSelected()){
bg10=1;
}else if(administration2.isSelected()){
bg10=2;
}else if(administration3.isSelected()){
bg10=3;
}else if(administration4.isSelected()){
bg10=4;
}else if(administration5.isSelected()){
bg10=5;
}


if(honnete1.isSelected()){
ag1=1;
}else if(honnete2.isSelected()){
ag1=2;
}else if(honnete3.isSelected()){
ag1=3;
}else if(honnete4.isSelected()){
ag1=4;
}else if(honnete5.isSelected()){
ag1=5;
}
if(enthousiasme1.isSelected()){
ag2=1;
}else if(enthousiasme2.isSelected()){
ag2=2;
}else if(enthousiasme3.isSelected()){
ag2=3;
}else if(enthousiasme4.isSelected()){
ag2=4;
}else if(enthousiasme5.isSelected()){
ag2=5;
}
if(attitude1.isSelected()){
ag3=1;
}else if(attitude2.isSelected()){
ag3=2;
}else if(attitude3.isSelected()){
ag3=3;
}else if(attitude4.isSelected()){
ag3=4;
}else if(attitude5.isSelected()){
ag3=5;
}
if(coeherence1.isSelected()){
ag4=1;
}else if(coeherence2.isSelected()){
ag4=2;
}else if(coeherence3.isSelected()){
ag4=3;
}else if(coeherence4.isSelected()){
ag4=4;
}else if(coeherence5.isSelected()){
ag4=5;
}
if(relation1.isSelected()){
ag5=1;
}else if(relation2.isSelected()){
ag5=2;
}else if(relation3.isSelected()){
ag5=3;
}else if(relation4.isSelected()){
ag5=4;
}else if(relation5.isSelected()){
ag5=5;
}
if(collaboration1.isSelected()){
ag6=1;
}else if(collaboration2.isSelected()){
ag6=2;
}else if(collaboration3.isSelected()){
ag6=3;
}else if(collaboration4.isSelected()){
ag6=4;
}else if(collaboration5.isSelected()){
ag6=5;
}
if(technique1.isSelected()){
ag7=1;
}else if(technique2.isSelected()){
ag7=2;
}else if(technique3.isSelected()){
ag7=3;
}else if(technique4.isSelected()){
ag7=4;
}else if(technique5.isSelected()){
ag7=5;
}
if(cooperation1.isSelected()){
ag8=1;
}else if(cooperation2.isSelected()){
ag8=2;
}else if(cooperation3.isSelected()){
ag8=3;
}else if(cooperation4.isSelected()){
ag8=4;
}else if(cooperation5.isSelected()){
ag8=5;
}
if(initiative1.isSelected()){
ag9=1;
}else if(initiative2.isSelected()){
ag9=2;
}else if(initiative3.isSelected()){
ag9=3;
}else if(initiative4.isSelected()){
ag9=4;
}else if(initiative5.isSelected()){
ag9=5;
}
if(presence1.isSelected()){
ag10=1;
}else if(presence2.isSelected()){
ag10=2;
}else if(presence3.isSelected()){
ag10=3;
}else if(presence4.isSelected()){
ag10=4;
}else if(presence5.isSelected()){
ag10=5;
}


int c=bg1+bg2+bg3+bg4+bg5+bg6+bg7+bg8+bg9+bg10+ag1+ag2+ag3+ag4+ag5+ag6+ag7+ag8+ag9+ag10;
note.setText(c+"/"+100);
}

 public void clic_attCall_IN_LIST() {
        int row = jTable5.getSelectedRow();
        String Table_click = (jTable5.getModel().getValueAt(row, 0).toString());

     
        try {
            String sql = "SELECT * from employee where rollnum ='" + Table_click + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {

                String rolll = rs.getString("NAME");
                

                String clients = rs.getString("LNAME");
                nom.setText(rolll+"  "+clients);

                String clientsA = rs.getString("DEPARTMENT");
                dep.setText(clientsA);
                
                String clientsA1 = rs.getString("SUP");
                sup.setText(clientsA1);

               
            }
        } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex);}

      
         try {
            String sql = "SELECT * from evaluation where roll ='" + Table_click + "' and annee='"+annee.getText()+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
// `metier`, `travail`, `creative`, `ponctualite`, `productivite`, `communication`, `fiabilite`, `probleme`, `responsabilite`, `administration`, `honnete`, `enthousiasme`, `attitude`, `coeherence`, `relation`, `collaboration`, `technique`, `cooperation`, `initiative`, `presence`, `forces`, `opportunite`, `but`,
String Metier = rs.getString("metier");
if(Metier.equals("1")){
metier1.setSelected(true);
}else if(Metier.equals("2")){
metier2.setSelected(true);
}else if(Metier.equals("3")){
metier3.setSelected(true);
}else if(Metier.equals("4")){
metier4.setSelected(true);
}else if(Metier.equals("5")){
metier5.setSelected(true);
}    
String Travail1 = rs.getString("travail");
if(Travail1.equals("1")){
travail1.setSelected(true);
}else if(Travail1.equals("2")){
travail2.setSelected(true);
}else if(Travail1.equals("3")){
travail3.setSelected(true);
}else if(Travail1.equals("4")){
travail4.setSelected(true);
}else if(Travail1.equals("5")){
travail5.setSelected(true);
}

String Creative = rs.getString("creative");
if(Travail1.equals("1")){
creative1.setSelected(true);
}else if(Creative.equals("2")){
creative2.setSelected(true);
}else if(Creative.equals("3")){
creative3.setSelected(true);
}else if(Creative.equals("4")){
creative4.setSelected(true);
}else if(Creative.equals("5")){
creative5.setSelected(true);
}

String Ponctualite = rs.getString("ponctualite");
if(Ponctualite.equals("1")){
ponctualite1.setSelected(true);
}else if(Ponctualite.equals("2")){
ponctualite2.setSelected(true);
}else if(Ponctualite.equals("3")){
ponctualite3.setSelected(true);
}else if(Ponctualite.equals("4")){
ponctualite4.setSelected(true);
}else if(Ponctualite.equals("5")){
ponctualite5.setSelected(true);
}

String Productivite = rs.getString("productivite");
if(Productivite.equals("1")){
productivite1.setSelected(true);
}else if(Productivite.equals("2")){
productivite2.setSelected(true);
}else if(Productivite.equals("3")){
productivite3.setSelected(true);
}else if(Productivite.equals("4")){
productivite4.setSelected(true);
}else if(Productivite.equals("5")){
productivite5.setSelected(true);
}

String Communication = rs.getString("communication");
if(Communication.equals("1")){
communication1.setSelected(true);
}else if(Communication.equals("2")){
communication2.setSelected(true);
}else if(Communication.equals("3")){
communication3.setSelected(true);
}else if(Communication.equals("4")){
communication4.setSelected(true);
}else if(Communication.equals("5")){
communication5.setSelected(true);
}

String Fiabilite = rs.getString("fiabilite");
if(Fiabilite.equals("1")){
fiabilite1.setSelected(true);
}else if(Fiabilite.equals("2")){
fiabilite2.setSelected(true);
}else if(Fiabilite.equals("3")){
fiabilite3.setSelected(true);
}else if(Fiabilite.equals("4")){
fiabilite4.setSelected(true);
}else if(Fiabilite.equals("5")){
fiabilite5.setSelected(true);
}

String Probleme = rs.getString("probleme");
if(Probleme.equals("1")){
probleme1.setSelected(true);
}else if(Probleme.equals("2")){
probleme2.setSelected(true);
}else if(Probleme.equals("3")){
probleme3.setSelected(true);
}else if(Probleme.equals("4")){
probleme4.setSelected(true);
}else if(Probleme.equals("5")){
probleme5.setSelected(true);
}

String Responsabilite = rs.getString("responsabilite");
if(Responsabilite.equals("1")){
responsabilite1.setSelected(true);
}else if(Responsabilite.equals("2")){
responsabilite2.setSelected(true);
}else if(Responsabilite.equals("3")){
responsabilite3.setSelected(true);
}else if(Responsabilite.equals("4")){
responsabilite4.setSelected(true);
}else if(Responsabilite.equals("5")){
responsabilite5.setSelected(true);
}

String Administration = rs.getString("administration");
if(Administration.equals("1")){
administration1.setSelected(true);
}else if(Administration.equals("2")){
administration2.setSelected(true);
}else if(Administration.equals("3")){
administration3.setSelected(true);
}else if(Administration.equals("4")){
administration4.setSelected(true);
}else if(Administration.equals("5")){
administration5.setSelected(true);
}

String Honnete = rs.getString("honnete");
if(Honnete.equals("1")){
honnete1.setSelected(true);
}else if(Honnete.equals("2")){
honnete2.setSelected(true);
}else if(Honnete.equals("3")){
honnete3.setSelected(true);
}else if(Honnete.equals("4")){
honnete4.setSelected(true);
}else if(Honnete.equals("5")){
honnete5.setSelected(true);
}

String Enthousiasme = rs.getString("enthousiasme");
if(Honnete.equals("1")){
enthousiasme1.setSelected(true);
}else if(Enthousiasme.equals("2")){
enthousiasme2.setSelected(true);
}else if(Enthousiasme.equals("3")){
enthousiasme3.setSelected(true);
}else if(Enthousiasme.equals("4")){
enthousiasme4.setSelected(true);
}else if(Enthousiasme.equals("5")){
enthousiasme5.setSelected(true);
}

String Attitude = rs.getString("attitude");
if(Attitude.equals("1")){
attitude1.setSelected(true);
}else if(Attitude.equals("2")){
attitude2.setSelected(true);
}else if(Attitude.equals("3")){
attitude3.setSelected(true);
}else if(Attitude.equals("4")){
attitude4.setSelected(true);
}else if(Attitude.equals("5")){
attitude5.setSelected(true);
}

String Coeherence = rs.getString("coeherence");
if(Coeherence.equals("1")){
coeherence1.setSelected(true);
}else if(Coeherence.equals("2")){
coeherence2.setSelected(true);
}else if(Coeherence.equals("3")){
coeherence3.setSelected(true);
}else if(Coeherence.equals("4")){
coeherence4.setSelected(true);
}else if(Coeherence.equals("5")){
coeherence5.setSelected(true);
}

String Relation = rs.getString("relation");
if(Relation.equals("1")){
relation1.setSelected(true);
}else if(Relation.equals("2")){
relation2.setSelected(true);
}else if(Relation.equals("3")){
relation3.setSelected(true);
}else if(Relation.equals("4")){
relation4.setSelected(true);
}else if(Relation.equals("5")){
relation5.setSelected(true);
}

String Collaboration = rs.getString("collaboration");
if(Collaboration.equals("1")){
collaboration1.setSelected(true);
}else if(Collaboration.equals("2")){
collaboration2.setSelected(true);
}else if(Collaboration.equals("3")){
collaboration3.setSelected(true);
}else if(Collaboration.equals("4")){
collaboration4.setSelected(true);
}else if(Collaboration.equals("5")){
collaboration5.setSelected(true);
}

String Technique = rs.getString("technique");
if(Technique.equals("1")){
technique1.setSelected(true);
}else if(Technique.equals("2")){
technique2.setSelected(true);
}else if(Technique.equals("3")){
technique3.setSelected(true);
}else if(Technique.equals("4")){
technique4.setSelected(true);
}else if(Technique.equals("5")){
technique5.setSelected(true);
}

String Cooperation = rs.getString("cooperation");
if(Cooperation.equals("1")){
cooperation1.setSelected(true);
}else if(Cooperation.equals("2")){
cooperation2.setSelected(true);
}else if(Cooperation.equals("3")){
cooperation3.setSelected(true);
}else if(Cooperation.equals("4")){
cooperation4.setSelected(true);
}else if(Cooperation.equals("5")){
cooperation5.setSelected(true);
}

String Initiative = rs.getString("initiative");
if(Initiative.equals("1")){
initiative1.setSelected(true);
}else if(Initiative.equals("2")){
initiative2.setSelected(true);
}else if(Initiative.equals("3")){
initiative3.setSelected(true);
}else if(Initiative.equals("4")){
initiative4.setSelected(true);
}else if(Initiative.equals("5")){
initiative5.setSelected(true);
}

String Presence = rs.getString("presence");
if(Presence.equals("1")){
presence1.setSelected(true);
}else if(Presence.equals("2")){
presence2.setSelected(true);
}else if(Presence.equals("3")){
presence3.setSelected(true);
}else if(Presence.equals("4")){
presence4.setSelected(true);
}else if(Presence.equals("5")){
presence5.setSelected(true);
}

String Presence1 = rs.getString("forces");
force.setText(Presence1);
String Presence2 = rs.getString("opportunite");
opportunite.setText(Presence2);
String Presence3 = rs.getString("but");
but.setText(Presence3);

String Note = rs.getString("NOTE");
note.setText(Note);

            }
        } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex);}
    }
 
 public void save(){
     selec();
      int row = jTable5.getSelectedRow();
        String Table_click = (jTable5.getModel().getValueAt(row, 0).toString());
    try {
            String sql = "SELECT * from evaluation where roll ='" + Table_click + "' and annee='"+annee.getText()+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
           if (rs.next()) {
         try {
       PreparedStatement pst = con.prepareStatement("UPDATE  evaluation SET  nom=?,DEPARTEMENT=?,ANNEE=?,SUP=?,DATES=?,NOTE=?,metier=?,travail=?,creative=?,ponctualite=?,productivite=?,communication=?,fiabilite=?,probleme=?,responsabilite=?,administration=?,honnete=?,enthousiasme=?,attitude=?,coeherence=?,relation=?,collaboration=?,technique=?,cooperation=?,initiative=?,presence=?,forces=?,opportunite=?,but=? where ROLL='"+Table_click+"' and annee='"+annee.getText()+"'");
                       
 pst.setString(1, nom.getText());
 pst.setString(2, dep.getText());
 pst.setString(3, annee.getText());
 pst.setString(4, sup.getText());
 pst.setString(5, date.getText());
 pst.setString(6, note.getText());
 pst.setInt(7, bg1);
 pst.setInt(8, bg2);
 pst.setInt(9,bg3);
 pst.setInt(10,bg4);
 pst.setInt(11, bg5);
 pst.setInt(12, bg6);
 pst.setInt(13, bg7);
 pst.setInt(14, bg8);
 pst.setInt(15, bg9);
 pst.setInt(16, bg10);
 pst.setInt(17, ag1);
 pst.setInt(18, ag2);
 pst.setInt(19,ag3);
 pst.setInt(20,ag4);
 pst.setInt(21, ag5);
 pst.setInt(22, ag6);
 pst.setInt(23, ag7);
 pst.setInt(24, ag8);
 pst.setInt(25, ag9);
 pst.setInt(26, ag10);
 pst.setString(27,force.getText());
 pst.setString(28, opportunite.getText());
 pst.setString(29,but.getText());
 //pst.setString(30,Table_click);
                
pst.executeUpdate();

JOptionPane.showMessageDialog(null,"Transaction Done!!! ");
            } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}   
           } else{
           try {
       PreparedStatement pst = con.prepareStatement("INSERT INTO `evaluation` ( `nom`, `DEPARTEMENT`, `ANNEE`, `SUP`, `DATES`, `NOTE`, `metier`, `travail`, `creative`, `ponctualite`, `productivite`, `communication`, `fiabilite`, `probleme`, `responsabilite`, `administration`, `honnete`, `enthousiasme`, `attitude`, `coeherence`, `relation`, `collaboration`, `technique`, `cooperation`, `initiative`, `presence`, `forces`, `opportunite`, `but`, `roll`)"
                        + "value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
 pst.setString(1, nom.getText());
 pst.setString(2, dep.getText());
 pst.setString(3, annee.getText());
 pst.setString(4, sup.getText());
 pst.setString(5, date.getText());
 pst.setString(6, note.getText());
 pst.setInt(7, bg1);
 pst.setInt(8, bg2);
 pst.setInt(9,bg3);
 pst.setInt(10,bg4);
 pst.setInt(11, bg5);
 pst.setInt(12, bg6);
 pst.setInt(13, bg7);
 pst.setInt(14, bg8);
 pst.setInt(15, bg9);
 pst.setInt(16, bg10);
 pst.setInt(17, ag1);
 pst.setInt(18, ag2);
 pst.setInt(19,ag3);
 pst.setInt(20,ag4);
 pst.setInt(21, ag5);
 pst.setInt(22, ag6);
 pst.setInt(23, ag7);
 pst.setInt(24, ag8);
 pst.setInt(25, ag9);
 pst.setInt(26, ag10);
 pst.setString(27,force.getText());
 pst.setString(28, opportunite.getText());
 pst.setString(29,but.getText());
 pst.setString(30,Table_click);
                
pst.executeUpdate();

JOptionPane.showMessageDialog(null,"Transaction Done!!! ");
            } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());} 
           }   
        } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex);}
  
 
 }
 
 public void refresh(){
 ButtonGroup bg1 = new ButtonGroup();
bg1.add(metier1);
bg1.add(metier2); 
bg1.add(metier3);
bg1.add(metier4);
bg1.add(metier5);

bg1.add(travail1);
bg1.add(travail2); 
bg1.add(travail3);
bg1.add(travail4);
bg1.add(travail5);

bg1.add(creative1);
bg1.add(creative2); 
bg1.add(creative3);
bg1.add(creative4);
bg1.add(creative5);

bg1.add(ponctualite1);
bg1.add(ponctualite2); 
bg1.add(ponctualite3);
bg1.add(ponctualite4);
bg1.add(ponctualite5);

bg1.add(productivite1);
bg1.add(productivite2); 
bg1.add(productivite3);
bg1.add(productivite4);
bg1.add(productivite5);

bg1.add(communication1);
bg1.add(communication2); 
bg1.add(communication3);
bg1.add(communication4);
bg1.add(communication5);

bg1.add(fiabilite1);
bg1.add(fiabilite2); 
bg1.add(fiabilite3);
bg1.add(fiabilite4);
bg1.add(fiabilite5);

bg1.add(probleme1);
bg1.add(probleme2); 
bg1.add(probleme3);
bg1.add(probleme4);
bg1.add(probleme5);

bg1.add(responsabilite1);
bg1.add(responsabilite2); 
bg1.add(responsabilite3);
bg1.add(responsabilite4);
bg1.add(responsabilite5);

bg1.add(administration1);
bg1.add(administration2); 
bg1.add(administration3);
bg1.add(administration4);
bg1.add(administration5);
bg1.add(honnete1);
bg1.add(honnete2); 
bg1.add(honnete3);
bg1.add(honnete4);
bg1.add(honnete5);
bg1.add(enthousiasme1);
bg1.add(enthousiasme2); 
bg1.add(enthousiasme3);
bg1.add(enthousiasme4);
bg1.add(enthousiasme5);
bg1.add(attitude1);
bg1.add(attitude2); 
bg1.add(attitude3);
bg1.add(attitude4);
bg1.add(attitude5);
bg1.add(coeherence1);
bg1.add(coeherence2); 
bg1.add(coeherence3);
bg1.add(coeherence4);
bg1.add(coeherence5);
bg1.add(relation1);
bg1.add(relation2); 
bg1.add(relation3);
bg1.add(relation4);
bg1.add(relation5);
bg1.add(collaboration1);
bg1.add(collaboration2); 
bg1.add(collaboration3);
bg1.add(collaboration4);
bg1.add(collaboration5);
bg1.add(technique1);
bg1.add(technique2); 
bg1.add(technique3);
bg1.add(technique4);
bg1.add(technique5);
bg1.add(cooperation1);
bg1.add(cooperation2); 
bg1.add(cooperation3);
bg1.add(cooperation4);
bg1.add(cooperation5);
bg1.add(initiative1);
bg1.add(initiative2); 
bg1.add(initiative3);
bg1.add(initiative4);
bg1.add(initiative5);
bg1.add(presence1);
bg1.add(presence2); 
bg1.add(presence3);
bg1.add(presence4);
bg1.add(presence5);
bg1.clearSelection();
note.setText("");
force.setText("");
opportunite.setText("");
but.setText("");
 
 }
 
  public void report()
     {
          
     int row = jTable5.getSelectedRow();
        String Table_click = (jTable5.getModel().getValueAt(row, 0).toString()); 
             try{
                 
                 
                 String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"evaluation.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
           String sql="select * from evaluation where roll='"+Table_click+"' and annee='"+annee.getText()+"' ";
            
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
 
 public void report_all()
     {
            
     
             try{
                 
                 
                 String sqls="Select path from pathn";
           
            pst=con.prepareStatement(sqls);
            rs=pst.executeQuery();
            if(rs.next()){
             
                String sum=rs.getString("path");
                  
                 String NameFiles=sum;
            
            String NameFile=""+NameFiles+"evaluation.jrxml";
                 
                 
                 
                JasperDesign jd=JRXmlLoader.load(NameFile);
                 
                 
                 
                // String report ="C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\maretiauxrepport.jrxml";
                // JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\Fiche_Materiaux_out.jrxml");
                String sql="select * from evaluation where  annee='"+annee.getText()+"' ";
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
   /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        jPanel3 = new javax.swing.JPanel();
        nom = new Palette.TextField();
        dep = new Palette.TextField();
        annee = new Palette.TextField();
        sup = new Palette.TextField();
        date = new Palette.TextField();
        note = new Palette.TextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        textField7 = new Palette.TextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        metier1 = new javax.swing.JCheckBox();
        metier2 = new javax.swing.JCheckBox();
        metier3 = new javax.swing.JCheckBox();
        metier4 = new javax.swing.JCheckBox();
        metier5 = new javax.swing.JCheckBox();
        travail5 = new javax.swing.JCheckBox();
        travail4 = new javax.swing.JCheckBox();
        travail3 = new javax.swing.JCheckBox();
        travail2 = new javax.swing.JCheckBox();
        travail1 = new javax.swing.JCheckBox();
        creative1 = new javax.swing.JCheckBox();
        creative2 = new javax.swing.JCheckBox();
        creative3 = new javax.swing.JCheckBox();
        creative4 = new javax.swing.JCheckBox();
        creative5 = new javax.swing.JCheckBox();
        ponctualite5 = new javax.swing.JCheckBox();
        ponctualite4 = new javax.swing.JCheckBox();
        ponctualite3 = new javax.swing.JCheckBox();
        ponctualite2 = new javax.swing.JCheckBox();
        ponctualite1 = new javax.swing.JCheckBox();
        productivite1 = new javax.swing.JCheckBox();
        productivite2 = new javax.swing.JCheckBox();
        productivite3 = new javax.swing.JCheckBox();
        productivite4 = new javax.swing.JCheckBox();
        productivite5 = new javax.swing.JCheckBox();
        communication5 = new javax.swing.JCheckBox();
        communication4 = new javax.swing.JCheckBox();
        communication3 = new javax.swing.JCheckBox();
        communication2 = new javax.swing.JCheckBox();
        communication1 = new javax.swing.JCheckBox();
        fiabilite1 = new javax.swing.JCheckBox();
        fiabilite2 = new javax.swing.JCheckBox();
        fiabilite3 = new javax.swing.JCheckBox();
        fiabilite4 = new javax.swing.JCheckBox();
        fiabilite5 = new javax.swing.JCheckBox();
        probleme5 = new javax.swing.JCheckBox();
        probleme4 = new javax.swing.JCheckBox();
        probleme3 = new javax.swing.JCheckBox();
        probleme2 = new javax.swing.JCheckBox();
        probleme1 = new javax.swing.JCheckBox();
        responsabilite1 = new javax.swing.JCheckBox();
        responsabilite2 = new javax.swing.JCheckBox();
        responsabilite3 = new javax.swing.JCheckBox();
        responsabilite4 = new javax.swing.JCheckBox();
        responsabilite5 = new javax.swing.JCheckBox();
        administration5 = new javax.swing.JCheckBox();
        administration4 = new javax.swing.JCheckBox();
        administration3 = new javax.swing.JCheckBox();
        administration2 = new javax.swing.JCheckBox();
        administration1 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        honnete1 = new javax.swing.JCheckBox();
        honnete2 = new javax.swing.JCheckBox();
        honnete3 = new javax.swing.JCheckBox();
        honnete4 = new javax.swing.JCheckBox();
        honnete5 = new javax.swing.JCheckBox();
        enthousiasme1 = new javax.swing.JCheckBox();
        enthousiasme2 = new javax.swing.JCheckBox();
        enthousiasme3 = new javax.swing.JCheckBox();
        enthousiasme4 = new javax.swing.JCheckBox();
        enthousiasme5 = new javax.swing.JCheckBox();
        attitude1 = new javax.swing.JCheckBox();
        attitude2 = new javax.swing.JCheckBox();
        attitude3 = new javax.swing.JCheckBox();
        attitude4 = new javax.swing.JCheckBox();
        attitude5 = new javax.swing.JCheckBox();
        coeherence1 = new javax.swing.JCheckBox();
        coeherence2 = new javax.swing.JCheckBox();
        coeherence3 = new javax.swing.JCheckBox();
        coeherence4 = new javax.swing.JCheckBox();
        coeherence5 = new javax.swing.JCheckBox();
        relation1 = new javax.swing.JCheckBox();
        relation2 = new javax.swing.JCheckBox();
        relation3 = new javax.swing.JCheckBox();
        relation4 = new javax.swing.JCheckBox();
        relation5 = new javax.swing.JCheckBox();
        collaboration1 = new javax.swing.JCheckBox();
        collaboration2 = new javax.swing.JCheckBox();
        collaboration3 = new javax.swing.JCheckBox();
        collaboration4 = new javax.swing.JCheckBox();
        collaboration5 = new javax.swing.JCheckBox();
        technique1 = new javax.swing.JCheckBox();
        technique2 = new javax.swing.JCheckBox();
        technique3 = new javax.swing.JCheckBox();
        technique4 = new javax.swing.JCheckBox();
        technique5 = new javax.swing.JCheckBox();
        cooperation1 = new javax.swing.JCheckBox();
        cooperation2 = new javax.swing.JCheckBox();
        cooperation3 = new javax.swing.JCheckBox();
        cooperation4 = new javax.swing.JCheckBox();
        cooperation5 = new javax.swing.JCheckBox();
        initiative1 = new javax.swing.JCheckBox();
        initiative2 = new javax.swing.JCheckBox();
        initiative3 = new javax.swing.JCheckBox();
        initiative4 = new javax.swing.JCheckBox();
        initiative5 = new javax.swing.JCheckBox();
        presence1 = new javax.swing.JCheckBox();
        presence2 = new javax.swing.JCheckBox();
        presence3 = new javax.swing.JCheckBox();
        presence4 = new javax.swing.JCheckBox();
        presence5 = new javax.swing.JCheckBox();
        textAreaScroll1 = new Palette.TextAreaScroll();
        opportunite = new Palette.TextArea();
        textAreaScroll2 = new Palette.TextAreaScroll();
        force = new Palette.TextArea();
        textAreaScroll3 = new Palette.TextAreaScroll();
        but = new Palette.TextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        dateChooser1.setForeground(new java.awt.Color(242, 242, 242));
        dateChooser1.setTextRefernce(date);
        dateChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateChooser1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dateChooser1MouseReleased(evt);
            }
        });

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        nom.setEditable(false);
        nom.setBackground(new java.awt.Color(242, 242, 241));
        nom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nom.setLabelText("Nom de l'employé");

        dep.setEditable(false);
        dep.setBackground(new java.awt.Color(242, 242, 241));
        dep.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dep.setLabelText("Departement");

        annee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        annee.setLabelText("Année");

        sup.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sup.setLabelText("Evalué par");

        date.setEditable(true);
        date.setBackground(new java.awt.Color(242, 242, 242));
        date.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        date.setLabelText("Date d'évaluation");
        date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateActionPerformed(evt);
            }
        });
        date.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dateKeyReleased(evt);
            }
        });

        note.setEditable(false);
        note.setBackground(new java.awt.Color(242, 242, 241));
        note.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        note.setLabelText("Note d'évaluation des employés");

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

        textField7.setLabelText("Recherche");
        textField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField7KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textField7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(note, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(annee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dep, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(textField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(annee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(note, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Connaissances du métier");
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 7, 156, 38));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Qualité du travail");
        jLabel2.setOpaque(true);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 49, 156, 38));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Pensée créative");
        jLabel3.setOpaque(true);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 90, 156, 38));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Ponctualité");
        jLabel4.setOpaque(true);
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 131, 156, 38));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Productivité");
        jLabel5.setOpaque(true);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 172, 156, 38));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Communication");
        jLabel6.setOpaque(true);
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 216, 156, 38));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Résolution de problème");
        jLabel7.setOpaque(true);
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 298, 156, 38));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Fiabilité");
        jLabel8.setOpaque(true);
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 257, 156, 38));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Responsabilité");
        jLabel9.setOpaque(true);
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 342, 156, 38));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Administration");
        jLabel10.setOpaque(true);
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 383, 156, 38));

        metier1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        metier1.setText("1(Faible)");
        metier1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        metier1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        metier1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        metier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metier1ActionPerformed(evt);
            }
        });
        jPanel1.add(metier1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 8, 54, 38));

        metier2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        metier2.setText("2(Moyen)");
        metier2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        metier2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        metier2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        metier2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metier2ActionPerformed(evt);
            }
        });
        jPanel1.add(metier2, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 7, -1, 38));

        metier3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        metier3.setText("3(Bon)");
        metier3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        metier3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        metier3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        metier3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metier3ActionPerformed(evt);
            }
        });
        jPanel1.add(metier3, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 7, 50, 38));

        metier4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        metier4.setText("4(Très bien)");
        metier4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        metier4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        metier4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        metier4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metier4ActionPerformed(evt);
            }
        });
        jPanel1.add(metier4, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 7, 78, 38));

        metier5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        metier5.setText("5(Excellente)");
        metier5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        metier5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        metier5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        metier5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metier5ActionPerformed(evt);
            }
        });
        jPanel1.add(metier5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 7, 70, 38));

        travail5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        travail5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        travail5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        travail5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                travail5ActionPerformed(evt);
            }
        });
        jPanel1.add(travail5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 49, 70, 38));

        travail4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        travail4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        travail4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        travail4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                travail4ActionPerformed(evt);
            }
        });
        jPanel1.add(travail4, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 49, 78, 38));

        travail3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        travail3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        travail3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        travail3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                travail3ActionPerformed(evt);
            }
        });
        jPanel1.add(travail3, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 49, 50, 38));

        travail2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        travail2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        travail2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        travail2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                travail2ActionPerformed(evt);
            }
        });
        jPanel1.add(travail2, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 49, 57, 38));

        travail1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        travail1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        travail1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        travail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                travail1ActionPerformed(evt);
            }
        });
        jPanel1.add(travail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 49, 54, 38));

        creative1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        creative1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        creative1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        creative1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creative1ActionPerformed(evt);
            }
        });
        jPanel1.add(creative1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 90, 54, 38));

        creative2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        creative2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        creative2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        creative2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creative2ActionPerformed(evt);
            }
        });
        jPanel1.add(creative2, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 90, 57, 38));

        creative3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        creative3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        creative3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        creative3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creative3ActionPerformed(evt);
            }
        });
        jPanel1.add(creative3, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 90, 50, 38));

        creative4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        creative4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        creative4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        creative4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creative4ActionPerformed(evt);
            }
        });
        jPanel1.add(creative4, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 90, 78, 38));

        creative5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        creative5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        creative5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        creative5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creative5ActionPerformed(evt);
            }
        });
        jPanel1.add(creative5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 90, 70, 38));

        ponctualite5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ponctualite5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ponctualite5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        ponctualite5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ponctualite5ActionPerformed(evt);
            }
        });
        jPanel1.add(ponctualite5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 131, 70, 38));

        ponctualite4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ponctualite4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ponctualite4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        ponctualite4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ponctualite4ActionPerformed(evt);
            }
        });
        jPanel1.add(ponctualite4, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 131, 78, 38));

        ponctualite3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ponctualite3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ponctualite3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        ponctualite3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ponctualite3ActionPerformed(evt);
            }
        });
        jPanel1.add(ponctualite3, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 131, 48, 38));

        ponctualite2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ponctualite2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ponctualite2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        ponctualite2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ponctualite2ActionPerformed(evt);
            }
        });
        jPanel1.add(ponctualite2, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 131, 57, 38));

        ponctualite1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ponctualite1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ponctualite1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        ponctualite1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ponctualite1ActionPerformed(evt);
            }
        });
        jPanel1.add(ponctualite1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 131, 54, 38));

        productivite1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productivite1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        productivite1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        productivite1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productivite1ActionPerformed(evt);
            }
        });
        jPanel1.add(productivite1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 172, 54, 38));

        productivite2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productivite2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        productivite2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        productivite2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productivite2ActionPerformed(evt);
            }
        });
        jPanel1.add(productivite2, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 172, 57, 38));

        productivite3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productivite3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        productivite3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        productivite3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productivite3ActionPerformed(evt);
            }
        });
        jPanel1.add(productivite3, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 172, 44, 38));

        productivite4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productivite4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        productivite4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        productivite4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productivite4ActionPerformed(evt);
            }
        });
        jPanel1.add(productivite4, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 172, 71, 38));

        productivite5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productivite5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        productivite5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        productivite5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productivite5ActionPerformed(evt);
            }
        });
        jPanel1.add(productivite5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 172, 70, 38));

        communication5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        communication5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        communication5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(communication5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 213, 70, 38));

        communication4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        communication4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        communication4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(communication4, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 213, 71, 38));

        communication3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        communication3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        communication3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        communication3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                communication3ActionPerformed(evt);
            }
        });
        jPanel1.add(communication3, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 213, 51, 38));

        communication2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        communication2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        communication2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        communication2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                communication2ActionPerformed(evt);
            }
        });
        jPanel1.add(communication2, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 213, 56, 38));

        communication1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        communication1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        communication1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        communication1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                communication1ActionPerformed(evt);
            }
        });
        jPanel1.add(communication1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 213, 52, 38));

        fiabilite1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fiabilite1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fiabilite1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        fiabilite1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiabilite1ActionPerformed(evt);
            }
        });
        jPanel1.add(fiabilite1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 254, 52, 38));

        fiabilite2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fiabilite2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fiabilite2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        fiabilite2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiabilite2ActionPerformed(evt);
            }
        });
        jPanel1.add(fiabilite2, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 254, 56, 38));

        fiabilite3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fiabilite3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fiabilite3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        fiabilite3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiabilite3ActionPerformed(evt);
            }
        });
        jPanel1.add(fiabilite3, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 254, 51, 38));

        fiabilite4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fiabilite4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fiabilite4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        fiabilite4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiabilite4ActionPerformed(evt);
            }
        });
        jPanel1.add(fiabilite4, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 254, 71, 38));

        fiabilite5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fiabilite5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fiabilite5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        fiabilite5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiabilite5ActionPerformed(evt);
            }
        });
        jPanel1.add(fiabilite5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 254, 70, 38));

        probleme5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        probleme5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        probleme5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        probleme5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                probleme5ActionPerformed(evt);
            }
        });
        jPanel1.add(probleme5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 295, 70, 38));

        probleme4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        probleme4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        probleme4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        probleme4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                probleme4ActionPerformed(evt);
            }
        });
        jPanel1.add(probleme4, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 295, 71, 38));

        probleme3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        probleme3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        probleme3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        probleme3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                probleme3ActionPerformed(evt);
            }
        });
        jPanel1.add(probleme3, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 295, 51, 38));

        probleme2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        probleme2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        probleme2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        probleme2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                probleme2ActionPerformed(evt);
            }
        });
        jPanel1.add(probleme2, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 295, 56, 38));

        probleme1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        probleme1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        probleme1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        probleme1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                probleme1ActionPerformed(evt);
            }
        });
        jPanel1.add(probleme1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 295, 52, 38));

        responsabilite1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        responsabilite1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        responsabilite1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        responsabilite1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                responsabilite1ActionPerformed(evt);
            }
        });
        jPanel1.add(responsabilite1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 336, 52, 38));

        responsabilite2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        responsabilite2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        responsabilite2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        responsabilite2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                responsabilite2ActionPerformed(evt);
            }
        });
        jPanel1.add(responsabilite2, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 336, 56, 38));

        responsabilite3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        responsabilite3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        responsabilite3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        responsabilite3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                responsabilite3ActionPerformed(evt);
            }
        });
        jPanel1.add(responsabilite3, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 336, 51, 38));

        responsabilite4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        responsabilite4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        responsabilite4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        responsabilite4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                responsabilite4ActionPerformed(evt);
            }
        });
        jPanel1.add(responsabilite4, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 336, 71, 38));

        responsabilite5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        responsabilite5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        responsabilite5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        responsabilite5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                responsabilite5ActionPerformed(evt);
            }
        });
        jPanel1.add(responsabilite5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 336, 70, 38));

        administration5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        administration5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        administration5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        administration5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administration5ActionPerformed(evt);
            }
        });
        jPanel1.add(administration5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 377, 70, 38));

        administration4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        administration4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        administration4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        administration4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administration4ActionPerformed(evt);
            }
        });
        jPanel1.add(administration4, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 377, 71, 38));

        administration3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        administration3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        administration3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        administration3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administration3ActionPerformed(evt);
            }
        });
        jPanel1.add(administration3, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 377, 51, 38));

        administration2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        administration2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        administration2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        administration2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administration2ActionPerformed(evt);
            }
        });
        jPanel1.add(administration2, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 377, 56, 38));

        administration1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        administration1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        administration1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        administration1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administration1ActionPerformed(evt);
            }
        });
        jPanel1.add(administration1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 377, 52, 38));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Honnêteté");
        jLabel11.setOpaque(true);
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 7, 156, 38));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Enthousiasme");
        jLabel12.setOpaque(true);
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 49, 156, 38));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Attitude");
        jLabel13.setOpaque(true);
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 90, 156, 38));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Cohérence du travail");
        jLabel14.setOpaque(true);
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 131, 156, 38));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Relation de travail");
        jLabel15.setOpaque(true);
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 172, 156, 38));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Collaboration");
        jLabel16.setOpaque(true);
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 213, 156, 38));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Coopération");
        jLabel17.setOpaque(true);
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 295, 156, 38));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Compétences techniques");
        jLabel18.setOpaque(true);
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 254, 156, 38));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Initiative");
        jLabel19.setOpaque(true);
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 336, 156, 38));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Presence");
        jLabel20.setOpaque(true);
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 377, 156, 38));

        honnete1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        honnete1.setText("1(Faible)");
        honnete1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        honnete1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        honnete1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        honnete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                honnete1ActionPerformed(evt);
            }
        });
        jPanel2.add(honnete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 8, 54, 38));

        honnete2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        honnete2.setText("2(Moyen)");
        honnete2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        honnete2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        honnete2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        honnete2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                honnete2ActionPerformed(evt);
            }
        });
        jPanel2.add(honnete2, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 7, -1, 38));

        honnete3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        honnete3.setText("3(Bon)");
        honnete3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        honnete3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        honnete3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        honnete3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                honnete3ActionPerformed(evt);
            }
        });
        jPanel2.add(honnete3, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 7, -1, 38));

        honnete4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        honnete4.setText("4(Très bien)");
        honnete4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        honnete4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        honnete4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        honnete4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                honnete4ActionPerformed(evt);
            }
        });
        jPanel2.add(honnete4, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 7, 78, 38));

        honnete5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        honnete5.setText("5(Excellente)");
        honnete5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        honnete5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        honnete5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        honnete5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                honnete5ActionPerformed(evt);
            }
        });
        jPanel2.add(honnete5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 7, 60, 38));

        enthousiasme1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enthousiasme1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        enthousiasme1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        enthousiasme1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enthousiasme1ActionPerformed(evt);
            }
        });
        jPanel2.add(enthousiasme1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 49, 54, 38));

        enthousiasme2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enthousiasme2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        enthousiasme2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        enthousiasme2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enthousiasme2ActionPerformed(evt);
            }
        });
        jPanel2.add(enthousiasme2, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 49, 57, 38));

        enthousiasme3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enthousiasme3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        enthousiasme3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        enthousiasme3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enthousiasme3ActionPerformed(evt);
            }
        });
        jPanel2.add(enthousiasme3, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 49, 41, 38));

        enthousiasme4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enthousiasme4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        enthousiasme4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        enthousiasme4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enthousiasme4ActionPerformed(evt);
            }
        });
        jPanel2.add(enthousiasme4, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 49, 78, 38));

        enthousiasme5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enthousiasme5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        enthousiasme5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        enthousiasme5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enthousiasme5ActionPerformed(evt);
            }
        });
        jPanel2.add(enthousiasme5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 49, 60, 38));

        attitude1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        attitude1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        attitude1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        attitude1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attitude1ActionPerformed(evt);
            }
        });
        jPanel2.add(attitude1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 90, 54, 38));

        attitude2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        attitude2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        attitude2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        attitude2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attitude2ActionPerformed(evt);
            }
        });
        jPanel2.add(attitude2, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 90, 57, 38));

        attitude3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        attitude3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        attitude3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        attitude3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attitude3ActionPerformed(evt);
            }
        });
        jPanel2.add(attitude3, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 90, 41, 38));

        attitude4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        attitude4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        attitude4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        attitude4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attitude4ActionPerformed(evt);
            }
        });
        jPanel2.add(attitude4, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 90, 78, 38));

        attitude5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        attitude5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        attitude5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        attitude5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attitude5ActionPerformed(evt);
            }
        });
        jPanel2.add(attitude5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 90, 60, 38));

        coeherence1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coeherence1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        coeherence1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        coeherence1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coeherence1ActionPerformed(evt);
            }
        });
        jPanel2.add(coeherence1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 131, 54, 38));

        coeherence2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coeherence2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        coeherence2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        coeherence2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coeherence2ActionPerformed(evt);
            }
        });
        jPanel2.add(coeherence2, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 131, 57, 38));

        coeherence3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coeherence3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        coeherence3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        coeherence3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coeherence3ActionPerformed(evt);
            }
        });
        jPanel2.add(coeherence3, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 131, 48, 38));

        coeherence4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coeherence4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        coeherence4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        coeherence4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coeherence4ActionPerformed(evt);
            }
        });
        jPanel2.add(coeherence4, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 131, 78, 38));

        coeherence5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coeherence5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        coeherence5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        coeherence5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coeherence5ActionPerformed(evt);
            }
        });
        jPanel2.add(coeherence5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 131, 60, 38));

        relation1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        relation1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        relation1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        relation1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relation1ActionPerformed(evt);
            }
        });
        jPanel2.add(relation1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 172, 54, 38));

        relation2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        relation2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        relation2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        relation2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relation2ActionPerformed(evt);
            }
        });
        jPanel2.add(relation2, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 172, 57, 38));

        relation3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        relation3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        relation3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        relation3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relation3ActionPerformed(evt);
            }
        });
        jPanel2.add(relation3, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 172, 44, 38));

        relation4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        relation4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        relation4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        relation4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relation4ActionPerformed(evt);
            }
        });
        jPanel2.add(relation4, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 172, 71, 38));

        relation5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        relation5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        relation5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        relation5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relation5ActionPerformed(evt);
            }
        });
        jPanel2.add(relation5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 172, 60, 38));

        collaboration1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        collaboration1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        collaboration1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        collaboration1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collaboration1ActionPerformed(evt);
            }
        });
        jPanel2.add(collaboration1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 213, 60, 38));

        collaboration2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        collaboration2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        collaboration2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        collaboration2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collaboration2ActionPerformed(evt);
            }
        });
        jPanel2.add(collaboration2, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 213, 56, 38));

        collaboration3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        collaboration3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        collaboration3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        collaboration3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collaboration3ActionPerformed(evt);
            }
        });
        jPanel2.add(collaboration3, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 213, 51, 38));

        collaboration4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        collaboration4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        collaboration4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        collaboration4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collaboration4ActionPerformed(evt);
            }
        });
        jPanel2.add(collaboration4, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 213, 71, 38));

        collaboration5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        collaboration5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        collaboration5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        collaboration5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collaboration5ActionPerformed(evt);
            }
        });
        jPanel2.add(collaboration5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 213, 60, 38));

        technique1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        technique1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        technique1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        technique1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                technique1ActionPerformed(evt);
            }
        });
        jPanel2.add(technique1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 254, 52, 38));

        technique2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        technique2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        technique2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        technique2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                technique2ActionPerformed(evt);
            }
        });
        jPanel2.add(technique2, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 254, 56, 38));

        technique3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        technique3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        technique3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        technique3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                technique3ActionPerformed(evt);
            }
        });
        jPanel2.add(technique3, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 254, 51, 38));

        technique4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        technique4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        technique4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        technique4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                technique4ActionPerformed(evt);
            }
        });
        jPanel2.add(technique4, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 254, 71, 38));

        technique5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        technique5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        technique5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        technique5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                technique5ActionPerformed(evt);
            }
        });
        jPanel2.add(technique5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 254, 60, 38));

        cooperation1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cooperation1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cooperation1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        cooperation1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cooperation1ActionPerformed(evt);
            }
        });
        jPanel2.add(cooperation1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 295, 52, 38));

        cooperation2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cooperation2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cooperation2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        cooperation2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cooperation2ActionPerformed(evt);
            }
        });
        jPanel2.add(cooperation2, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 295, 56, 38));

        cooperation3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cooperation3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cooperation3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        cooperation3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cooperation3ActionPerformed(evt);
            }
        });
        jPanel2.add(cooperation3, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 295, 51, 38));

        cooperation4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cooperation4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cooperation4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        cooperation4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cooperation4ActionPerformed(evt);
            }
        });
        jPanel2.add(cooperation4, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 295, 71, 38));

        cooperation5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cooperation5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cooperation5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        cooperation5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cooperation5ActionPerformed(evt);
            }
        });
        jPanel2.add(cooperation5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 295, 60, 38));

        initiative1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        initiative1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        initiative1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        initiative1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initiative1ActionPerformed(evt);
            }
        });
        jPanel2.add(initiative1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 336, 52, 38));

        initiative2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        initiative2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        initiative2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        initiative2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initiative2ActionPerformed(evt);
            }
        });
        jPanel2.add(initiative2, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 336, 56, 38));

        initiative3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        initiative3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        initiative3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        initiative3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initiative3ActionPerformed(evt);
            }
        });
        jPanel2.add(initiative3, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 336, 51, 38));

        initiative4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        initiative4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        initiative4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        initiative4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initiative4ActionPerformed(evt);
            }
        });
        jPanel2.add(initiative4, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 336, 71, 38));

        initiative5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        initiative5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        initiative5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        initiative5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initiative5ActionPerformed(evt);
            }
        });
        jPanel2.add(initiative5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 336, 60, 38));

        presence1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        presence1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        presence1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        presence1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presence1ActionPerformed(evt);
            }
        });
        jPanel2.add(presence1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 377, 52, 38));

        presence2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        presence2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        presence2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        presence2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presence2ActionPerformed(evt);
            }
        });
        jPanel2.add(presence2, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 377, 56, 38));

        presence3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        presence3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        presence3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        presence3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presence3ActionPerformed(evt);
            }
        });
        jPanel2.add(presence3, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 377, 51, 38));

        presence4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        presence4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        presence4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        presence4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presence4ActionPerformed(evt);
            }
        });
        jPanel2.add(presence4, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 377, 71, 38));

        presence5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        presence5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        presence5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        presence5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presence5ActionPerformed(evt);
            }
        });
        jPanel2.add(presence5, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 377, 60, 38));

        textAreaScroll1.setLabelText("Opportunité");

        opportunite.setColumns(20);
        opportunite.setRows(5);
        textAreaScroll1.setViewportView(opportunite);

        textAreaScroll2.setLabelText("Forces");

        force.setColumns(20);
        force.setRows(5);
        textAreaScroll2.setViewportView(force);

        textAreaScroll3.setLabelText("But /Objectifs");

        but.setColumns(20);
        but.setRows(5);
        textAreaScroll3.setViewportView(but);

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Print All");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Print");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textAreaScroll2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textAreaScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textAreaScroll3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textAreaScroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textAreaScroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
 

        clic_attCall_IN_LIST();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable5MouseClicked

    private void relation4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relation4ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_relation4ActionPerformed

    private void metier1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metier1ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_metier1ActionPerformed

    private void metier2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metier2ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_metier2ActionPerformed

    private void metier3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metier3ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_metier3ActionPerformed

    private void metier4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metier4ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_metier4ActionPerformed

    private void metier5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metier5ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_metier5ActionPerformed

    private void travail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_travail1ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_travail1ActionPerformed

    private void travail2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_travail2ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_travail2ActionPerformed

    private void travail3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_travail3ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_travail3ActionPerformed

    private void travail4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_travail4ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_travail4ActionPerformed

    private void travail5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_travail5ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_travail5ActionPerformed

    private void creative1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creative1ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_creative1ActionPerformed

    private void creative2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creative2ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_creative2ActionPerformed

    private void creative3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creative3ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_creative3ActionPerformed

    private void creative4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creative4ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_creative4ActionPerformed

    private void creative5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creative5ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_creative5ActionPerformed

    private void ponctualite1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ponctualite1ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_ponctualite1ActionPerformed

    private void ponctualite2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ponctualite2ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_ponctualite2ActionPerformed

    private void ponctualite3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ponctualite3ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_ponctualite3ActionPerformed

    private void ponctualite4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ponctualite4ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_ponctualite4ActionPerformed

    private void ponctualite5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ponctualite5ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_ponctualite5ActionPerformed

    private void productivite1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productivite1ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_productivite1ActionPerformed

    private void productivite2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productivite2ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_productivite2ActionPerformed

    private void productivite3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productivite3ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_productivite3ActionPerformed

    private void productivite4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productivite4ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_productivite4ActionPerformed

    private void productivite5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productivite5ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_productivite5ActionPerformed

    private void communication1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_communication1ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_communication1ActionPerformed

    private void communication2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_communication2ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_communication2ActionPerformed

    private void communication3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_communication3ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_communication3ActionPerformed

    private void dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateActionPerformed

    private void fiabilite1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiabilite1ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_fiabilite1ActionPerformed

    private void fiabilite2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiabilite2ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_fiabilite2ActionPerformed

    private void fiabilite3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiabilite3ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_fiabilite3ActionPerformed

    private void fiabilite4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiabilite4ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_fiabilite4ActionPerformed

    private void fiabilite5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiabilite5ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_fiabilite5ActionPerformed

    private void probleme1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_probleme1ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_probleme1ActionPerformed

    private void probleme2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_probleme2ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_probleme2ActionPerformed

    private void probleme3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_probleme3ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_probleme3ActionPerformed

    private void probleme4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_probleme4ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_probleme4ActionPerformed

    private void probleme5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_probleme5ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_probleme5ActionPerformed

    private void responsabilite1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_responsabilite1ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_responsabilite1ActionPerformed

    private void responsabilite2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_responsabilite2ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_responsabilite2ActionPerformed

    private void responsabilite3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_responsabilite3ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_responsabilite3ActionPerformed

    private void responsabilite4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_responsabilite4ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_responsabilite4ActionPerformed

    private void responsabilite5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_responsabilite5ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_responsabilite5ActionPerformed

    private void administration1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administration1ActionPerformed
selec();           // TODO add your handling code here:
    }//GEN-LAST:event_administration1ActionPerformed

    private void administration2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administration2ActionPerformed
 selec();       // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_administration2ActionPerformed

    private void administration3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administration3ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_administration3ActionPerformed

    private void administration4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administration4ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_administration4ActionPerformed

    private void administration5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administration5ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_administration5ActionPerformed

    private void honnete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_honnete1ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_honnete1ActionPerformed

    private void honnete2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_honnete2ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_honnete2ActionPerformed

    private void honnete3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_honnete3ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_honnete3ActionPerformed

    private void honnete4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_honnete4ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_honnete4ActionPerformed

    private void honnete5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_honnete5ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_honnete5ActionPerformed

    private void enthousiasme1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enthousiasme1ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_enthousiasme1ActionPerformed

    private void enthousiasme2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enthousiasme2ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_enthousiasme2ActionPerformed

    private void enthousiasme3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enthousiasme3ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_enthousiasme3ActionPerformed

    private void enthousiasme4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enthousiasme4ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_enthousiasme4ActionPerformed

    private void enthousiasme5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enthousiasme5ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_enthousiasme5ActionPerformed

    private void attitude1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attitude1ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_attitude1ActionPerformed

    private void attitude2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attitude2ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_attitude2ActionPerformed

    private void attitude3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attitude3ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_attitude3ActionPerformed

    private void attitude4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attitude4ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_attitude4ActionPerformed

    private void attitude5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attitude5ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_attitude5ActionPerformed

    private void coeherence1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coeherence1ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_coeherence1ActionPerformed

    private void coeherence2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coeherence2ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_coeherence2ActionPerformed

    private void coeherence3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coeherence3ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_coeherence3ActionPerformed

    private void coeherence4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coeherence4ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_coeherence4ActionPerformed

    private void coeherence5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coeherence5ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_coeherence5ActionPerformed

    private void relation1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relation1ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_relation1ActionPerformed

    private void relation2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relation2ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_relation2ActionPerformed

    private void relation3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relation3ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_relation3ActionPerformed

    private void relation5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relation5ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_relation5ActionPerformed

    private void collaboration1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collaboration1ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_collaboration1ActionPerformed

    private void collaboration2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collaboration2ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_collaboration2ActionPerformed

    private void collaboration3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collaboration3ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_collaboration3ActionPerformed

    private void collaboration4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collaboration4ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_collaboration4ActionPerformed

    private void collaboration5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collaboration5ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_collaboration5ActionPerformed

    private void technique1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_technique1ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_technique1ActionPerformed

    private void technique2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_technique2ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_technique2ActionPerformed

    private void technique3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_technique3ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_technique3ActionPerformed

    private void technique4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_technique4ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_technique4ActionPerformed

    private void technique5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_technique5ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_technique5ActionPerformed

    private void cooperation1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cooperation1ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_cooperation1ActionPerformed

    private void cooperation2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cooperation2ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_cooperation2ActionPerformed

    private void cooperation3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cooperation3ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_cooperation3ActionPerformed

    private void cooperation4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cooperation4ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_cooperation4ActionPerformed

    private void cooperation5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cooperation5ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_cooperation5ActionPerformed

    private void initiative1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initiative1ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_initiative1ActionPerformed

    private void initiative2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initiative2ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_initiative2ActionPerformed

    private void initiative3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initiative3ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_initiative3ActionPerformed

    private void initiative4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initiative4ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_initiative4ActionPerformed

    private void initiative5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initiative5ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_initiative5ActionPerformed

    private void presence1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presence1ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_presence1ActionPerformed

    private void presence2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presence2ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_presence2ActionPerformed

    private void presence3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presence3ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_presence3ActionPerformed

    private void presence4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presence4ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_presence4ActionPerformed

    private void presence5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presence5ActionPerformed
selec();        // TODO add your handling code here:
    }//GEN-LAST:event_presence5ActionPerformed

    private void textField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField7KeyReleased
 try {
            String sql = ("SELECT NAME AS Nom,LNAME as 'P. Nom' FROM employee WHERE ACTIVE='Active' and NAME LIKE '"+textField7.getText()+"%' OR LNAME LIKE '"+textField7.getText()+"%' GROUP BY NAME");
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_textField7KeyReleased

    private void dateChooser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateChooser1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dateChooser1MouseClicked

    private void dateChooser1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateChooser1MouseReleased
// JOptionPane.showMessageDialog(null, "working");        // TODO add your handling code here:
    }//GEN-LAST:event_dateChooser1MouseReleased

    private void dateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateKeyReleased
       // TODO add your handling code here:
    }//GEN-LAST:event_dateKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
save();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//report_all();
boolean status=false;
metier1.setSelected(status);
metier2.setSelected(status); 
metier3.setSelected(status);
metier4.setSelected(status);
metier5.setSelected(status);
//refresh();    
// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
report();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox administration1;
    private javax.swing.JCheckBox administration2;
    private javax.swing.JCheckBox administration3;
    private javax.swing.JCheckBox administration4;
    private javax.swing.JCheckBox administration5;
    private Palette.TextField annee;
    private javax.swing.JCheckBox attitude1;
    private javax.swing.JCheckBox attitude2;
    private javax.swing.JCheckBox attitude3;
    private javax.swing.JCheckBox attitude4;
    private javax.swing.JCheckBox attitude5;
    private Palette.TextArea but;
    private javax.swing.JCheckBox coeherence1;
    private javax.swing.JCheckBox coeherence2;
    private javax.swing.JCheckBox coeherence3;
    private javax.swing.JCheckBox coeherence4;
    private javax.swing.JCheckBox coeherence5;
    private javax.swing.JCheckBox collaboration1;
    private javax.swing.JCheckBox collaboration2;
    private javax.swing.JCheckBox collaboration3;
    private javax.swing.JCheckBox collaboration4;
    private javax.swing.JCheckBox collaboration5;
    private javax.swing.JCheckBox communication1;
    private javax.swing.JCheckBox communication2;
    private javax.swing.JCheckBox communication3;
    private javax.swing.JCheckBox communication4;
    private javax.swing.JCheckBox communication5;
    private javax.swing.JCheckBox cooperation1;
    private javax.swing.JCheckBox cooperation2;
    private javax.swing.JCheckBox cooperation3;
    private javax.swing.JCheckBox cooperation4;
    private javax.swing.JCheckBox cooperation5;
    private javax.swing.JCheckBox creative1;
    private javax.swing.JCheckBox creative2;
    private javax.swing.JCheckBox creative3;
    private javax.swing.JCheckBox creative4;
    private javax.swing.JCheckBox creative5;
    private Palette.TextField date;
    private com.raven.datechooser.DateChooser dateChooser1;
    private Palette.TextField dep;
    private javax.swing.JCheckBox enthousiasme1;
    private javax.swing.JCheckBox enthousiasme2;
    private javax.swing.JCheckBox enthousiasme3;
    private javax.swing.JCheckBox enthousiasme4;
    private javax.swing.JCheckBox enthousiasme5;
    private javax.swing.JCheckBox fiabilite1;
    private javax.swing.JCheckBox fiabilite2;
    private javax.swing.JCheckBox fiabilite3;
    private javax.swing.JCheckBox fiabilite4;
    private javax.swing.JCheckBox fiabilite5;
    private Palette.TextArea force;
    private javax.swing.JCheckBox honnete1;
    private javax.swing.JCheckBox honnete2;
    private javax.swing.JCheckBox honnete3;
    private javax.swing.JCheckBox honnete4;
    private javax.swing.JCheckBox honnete5;
    private javax.swing.JCheckBox initiative1;
    private javax.swing.JCheckBox initiative2;
    private javax.swing.JCheckBox initiative3;
    private javax.swing.JCheckBox initiative4;
    private javax.swing.JCheckBox initiative5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable5;
    private javax.swing.JCheckBox metier1;
    private javax.swing.JCheckBox metier2;
    private javax.swing.JCheckBox metier3;
    private javax.swing.JCheckBox metier4;
    private javax.swing.JCheckBox metier5;
    private Palette.TextField nom;
    private Palette.TextField note;
    private Palette.TextArea opportunite;
    private javax.swing.JCheckBox ponctualite1;
    private javax.swing.JCheckBox ponctualite2;
    private javax.swing.JCheckBox ponctualite3;
    private javax.swing.JCheckBox ponctualite4;
    private javax.swing.JCheckBox ponctualite5;
    private javax.swing.JCheckBox presence1;
    private javax.swing.JCheckBox presence2;
    private javax.swing.JCheckBox presence3;
    private javax.swing.JCheckBox presence4;
    private javax.swing.JCheckBox presence5;
    private javax.swing.JCheckBox probleme1;
    private javax.swing.JCheckBox probleme2;
    private javax.swing.JCheckBox probleme3;
    private javax.swing.JCheckBox probleme4;
    private javax.swing.JCheckBox probleme5;
    private javax.swing.JCheckBox productivite1;
    private javax.swing.JCheckBox productivite2;
    private javax.swing.JCheckBox productivite3;
    private javax.swing.JCheckBox productivite4;
    private javax.swing.JCheckBox productivite5;
    private javax.swing.JCheckBox relation1;
    private javax.swing.JCheckBox relation2;
    private javax.swing.JCheckBox relation3;
    private javax.swing.JCheckBox relation4;
    private javax.swing.JCheckBox relation5;
    private javax.swing.JCheckBox responsabilite1;
    private javax.swing.JCheckBox responsabilite2;
    private javax.swing.JCheckBox responsabilite3;
    private javax.swing.JCheckBox responsabilite4;
    private javax.swing.JCheckBox responsabilite5;
    private Palette.TextField sup;
    private javax.swing.JCheckBox technique1;
    private javax.swing.JCheckBox technique2;
    private javax.swing.JCheckBox technique3;
    private javax.swing.JCheckBox technique4;
    private javax.swing.JCheckBox technique5;
    private Palette.TextAreaScroll textAreaScroll1;
    private Palette.TextAreaScroll textAreaScroll2;
    private Palette.TextAreaScroll textAreaScroll3;
    private Palette.TextField textField7;
    private javax.swing.JCheckBox travail1;
    private javax.swing.JCheckBox travail2;
    private javax.swing.JCheckBox travail3;
    private javax.swing.JCheckBox travail4;
    private javax.swing.JCheckBox travail5;
    // End of variables declaration//GEN-END:variables
}
