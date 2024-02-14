/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;

import com.alee.laf.WebLookAndFeel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import menu.MenuItem;

/**
 *
 * @author DOSHE
 */
public class programme extends javax.swing.JFrame {

    /**
     * Creates new form home
     */
    public programme() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
       execute();
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icons8_Raspberry_Pi_48px_1.png")));
    
 // nname.setText("Levi Likoyo Mbalaninya");
   // ndep.setText("La Gynechologie");

     // post.setText("Infirmier Chef");

    }
    private void execute(){
        ImageIcon iconuser= new ImageIcon(getClass().getResource("/intreprisemanagementsystem/pop.png"));
        ImageIcon icondoctor= new ImageIcon(getClass().getResource("/intreprisemanagementsystem/objectif.png"));
         ImageIcon iconstaff= new ImageIcon(getClass().getResource("/intreprisemanagementsystem/staff.png"));
          ImageIcon iconnurse= new ImageIcon(getClass().getResource("/intreprisemanagementsystem/follow.png"));
           ImageIcon iconsubmenu= new ImageIcon(getClass().getResource("/intreprisemanagementsystem/submenu.png"));
           ImageIcon iconbossnurse= new ImageIcon(getClass().getResource("/intreprisemanagementsystem/nurs_boss.png"));
           
           ImageIcon iconvisite= new ImageIcon(getClass().getResource("/intreprisemanagementsystem/visit.png"));
           ImageIcon iconpatient= new ImageIcon(getClass().getResource("/intreprisemanagementsystem/Plannification.png"));
           
           ImageIcon icondatabase= new ImageIcon(getClass().getResource("/intreprisemanagementsystem/database.png"));
           ImageIcon iconsettings= new ImageIcon(getClass().getResource("/intreprisemanagementsystem/setting.png"));
           
        
         
           //submenu
           //--->>LIT
            MenuItem Objectifs= new MenuItem(iconsubmenu,"Les Objectifs stratégiques",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
body.add(new programme1());
body.repaint();
body.revalidate();
            }
        });  
            
          //    /* 
            
                  MenuItem timing= new MenuItem(iconsubmenu,"Les Timing",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
body.add(new Programme_timing());
body.repaint();
body.revalidate();
            }
        }); 
     MenuItem nenustaff1= new MenuItem(iconsubmenu,"Les Resultat",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
body.add(new programme_Resultat());
body.repaint();
body.revalidate();
            }
        });    
    MenuItem lit = new MenuItem(iconuser ,"P.O.Projet/de la Structure",null,Objectifs,nenustaff1,timing);
    
    //INDICATEUR
        MenuItem ens_indis= new MenuItem(iconsubmenu,"Les Indicateurs",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
body.add(new Programme_iindicateur());
body.repaint();
body.revalidate();
            }
        });
            MenuItem Indis= new MenuItem(iconsubmenu,"La Quantification",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
body.add(new Programme_iindicateur_quatification());
body.repaint();
body.revalidate();
            }
        });
             MenuItem quali= new MenuItem(iconsubmenu,"La Qualitative",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
body.add(new Programme_iindicateur_quatification());
body.repaint();
body.revalidate();
            }
        });
              MenuItem Timing= new MenuItem(iconsubmenu,"Le Timing",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
body.add(new Programme_iindicateur_quatification());
body.repaint();
body.revalidate();
            }
        });
              MenuItem INDS = new MenuItem(iconuser ,"Les Indicateurs",null,ens_indis,Indis,quali,Timing);
              
               //TIMING
        MenuItem year= new MenuItem(iconsubmenu,"Années",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
body.add(new Programme_iindicateur());
body.repaint();
body.revalidate();
            }
        });
            MenuItem timings= new MenuItem(iconsubmenu,"Timing",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
body.add(new Programme_iindicateur());
body.repaint();
body.revalidate();
            }
        });
                MenuItem sub_timings= new MenuItem(iconsubmenu,"Sub_Timing",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
body.add(new Programme_iindicateur());
body.repaint();
body.revalidate();
            }
        });
              MenuItem TIMING= new MenuItem(iconuser ,"Timings",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
body.add(new Program_timing());
body.repaint();
body.revalidate();
            }
        });  
     //--->NURSING
    MenuItem sa= new MenuItem(iconsubmenu,"Planification SA",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
body.add(new Programme_SA());
body.repaint();
body.revalidate();
            }
        });      
     MenuItem daily= new MenuItem(iconsubmenu,"Planification SA",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
body.add(new Programme_Plannification());
body.repaint();
body.revalidate();
            }
        });    
      MenuItem patient = new MenuItem(iconpatient ,"Planification",null,daily);
   //--->NURSING
    MenuItem nurse1= new MenuItem(iconsubmenu,"Indicators Following UP",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
body.add(new Programme_following_timing());
body.repaint();
body.revalidate();
            }
        });    
     MenuItem time_foll= new MenuItem(iconsubmenu,"Activities Following UP",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
body.add(new Prgramm_task());
body.repaint();
body.revalidate();
            }
        });   
        MenuItem lb= new MenuItem(iconsubmenu,"LB Following UP",new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//body.add(new Prgramm_task());
//body.repaint();
//body.revalidate();
            }
        }); 
    //
      MenuItem nurse = new MenuItem(iconnurse,"Following UP",null,nurse1,time_foll,lb);
      
        
  //--->NURSING
    MenuItem recherche= new MenuItem(iconsubmenu,"Allocation et Ajustage Budget",null); 
    MenuItem liste1= new MenuItem(iconsubmenu,"Planification Budgetaire",null); 
     MenuItem liste= new MenuItem(iconsubmenu,"Suivis Budgetaire",null);  
      MenuItem visite = new MenuItem(iconvisite ,"LB",null,liste1,recherche,liste);
      
    
    
     addMenu(TIMING,lit,INDS,patient,nurse);
 //   */
    }
    
    private void addMenu(MenuItem...menu){
    for(int i=0;i<menu.length;i++){
    menus.add(menu[i]);
    
    ArrayList<MenuItem>submenu=menu[i].getSubMenu();
    for(MenuItem m:submenu){
    addMenu(m);
    }
    }
   menus.revalidate();
    }

    
     //String imgPath= null;
   
           
    //BROUSWER
    // String filename=null;
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelmenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelmenu.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        menus.setBackground(new java.awt.Color(255, 255, 255));
        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(menus);

        javax.swing.GroupLayout panelmenuLayout = new javax.swing.GroupLayout(panelmenu);
        panelmenu.setLayout(panelmenuLayout);
        panelmenuLayout.setHorizontalGroup(
            panelmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );
        panelmenuLayout.setVerticalGroup(
            panelmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
        );

        getContentPane().add(panelmenu, java.awt.BorderLayout.LINE_START);

        jPanel2.setPreferredSize(new java.awt.Dimension(895, 50));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("PROGRAM");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 914, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        body.setBackground(new java.awt.Color(255, 255, 255));
        body.setLayout(new javax.swing.BoxLayout(body, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane2.setViewportView(body);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1162, 657));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
      // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(programme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(programme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(programme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(programme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                  WebLookAndFeel.install(true);
                new programme().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel panelmenu;
    // End of variables declaration//GEN-END:variables
}
