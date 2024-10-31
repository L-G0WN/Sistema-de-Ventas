package com.monagas.view.sales.forms.dialogs;

import com.monagas.controllers.sales.CategoryController;
import com.monagas.view.sales.components.CustomJTable;
import com.monagas.view.sales.renderer.cell.TableActionCellEditor;
import com.monagas.view.sales.renderer.cell.TableActionCellRender;
import com.monagas.view.sales.renderer.cell.TableActionEvent;
import com.monagas.view.sales.style.FlatStyle;
import java.awt.Frame;

public class DialogCategory extends javax.swing.JDialog {

    private final CategoryController categoryController = new CategoryController();

    private final Frame parent;

    public DialogCategory(Frame parent, boolean modal) {
        super(parent, modal);
        this.parent = parent;
        initComponents();

        FlatStyle.setStyle(spCategories, tblCategories);

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                Long id = Long.valueOf(tblCategories.getValueAt(row, 1).toString().substring(2));
                new DialogSubCategory(parent, true, tblCategories, id, false).setVisible(true);
            }

            @Override
            public void onDelete(int row) {
                Long id = Long.valueOf(tblCategories.getValueAt(row, 1).toString().substring(2));
                new DialogConfirm(parent, true, tblCategories, id, "Categories").setVisible(true);
            }
        };

        tblCategories.getColumnModel().getColumn(tblCategories.getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
        tblCategories.getColumnModel().getColumn(tblCategories.getColumnCount() - 1).setCellEditor(new TableActionCellEditor(event));

        categoryController.loadCategories(tblCategories);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCategory = new javax.swing.JPanel();
        btnCategory = new javax.swing.JButton();
        spCategories = new javax.swing.JScrollPane();
        tblCategories = new CustomJTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Categorías");
        setResizable(false);

        btnCategory.setBackground(new java.awt.Color(40, 188, 72));
        btnCategory.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCategory.setForeground(new java.awt.Color(255, 255, 255));
        btnCategory.setText("REGISTRAR CATEGORÍA");
        btnCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoryActionPerformed(evt);
            }
        });

        tblCategories.setAutoCreateRowSorter(true);
        tblCategories.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblCategories.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "CODIGO", "NOMBRE", "ACCIONES"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Long.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCategories.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblCategories.setGridColor(new java.awt.Color(102, 102, 102));
        tblCategories.setName("Categories"); // NOI18N
        tblCategories.setRowHeight(30);
        tblCategories.setRowMargin(1);
        tblCategories.setSelectionBackground(new java.awt.Color(230, 230, 230));
        tblCategories.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblCategories.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCategories.setShowGrid(true);
        tblCategories.getTableHeader().setReorderingAllowed(false);
        spCategories.setViewportView(tblCategories);
        if (tblCategories.getColumnModel().getColumnCount() > 0) {
            tblCategories.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblCategories.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblCategories.getColumnModel().getColumn(2).setPreferredWidth(185);
        }

        javax.swing.GroupLayout panelCategoryLayout = new javax.swing.GroupLayout(panelCategory);
        panelCategory.setLayout(panelCategoryLayout);
        panelCategoryLayout.setHorizontalGroup(
            panelCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCategoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spCategories, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                    .addComponent(btnCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelCategoryLayout.setVerticalGroup(
            panelCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCategoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spCategories, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoryActionPerformed
        new DialogSubCategory(parent, true, tblCategories, null, true).setVisible(true);
    }//GEN-LAST:event_btnCategoryActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCategory;
    private javax.swing.JPanel panelCategory;
    private javax.swing.JScrollPane spCategories;
    private javax.swing.JTable tblCategories;
    // End of variables declaration//GEN-END:variables
}
