/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package journals;

import com.license4j.License;
import com.license4j.ValidationStatus;
import intreprisemanagementsystem.JavaDbConnect;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Doshe PC
 */
public class print extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    DefaultTableModel model;
    String rolls;
 private LicenseKeyGUI licenseKeyGUI;
    public print() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
        con = JavaDbConnect.dbConnect();
        call();
          licenseKeyGUI = new LicenseKeyGUI(this, true);
    }

    public void call() {

        try {
            String sql = ("SELECT num_fiche as FACTURE,name FROM  recu WHERE projet='" + journal1.buss.getText() + "'  AND TRANSACTION='FACTURE' GROUP BY num_fiche");
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        try {
            String sql = ("SELECT num_fiche as FACTURE,NAME FROM RECU WHERE PROJET='" + journal1.buss.getText() + "' AND TRANSACTION='FACTURE' GROUP BY num_fiche");
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
//            jTable9.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

       
        try {
            String sql = "SELECT * FROM currency";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                //String sum=rs.getString("nom");
                String sums = rs.getString("APPR");

                jComboBox1.addItem(sums);
               jComboBox6.addItem(sums);
               jComboBox7.addItem(sums);
              jComboBox9.addItem(sums);
//              jComboBox10.addItem(sums);
              //  jComboBox2.addItem(sums);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
try {
            String sql = "SELECT * FROM projet";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                //String sum=rs.getString("nom");
                String sums = rs.getString("projet_num");

                factbuss.addItem(sums);
              // jComboBox6.addItem(sums);
               //jComboBox7.addItem(sums);
              //jComboBox9.addItem(sums);
//              jComboBox10.addItem(sums);
              //  jComboBox2.addItem(sums);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        try {
            String sql = ("SELECT num as FACTURE,NAME FROM RECU WHERE PROJET='" + journal1.buss.getText() + "'  AND TRANSACTION='FACTURE' GROUP BY num_fiche");
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
          //  jTable12.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex);}

        try {
            String sql = ("SELECT num_fiche as Numéro,name as 'Client/Four' FROM recu WHERE transaction='Bon de Livraison' and projet='" + journal1.buss.getText() + "' group by num_fiche");
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable13.setModel(DbUtils.resultSetToTableModel(rs));
            TableColumn col0 = jTable13.getColumnModel().getColumn(0);
            TableColumn col1 = jTable13.getColumnModel().getColumn(1);

            col0.setPreferredWidth(55);
            col1.setPreferredWidth(100);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
         try {
            String sql = ("SELECT num as FACTURE,NAME FROM RECU WHERE PROJET='" + journal1.buss.getText() + "'  AND TRANSACTION='Facture proforma' GROUP BY num");
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
          tbl_proforma_db.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex);}

    }

    public void selectontable_vide() {

//        TableModel model1 =doc.getModel();
        int indexs[] = {1};

        Object[] row = new Object[5];
        DefaultTableModel model2 = (DefaultTableModel) tb_facture.getModel();
        for (int i = 0; i < indexs.length; i++) {
            row[0] = "Détails  ici";
            row[1] = 0;
            row[2] = 0;
            row[3] = 0;
            row[4] = "...";
//        row[2]= model1.getValueAt(indexs[i],2);
//        row[3]= model1.getValueAt(indexs[i],3);
            model2.addRow(row);
DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
centre.setHorizontalAlignment(JLabel.RIGHT);
            // jTable3.setModel(DbUtils.resultSetToTableModel(rs));
            TableColumn col0 = tb_facture.getColumnModel().getColumn(0);
            TableColumn col1 = tb_facture.getColumnModel().getColumn(1);
             tb_facture.getColumnModel().getColumn(1).setCellRenderer(centre);
            TableColumn col2 = tb_facture.getColumnModel().getColumn(2);
            tb_facture.getColumnModel().getColumn(2).setCellRenderer(centre);
            TableColumn col3 = tb_facture.getColumnModel().getColumn(3);
             tb_facture.getColumnModel().getColumn(3).setCellRenderer(centre);
            TableColumn col4 = tb_facture.getColumnModel().getColumn(4);
            

            col0.setPreferredWidth(500);
            col1.setPreferredWidth(50);
            col2.setPreferredWidth(50);
            col3.setPreferredWidth(100);
            col4.setPreferredWidth(50);
//        
        }

    }



    public void selectontablemoin() {

        int indexs[] = tb_facture.getSelectedRows();

        DefaultTableModel model = (DefaultTableModel) tb_facture.getModel();
        for (int i = 0; i < indexs.length; i++) {

            int getSelectedRowsForDeletion = tb_facture.getSelectedRow();
            model.removeRow(getSelectedRowsForDeletion);

        }

    }

    public void calculateraw() {
        int sum = 0;
        for (int i = 0; i < tb_facture.getRowCount(); i++) {
            sum = sum + Integer.parseInt(tb_facture.getValueAt(1, 3).toString());
        }
        //jTextField1.setText(Integer.toString(sum));
    }

    public void Rolls() {
        try {
           // SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM-dd");
            //String addDateS = dateFormatsS.format(journal1.jDateChooser1.getDate());
            
            String addDateS =factdate.getText().substring(0, 7);

            String sql = "SELECT num_fiche FROM recu where projet='" + factbuss.getSelectedItem() + "' and transaction='Facture' ORDER BY num_fiche DESC LIMIT 1";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String rl = rs.getString("num_fiche");
                int ln = rl.length();
                String stxt = rl.substring(0, 8);
                String snum_fiche = rl.substring(8, ln);
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
                rolls = "" + addDateS + "-001";

            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        num.setText(rolls);
    }

    public void save() {
        Rolls();
        int rows = tb_facture.getRowCount();
        for (int row = 0; row < rows; row++) {
            String libelle;
            //  ReplaceString = libelle.replace("'", "''");
            String Docc = (String) tb_facture.getValueAt(row, 0);
            String Qty = (String) tb_facture.getValueAt(row, 1);
            String Up = (String) tb_facture.getValueAt(row, 2);
            String Unite = (String) tb_facture.getValueAt(row, 4);
            libelle = Docc.replace("'", "''");
            Double qty = Double.parseDouble(Qty);
            Double up = Double.parseDouble(Up);
            Double pt = qty * up;

            // Pts = Ptss.replace("'", "''");
            try {
                //                                                               `DESCR`, `QTY`, `PU`, `PT`, `NUM`, `NAME`, `LETTRE`, `DATE`, `TRANSACTION`, `NAME_TO`, `MONAIS`, `PROJET`, `NUM_FICHE`   
                PreparedStatement pst = con.prepareStatement("INSERT INTO `recu`( `DESCR`, `QTY`, `PU`, `PT`, `NUM`, `NAME`, `LETTRE`, `DATE`, `TRANSACTION`, `NAME_TO`, `MONAIS`, `PROJET`, `NUM_FICHE`,`Accompte`)"
                        + "value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                //`DESCR`, `QTY`, `PU`, `PT`, `NUM`, `NAME`, `LETTRE`, `DATE`, `TRANSACTION`, `NAME_TO`, `MONAIS`, `PROJET`, `NUM_FICHE`          
                pst.setString(1, libelle);
                pst.setDouble(2, qty);
                pst.setDouble(3, up);
                pst.setDouble(4, pt);
                pst.setString(5, rolls);
                pst.setString(6, client_facture.getText());
                pst.setString(7, lettre_receptiona.getText());
                pst.setString(8,factdate.getText());
                pst.setString(9, "Facture");
                pst.setString(10, rolls);
                pst.setString(11, jComboBox1.getSelectedItem().toString());
                pst.setString(12, factbuss.getSelectedItem().toString());
                pst.setString(13, num.getText());
                 pst.setString(14,accompte.getText());

                pst.executeUpdate();

                //   JOptionPane.showMessageDialog(null,"CLERCK_IN  SUCCESSFUL BONNE JOURNEE!!!        ");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }
        JOptionPane.showMessageDialog(null, "Transaction Saved");

    }

    public void report() {
        // Rolls();
       
        String PrintName = null;
        try {
            String sql = "SELECT * FROM PROJET WHERE PROJET_NUM='" + factbuss.getSelectedItem()+ "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                //String sum=rs.getString("nom");
                PrintName = rs.getString("FACTURE");

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     
        try {

            String sqls = "Select path from pathn";

            pst = con.prepareStatement(sqls);
            rs = pst.executeQuery();
            if (rs.next()) {

                String sum = rs.getString("path");

                String NameFiles = sum;

                String NameFile = "" + NameFiles + PrintName + ".jrxml";

                JasperDesign jd = JRXmlLoader.load(NameFile);
                //Select * from recu INNER JOIN projet ON recu.projet=projet.projet_num
                String sql = "Select * from recu INNER JOIN projet ON recu.projet=projet.projet_num where NAME_TO= '" + num.getText() + "' and recu.PROJET='" +factbuss.getSelectedItem()+"'";

                JRDesignQuery nq = new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);

                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
                JRViewer m = new JRViewer(jp);

                print_show ms = new print_show();
                ms.show();
                ms.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                print_show.boody.removeAll();
                print_show.boody.setLayout(new BorderLayout());
                print_show.boody.add(m);

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void report_PRO() {
        // Rolls();
        String PrintName = null, Fact = null;
        try {
            String sql = "SELECT * FROM new_buss WHERE DENO1='" + journal1.buss.getText() + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                //String sum=rs.getString("nom");
                Fact = rs.getString("FACTURE");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        switch (Fact) {
            case "facture":
                PrintName = "facture_1";
                break;
            case "facture_2":
                PrintName = "proforma_2";
                break;
            default:
                PrintName = "proforma_2_1";
                break;
        }

        try {

            String sqls = "Select path from pathn";

            pst = con.prepareStatement(sqls);
            rs = pst.executeQuery();
            if (rs.next()) {

                String sum = rs.getString("path");

                String NameFiles = sum;

                String NameFile = "" + NameFiles + PrintName + ".jrxml";

                JasperDesign jd = JRXmlLoader.load(NameFile);

                //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
                // String sql="Select * from ohada_trans INNER JOIN projet ON ohada_trans.BUSS=projet.PROJET_num_fiche where buss='"+tmp+" ' and DATES BETWEEN '"+date1+"' AND '"+date2+"'AND CODE ='"+journal_caisse.jComboBox1.getSelectedItem()+"'  order by DATES,ohada_trans.num_fiche";
                String sql = "Select * from facture INNER JOIN new_buss ON facture.startup=new_buss.deno1 where roll='" + num.getText() + "' and buss='" + journal1.buss.getText() + "' and startup='" + journal1.buss.getText() + "' and docs='PRO FORMAT'";
//      HashMap param= new HashMap();
//    param.put("date1", jDateChooser1.getText());
//     param.put("date2", jDateChooser2.getText());
                JRDesignQuery nq = new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);

                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
                JRViewer m = new JRViewer(jp);

                print_show ms = new print_show();
                ms.show();
                ms.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                print_show.boody.removeAll();
                print_show.boody.setLayout(new BorderLayout());
                print_show.boody.add(m);

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void report_LIVRAISON() {
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
        try {

            String sqls = "Select path from pathn";

            pst = con.prepareStatement(sqls);
            rs = pst.executeQuery();
            if (rs.next()) {

                String sum = rs.getString("path");

                String NameFiles = sum;

                String NameFile = "" + NameFiles + "docs.jrxml";

                JasperDesign jd = JRXmlLoader.load(NameFile);

                //JasperDesign jd=JRXmlLoader.load("C:\\Users\\Dosh\\Documents\\NetBeansProjects\\intreprisemanagementsystem\\src\\intreprisemanagementsystem\\journaldecaise.jrxml");
                // String sql="Select * from ohada_trans INNER JOIN projet ON ohada_trans.BUSS=projet.PROJET_num_fiche where buss='"+tmp+" ' and DATES BETWEEN '"+date1+"' AND '"+date2+"'AND CODE ='"+journal_caisse.jComboBox1.getSelectedItem()+"'  order by DATES,ohada_trans.num_fiche";
                String sql = "Select * from facture INNER JOIN new_buss ON facture.startup=new_buss.deno1 where roll='" + num.getText() + "' and buss='" + journal1.buss.getText() + "' and startup='" + journal1.buss.getText() + "' and docs='Bon de Livraison'";
//      HashMap param= new HashMap();
//    param.put("date1", jDateChooser1.getText());
//     param.put("date2", jDateChooser2.getText());
                JRDesignQuery nq = new JRDesignQuery();
                nq.setText(sql);
                jd.setQuery(nq);

                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
                JRViewer m = new JRViewer(jp);

                print_show ms = new print_show();
                ms.show();
                ms.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                print_show.boody.removeAll();
                print_show.boody.setLayout(new BorderLayout());
                print_show.boody.add(m);

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void clic_attCall_IN_LIST() {
        int row = jTable7.getSelectedRow();
        String Table_click = (jTable7.getModel().getValueAt(row, 0).toString());

        try {
            String sql = "SELECT LIBELLE,QTY,UP,UNITE FROM FACTURE where num_fiche ='" + Table_click + "' and buss='" + journal1.buss.getText() + "' and startup='" + journal1.buss.getText() + "' AND transaction='FACTURE'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tb_facture.setModel(DbUtils.resultSetToTableModel(rs));

            TableColumn col0 = tb_facture.getColumnModel().getColumn(0);
            TableColumn col1 = tb_facture.getColumnModel().getColumn(1);
            TableColumn col2 = tb_facture.getColumnModel().getColumn(2);
            TableColumn col3 = tb_facture.getColumnModel().getColumn(3);
            // TableColumn col4=jTable1.getColumnModel().getColumn(4);

            col0.setPreferredWidth(500);
            col1.setPreferredWidth(50);
            col2.setPreferredWidth(50);
            col3.setPreferredWidth(50);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        try {
            String sql = "SELECT CLIENT,num_fiche,DEVICE,SUM(PT) FROM FACTURE where num_fiche ='" + Table_click + "' and buss='" + journal1.buss.getText() + "' and startup='" + journal1.buss.getText() + "' AND transaction='FACTURE'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {

                String rolll = rs.getString("CLIENT");
                client_facture.setText(rolll);

                String clients = rs.getString("roll");
                num.setText(clients);

                String clientsA = rs.getString("SUM(PT)");
                fact_ht.setText(clientsA);

                String clientsS = rs.getString("DEVICE");
//                jLabel6.setText(clientsS);

                jComboBox1.setSelectedItem(clientsS);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void clic_attCall_IN_LISTS() {
        int row = tbl_proforma_db.getSelectedRow();
        String Table_click = (tbl_proforma_db.getModel().getValueAt(row, 0).toString());

        try {
            String sql = "SELECT LIBELLE,QTY,UP,PT FROM FACTURE where num_fiche ='" + Table_click + "' and buss='" + journal1.buss.getText() + "' and startup='" + journal1.buss.getText() + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tb_profacture.setModel(DbUtils.resultSetToTableModel(rs));

            TableColumn col0 = tb_profacture.getColumnModel().getColumn(0);
            TableColumn col1 = tb_profacture.getColumnModel().getColumn(1);
            TableColumn col2 = tb_profacture.getColumnModel().getColumn(2);
            TableColumn col3 = tb_profacture.getColumnModel().getColumn(3);
            // TableColumn col4=jTable1.getColumnModel().getColumn(4);

            col0.setPreferredWidth(500);
            col1.setPreferredWidth(50);
            col2.setPreferredWidth(50);
            col3.setPreferredWidth(50);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        try {
            String sql = "SELECT CLIENT,num_fiche,DEVICE,SUM(PT) FROM FACTURE where num_fiche ='" + Table_click + "' and buss='" + journal1.buss.getText() + "' and startup='" + journal1.buss.getText() + "' AND transaction='PRO FORMAT'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {

                String rolll = rs.getString("CLIENT");
//                client1.setText(rolll);

                String clients = rs.getString("roll");
                num.setText(clients);

//              String clientsA=rs.getString("SUM(PT)");
//              jLabel.setText(clientsA);
                String clientsS = rs.getString("DEVICE");
//                jLabel6.setText(clientsS);

                jComboBox1.setSelectedItem(clientsS);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void Rollss() {
        try {
            SimpleDateFormat dateFormatsS = new SimpleDateFormat("yyyy-MM");
            String addDateS = dateFormatsS.format(journal1.jDateChooser1.getDate());

            String sql = "SELECT num_fiche FROM RECUS where STARTUP='" + journal1.buss.getText() + "' where transaction='Recus' ORDER BY num_fiche DESC LIMIT 1";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String rl = rs.getString("num_fiche");
                int ln = rl.length();
                String stxt = rl.substring(0, 8);
                String snum_fiche = rl.substring(8, ln);
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
                rolls = "" + addDateS + "-001";

            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        num.setText(rolls);
    }

    public void getSumFacture() {

        int rowcount = tb_facture.getRowCount();
      int sum = 0;
        for (int i = 0; i < rowcount; i++) {
           String strgqty =  tb_facture.getValueAt(i, 1).toString();
           String strgpu =  tb_facture.getValueAt(i, 2).toString();
//         double qty = Double.parseDouble(strgqty);
//         double pu = Double.parseDouble(strgpu);
         
         int qty = Integer.parseInt(strgqty);
        int pu = Integer.parseInt(strgpu);
         int b = qty * pu;

           tb_facture.getModel().setValueAt(b, i, 3);
           sum = sum + Integer.parseInt(tb_facture.getValueAt(i, 3).toString());
        }
        fact_ht.setText(""+sum);
        
        Long a = Long.parseLong(fact_ht.getText());
lettre_receptiona.setText(""+ FrenchNumberToWords.convert(a));        
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
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_facture = new javax.swing.JTable();
        client_facture = new Palette.TextField();
        jPanel33 = new javax.swing.JPanel();
        fact_ht = new javax.swing.JLabel();
        fact_tva = new javax.swing.JLabel();
        fact_ttc = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lettre_receptiona = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        fact_jComboBox2 = new javax.swing.JComboBox<>();
        accompte = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        factdate = new Palette.MyTextField();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tbl_proforma_db = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tb_profacture = new javax.swing.JTable();
        jPanel34 = new javax.swing.JPanel();
        pro_ht = new javax.swing.JLabel();
        pro_tva = new javax.swing.JLabel();
        pro_ttc = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        pro_lettre_receptiona = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        pro_jComboBox3 = new javax.swing.JComboBox<>();
        client_proformat = new Palette.TextField();
        jPanel19 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tb_bon_livraison = new javax.swing.JTable();
        jPanel35 = new javax.swing.JPanel();
        livre_ht = new javax.swing.JLabel();
        livre_tva = new javax.swing.JLabel();
        livre_ttc = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        livre_lettre_receptiona = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        livre_jComboBox4 = new javax.swing.JComboBox<>();
        client_bon_livraison = new Palette.TextField();
        jTextField3 = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTable16 = new javax.swing.JTable();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tb_bon_reception = new javax.swing.JTable();
        client_bon_reception = new Palette.TextField();
        jPanel36 = new javax.swing.JPanel();
        rec_ht = new javax.swing.JLabel();
        rec_tva = new javax.swing.JLabel();
        rec_ttc = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        rec_lettre_receptiona = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        rec_jComboBox5 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();

        dateChooser1.setDateFormat("yyyy-MM-dd");
        dateChooser1.setTextRefernce(factdate);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseClicked(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(242, 242, 241));
        jTextField5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText("Factures/Recus");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

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
            .addComponent(jScrollPane5)
        );

        jTabbedPane1.addTab("", jPanel3);

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

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......." }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_facture.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Libelle", "Qty", "Up", "Pt", "Ute"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_facture.setGridColor(new java.awt.Color(204, 204, 204));
        tb_facture.setRowHeight(30);
        tb_facture.setShowHorizontalLines(true);
        tb_facture.setShowVerticalLines(true);
        tb_facture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_factureMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_factureMouseReleased(evt);
            }
        });
        tb_facture.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_factureKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tb_facture);
        if (tb_facture.getColumnModel().getColumnCount() > 0) {
            tb_facture.getColumnModel().getColumn(3).setResizable(false);
        }

        client_facture.setLabelText("Client");

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        fact_ht.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fact_ht.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fact_ht.setText("0.00");
        fact_ht.setOpaque(true);

        fact_tva.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fact_tva.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fact_tva.setText("0.00");
        fact_tva.setOpaque(true);

        fact_ttc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fact_ttc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fact_ttc.setText("0.00");
        fact_ttc.setOpaque(true);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("HT");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Tax (Tva)");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("TTC");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lettre_receptiona.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lettre_receptiona.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lettre_receptiona.setText("........");

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

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Numero de facture");

        num.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        num.setText("Num:");
        num.setOpaque(true);

        fact_jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fr", "Eng" }));
        fact_jComboBox2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        accompte.setBackground(new java.awt.Color(242, 242, 242));
        accompte.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        accompte.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        accompte.setText("0.00");
        accompte.setBorder(null);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Accoumpte(s)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 94, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(fact_jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lettre_receptiona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel33Layout.createSequentialGroup()
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(fact_tva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fact_ttc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fact_ht, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                .addComponent(accompte))
                            .addContainerGap())
                        .addComponent(jSeparator1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(accompte, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fact_ht, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(fact_tva, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel33Layout.createSequentialGroup()
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(fact_ttc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel33Layout.createSequentialGroup()
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1))))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(num, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fact_jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lettre_receptiona))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        factdate.setEditable(false);
        factdate.setBackground(new java.awt.Color(242, 242, 241));
        factdate.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        factbuss.setEditable(true);
        factbuss.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        factbuss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Select One Business/Project-" }));
        factbuss.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(client_facture, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(factbuss, 0, 286, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(factdate, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(client_facture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(factbuss, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(factdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField5)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Factures/Recus", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        tbl_proforma_db.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_proforma_db.setGridColor(new java.awt.Color(204, 204, 204));
        tbl_proforma_db.setRowHeight(30);
        tbl_proforma_db.setShowHorizontalLines(true);
        tbl_proforma_db.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_proforma_dbMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tbl_proforma_db);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("", jPanel22);

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

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jComboBox6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......." }));
        jComboBox6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_profacture.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Libelle", "Qty", "Up", "Pt", "Ute"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_profacture.setGridColor(new java.awt.Color(204, 204, 204));
        tb_profacture.setRowHeight(30);
        tb_profacture.setShowHorizontalLines(true);
        tb_profacture.setShowVerticalLines(true);
        tb_profacture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_profactureMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_profactureMouseReleased(evt);
            }
        });
        tb_profacture.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_profactureKeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(tb_profacture);
        if (tb_profacture.getColumnModel().getColumnCount() > 0) {
            tb_profacture.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        pro_ht.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pro_ht.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pro_ht.setText("0.00");
        pro_ht.setOpaque(true);

        pro_tva.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pro_tva.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pro_tva.setText("0.00");
        pro_tva.setOpaque(true);

        pro_ttc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pro_ttc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pro_ttc.setText("0.00");
        pro_ttc.setOpaque(true);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("HT");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Tax (Tva)");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("TTC");

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        pro_lettre_receptiona.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pro_lettre_receptiona.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pro_lettre_receptiona.setText("........");

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton7.setText("Save");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("No.: ");

        pro_num.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pro_num.setText("Num:");

        pro_jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fr", "Eng" }));
        pro_jComboBox3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pro_num, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pro_jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 322, Short.MAX_VALUE)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(pro_tva, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                    .addComponent(pro_ttc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(pro_ht, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pro_lettre_receptiona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator4)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel34Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(pro_ht)
                                .addGroup(jPanel34Layout.createSequentialGroup()
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(6, 6, 6)))
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(pro_tva)
                                .addGroup(jPanel34Layout.createSequentialGroup()
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(1, 1, 1)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(pro_ttc)
                                .addGroup(jPanel34Layout.createSequentialGroup()
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(1, 1, 1)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(pro_num, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pro_jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pro_lettre_receptiona)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        client_proformat.setLabelText("Client");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(client_proformat, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(client_proformat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jTextField4)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane3)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane2.addTab("Facture Proformat", jPanel5);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

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
            .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("", jPanel25);

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

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jComboBox7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......." }));
        jComboBox7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_bon_livraison.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Libelle", "Qty", "Up", "Pt", "Ute"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_bon_livraison.setGridColor(new java.awt.Color(204, 204, 204));
        tb_bon_livraison.setRowHeight(30);
        tb_bon_livraison.setShowHorizontalLines(true);
        tb_bon_livraison.setShowVerticalLines(true);
        tb_bon_livraison.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_bon_livraisonMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_bon_livraisonMouseReleased(evt);
            }
        });
        tb_bon_livraison.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_bon_livraisonKeyReleased(evt);
            }
        });
        jScrollPane7.setViewportView(tb_bon_livraison);
        if (tb_bon_livraison.getColumnModel().getColumnCount() > 0) {
            tb_bon_livraison.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        livre_ht.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        livre_ht.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        livre_ht.setText("0.00");
        livre_ht.setOpaque(true);

        livre_tva.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        livre_tva.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        livre_tva.setText("0.00");
        livre_tva.setOpaque(true);

        livre_ttc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        livre_ttc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        livre_ttc.setText("0.00");
        livre_ttc.setOpaque(true);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("HT");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("Tax (Tva)");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("TTC");

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        livre_lettre_receptiona.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        livre_lettre_receptiona.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        livre_lettre_receptiona.setText("........");

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton13.setText("Save");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText("No.: ");

        livre_num.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        livre_num.setText("Num:");

        livre_jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fr", "Eng" }));
        livre_jComboBox4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel35Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(livre_num, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(livre_jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 322, Short.MAX_VALUE)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(livre_tva, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                    .addComponent(livre_ttc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(livre_ht, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(livre_lettre_receptiona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel35Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator6)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel35Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(livre_ht)
                                .addGroup(jPanel35Layout.createSequentialGroup()
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(6, 6, 6)))
                            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(livre_tva)
                                .addGroup(jPanel35Layout.createSequentialGroup()
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(1, 1, 1)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(livre_ttc)
                                .addGroup(jPanel35Layout.createSequentialGroup()
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(1, 1, 1)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(livre_num, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(livre_jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(livre_lettre_receptiona)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        client_bon_livraison.setLabelText("Client");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(client_bon_livraison, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(client_bon_livraison, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
            .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("", jPanel28);

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

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jComboBox9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "......." }));
        jComboBox9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_bon_reception.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Libelle", "Qty", "Up", "Pt", "Ute"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_bon_reception.setGridColor(new java.awt.Color(204, 204, 204));
        tb_bon_reception.setRowHeight(30);
        tb_bon_reception.setShowHorizontalLines(true);
        tb_bon_reception.setShowVerticalLines(true);
        tb_bon_reception.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_bon_receptionMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_bon_receptionMouseReleased(evt);
            }
        });
        tb_bon_reception.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_bon_receptionKeyReleased(evt);
            }
        });
        jScrollPane9.setViewportView(tb_bon_reception);
        if (tb_bon_reception.getColumnModel().getColumnCount() > 0) {
            tb_bon_reception.getColumnModel().getColumn(3).setResizable(false);
        }

        client_bon_reception.setLabelText("Client");

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        rec_ht.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rec_ht.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rec_ht.setText("0.00");
        rec_ht.setOpaque(true);

        rec_tva.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rec_tva.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rec_tva.setText("0.00");
        rec_tva.setOpaque(true);

        rec_ttc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rec_ttc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rec_ttc.setText("0.00");
        rec_ttc.setOpaque(true);

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("HT");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("Tax (Tva)");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel42.setText("TTC");

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

        rec_lettre_receptiona.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rec_lettre_receptiona.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rec_lettre_receptiona.setText("........");

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton14.setText("Save");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel43.setText("No.: ");

        rec_num1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rec_num1.setText("Num:");

        rec_jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fr", "Eng" }));
        rec_jComboBox5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rec_num1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rec_jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 322, Short.MAX_VALUE)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(rec_tva, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                    .addComponent(rec_ttc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rec_ht, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                            .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rec_lettre_receptiona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator8)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel36Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(rec_ht)
                                .addGroup(jPanel36Layout.createSequentialGroup()
                                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(6, 6, 6)))
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(rec_tva)
                                .addGroup(jPanel36Layout.createSequentialGroup()
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(1, 1, 1)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(rec_ttc)
                                .addGroup(jPanel36Layout.createSequentialGroup()
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(1, 1, 1)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addComponent(rec_num1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rec_jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rec_lettre_receptiona)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(client_bon_reception, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(client_bon_reception, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTabbedPane5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel26Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked
        clic_attCall_IN_LIST();        // TODO add your handling code here:
    }//GEN-LAST:event_jTable7MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
          // selectontable();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        selectontablemoin();       // selectontablemoin();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        selectontable_vide();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void tb_factureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_factureMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_tb_factureMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       // if (journal1.buss.getText().equals("Start Up") || client_facture.getText().equals("") || client_facture.getText().equals("Client:")) {
        //    JOptionPane.showMessageDialog(null, "Unknown Start up  or Client", "Error", JOptionPane.ERROR_MESSAGE);
        //} else {
            if (num.getText().equals("Num:")) {
                save();
                report();
                call();
            } else {
                report();
                call();
            }

        //}

        //calculateraw();//        savealltransactionS();
        //        save();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //   recu m= new  recu  ();
        //  m.show();
        //    m. setDefaultCloseOperation(DISPOSE_ON_CLOSE);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tbl_proforma_dbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_proforma_dbMouseClicked
        int row = tbl_proforma_db.getSelectedRow();
        String Table_click = (tbl_proforma_db.getModel().getValueAt(row, 0).toString());

        try {
            String sql = "SELECT LIBELLE,QTY,UP,UNITE FROM FACTURE where num_fiche ='" + Table_click + "' and buss='" + journal1.buss.getText() + "' and startup='" + journal1.buss.getText() + "' and transaction='PRO FORMAT'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tb_profacture.setModel(DbUtils.resultSetToTableModel(rs));

            TableColumn col0 = tb_profacture.getColumnModel().getColumn(0);
            TableColumn col1 = tb_profacture.getColumnModel().getColumn(1);
            TableColumn col2 = tb_profacture.getColumnModel().getColumn(2);
            TableColumn col3 = tb_profacture.getColumnModel().getColumn(3);
            // TableColumn col4=jTable1.getColumnModel().getColumn(4);

            col0.setPreferredWidth(500);
            col1.setPreferredWidth(50);
            col2.setPreferredWidth(50);
            col3.setPreferredWidth(50);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        try {
            String sql = "SELECT CLIENT,num_fiche,DEVICE,SUM(PT) FROM FACTURE where num_fiche ='" + Table_click + "' and buss='" + journal1.buss.getText() + "' and startup='" + journal1.buss.getText() + "' AND transaction='PRO FORMAT'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {

                String rolll = rs.getString("CLIENT");
//                client1.setText(rolll);

                String clients = rs.getString("roll");
                num.setText(clients);

                //              String clientsA=rs.getString("SUM(PT)");
                //              jLabel5.setText(clientsA);
                //
                String clientsS = rs.getString("DEVICE");
//                jLabel6.setText(clientsS);

          //      jComboBox2.setSelectedItem(clientsS);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_proforma_dbMouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        int indexs[] = {1};

        Object[] row = new Object[4];
        DefaultTableModel model2 = (DefaultTableModel) tb_profacture.getModel();
        for (int i = 0; i < indexs.length; i++) {
            row[0] = "Détails  ici";
            row[1] = "0";
            row[2] = "0";
            row[3] = "0";
            //        row[2]= model1.getValueAt(indexs[i],2);
            //        row[3]= model1.getValueAt(indexs[i],3);
            model2.addRow(row);

            // jTable3.setModel(DbUtils.resultSetToTableModel(rs));
            TableColumn col0 = tb_profacture.getColumnModel().getColumn(0);
            TableColumn col1 = tb_profacture.getColumnModel().getColumn(1);
            TableColumn col2 = tb_profacture.getColumnModel().getColumn(2);
            TableColumn col3 = tb_profacture.getColumnModel().getColumn(3);
            // TableColumn col4=jTable1.getColumnModel().getColumn(4);

            col0.setPreferredWidth(500);
            col1.setPreferredWidth(50);
            col2.setPreferredWidth(50);
            col3.setPreferredWidth(50);
            // col4.setPreferredWidth(50);
            //
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel33MouseClicked

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked

        int indexs[] = tb_profacture.getSelectedRows();

        DefaultTableModel model = (DefaultTableModel) tb_profacture.getModel();
        for (int i = 0; i < indexs.length; i++) {

            int getSelectedRowsForDeletion = tb_profacture.getSelectedRow();
            model.removeRow(getSelectedRowsForDeletion);

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel34MouseClicked

    private void jTable13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable13MouseClicked
        int row = jTable13.getSelectedRow();
        String Table_click = (jTable13.getModel().getValueAt(row, 0).toString());
        try {
            String sql = "SELECT CLIENT,num_fiche FROM FACTURE where num_fiche ='" + Table_click + "' and buss='" + journal1.buss.getText() + "' and startup='" + journal1.buss.getText() + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {

                String rolll = rs.getString("CLIENT");
          //      client2.setText(rolll);
                String rolllS = rs.getString("num_fiche");
                num.setText(rolllS);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        try {
            String sqls = "SELECT `LIBELLE`, `QTY`, `UP`,`PT`,`UNITE` FROM facture where num_fiche='" + Table_click + "' and BUSS='" + journal1.buss.getText() + "' and STARTUP='" + journal1.buss.getText() + "' and docs='Bon de Livraison'";
            pst = con.prepareStatement(sqls);
            rs = pst.executeQuery();

//            jTable14.setModel(DbUtils.resultSetToTableModel(rs));
            DefaultTableCellRenderer centre = new DefaultTableCellRenderer();
            centre.setHorizontalAlignment(JLabel.CENTER);
//            TableColumn col0 = jTable14.getColumnModel().getColumn(0);
//            TableColumn col1 = jTable14.getColumnModel().getColumn(1);
 //           jTable14.getColumnModel().getColumn(1).setCellRenderer(centre);
//            TableColumn col2 = jTable14.getColumnModel().getColumn(2);
//            jTable14.getColumnModel().getColumn(2).setCellRenderer(centre);
//            TableColumn col3 = jTable14.getColumnModel().getColumn(3);
//            jTable14.getColumnModel().getColumn(3).setCellRenderer(centre);
 //           TableColumn col4 = jTable14.getColumnModel().getColumn(4);
//            jTable14.getColumnModel().getColumn(4).setCellRenderer(centre);

//            col0.setPreferredWidth(500);
  //          col1.setPreferredWidth(50);
    //        col2.setPreferredWidth(50);
      //      col3.setPreferredWidth(50);
        //    col4.setPreferredWidth(50);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }           // TODO add your handling code here:
    }//GEN-LAST:event_jTable13MouseClicked

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
  int indexs[] = {1};

        Object[] row = new Object[5];
        DefaultTableModel model2 = (DefaultTableModel) tb_bon_livraison.getModel();
        for (int i = 0; i < indexs.length; i++) {
            row[0] = "Détails  ici";
            row[1] = 0;
            row[2] = 0;
            row[3] = 0;
            row[4] = "...";
//        row[2]= model1.getValueAt(indexs[i],2);
//        row[3]= model1.getValueAt(indexs[i],3);
            model2.addRow(row);
DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
centre.setHorizontalAlignment(JLabel.RIGHT);
            // jTable3.setModel(DbUtils.resultSetToTableModel(rs));
            TableColumn col0 = tb_bon_livraison.getColumnModel().getColumn(0);
            TableColumn col1 = tb_bon_livraison.getColumnModel().getColumn(1);
             tb_bon_livraison.getColumnModel().getColumn(1).setCellRenderer(centre);
            TableColumn col2 = tb_bon_livraison.getColumnModel().getColumn(2);
            tb_bon_livraison.getColumnModel().getColumn(2).setCellRenderer(centre);
            TableColumn col3 = tb_bon_livraison.getColumnModel().getColumn(3);
             tb_bon_livraison.getColumnModel().getColumn(3).setCellRenderer(centre);
            TableColumn col4 = tb_bon_livraison.getColumnModel().getColumn(4);
            

            col0.setPreferredWidth(500);
            col1.setPreferredWidth(50);
            col2.setPreferredWidth(50);
            col3.setPreferredWidth(100);
            col4.setPreferredWidth(50);
//        
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
     int indexs[] = tb_bon_livraison.getSelectedRows();

        DefaultTableModel model = (DefaultTableModel) tb_bon_livraison.getModel();
        for (int i = 0; i < indexs.length; i++) {

            int getSelectedRowsForDeletion = tb_bon_livraison.getSelectedRow();
            model.removeRow(getSelectedRowsForDeletion);

        }    //    int indexs[] = jTable14.getSelectedRows();

      //  DefaultTableModel model = (DefaultTableModel) jTable14.getModel();
        //for (int i = 0; i < indexs.length; i++) {

          //  int getSelectedRowsForDeletion = jTable14.getSelectedRow();
            //model.removeRow(getSelectedRowsForDeletion);

     //   }        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel36MouseClicked

    private void jTable16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable16MouseClicked

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
int indexs[] = {1};

        Object[] row = new Object[5];
        DefaultTableModel model2 = (DefaultTableModel) tb_bon_reception.getModel();
        for (int i = 0; i < indexs.length; i++) {
            row[0] = "Détails  ici";
            row[1] = 0;
            row[2] = 0;
            row[3] = 0;
            row[4] = "...";
//        row[2]= model1.getValueAt(indexs[i],2);
//        row[3]= model1.getValueAt(indexs[i],3);
            model2.addRow(row);
DefaultTableCellRenderer centre =new DefaultTableCellRenderer();
centre.setHorizontalAlignment(JLabel.RIGHT);
            // jTable3.setModel(DbUtils.resultSetToTableModel(rs));
            TableColumn col0 = tb_bon_reception.getColumnModel().getColumn(0);
            TableColumn col1 = tb_bon_reception.getColumnModel().getColumn(1);
             tb_bon_reception.getColumnModel().getColumn(1).setCellRenderer(centre);
            TableColumn col2 = tb_bon_reception.getColumnModel().getColumn(2);
            tb_bon_reception.getColumnModel().getColumn(2).setCellRenderer(centre);
            TableColumn col3 = tb_bon_reception.getColumnModel().getColumn(3);
             tb_bon_reception.getColumnModel().getColumn(3).setCellRenderer(centre);
            TableColumn col4 = tb_bon_reception.getColumnModel().getColumn(4);
            

            col0.setPreferredWidth(500);
            col1.setPreferredWidth(50);
            col2.setPreferredWidth(50);
            col3.setPreferredWidth(100);
            col4.setPreferredWidth(50);
//        
        }              // TODO add your handling code here:
    }//GEN-LAST:event_jLabel37MouseClicked

    private void jLabel38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseClicked
    int indexs[] = tb_bon_reception.getSelectedRows();

        DefaultTableModel model = (DefaultTableModel) tb_bon_reception.getModel();
        for (int i = 0; i < indexs.length; i++) {

            int getSelectedRowsForDeletion = tb_bon_reception.getSelectedRow();
            model.removeRow(getSelectedRowsForDeletion);

        }         // TODO add your handling code here:
    }//GEN-LAST:event_jLabel38MouseClicked

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked
          // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane2MouseClicked

    private void tb_factureMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_factureMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_factureMouseReleased

    private void tb_factureKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_factureKeyReleased
    //    getSumFacture();   
if(client_facture.getText().equals("")||factbuss.getSelectedItem().equals("-Select One Business/Project-")||jComboBox1.getSelectedItem().equals(".......")){
 JOptionPane.showMessageDialog(null,"Wrong Data","Error",JOptionPane.ERROR_MESSAGE);
}else{
 NumberFormat nf = new DecimalFormat("#,###.00");
  float Tva=0;
   try {
            String sql = "SELECT * FROM  projet  where PROJET_NUM ='" +factbuss.getSelectedItem()+ "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
          if(rs.next()) {
 Tva = rs.getFloat("tva");
            }
          //JOptionPane.showMessageDialog(null, Tva);
        } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex);}

        int rowcount = tb_facture.getRowCount();
     float sum = 0,sumtva=0,sumttc=0,accomptes=0;
        for (int i = 0; i < rowcount; i++) {
           String strgqty = tb_facture.getValueAt(i, 1).toString();
           String strgpu =  tb_facture.getValueAt(i, 2).toString();
           String acc= accompte.getText();
  float qty = Float.parseFloat(strgqty);
        float pu = Float.parseFloat(strgpu);
         float b = qty * pu;
         accomptes=Float.parseFloat(acc);
String fn = nf.format(b);
    tb_facture.getModel().setValueAt(fn, i, 3);
       String replaceString = tb_facture.getValueAt(i, 3).toString().replace(",", "");
      float c= Float.parseFloat(replaceString);
           sum = (sum + c)-accomptes;
          sumtva=(sum*Tva)/100;
          sumttc=sum+sumtva;
        }
        String fnsum = nf.format(sum);
       fact_ht.setText(fnsum);
       String tvasum = nf.format(sumtva);
       fact_tva.setText(tvasum);
       String ttcsum = nf.format(sumttc);
       fact_ttc.setText(ttcsum);
      String replaceString = fact_ttc.getText().replace(",", ""); 
      String entier =StringUtils.substringBefore(replaceString, ".");
      String decima =StringUtils.substringAfter(replaceString, ".");
        Long Entier = Long.parseLong(entier);
        Long Decima = Long.parseLong(decima);
        if(fact_jComboBox2.getSelectedItem().equals("Fr")){
        lettre_receptiona.setText(""+ FrenchNumberToWords.convert(Entier)+" point "+FrenchNumberToWords.convert(Decima)); 
        }else{
        lettre_receptiona.setText(""+ EnglishNumberToWord.convert(Entier)+" dot "+EnglishNumberToWord.convert(Decima));
        } 
} 
             // TODO add your handling code here:
    }//GEN-LAST:event_tb_factureKeyReleased

    private void tb_profactureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_profactureMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_profactureMouseClicked

    private void tb_profactureMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_profactureMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_profactureMouseReleased

    private void tb_profactureKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_profactureKeyReleased
 NumberFormat nf = new DecimalFormat("#,###.00");
  float Tva=0;
   try {
            String sql = "SELECT * FROM  projet  where PROJET_NUM ='" +journal1.buss.getText()+ "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
          if(rs.next()) {
 Tva = rs.getFloat("QPP");
            }
        } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex);}

        int rowcount = tb_profacture.getRowCount();
     float sum = 0,sumtva=0,sumttc=0;
        for (int i = 0; i < rowcount; i++) {
           String strgqty = tb_profacture.getValueAt(i, 1).toString();
           String strgpu =  tb_profacture.getValueAt(i, 2).toString();
  float qty = Float.parseFloat(strgqty);
        float pu = Float.parseFloat(strgpu);
         float b = qty * pu;
String fn = nf.format(b);
    tb_profacture.getModel().setValueAt(fn, i, 3);
       String replaceString = tb_profacture.getValueAt(i, 3).toString().replace(",", "");
      float c= Float.parseFloat(replaceString);
           sum = sum + c;
          sumtva=(sum*Tva)/100;
          sumttc=sum+sumtva;
        }
        String fnsum = nf.format(sum);
       pro_ht.setText(fnsum);
       String tvasum = nf.format(sumtva);
      pro_tva.setText(tvasum);
       String ttcsum = nf.format(sumttc);
       pro_ttc.setText(ttcsum);
      String replaceString = pro_ttc.getText().replace(",", ""); 
      String entier =StringUtils.substringBefore(replaceString, ".");
      String decima =StringUtils.substringAfter(replaceString, ".");
        Long Entier = Long.parseLong(entier);
        Long Decima = Long.parseLong(decima);
        if(pro_jComboBox3.getSelectedItem().equals("Fr")){
        pro_lettre_receptiona.setText(""+ FrenchNumberToWords.convert(Entier)+" point "+FrenchNumberToWords.convert(Decima)); 
        }else{
        pro_lettre_receptiona.setText(""+ EnglishNumberToWord.convert(Entier)+" dot "+EnglishNumberToWord.convert(Decima));
        }      // TODO add your handling code here:
    }//GEN-LAST:event_tb_profactureKeyReleased

    private void tb_bon_livraisonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_bon_livraisonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_bon_livraisonMouseClicked

    private void tb_bon_livraisonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_bon_livraisonMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_bon_livraisonMouseReleased

    private void tb_bon_livraisonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_bon_livraisonKeyReleased
NumberFormat nf = new DecimalFormat("#,###.00");
  float Tva=0;
   try {
            String sql = "SELECT * FROM  projet  where PROJET_NUM ='" +journal1.buss.getText()+ "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
          if(rs.next()) {
 Tva = rs.getFloat("QPP");
            }
        } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex);}

        int rowcount = tb_bon_livraison.getRowCount();
     float sum = 0,sumtva=0,sumttc=0;
        for (int i = 0; i < rowcount; i++) {
           String strgqty = tb_bon_livraison.getValueAt(i, 1).toString();
           String strgpu =  tb_bon_livraison.getValueAt(i, 2).toString();
  float qty = Float.parseFloat(strgqty);
        float pu = Float.parseFloat(strgpu);
         float b = qty * pu;
String fn = nf.format(b);
    tb_bon_livraison.getModel().setValueAt(fn, i, 3);
       String replaceString = tb_bon_livraison.getValueAt(i, 3).toString().replace(",", "");
      float c= Float.parseFloat(replaceString);
           sum = sum + c;
          sumtva=(sum*Tva)/100;
          sumttc=sum+sumtva;
        }
        String fnsum = nf.format(sum);
       livre_ht.setText(fnsum);
       String tvasum = nf.format(sumtva);
      livre_tva.setText(tvasum);
       String ttcsum = nf.format(sumttc);
       livre_ttc.setText(ttcsum);
      String replaceString = livre_ttc.getText().replace(",", ""); 
      String entier =StringUtils.substringBefore(replaceString, ".");
      String decima =StringUtils.substringAfter(replaceString, ".");
        Long Entier = Long.parseLong(entier);
        Long Decima = Long.parseLong(decima);
        if(livre_jComboBox4.getSelectedItem().equals("Fr")){
        livre_lettre_receptiona.setText(""+ FrenchNumberToWords.convert(Entier)+" point "+FrenchNumberToWords.convert(Decima)); 
        }else{
       livre_lettre_receptiona.setText(""+ EnglishNumberToWord.convert(Entier)+" dot "+EnglishNumberToWord.convert(Decima));
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tb_bon_livraisonKeyReleased

    private void tb_bon_receptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_bon_receptionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_bon_receptionMouseClicked

    private void tb_bon_receptionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_bon_receptionMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_bon_receptionMouseReleased

    private void tb_bon_receptionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_bon_receptionKeyReleased
 NumberFormat nf = new DecimalFormat("#,###.00");
  float Tva=0;
   try {
            String sql = "SELECT * FROM  projet  where PROJET_NUM ='" +journal1.buss.getText()+ "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
          if(rs.next()) {
 Tva = rs.getFloat("QPP");
            }
        } catch (Exception ex) {JOptionPane.showMessageDialog(null, ex);}

        int rowcount = tb_bon_reception.getRowCount();
     float sum = 0,sumtva=0,sumttc=0;
        for (int i = 0; i < rowcount; i++) {
           String strgqty = tb_bon_reception.getValueAt(i, 1).toString();
           String strgpu =  tb_bon_reception.getValueAt(i, 2).toString();
  float qty = Float.parseFloat(strgqty);
        float pu = Float.parseFloat(strgpu);
         float b = qty * pu;
String fn = nf.format(b);
    tb_bon_reception.getModel().setValueAt(fn, i, 3);
       String replaceString =tb_bon_reception.getValueAt(i, 3).toString().replace(",", "");
      float c= Float.parseFloat(replaceString);
           sum = sum + c;
          sumtva=(sum*Tva)/100;
          sumttc=sum+sumtva;
        }
        String fnsum = nf.format(sum);
      rec_ht.setText(fnsum);
       String tvasum = nf.format(sumtva);
      rec_tva.setText(tvasum);
       String ttcsum = nf.format(sumttc);
      rec_ttc.setText(ttcsum);
      String replaceString = livre_ttc.getText().replace(",", ""); 
      String entier =StringUtils.substringBefore(replaceString, ".");
      String decima =StringUtils.substringAfter(replaceString, ".");
        Long Entier = Long.parseLong(entier);
        Long Decima = Long.parseLong(decima);
        if(rec_jComboBox5.getSelectedItem().equals("Fr")){
        rec_lettre_receptiona.setText(""+ FrenchNumberToWords.convert(Entier)+" point "+FrenchNumberToWords.convert(Decima)); 
        }else{
       rec_lettre_receptiona.setText(""+ EnglishNumberToWord.convert(Entier)+" dot "+EnglishNumberToWord.convert(Decima));
        }       // TODO add your handling code here:
    }//GEN-LAST:event_tb_bon_receptionKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
 /*License license = licenseKeyGUI.checkLicense();

        if (license != null) {
            if (license.getValidationStatus() == ValidationStatus.LICENSE_VALID) {
               
                if (license.isActivationRequired() && license.getLicenseActivationDaysRemaining(null) == 0) {
                    JOptionPane.showMessageDialog(null, "Your license activation period is over, activate on the next window.", "License Activation", WIDTH);

                    licenseKeyGUI.setVisible(true);
                }
            } else {
           
                JOptionPane.showMessageDialog(null, "Your license is not valid (" + license.getValidationStatus() + ")", "License Error", WIDTH);

                licenseKeyGUI.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "You should have a valid license to run this software.", "License Error", JOptionPane.ERROR_MESSAGE);

            licenseKeyGUI.setVisible(true);
        }   */     // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new print().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accompte;
    private Palette.TextField client_bon_livraison;
    private Palette.TextField client_bon_reception;
    private Palette.TextField client_facture;
    private Palette.TextField client_proformat;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel fact_ht;
    private javax.swing.JComboBox<String> fact_jComboBox2;
    private javax.swing.JLabel fact_ttc;
    private javax.swing.JLabel fact_tva;
    public static final javax.swing.JComboBox<String> factbuss = new javax.swing.JComboBox<>();
    private Palette.MyTextField factdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    public static final javax.swing.JComboBox<String> jComboBox1 = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> jComboBox6 = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> jComboBox7 = new javax.swing.JComboBox<>();
    public static final javax.swing.JComboBox<String> jComboBox9 = new javax.swing.JComboBox<>();
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable jTable13;
    private javax.swing.JTable jTable16;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel lettre_receptiona;
    private javax.swing.JLabel livre_ht;
    private javax.swing.JComboBox<String> livre_jComboBox4;
    private javax.swing.JLabel livre_lettre_receptiona;
    public static final javax.swing.JLabel livre_num = new javax.swing.JLabel();
    private javax.swing.JLabel livre_ttc;
    private javax.swing.JLabel livre_tva;
    public static final javax.swing.JLabel num = new javax.swing.JLabel();
    private javax.swing.JLabel pro_ht;
    private javax.swing.JComboBox<String> pro_jComboBox3;
    private javax.swing.JLabel pro_lettre_receptiona;
    public static final javax.swing.JLabel pro_num = new javax.swing.JLabel();
    private javax.swing.JLabel pro_ttc;
    private javax.swing.JLabel pro_tva;
    private javax.swing.JLabel rec_ht;
    private javax.swing.JComboBox<String> rec_jComboBox5;
    private javax.swing.JLabel rec_lettre_receptiona;
    public static final javax.swing.JLabel rec_num1 = new javax.swing.JLabel();
    private javax.swing.JLabel rec_ttc;
    private javax.swing.JLabel rec_tva;
    private javax.swing.JTable tb_bon_livraison;
    private javax.swing.JTable tb_bon_reception;
    private javax.swing.JTable tb_facture;
    private javax.swing.JTable tb_profacture;
    private javax.swing.JTable tbl_proforma_db;
    // End of variables declaration//GEN-END:variables
}
