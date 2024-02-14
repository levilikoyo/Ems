/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canalplus.cell;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Doshe PC
 */
public class TableActionCellRender extends DefaultTableCellRenderer{
  @Override
  public Component getTableCellRendererComponent(JTable jtable,Object O, boolean isSeleted, boolean blnl,int row ,int column){
Component com= super.getTableCellRendererComponent(jtable, O, isSeleted, blnl, row, column);
panelAction action = new panelAction();
if(isSeleted == false && row % 2 == 0){
action.setBackground(Color.WHITE);
}else{
action.setBackground(com.getBackground());
}

return action;

}
    
    
}
