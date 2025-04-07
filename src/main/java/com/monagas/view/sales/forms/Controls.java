package com.monagas.view.sales.forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.monagas.controllers.login.UserController;
import com.monagas.view.sales.components.CustomJTable;
import com.monagas.view.sales.components.CustomJTextField;
import com.monagas.view.sales.style.FlatStyle;
import java.awt.Frame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Controls extends JPanel {

    private final UserController controller = new UserController();

    private final Frame parent;

    public Controls(Frame parent) {
        this.parent = parent;
        initComponents();

        FlatStyle.setStyle(spDelete, tblDelete);

        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingresa tu contraseña");
        txtPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true");

        controller.loadUsers(tblDelete);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbFirstname = new javax.swing.JLabel();
        txtFirstname = new CustomJTextField("Name");
        lbLastname = new javax.swing.JLabel();
        txtLastname = new CustomJTextField("Lastname");
        lbUsername = new javax.swing.JLabel();
        txtUsername = new CustomJTextField("Username2");
        lbPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnRegister = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        spDelete = new javax.swing.JScrollPane();
        tblDelete = new CustomJTable();
        txtSearch = new CustomJTextField(tblDelete);
        lbPhone = new javax.swing.JLabel();
        txtPhone = new CustomJTextField("Phone");
        lbState = new javax.swing.JLabel();
        txtState = new CustomJTextField("State");
        lbCity = new javax.swing.JLabel();
        txtCity = new CustomJTextField("City");
        lbTown = new javax.swing.JLabel();
        txtTown = new CustomJTextField("Town");
        lbParish = new javax.swing.JLabel();
        txtParish = new CustomJTextField("Parish");
        lbDetails = new javax.swing.JLabel();
        txtDetails = new CustomJTextField("Details");
        lbStatus = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox<>();

        lbFirstname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbFirstname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFirstname.setText("Nombre");

        lbLastname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbLastname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLastname.setText("Apellido");

        lbUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUsername.setText("Nombre de Usuario");

        lbPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPassword.setText("Contraseña");

        btnRegister.setBackground(new java.awt.Color(40, 188, 72));
        btnRegister.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnRegister.setForeground(new java.awt.Color(255, 255, 255));
        btnRegister.setText("REGISTRAR CUENTA");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(232, 213, 42));
        btnUpdate.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("ACTUALIZAR CUENTA");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(185, 39, 39));
        btnDelete.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("ELIMINAR CUENTA");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        tblDelete.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "USUARIO", "NOMBRE Y APELLIDO", "ESTADO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDelete.setGridColor(new java.awt.Color(102, 102, 102));
        tblDelete.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblDelete.setName("Controls"); // NOI18N
        tblDelete.setRowHeight(30);
        tblDelete.setSelectionBackground(new java.awt.Color(230, 230, 230));
        tblDelete.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblDelete.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDelete.setShowGrid(true);
        tblDelete.getTableHeader().setReorderingAllowed(false);
        tblDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDeleteMouseClicked(evt);
            }
        });
        spDelete.setViewportView(tblDelete);
        if (tblDelete.getColumnModel().getColumnCount() > 0) {
            tblDelete.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblDelete.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblDelete.getColumnModel().getColumn(2).setPreferredWidth(200);
            tblDelete.getColumnModel().getColumn(3).setPreferredWidth(95);
        }

        lbPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbPhone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPhone.setText("Teléfono");

        txtPhone.setEditable(false);

        lbState.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbState.setText("Estado");

        txtState.setEditable(false);

        lbCity.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbCity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCity.setText("Ciudad");

        txtCity.setEditable(false);

        lbTown.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbTown.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTown.setText("Municipio");

        txtTown.setEditable(false);

        lbParish.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbParish.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbParish.setText("Parroquia");

        txtParish.setEditable(false);

        lbDetails.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbDetails.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDetails.setText("Dirección");

        txtDetails.setEditable(false);

        lbStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbStatus.setText("Estado de Cuenta");

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activado", "Desactivado" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbFirstname)
                                    .addComponent(lbUsername)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtFirstname, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbLastname)
                                    .addComponent(txtPassword)
                                    .addComponent(lbPassword)
                                    .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPhone)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(lbState))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCity)
                                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(lbTown))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTown, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbParish)
                                    .addComponent(txtParish, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(lbDetails))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRegister)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate))
                            .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDelete)
                        .addGap(83, 83, 83)
                        .addComponent(txtSearch))
                    .addComponent(spDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbFirstname)
                            .addComponent(lbLastname, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbUsername)
                            .addComponent(lbPassword))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbPhone)
                            .addComponent(lbState))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbCity)
                            .addComponent(lbTown))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTown, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbParish)
                            .addComponent(lbDetails))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtParish, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbStatus)
                        .addGap(0, 0, 0)
                        .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(82, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        controller.createUser(parent, tblDelete, txtFirstname, txtLastname, txtUsername, txtPassword, cbStatus);
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (tblDelete.getRowCount() > 0) {
            int row = tblDelete.getSelectedRow();

            if (row != -1) {
                int confirm = JOptionPane.showConfirmDialog(parent,
                        "¿Estás seguro de eliminar este usuario?",
                        "Sistema de Ventas",
                        JOptionPane.OK_CANCEL_OPTION);

                if (confirm == 0) {
                    Long id = Long.valueOf(tblDelete.getValueAt(row, 0).toString().substring(1));
                    controller.deleteUser(parent, tblDelete, id);
                }
            } else {
                JOptionPane.showMessageDialog(
                        parent,
                        "Por favor, Seleccione algún usuario para eliminar.",
                        "Sistema de Ventas - Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "No hay usuarios registrados en el sistema.",
                    "Sistema de Ventas - Información",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (tblDelete.getRowCount() > 0) {
            int row = tblDelete.getSelectedRow();

            if (row != -1) {
                Long id = Long.valueOf(tblDelete.getValueAt(row, 0).toString().substring(1));
                controller.editUser(parent, tblDelete, id, txtFirstname, txtLastname, txtUsername, txtPassword, cbStatus);
            } else {
                JOptionPane.showMessageDialog(
                        parent,
                        "Por favor, Seleccione algún usuario para realizar los cambios.",
                        "Sistema de Ventas - Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "No hay usuarios registrados en el sistema.",
                    "Sistema de Ventas - Información",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDeleteMouseClicked
        if (tblDelete.getRowCount() > 0) {
            int row = tblDelete.getSelectedRow();

            if (row != -1) {
                Long id = Long.valueOf(tblDelete.getValueAt(row, 0).toString().substring(1));
                controller.loadUserById(id, txtFirstname, txtLastname, txtUsername, txtPassword, cbStatus, txtPhone, txtState, txtCity, txtTown, txtParish, txtDetails);
            }
        }
    }//GEN-LAST:event_tblDeleteMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JLabel lbCity;
    private javax.swing.JLabel lbDetails;
    private javax.swing.JLabel lbFirstname;
    private javax.swing.JLabel lbLastname;
    private javax.swing.JLabel lbParish;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbPhone;
    private javax.swing.JLabel lbState;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTown;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JScrollPane spDelete;
    private javax.swing.JTable tblDelete;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtDetails;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JTextField txtParish;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtState;
    private javax.swing.JTextField txtTown;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
