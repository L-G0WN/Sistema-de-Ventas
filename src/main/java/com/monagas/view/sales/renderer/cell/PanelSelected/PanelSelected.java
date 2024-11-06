package com.monagas.view.sales.renderer.cell.PanelSelected;

import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class PanelSelected extends JPanel {

    public PanelSelected() {
        initComponents();
    }

    public void initEvent(TableSelectedEvent event, int row) {
        btnSelected.addActionListener((ActionEvent ae) -> {
            event.onSelected(row);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSelected = new javax.swing.JButton();

        btnSelected.setBackground(new java.awt.Color(40, 188, 72));
        btnSelected.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnSelected.setForeground(new java.awt.Color(255, 255, 255));
        btnSelected.setText("AÑADIR PRODUCTO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSelected)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(btnSelected))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelected;
    // End of variables declaration//GEN-END:variables
}
