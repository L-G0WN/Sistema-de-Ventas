package com.monagas.view.sales.forms;

import com.monagas.view.sales.components.CustomJTable;
import com.monagas.view.sales.components.CustomJTextField;
import com.monagas.view.sales.style.FlatStyle;
import java.awt.Frame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

public class Controls extends JPanel {

    public Controls(Frame parent) {
        initComponents();
        
        FlatStyle.setStyle(spDelete, tblDelete);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbFirstname = new javax.swing.JLabel();
        txtFirstname = new CustomJTextField("Name");
        lbLastname = new javax.swing.JLabel();
        txtLastname = new CustomJTextField("Lastname");
        lbUsername = new javax.swing.JLabel();
        txtUsername = new CustomJTextField("Username");
        lbPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        lbQuestions = new javax.swing.JLabel();
        cbQuestion = new javax.swing.JComboBox<>();
        lbAnswer = new javax.swing.JLabel();
        txtAnswer = new CustomJTextField("Answer");
        lbAccount = new javax.swing.JLabel();
        cbAccount = new javax.swing.JComboBox<>();
        btnRegister = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        spDelete = new javax.swing.JScrollPane();
        tblDelete = new CustomJTable();
        txtSearch = new CustomJTextField(tblDelete);
        cbAccount1 = new javax.swing.JComboBox<>();
        lbAccount1 = new javax.swing.JLabel();

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

        lbQuestions.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbQuestions.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbQuestions.setText("Pregunta de Seguridad");

        cbQuestion.setModel(new DefaultComboBoxModel<>(new String[]{"¿CÓMO SE LLAMA TU MASCOTA?", "¿EN QUÉ CIUDAD NACIÓ TU MADRE?", "¿EN QUÉ CIUDAD NACIÓ TU PADRE?", "¿CUÁL ES TU PELÍCULA FAVORITA?"}));

        lbAnswer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbAnswer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAnswer.setText("Respuesta");

        lbAccount.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbAccount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAccount.setText("Tipo de Cuenta");

        cbAccount.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado" }));

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
                "CODIGO", "USUARIO", "NOMBRE Y APELLIDO", "VENTAS REALIZADAS", "ESTADO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDelete.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblDelete.setGridColor(new java.awt.Color(102, 102, 102));
        tblDelete.setIntercellSpacing(new java.awt.Dimension(0, 1));
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
            tblDelete.getColumnModel().getColumn(4).setPreferredWidth(80);
        }

        cbAccount1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activado", "Desactivado" }));

        lbAccount1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbAccount1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAccount1.setText("Estado de Cuenta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbQuestion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbQuestions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRegister)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate))
                    .addComponent(txtAnswer)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbAnswer)
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
                                    .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbAccount, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbAccount)
                                .addGap(0, 86, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbAccount1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbAccount1))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                        .addComponent(lbQuestions)
                        .addGap(0, 0, 0)
                        .addComponent(cbQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbAnswer)
                                .addGap(0, 0, 0)
                                .addComponent(txtAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbAccount)
                                    .addComponent(lbAccount1))
                                .addGap(0, 0, 0)
                                .addComponent(cbAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbAccount1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed

    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDeleteMouseClicked

    }//GEN-LAST:event_tblDeleteMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbAccount;
    private javax.swing.JComboBox<String> cbAccount1;
    private javax.swing.JComboBox<String> cbQuestion;
    private javax.swing.JLabel lbAccount;
    private javax.swing.JLabel lbAccount1;
    private javax.swing.JLabel lbAnswer;
    private javax.swing.JLabel lbFirstname;
    private javax.swing.JLabel lbLastname;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbQuestions;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JScrollPane spDelete;
    private javax.swing.JTable tblDelete;
    private javax.swing.JTextField txtAnswer;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
