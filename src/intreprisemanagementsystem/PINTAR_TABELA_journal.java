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
public class PINTAR_TABELA_journal extends DefaultTableCellRenderer {
    
    public PINTAR_TABELA_journal(){}
//@Override
    @Override
    public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,
        int row,int column){
JLabel laber;
        laber = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      
Color background = Color.WHITE;
Object objeto=table.getValueAt(row,5);
Object objeto2=table.getValueAt(row,4);
//Object objeto3=table.getValueAt(row,1);
//Approvisionement Caisse
try{
     // nota = Double.parseDouble(objeto.toString());
     // double nota2 = Double.parseDouble(objeto2.toString());
   //  nota=0.00;
     if(objeto2.equals("0.00")){
     background=Color.blue;
     laber.setBackground(background);
laber.setForeground(Color.WHITE);
//laber.setFont(font);
     }
      else if(objeto.equals("0.00")){
   //  laber.setBackground(background);
laber.setForeground(Color.BLACK);
//laber.setBackground(background);
     }
      
      }catch(Exception ex){
      JOptionPane.showMessageDialog(null,ex);
      }
 laber.setBackground(background);
return laber;


}   
    
}
