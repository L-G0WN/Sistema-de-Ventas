package com.monagas.view.sales.forms;

import com.monagas.controllers.sales.CommerceController;
import com.monagas.controllers.sales.CurrencyController;
import com.monagas.view.sales.components.CustomJTextField;
import java.awt.Frame;
import javax.swing.JPanel;

public class Commerce extends JPanel {

    private final CommerceController commerceController = new CommerceController();
    private final CurrencyController currencyController = new CurrencyController();

    private final Frame parent;

    public Commerce(Frame parent) {
        this.parent = parent;
        initComponents();

        commerceController.findCommerce(txtName, txtRif, cbType);
        currencyController.findPrice(rbDolar, rbManual, txtPrice);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroups = new javax.swing.ButtonGroup();
        lbName = new javax.swing.JLabel();
        txtName = new CustomJTextField("Commerce");
        lbType = new javax.swing.JLabel();
        cbType = new javax.swing.JComboBox<>();
        lbRif = new javax.swing.JLabel();
        txtRif = new CustomJTextField("Rif");
        btnUpdate = new javax.swing.JButton();
        lbDolar = new javax.swing.JLabel();
        rbDolar = new javax.swing.JRadioButton();
        rbManual = new javax.swing.JRadioButton();
        lbPrice = new javax.swing.JLabel();
        txtPrice = new CustomJTextField("Dolar");
        btnDolar = new javax.swing.JButton();

        lbName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbName.setText("Nombre del Comercio");

        txtName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbType.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbType.setText("RIF");

        cbType.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "J-", "G-" }));

        lbRif.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbRif.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbRif.setText("Tipo");

        txtRif.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnUpdate.setBackground(new java.awt.Color(40, 188, 72));
        btnUpdate.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("ACTUALIZAR INFORMACIÓN DE COMERCIO");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        lbDolar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDolar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDolar.setText("Información sobre el Dólar");

        btnGroups.add(rbDolar);
        rbDolar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbDolar.setSelected(true);
        rbDolar.setText("Obtener el valor en tiempo real sobre el dólar (Requiere Internet)");
        rbDolar.setName("True"); // NOI18N
        rbDolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDolarActionPerformed(evt);
            }
        });

        btnGroups.add(rbManual);
        rbManual.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbManual.setText("Colocar de manera manual el valor del dólar");
        rbManual.setName("False"); // NOI18N
        rbManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbManualActionPerformed(evt);
            }
        });

        lbPrice.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPrice.setText("Precio del Dólar ");

        txtPrice.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnDolar.setBackground(new java.awt.Color(40, 188, 72));
        btnDolar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnDolar.setForeground(new java.awt.Color(255, 255, 255));
        btnDolar.setText("GUARDAR PRECIO DEL DÓLAR");
        btnDolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDolarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtName)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbRif))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbType)
                                .addComponent(txtRif))))
                    .addComponent(lbName)
                    .addComponent(lbDolar)
                    .addComponent(rbDolar)
                    .addComponent(rbManual)
                    .addComponent(lbPrice)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnDolar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                        .addComponent(txtPrice, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(415, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbName)
                .addGap(0, 0, 0)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbType)
                    .addComponent(lbRif))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRif, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbDolar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbDolar)
                .addGap(0, 0, 0)
                .addComponent(rbManual)
                .addGap(18, 18, 18)
                .addComponent(lbPrice)
                .addGap(0, 0, 0)
                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDolar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        commerceController.loadCommerce(parent, txtName, txtRif, cbType);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDolarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDolarActionPerformed
        currencyController.loadPrice(parent, rbDolar, rbManual, txtPrice);
    }//GEN-LAST:event_btnDolarActionPerformed

    private void rbDolarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDolarActionPerformed
        isSelected(false);
    }//GEN-LAST:event_rbDolarActionPerformed

    private void rbManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbManualActionPerformed
        isSelected(true);
    }//GEN-LAST:event_rbManualActionPerformed

    private void isSelected(boolean check) {
        txtPrice.setEnabled(check);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDolar;
    private javax.swing.ButtonGroup btnGroups;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbType;
    private javax.swing.JLabel lbDolar;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbRif;
    private javax.swing.JLabel lbType;
    private javax.swing.JRadioButton rbDolar;
    private javax.swing.JRadioButton rbManual;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtRif;
    // End of variables declaration//GEN-END:variables
}
