package com.monagas.view.sales.forms.dialogs;

import com.monagas.controllers.sales.SellingController;
import java.awt.Frame;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DialogMethod extends JDialog {

    private final SellingController controller = new SellingController();

    private final Frame parent;
    private final JTable table;
    private final Long oldInvoiceId;
    private final JComboBox cbType;
    private final JTextField txtCedula;
    private final JTextField txtFirstname;
    private final JTextField txtLastname;
    private final JComboBox cbCode;
    private final JTextField txtPhone;
    private final JTextField txtState;
    private final JTextField txtCity;
    private final JTextField txtTown;
    private final JTextField txtParish;
    private final JTextField txtDetails;
    private final Integer amountTotal;
    private final Double total;
    private final Double totalBs;
    private final JButton btnClear;
    private final JLabel lbTotal;
    private final JLabel lbBs;
    private final JLabel lbTotalProducts;
    private final boolean isReturn;
    private final JDialog dialog;
    private final JTable tblHistory;
    
    public DialogMethod(Frame parent, boolean modal,
            JTable table,
            Long oldInvoiceId,
            JComboBox cbType,
            JTextField txtCedula,
            JTextField txtFirstname,
            JTextField txtLastname,
            JComboBox cbCode,
            JTextField txtPhone,
            JTextField txtState,
            JTextField txtCity,
            JTextField txtTown,
            JTextField txtParish,
            JTextField txtDetails,
            Integer amountTotal,
            Double total,
            Double totalBs,
            JButton btnClear,
            JLabel lbTotal, JLabel lbBs, JLabel lbTotalProducts,
            boolean isReturn,
            JDialog dialog,
            JTable tblHistory) {
        super(parent, modal);
        this.parent = parent;
        this.table = table;
        this.cbType = cbType;
        this.txtCedula = txtCedula;
        this.txtFirstname = txtFirstname;
        this.txtLastname = txtLastname;
        this.cbCode = cbCode;
        this.txtPhone = txtPhone;
        this.txtState = txtState;
        this.txtCity = txtCity;
        this.txtTown = txtTown;
        this.txtParish = txtParish;
        this.txtDetails = txtDetails;
        this.amountTotal = amountTotal;
        this.total = total;
        this.totalBs = totalBs;
        this.btnClear = btnClear;
        this.lbTotal = lbTotal;
        this.lbBs = lbBs;
        this.lbTotalProducts = lbTotalProducts;
        this.isReturn = isReturn;
        this.oldInvoiceId = oldInvoiceId;
        this.dialog = dialog;
        this.tblHistory = tblHistory;
        
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroups = new javax.swing.ButtonGroup();
        panelGeneral = new javax.swing.JPanel();
        btnSell = new javax.swing.JButton();
        jcSell = new javax.swing.JRadioButton();
        jcPM = new javax.swing.JRadioButton();
        jcEfectivo = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Generar Venta");
        setResizable(false);

        btnSell.setBackground(new java.awt.Color(39, 92, 183));
        btnSell.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnSell.setForeground(new java.awt.Color(255, 255, 255));
        btnSell.setText("FINALIZAR VENTA");
        btnSell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSellActionPerformed(evt);
            }
        });

        btnGroups.add(jcSell);
        jcSell.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jcSell.setText("Punto de Venta");

        btnGroups.add(jcPM);
        jcPM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jcPM.setText("Pago Movil");

        btnGroups.add(jcEfectivo);
        jcEfectivo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jcEfectivo.setSelected(true);
        jcEfectivo.setText("Efectivo / Divisa");

        javax.swing.GroupLayout panelGeneralLayout = new javax.swing.GroupLayout(panelGeneral);
        panelGeneral.setLayout(panelGeneralLayout);
        panelGeneralLayout.setHorizontalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcEfectivo)
                            .addComponent(jcPM)
                            .addComponent(jcSell))
                        .addGap(268, 268, 268)))
                .addContainerGap())
        );
        panelGeneralLayout.setVerticalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcEfectivo)
                .addGap(18, 18, 18)
                .addComponent(jcPM)
                .addGap(18, 18, 18)
                .addComponent(jcSell)
                .addGap(18, 18, 18)
                .addComponent(btnSell, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(187, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSellActionPerformed
        if (btnGroups.getSelection() != null) {
            for (Enumeration<AbstractButton> buttons = btnGroups.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();

                if (button.isSelected()) {
                    controller.createSelling(parent, dialog, table, oldInvoiceId, cbType, txtCedula, txtFirstname, txtLastname, cbCode, txtPhone, txtState, txtCity, txtTown, txtParish, txtDetails, amountTotal, total, totalBs, btnClear, button.getText(), isReturn, tblHistory);
                }
            }

            lbTotal.setText("Monto : " + 0 + "$");
            lbBs.setText("Monto en Bs. : " + 0 + " Bs.");
            lbTotalProducts.setText("Cantidad de Productos : " + 0);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(parent,
                    "Por favor, Seleccione algún metodo para finalizar la venta.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSellActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroups;
    private javax.swing.JButton btnSell;
    private javax.swing.JRadioButton jcEfectivo;
    private javax.swing.JRadioButton jcPM;
    private javax.swing.JRadioButton jcSell;
    private javax.swing.JPanel panelGeneral;
    // End of variables declaration//GEN-END:variables
}
