package com.monagas.view.sales.forms;

import com.monagas.controllers.sales.ProductController;
import com.monagas.view.sales.components.CustomJPanel;
import com.monagas.view.sales.components.CustomJTable;
import com.monagas.view.sales.components.CustomJTextField;
import com.monagas.view.sales.forms.dialogs.DialogCategory;
import com.monagas.view.sales.forms.dialogs.DialogConfirm;
import com.monagas.view.sales.forms.dialogs.DialogProducts;
import com.monagas.view.sales.renderer.cell.TableActionCellEditor;
import com.monagas.view.sales.renderer.cell.TableActionCellRender;
import com.monagas.view.sales.renderer.cell.TableActionEvent;
import com.monagas.view.sales.style.FlatStyle;
import java.awt.Frame;

public class Products extends CustomJPanel {

    private final ProductController controller = new ProductController();

    private final Frame parent;

    public Products(Frame parent) {
        this.parent = parent;
        initComponents();

        FlatStyle.setStyle(spProducts, tblProducts);

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                Long id = Long.valueOf(tblProducts.getValueAt(row, 1).toString().substring(2));
                new DialogProducts(parent, true, tblProducts, id, false).setVisible(true);

            }

            @Override
            public void onDelete(int row) {
                Long id = Long.valueOf(tblProducts.getValueAt(row, 1).toString().substring(2));
                new DialogConfirm(parent, true, tblProducts, id, "Products").setVisible(true);
            }
        };

        tblProducts.getColumnModel().getColumn(tblProducts.getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
        tblProducts.getColumnModel().getColumn(tblProducts.getColumnCount() - 1).setCellEditor(new TableActionCellEditor(event));

        controller.loadProducts(tblProducts);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spProducts = new javax.swing.JScrollPane();
        tblProducts = new CustomJTable();
        txtSearch = new CustomJTextField(tblProducts);
        btnProduct = new javax.swing.JButton();
        btnCategory = new javax.swing.JButton();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        tblProducts.setAutoCreateRowSorter(true);
        tblProducts.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "CODIGO", "DESCRIPCIÓN", "P.C.", "P.V.", "STOCK", "CATEGORÍA", "PROVEEDOR", "REGISTRADO EN", "REGISTRADO POR", "ACTUALIZADO EN", "ACTUALIZADO POR", "ACCIONES"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Long.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProducts.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblProducts.setGridColor(new java.awt.Color(102, 102, 102));
        tblProducts.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblProducts.setName("Products"); // NOI18N
        tblProducts.setRowHeight(30);
        tblProducts.setSelectionBackground(new java.awt.Color(230, 230, 230));
        tblProducts.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblProducts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblProducts.setShowGrid(true);
        tblProducts.getTableHeader().setReorderingAllowed(false);
        spProducts.setViewportView(tblProducts);
        if (tblProducts.getColumnModel().getColumnCount() > 0) {
            tblProducts.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblProducts.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblProducts.getColumnModel().getColumn(3).setPreferredWidth(60);
            tblProducts.getColumnModel().getColumn(4).setPreferredWidth(60);
            tblProducts.getColumnModel().getColumn(5).setPreferredWidth(60);
        }

        txtSearch.setName("Products"); // NOI18N

        btnProduct.setBackground(new java.awt.Color(40, 188, 72));
        btnProduct.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnProduct.setForeground(new java.awt.Color(255, 255, 255));
        btnProduct.setText("REGISTRAR PRODUCTO");
        btnProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductActionPerformed(evt);
            }
        });

        btnCategory.setBackground(new java.awt.Color(40, 188, 72));
        btnCategory.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCategory.setForeground(new java.awt.Color(255, 255, 255));
        btnCategory.setText("CATEGORÍAS");
        btnCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spProducts))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(btnCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spProducts, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductActionPerformed
        new DialogProducts(parent, true, tblProducts, null, true).setVisible(true);
    }//GEN-LAST:event_btnProductActionPerformed

    private void btnCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoryActionPerformed
        new DialogCategory(parent, true).setVisible(true);
    }//GEN-LAST:event_btnCategoryActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        controller.loadProducts(tblProducts);
    }//GEN-LAST:event_formComponentShown

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCategory;
    private javax.swing.JButton btnProduct;
    private javax.swing.JScrollPane spProducts;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
