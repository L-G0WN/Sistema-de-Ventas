package com.monagas.view.sales.forms;

import com.monagas.controllers.sales.SellingController;
import com.monagas.view.sales.components.CustomJTable;
import com.monagas.view.sales.components.CustomJTextField;
import com.monagas.view.sales.forms.dialogs.DialogHistory;
import com.monagas.view.sales.renderer.cell.PanelView.TableActionCellEditor;
import com.monagas.view.sales.renderer.cell.PanelView.TableActionCellRender;
import com.monagas.view.sales.renderer.cell.PanelView.TableViewEvent;
import com.monagas.view.sales.style.FlatStyle;
import java.awt.Frame;
import javax.swing.JPanel;

public class History extends JPanel {

    private final SellingController controller = new SellingController();

    public History(Frame parent) {
        initComponents();

        FlatStyle.setStyle(spHistory, tblHistory);
        
        TableViewEvent event = (int row) -> {
            Long id = Long.valueOf(tblHistory.getValueAt(row, 0).toString().substring(1));
            new DialogHistory(parent, true, id).setVisible(true);
        };

        tblHistory.getColumnModel().getColumn(tblHistory.getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
        tblHistory.getColumnModel().getColumn(tblHistory.getColumnCount() - 1).setCellEditor(new TableActionCellEditor(event));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spHistory = new javax.swing.JScrollPane();
        tblHistory = new CustomJTable();
        txtSearch = new CustomJTextField(tblHistory);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        tblHistory.setAutoCreateRowSorter(true);
        tblHistory.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "FECHA DE VENTA", "CEDULA", "NOMBRE Y APELLIDO", "MONTO TOTAL $", "MONTO TOTAL BS", "MÉTODO DE PAGO", "VENTA REALIZADA POR", "PRODUCTOS"
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
        tblHistory.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblHistory.setGridColor(new java.awt.Color(102, 102, 102));
        tblHistory.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblHistory.setName("Clients"); // NOI18N
        tblHistory.setRowHeight(30);
        tblHistory.setSelectionBackground(new java.awt.Color(230, 230, 230));
        tblHistory.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblHistory.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblHistory.setShowGrid(true);
        tblHistory.getTableHeader().setReorderingAllowed(false);
        spHistory.setViewportView(tblHistory);
        if (tblHistory.getColumnModel().getColumnCount() > 0) {
            tblHistory.getColumnModel().getColumn(0).setPreferredWidth(80);
        }

        txtSearch.setName("Clients"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(spHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
                        .addComponent(txtSearch))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(spHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        controller.loadSellings(tblHistory);
    }//GEN-LAST:event_formComponentShown

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane spHistory;
    private javax.swing.JTable tblHistory;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
