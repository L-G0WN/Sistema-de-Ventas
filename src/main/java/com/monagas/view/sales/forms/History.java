package com.monagas.view.sales.forms;

import com.monagas.controllers.sales.SellingController;
import com.monagas.view.sales.components.CustomJTable;
import com.monagas.view.sales.components.CustomJTextField;
import com.monagas.view.sales.forms.dialogs.DialogHistory;
import com.monagas.view.sales.forms.dialogs.DialogReturn;
import com.monagas.view.sales.print.Reports;
import com.monagas.view.sales.renderer.cell.PanelView.TableActionCellEditor;
import com.monagas.view.sales.renderer.cell.PanelView.TableActionCellRender;
import com.monagas.view.sales.renderer.cell.PanelView.TableViewEvent;
import com.monagas.view.sales.style.FlatStyle;
import java.awt.Frame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class History extends JPanel {

    private final SellingController controller = new SellingController();

    public History(Frame parent) {
        initComponents();

        FlatStyle.setStyle(spHistory, tblHistory);

        TableViewEvent event = new TableViewEvent() {
            @Override
            public void onView(int row) {
                Long id = Long.valueOf(tblHistory.getValueAt(row, 0).toString().substring(1));
                new DialogHistory(parent, true, id).setVisible(true);
            }

            @Override
            public void onReturn(int row) {
                Object[] options = {"DEVOLUCIÓN TOTAL", "MODIFICAR FACTURA Y GENERAR"};

                int confirm = JOptionPane.showOptionDialog(parent,
                        "Seleccione el tipo de devolución que desea realizar:",
                        "Tipo de Devolución",
                        0,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (confirm == 0) {
                    Long id = Long.valueOf(tblHistory.getValueAt(row, 0).toString().substring(1));
                    controller.editSelling(parent, id, true);
                    btnRefresh.doClick();
                }

                if (confirm == 1) {
                    Long id = Long.valueOf(tblHistory.getValueAt(row, 0).toString().substring(1));
                    new DialogReturn(parent, false, id).setVisible(true);
                }
            }

            @Override
            public void onInvoice(int row) {
                Reports invoiceReport = new Reports();
                Long id = Long.valueOf(tblHistory.getValueAt(row, 0).toString().substring(1));
                try {
                    invoiceReport.generateInvoice(id);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            parent,
                            ex.getMessage(),
                            "Sistema de Ventas - Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
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
        btnRefresh = new javax.swing.JButton();

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
            tblHistory.getColumnModel().getColumn(8).setMinWidth(340);
        }

        txtSearch.setName("Clients"); // NOI18N

        btnRefresh.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconRefresh16.png"))); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addComponent(spHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        controller.loadSellings(tblHistory, false);
    }//GEN-LAST:event_formComponentShown

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        controller.loadSellings(tblHistory, false);
    }//GEN-LAST:event_btnRefreshActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JScrollPane spHistory;
    private javax.swing.JTable tblHistory;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
