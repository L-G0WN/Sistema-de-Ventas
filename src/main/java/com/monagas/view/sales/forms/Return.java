package com.monagas.view.sales.forms;

import com.monagas.controllers.sales.SellingController;
import com.monagas.view.sales.components.CustomJTable;
import com.monagas.view.sales.components.CustomJTextField;
import com.monagas.view.sales.style.FlatStyle;
import java.awt.Frame;
import javax.swing.JPanel;

public class Return extends JPanel {

    private final SellingController controller = new SellingController();

    public Return(Frame parent) {
        initComponents();

        FlatStyle.setStyle(spHistory, tblReturn);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spHistory = new javax.swing.JScrollPane();
        tblReturn = new CustomJTable();
        txtSearch = new CustomJTextField(tblReturn);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        tblReturn.setAutoCreateRowSorter(true);
        tblReturn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblReturn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "FECHA DE VENTA", "CÉDULA", "NOMBRE Y APELLIDO", "MONTO TOTAL $", "MONTO TOTAL BS", "MÉTODO DE PAGO", "VENTA REALIZADA POR", "ACCIONES"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblReturn.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblReturn.setGridColor(new java.awt.Color(102, 102, 102));
        tblReturn.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblReturn.setName("Clients"); // NOI18N
        tblReturn.setRowHeight(30);
        tblReturn.setSelectionBackground(new java.awt.Color(230, 230, 230));
        tblReturn.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblReturn.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblReturn.setShowGrid(true);
        tblReturn.getTableHeader().setReorderingAllowed(false);
        spHistory.setViewportView(tblReturn);
        if (tblReturn.getColumnModel().getColumnCount() > 0) {
            tblReturn.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblReturn.getColumnModel().getColumn(8).setMinWidth(120);
        }

        txtSearch.setName("Clients"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
                    .addComponent(txtSearch))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(spHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        controller.loadSellings(tblReturn, true);
    }//GEN-LAST:event_formComponentShown

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane spHistory;
    private javax.swing.JTable tblReturn;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
