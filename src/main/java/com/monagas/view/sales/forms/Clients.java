package com.monagas.view.sales.forms;

import com.monagas.controllers.sales.ClientController;
import com.monagas.view.sales.components.CustomJPanel;
import com.monagas.view.sales.components.CustomJTable;
import com.monagas.view.sales.components.CustomJTextField;
import com.monagas.view.sales.forms.dialogs.DialogClients;
import com.monagas.view.sales.forms.dialogs.DialogConfirm;
import com.monagas.view.sales.renderer.cell.TableActionCellEditor;
import com.monagas.view.sales.renderer.cell.TableActionCellRender;
import com.monagas.view.sales.renderer.cell.TableActionEvent;
import com.monagas.view.sales.style.FlatStyle;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JOptionPane;

public class Clients extends CustomJPanel {

    private final ClientController controller = new ClientController();

    public Clients(Frame parent) {
        initComponents();

        FlatStyle.setStyle(spClients, tblClients);

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                Long id = Long.valueOf(tblClients.getValueAt(row, 1).toString().substring(1));
                new DialogClients(parent, true, tblClients, id).setVisible(true);
            }

            @Override
            public void onDelete(int row) {
                Long id = Long.valueOf(tblClients.getValueAt(row, 1).toString().substring(1));
                new DialogConfirm(parent, true, tblClients, id, "Clients").setVisible(true);
            }
        };

        tblClients.getColumnModel().getColumn(tblClients.getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
        tblClients.getColumnModel().getColumn(tblClients.getColumnCount() - 1).setCellEditor(new TableActionCellEditor(event));

        controller.loadClients(tblClients);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spClients = new javax.swing.JScrollPane();
        tblClients = new CustomJTable();
        txtSearch = new CustomJTextField(tblClients);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        tblClients.setAutoCreateRowSorter(true);
        tblClients.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblClients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "CODIGO", "CEDULA", "NOMBRE Y APELLIDO", "TELÉFONO", "DIRECCIÓN PRINCIPAL", "REGISTRADO EN", "REGISTRADO POR", "ACTUALIZADO EN", "ACTUALIZADO POR", "ACCIONES"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClients.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblClients.setGridColor(new java.awt.Color(102, 102, 102));
        tblClients.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblClients.setName("Clients"); // NOI18N
        tblClients.setRowHeight(30);
        tblClients.setSelectionBackground(new java.awt.Color(230, 230, 230));
        tblClients.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblClients.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblClients.setShowGrid(true);
        tblClients.getTableHeader().setReorderingAllowed(false);
        spClients.setViewportView(tblClients);
        if (tblClients.getColumnModel().getColumnCount() > 0) {
            tblClients.getColumnModel().getColumn(0).setMaxWidth(50);
            tblClients.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblClients.getColumnModel().getColumn(10).setMinWidth(110);
        }

        txtSearch.setName("Clients"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spClients, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
                    .addComponent(txtSearch))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spClients, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        controller.loadClients(tblClients);
    }//GEN-LAST:event_formComponentShown

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane spClients;
    private javax.swing.JTable tblClients;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
