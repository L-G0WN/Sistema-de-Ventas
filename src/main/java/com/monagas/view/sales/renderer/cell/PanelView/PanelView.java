package com.monagas.view.sales.renderer.cell.PanelView;

import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class PanelView extends JPanel {

    public PanelView() {
        initComponents();
    }

    public void initEvent(TableViewEvent event, int row) {
        btnInvoice.addActionListener((ActionEvent ae) -> {
            event.onInvoice(row);
        });

        btnView.addActionListener((ActionEvent ae) -> {
            event.onView(row);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnInvoice = new javax.swing.JButton();
        btnView = new javax.swing.JButton();

        btnInvoice.setBackground(new java.awt.Color(40, 188, 72));
        btnInvoice.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnInvoice.setForeground(new java.awt.Color(255, 255, 255));
        btnInvoice.setText("FACTURA");

        btnView.setBackground(new java.awt.Color(232, 213, 42));
        btnView.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnView.setForeground(new java.awt.Color(255, 255, 255));
        btnView.setText("VER PRODUCTOS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnInvoice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnView)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnView)
                    .addComponent(btnInvoice)))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInvoice;
    private javax.swing.JButton btnView;
    // End of variables declaration//GEN-END:variables
}
