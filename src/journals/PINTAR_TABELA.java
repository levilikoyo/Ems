/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package journals;

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
public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,
        int row,int column){
JLabel laber=(JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      
Color background = Color.WHITE;
Object credit=table.getValueAt(row,6);
Object debit=table.getValueAt(row,5);
Object lb=table.getValueAt(row,2);
Object codes=table.getValueAt(row,3);
String s =codes.toString();
String codet=null;
String code=s.substring(0,1);

if(codes.equals("-")){
codet=s.substring(0,1);
}else{
codet=s.substring(0,2);
}



  
try{
    // CLIENT
   if(journal1.classe1.getText().equals("4") && !journal1.substr1.getText().equals("40") ){
       if(credit.equals("0.00") && codes.equals(journal1.code1.getText())){
     background=Color.YELLOW;
     laber.setBackground(background);
laber.setForeground(Color.BLACK);
//laber.setFont(font);
     }
      else {
    // background=Color.RED;
     laber.setBackground(background);
laber.setForeground(Color.BLACK);
     }
//            if(debit.equals("0.00") && codes.equals(journal.code1.getText())){
//     background=Color.YELLOW;
//     laber.setBackground(background);
//laber.setForeground(Color.BLACK);
////laber.setFont(font);
//     }
//      else {
//    // background=Color.RED;
//     laber.setBackground(background);
//laber.setForeground(Color.BLACK);
//     }
    // FOURNISSEUR 
   }else if(journal1.classe1.getText().equals("4") && journal1.substr1.getText().equals("40") ){
          if(debit.equals("0.00") && codes.equals(journal1.code1.getText()) && lb.equals("-")){
     background=Color.YELLOW;
     laber.setBackground(background);
laber.setForeground(Color.BLACK);
//laber.setFont(font);
     }
      else {
    // background=Color.RED;
     laber.setBackground(background);
laber.setForeground(Color.BLACK);
    }
//            if(debit.equals("0.00") && codes.equals(journal.code1.getText())){
//     background=Color.YELLOW;
//     laber.setBackground(background);
//laber.setForeground(Color.BLACK);
////laber.setFont(font);
//     }
//      else {
//    // background=Color.RED;
//     laber.setBackground(background);
//laber.setForeground(Color.BLACK);
//     }
   
   }else if(journal1.classe1.getText().equals("5") ){
     if(debit.equals("0.00") && lb.equals("-")){
     background=Color.YELLOW;
     laber.setBackground(background);
laber.setForeground(Color.WHITE);
//laber.setFont(font);
   }else {
    // background=Color.RED;
     laber.setBackground(background);
laber.setForeground(Color.BLACK);
     }
   if(credit.equals("0.00") && debit.equals("0.00")){
     background=Color.RED;
     laber.setBackground(background);
laber.setForeground(Color.WHITE);
//laber.setFont(font);
   }else {
    // background=Color.RED;
     laber.setBackground(background);
laber.setForeground(Color.BLACK);
     }
   
    if(lb.equals("-") && code.equals("6")  && credit.equals("0.00")){
     background=Color.RED;
     laber.setBackground(background);
laber.setForeground(Color.WHITE);
//laber.setFont(font);
   }else {
    // background=Color.RED;
     laber.setBackground(background);
laber.setForeground(Color.BLACK);
     }
      if(lb.equals("-") && codet.equals("40") && credit.equals("0.00")){
     background=Color.RED;
     laber.setBackground(background);
laber.setForeground(Color.WHITE);
//laber.setFont(font);
   }else {
    // background=Color.RED;
     laber.setBackground(background);
laber.setForeground(Color.BLACK);
     }
        if(lb.equals("-") && codet.equals("40") && debit.equals("0.00")){
     background=Color.RED;
     laber.setBackground(background);
laber.setForeground(Color.WHITE);
//laber.setFont(font);
   }else {
    // background=Color.RED;
     laber.setBackground(background);
laber.setForeground(Color.BLACK);
     }
//            if(codet.equals("40") && debit.equals("0.00")){
//     background=Color.RED;
//     laber.setBackground(background);
//laber.setForeground(Color.WHITE);
////laber.setFont(font);
//   }else {
//    // background=Color.RED;
//     laber.setBackground(background);
//laber.setForeground(Color.BLACK);
//     }
//             if(codet.equals("40") && credit.equals("0.00")){
//     background=Color.RED;
//     laber.setBackground(background);
//laber.setForeground(Color.WHITE);
////laber.setFont(font);
//   }else {
//    // background=Color.RED;
//     laber.setBackground(background);
//laber.setForeground(Color.BLACK);
//     }
   }
 
      
      }catch(Exception ex){
      JOptionPane.showMessageDialog(null,ex);
      }
 laber.setBackground(background);
return laber;


}   
    
}
