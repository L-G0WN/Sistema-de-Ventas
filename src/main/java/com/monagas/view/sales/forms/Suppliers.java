package com.monagas.view.sales.forms;

import com.monagas.controllers.sales.SupplierController;
import com.monagas.view.sales.components.CustomJPanel;
import com.monagas.view.sales.components.CustomJTable;
import com.monagas.view.sales.components.CustomJTextField;
import com.monagas.view.sales.forms.dialogs.DialogConfirm;
import com.monagas.view.sales.forms.dialogs.DialogSuppliers;
import com.monagas.view.sales.renderer.cell.PanelAction.TableActionCellEditor;
import com.monagas.view.sales.renderer.cell.PanelAction.TableActionCellRender;
import com.monagas.view.sales.renderer.cell.PanelAction.TableActionEvent;
import com.monagas.view.sales.style.FlatStyle;
import java.awt.Frame;

public class Suppliers extends CustomJPanel {

    private final SupplierController controller = new SupplierController();

    private final Frame parent;

    public Suppliers(Frame parent) {
        this.parent = parent;
        initComponents();

        FlatStyle.setStyle(spSuppliers, tblSuppliers);

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                Long id = Long.valueOf(tblSuppliers.getValueAt(row, 1).toString().substring(1));
                new DialogSuppliers(parent, true, tblSuppliers, id, false).setVisible(true);
            }

            @Override
            public void onDelete(int row) {
                Long id = Long.valueOf(tblSuppliers.getValueAt(row, 1).toString().substring(1));
                new DialogConfirm(parent, true, tblSuppliers, id, "Suppliers").setVisible(true);
            }
        };

        tblSuppliers.getColumnModel().getColumn(tblSuppliers.getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
        tblSuppliers.getColumnModel().getColumn(tblSuppliers.getColumnCount() - 1).setCellEditor(new TableActionCellEditor(event));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spSuppliers = new javax.swing.JScrollPane();
        tblSuppliers = new CustomJTable();
        txtSearch = new CustomJTextField(tblSuppliers);
        btnRegister = new javax.swing.JButton();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        tblSuppliers.setAutoCreateRowSorter(true);
        tblSuppliers.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblSuppliers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "CODIGO", "RIF", "NOMBRE", "TELÉFONO", "CORREO ELECTRÓNICO", "DIRECCIÓN", "REGISTRADO EN", "REGISTRADO POR", "ACTUALIZADO EN", "ACTUALIZADO POR", "ACCIONES"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSuppliers.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblSuppliers.setGridColor(new java.awt.Color(102, 102, 102));
        tblSuppliers.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblSuppliers.setName("Suppliers"); // NOI18N
        tblSuppliers.setRowHeight(30);
        tblSuppliers.setSelectionBackground(new java.awt.Color(230, 230, 230));
        tblSuppliers.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblSuppliers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSuppliers.setShowGrid(true);
        tblSuppliers.getTableHeader().setReorderingAllowed(false);
        spSuppliers.setViewportView(tblSuppliers);
        if (tblSuppliers.getColumnModel().getColumnCount() > 0) {
            tblSuppliers.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblSuppliers.getColumnModel().getColumn(1).setPreferredWidth(80);
        }

        txtSearch.setName("Suppliers"); // NOI18N

        btnRegister.setBackground(new java.awt.Color(40, 188, 72));
        btnRegister.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnRegister.setForeground(new java.awt.Color(255, 255, 255));
        btnRegister.setText("REGISTRAR PROVEEDOR");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spSuppliers, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(btnRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spSuppliers, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        new DialogSuppliers(parent, true, tblSuppliers, null, true).setVisible(true);
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        controller.loadSuppliers(tblSuppliers);
    }//GEN-LAST:event_formComponentShown

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegister;
    private javax.swing.JScrollPane spSuppliers;
    private javax.swing.JTable tblSuppliers;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
