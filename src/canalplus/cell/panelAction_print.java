/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canalplus.cell;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Doshe PC
 */
public class panelAction_print extends javax.swing.JPanel {

    /**
     * Creates new form panelAction
     */
    public panelAction_print() {
        initComponents();
    }
public void initEvent(TableActionEvent_print event, int row){




cmddelet.addActionListener(new ActionListener(){

@Override
public void actionPerformed(ActionEvent ae){
event.ondelete(row);

}
});


}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmddelet = new canalplus.cell.ActionButton();

        cmddelet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/journals/icons8_Print_24px.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmddelet, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmddelet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private canalplus.cell.ActionButton cmddelet;
    // End of variables declaration//GEN-END:variables
}