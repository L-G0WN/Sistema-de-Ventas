package com.monagas.view.sales.forms.dialogs;

import com.monagas.controllers.sales.BrandController;
import com.monagas.controllers.sales.CategoryController;
import com.monagas.view.sales.components.CustomJTextField;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DialogSubProducts extends JDialog {

    private final BrandController brandController = new BrandController();
    private final CategoryController categoryController = new CategoryController();

    private final Frame parent;
    private final JTable table;
    private final Long id;
    private final boolean isRegister;
    private final boolean isCategory;

    public DialogSubProducts(Frame parent, boolean modal, JTable table, Long id, boolean isRegister, boolean isCategory) {
        super(parent, modal);
        this.parent = parent;
        this.table = table;
        this.id = id;
        this.isRegister = isRegister;
        this.isCategory = isCategory;
        initComponents();

        if (isRegister) {
            btnGeneral.setBackground(new Color(40, 188, 72));
            btnGeneral.setText("REGISTRAR");

            if (isCategory) {
                lbTitle.setText("Registrar Categoría");
            } else {
                lbTitle.setText("Registrar Marca");
            }
        } else {
            btnGeneral.setBackground(new java.awt.Color(232, 213, 42));
            btnGeneral.setText("ACTUALIZAR");

            if (isCategory) {
                categoryController.loadCategoryById(id, txtName);
                lbTitle.setText("Categoría : " + txtName.getText());
            } else {
                brandController.loadBrandById(id, txtName);
                lbTitle.setText("Marca : " + txtName.getText());
            }
        }

        eventField(txtName);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGeneral = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        txtName = new CustomJTextField("Firstname");
        btnCancel = new javax.swing.JButton();
        btnGeneral = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Categorías/Marcas");
        setUndecorated(true);

        panelGeneral.setBackground(new java.awt.Color(39, 92, 183));

        lbTitle.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(255, 255, 255));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText(":");

        txtName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

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
                .addContainerGap()
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGeneralLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addComponent(btnGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
        );
        panelGeneralLayout.setVerticalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle)
                .addGap(28, 28, 28)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(btnGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(panelGeneral, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneralActionPerformed
        if (isRegister) {
            if (isCategory) {
                categoryController.createCategory(parent, this, table, txtName);
            } else {
                brandController.createBrand(parent, this, table, txtName);
            }
        } else {
            if (isCategory) {
                categoryController.editCategory(parent, this, table, id, txtName);
            } else {
                brandController.editBrand(parent, this, table, id, txtName);
            }
        }
    }//GEN-LAST:event_btnGeneralActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void eventField(JTextField txtName) {
        txtName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnGeneral.doClick();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnGeneral;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
