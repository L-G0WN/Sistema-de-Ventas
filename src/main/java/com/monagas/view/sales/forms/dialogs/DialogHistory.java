package com.monagas.view.sales.forms.dialogs;

import com.monagas.controllers.sales.SellingController;
import com.monagas.view.sales.components.CustomJTable;
import com.monagas.view.sales.style.FlatStyle;
import java.awt.Frame;
import javax.swing.JDialog;

public class DialogHistory extends JDialog {
    
    private final SellingController controller = new SellingController();
    
    public DialogHistory(Frame parent, boolean modal, Long id) {
        super(parent, modal);
        initComponents();

        FlatStyle.setStyle(spProducts, tblProducts);
        
        controller.loadProducts(tblProducts, id);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spProducts = new javax.swing.JScrollPane();
        tblProducts = new CustomJTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Productos Cliente");
        setResizable(false);

        tblProducts.setAutoCreateRowSorter(true);
        tblProducts.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "PRODUCTO", "CANTIDAD", "P.V. $", "SUBTOTAL $", "P.V. BS", "SUBTOTAL BS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProducts.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblProducts.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblProducts.setRowHeight(30);
        tblProducts.setSelectionBackground(new java.awt.Color(230, 230, 230));
        tblProducts.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblProducts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblProducts.setShowGrid(true);
        tblProducts.setShowHorizontalLines(true);
        tblProducts.setShowVerticalLines(true);
        tblProducts.getTableHeader().setReorderingAllowed(false);
        spProducts.setViewportView(tblProducts);
        if (tblProducts.getColumnModel().getColumnCount() > 0) {
            tblProducts.getColumnModel().getColumn(0).setPreferredWidth(70);
            tblProducts.getColumnModel().getColumn(1).setPreferredWidth(265);
            tblProducts.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblProducts.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblProducts.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblProducts.getColumnModel().getColumn(5).setPreferredWidth(80);
            tblProducts.getColumnModel().getColumn(6).setPreferredWidth(105);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spProducts, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane spProducts;
    private javax.swing.JTable tblProducts;
    // End of variables declaration//GEN-END:variables
}
