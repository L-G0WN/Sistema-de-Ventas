package com.monagas.view.sales.forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.monagas.controllers.login.UserController;
import com.monagas.entities.login.User;
import com.monagas.view.sales.components.CustomJTextField;
import java.awt.Frame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenu;
import javax.swing.JPanel;

public class Account extends JPanel {

    private final UserController usercontroller = new UserController();

    private final User currentUser;

    private final Frame parent;
    private final JMenu mAccount;
    
    public Account(Frame parent, User currentUser, JMenu mAccount) {
        this.parent = parent;
        this.currentUser = currentUser;
        this.mAccount = mAccount;
        initComponents();

        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingresa tu contraseña");
        txtPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true");

        txtFirstname.setText(currentUser.getFirstname());
        txtLastname.setText(currentUser.getLastname());
        cbAccount.setSelectedItem(currentUser.getAccountType() == 1 ? "Administrador" : "Empleado");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbFirstname = new javax.swing.JLabel();
        txtFirstname = new CustomJTextField("Firstname");
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
        btnUpdate = new javax.swing.JButton();

        lbFirstname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbFirstname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFirstname.setText("Nombre");

        txtFirstname.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbLastname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbLastname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLastname.setText("Apellido");

        txtLastname.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUsername.setText("Nombre de Usuario");

        txtUsername.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPassword.setText("Contraseña");

        txtPassword.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbQuestions.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbQuestions.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbQuestions.setText("Nueva Pregunta de Seguridad");

        cbQuestion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbQuestion.setModel(new DefaultComboBoxModel<>(new String[]{"¿CÓMO SE LLAMA TU MASCOTA?", "¿EN QUÉ CIUDAD NACIÓ TU MADRE?", "¿EN QUÉ CIUDAD NACIÓ TU PADRE?", "¿CUÁL ES TU PELÍCULA FAVORITA?"}));

        lbAnswer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbAnswer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAnswer.setText("Nueva Respuesta");

        txtAnswer.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbAccount.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbAccount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAccount.setText("Tipo de Cuenta");

        cbAccount.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbAccount.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Empleado" }));
        cbAccount.setEnabled(false);

        btnUpdate.setBackground(new java.awt.Color(40, 188, 72));
        btnUpdate.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("ACTUALIZAR INFORMACIÓN DE LA CUENTA");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbAnswer))
                            .addGap(14, 14, 14)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbAccount, 0, 175, Short.MAX_VALUE)
                                .addComponent(lbAccount)))
                        .addComponent(cbQuestion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(470, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFirstname)
                    .addComponent(lbLastname, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbAnswer)
                        .addGap(0, 0, 0)
                        .addComponent(txtAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbAccount)
                        .addGap(0, 0, 0)
                        .addComponent(cbAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(200, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        usercontroller.edit(parent, currentUser, txtFirstname, txtLastname, txtUsername, txtPassword, cbQuestion, txtAnswer, mAccount);
    }//GEN-LAST:event_btnUpdateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbAccount;
    private javax.swing.JComboBox<String> cbQuestion;
    private javax.swing.JLabel lbAccount;
    private javax.swing.JLabel lbAnswer;
    private javax.swing.JLabel lbFirstname;
    private javax.swing.JLabel lbLastname;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbQuestions;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JTextField txtAnswer;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
