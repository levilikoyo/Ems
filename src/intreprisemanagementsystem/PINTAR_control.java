/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intreprisemanagementsystem;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import static java.awt.Font.BOLD;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Dosh
 */
public class PINTAR_control extends DefaultTableCellRenderer {
    
    public PINTAR_control(){}
//@Override
public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,
        int row,int column){
JLabel laber=(JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      
Color background = Color.WHITE;
//Object objeto=table.getValueAt(row,2);
Object objeto2=table.getValueAt(row,2);
//Approvisionement Caisse
try{
    // double nota = Double.parseDouble(objeto.toString());
     // double nota2 = Double.parseDouble(objeto2.toString());
   //  nota=0.00;
     if(objeto2.equals("Achat  Ordinateur")){
     background=Color.RED;
     laber.setBackground(background);
laber.setForeground(Color.WHITE);

//     
//     }else if (objeto.equals("0.00")){
//     background=Color.RED;
//     laber.setBackground(background);
//laber.setForeground(Color.WHITE);

     } else {
    // background=Color.RED;
     laber.setBackground(background);
laber.setForeground(Color.BLACK);
     }
      
      }catch(Exception ex){
      JOptionPane.showMessageDialog(null,ex);
      }
 laber.setBackground(background);
return laber;


}   
    
}
