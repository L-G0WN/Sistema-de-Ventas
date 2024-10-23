package com.monagas.view.sales.forms;

import com.monagas.view.sales.components.CustomJPanel;
import com.monagas.view.sales.components.CustomJTable;
import com.monagas.view.sales.components.CustomJTextField;
import com.monagas.view.sales.forms.dialogs.DialogClients;
import com.monagas.view.sales.renderer.cell.TableActionCellEditor;
import com.monagas.view.sales.renderer.cell.TableActionCellRender;
import com.monagas.view.sales.renderer.cell.TableActionEvent;
import com.monagas.view.sales.style.FlatStyle;
import java.awt.Frame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Clients extends CustomJPanel {

    public Clients(Frame parent) {
        initComponents();

        FlatStyle.setStyle(tblClients);

        Object[][] data = {
            {1, "1234567890", "Juan", "Pérez", "0426-1234", "Calle 1, Madrid"},
            {2, "9876543210", "Ana", "Gómez", "555-5678", "Avenida 2, Barcelona"},
            {3, "1111111111", "Luis", "Fernández", "555-9012", "Calle 3, Valencia"},
            {4, "2222222222", "María", "López", "555-1111", "Avenida 4, Sevilla"},
            {5, "3333333333", "Carlos", "Sánchez", "555-2222", "Calle 5, Bilbao"},
            {6, "4444444444", "Laura", "Martínez", "555-3333", "Avenida 6, Zaragoza"},
            {7, "5555555555", "José", "Rodríguez", "555-4444", "Calle 7, Granada"},
            {8, "6666666666", "Isabel", "Torres", "555-5555", "Avenida 8, Málaga"},
            {9, "7777777777", "Javier", "Ramírez", "555-6666", "Calle 9, Alicante"},
            {10, "8888888888", "Patricia", "Díaz", "555-7777", "Avenida 10, Córdoba"},
            {11, "9999999999", "Fernando", "Castro", "555-8888", "Calle 11, Murcia"},
            {12, "1010101010", "Sofía", "Herrera", "555-9999", "Avenida 12, Toledo"},
            {13, "1212121212", "Diego", "Romero", "555-1010", "Calle 13, Salamanca"},
            {14, "1313131313", "Clara", "Ruiz", "555-1212", "Avenida 14, Burgos"},
            {15, "1414141414", "Gabriel", "Vargas", "555-1313", "Calle 15, Oviedo"},
            {16, "1515151515", "Elena", "Morales", "555-1414", "Avenida 16, Santiago"},
            {17, "1616161616", "Andrés", "Jiménez", "555-1515", "Calle 17, La Coruña"},
            {18, "1717171717", "Carmen", "Medina", "555-1616", "Avenida 18, Almería"},
            {19, "1818181818", "Hugo", "Paredes", "555-1717", "Calle 19, Tarragona"},
            {20, "1919191919", "Valeria", "Peña", "555-1818", "Avenida 20, San Sebastián"},
            {21, "2020202020", "Rafael", "Castro", "555-1919", "Calle 21, Gijón"},
            {22, "2121212121", "Lucía", "Salas", "555-2020", "Avenida 22, Pamplona"},
            {23, "2222222222", "Santiago", "Ortega", "555-2121", "Calle 23, Lérida"},
            {24, "2323232323", "Marisol", "Aguirre", "555-2222", "Avenida 24, Albacete"},
            {25, "2424242424", "Pablo", "Cordero", "555-2323", "Calle 25, Cádiz"},
            {26, "2525252525", "Nuria", "Ramos", "555-2424", "Avenida 26, Badajoz"},
            {27, "2626262626", "Joaquín", "León", "555-2525", "Calle 27, Huelva"},
            {28, "2727272727", "Silvia", "Aguado", "555-2626", "Avenida 28, Tenerife"},
            {29, "2828282828", "Ricardo", "Salgado", "555-2727", "Calle 29, Las Palmas"},
            {30, "2929292929", "Teresa", "Castro", "555-2828", "Avenida 30, Murcia"},
            {31, "3030303030", "Cristian", "Martínez", "555-2929", "Calle 31, Valencia"},
            {32, "3131313131", "Lola", "García", "555-3030", "Avenida 32, Madrid"}
        };

        DefaultTableModel model = (DefaultTableModel) tblClients.getModel();
        for (Object[] date : data) {
            model.addRow(date);
        }

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                int id = Integer.parseInt(tblClients.getValueAt(row, 0).toString());
                String cedula = tblClients.getValueAt(row, 1).toString();
                String type = (cedula.startsWith("V")) ? "V" : "E";

                String firstname = tblClients.getValueAt(row, 2).toString();
                String lastname = tblClients.getValueAt(row, 3).toString();

                String phone = tblClients.getValueAt(row, 4).toString();
                String[] PhoneSplit = phone.split("-\\s*");

                String address = tblClients.getValueAt(row, 5).toString();
                new DialogClients(parent, true, id, type, cedula.substring(1), firstname, lastname, PhoneSplit, address).setVisible(true);
            }

            @Override
            public void onDelete(int row) {
                StringBuilder message = new StringBuilder();

                message.append("Le solicitamos su confirmación para proceder con la eliminación de la información del cliente. ")
                        .append("Esta acción es irreversible y resultará en la pérdida permanente de todos los datos asociados. ")
                        .append("Agradecemos su atención a este asunto y le pedimos que confirme si desea continuar con este proceso.");

                int confirm = JOptionPane.showConfirmDialog(parent, message, "Sistema de Ventas", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

                if (confirm == 0) {
                    System.out.println("ELIMINAR");
                } else {
                    System.out.println("CANCELAR");
                }
            }
        };

        tblClients.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
        tblClients.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spClients = new javax.swing.JScrollPane();
        tblClients = new CustomJTable();
        txtSearch = new CustomJTextField(tblClients);

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblClients.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblClients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CEDULA", "NOMBRE", "APELLIDO", "TELÉFONO", "DIRECCIÓN PRINCIPAL", "ACCIONES"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClients.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblClients.setName("Clients"); // NOI18N
        tblClients.setRowHeight(30);
        tblClients.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblClients.setShowGrid(true);
        tblClients.setShowHorizontalLines(true);
        tblClients.getTableHeader().setReorderingAllowed(false);
        spClients.setViewportView(tblClients);
        if (tblClients.getColumnModel().getColumnCount() > 0) {
            tblClients.getColumnModel().getColumn(6).setMinWidth(110);
        }

        txtSearch.setName("Clients"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spClients, javax.swing.GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE)
                    .addComponent(txtSearch))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spClients, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane spClients;
    private javax.swing.JTable tblClients;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
