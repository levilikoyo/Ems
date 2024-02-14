/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canalplus.cell;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author Doshe PC
 */
public class TableActionCellEditor_print extends DefaultCellEditor{
private TableActionEvent_print event;

    public TableActionCellEditor_print(TableActionEvent_print event) {
        super(new JCheckBox());
        this.event=event;
    }

    
     @Override
  public Component getTableCellEditorComponent(JTable jtable,Object o, boolean bln, int row ,int column){
panelAction_print action = new panelAction_print();
action.initEvent(event, row);
action.setBackground(jtable.getSelectionBackground());
return action;
    
}
}
