package com.monagas.view.sales.forms.dialogs;

import com.monagas.controllers.sales.CategoryController;
import com.monagas.controllers.sales.ProductController;
import com.monagas.controllers.sales.SupplierController;
import com.monagas.view.sales.components.CustomJTextField;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DialogProducts extends JDialog {

    private final ProductController productController = new ProductController();
    private final CategoryController categoryController = new CategoryController();
    private final SupplierController supplierController  = new SupplierController();

    private final Frame parent;
    private final JTable table;
    private final Long id;
    private final boolean isRegister;

    public DialogProducts(Frame parent, boolean modal, JTable table, Long id, boolean isRegister) {
        super(parent, modal);
        this.parent = parent;
        this.table = table;
        this.id = id;
        this.isRegister = isRegister;
        initComponents();

        categoryController.loadCategories(cbCategories);
        supplierController.loadSuppliers(cbSuppliers);

        if (isRegister) {
            btnGeneral.setBackground(new Color(40, 188, 72));
            btnGeneral.setText("REGISTRAR");

            lbTitle.setText("Registrar Producto");
        } else {
            btnGeneral.setBackground(new java.awt.Color(232, 213, 42));
            btnGeneral.setText("ACTUALIZAR");

            productController.loadProductById(id, txtDescription, txtPrice, txtPurchase, txtAmount, cbCategories, cbSuppliers);
            lbTitle.setText("Producto : " + txtDescription.getText());
        }

        JTextField[] textFields = {txtDescription, txtPrice, txtPurchase, txtAmount};
        eventField(textFields);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGeneral = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        txtDescription = new CustomJTextField("Description");
        txtPrice = new CustomJTextField("Price");
        txtPurchase = new CustomJTextField("Purchase");
        txtAmount = new CustomJTextField("Amount");
        cbCategories = new javax.swing.JComboBox<>();
        cbSuppliers = new javax.swing.JComboBox<>();
        btnCancel = new javax.swing.JButton();
        btnGeneral = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Productos");
        setUndecorated(true);

        panelGeneral.setBackground(new java.awt.Color(39, 92, 183));

        lbTitle.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(255, 255, 255));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Producto : ");

        txtDescription.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtPrice.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtPurchase.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtAmount.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        cbCategories.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleccionar Categoría (Opcional)" }));

        cbSuppliers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleccionar Proveedor" }));

        btnCancel.setBackground(new java.awt.Color(185, 39, 39));
        btnCancel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("CANCELAR");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnGeneral.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnGeneral.setForeground(new java.awt.Color(255, 255, 255));
        btnGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneralActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelGeneralLayout = new javax.swing.GroupLayout(panelGeneral);
        panelGeneral.setLayout(panelGeneralLayout);
        panelGeneralLayout.setHorizontalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelGeneralLayout.createSequentialGroup()
                                .addComponent(btnGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtDescription, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbCategories, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelGeneralLayout.createSequentialGroup()
                                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtAmount, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbSuppliers, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelGeneralLayout.setVerticalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbTitle)
                .addGap(18, 18, 18)
                .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(15, 15, 15))
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

    private void btnGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneralActionPerformed
        if (isRegister) {
            productController.createProduct(parent, this, table, txtDescription, txtPrice, txtPurchase, txtAmount, cbCategories, cbSuppliers);
        } else {
            productController.editProduct(parent, this, table, id, txtDescription, txtPrice, txtPurchase, txtAmount, cbCategories, cbSuppliers);
        }
    }//GEN-LAST:event_btnGeneralActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void eventField(JTextField[] textFields) {
        for (JTextField textField : textFields) {
            textField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        btnGeneral.doClick();
                    }
                }
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnGeneral;
    private javax.swing.JComboBox<String> cbCategories;
    private javax.swing.JComboBox<String> cbSuppliers;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtPurchase;
    // End of variables declaration//GEN-END:variables
}
