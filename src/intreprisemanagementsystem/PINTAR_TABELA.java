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
public class PINTAR_TABELA extends DefaultTableCellRenderer {
    
    public PINTAR_TABELA(){}
//@Override
    @Override
    public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,
        int row,int column){
JLabel laber=(JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      
Color background = Color.WHITE;
Object objeto=table.getValueAt(row,5);
Object objeto2=table.getValueAt(row,4);
//Approvisionement Caisse
try{
     double nota = Double.parseDouble(objeto.toString());
      double nota2 = Double.parseDouble(objeto2.toString());
   //  nota=0.00;
     if(nota>0){
     background=Color.BLUE;
     laber.setBackground(background);
laber.setForeground(Color.WHITE);
//laber.setFont(font);
     }
      else if(nota2>0){
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
