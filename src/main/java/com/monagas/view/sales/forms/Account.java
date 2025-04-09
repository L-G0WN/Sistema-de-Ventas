package com.monagas.view.sales.forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.monagas.controllers.login.UserController;
import com.monagas.entities.login.User;
import com.monagas.view.sales.components.CustomJTextField;
import java.awt.Frame;
import javax.swing.JMenu;
import javax.swing.JPanel;

public class Account extends JPanel {

    private final UserController controller = new UserController();

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

        txtAnswer1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nueva Repuesta 1 (Opcional)");
        txtAnswer1.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton: true");

        txtAnswer2.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nueva Repuesta 2 (Opcional)");
        txtAnswer2.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton: true");

        txtAnswer3.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nueva Repuesta 3 (Opcional)");
        txtAnswer3.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton: true");

        txtFirstname.setText(currentUser.getPerson().getFirstname());
        txtLastname.setText(currentUser.getPerson().getLastname());
        cbCode.setSelectedItem((currentUser.getPerson().getDetailPerson().getPhone() != null) ? currentUser.getPerson().getDetailPerson().getPhone().replaceAll("-.*", "") : "0424");
        txtPhone.setText((currentUser.getPerson().getDetailPerson().getPhone() != null) ? currentUser.getPerson().getDetailPerson().getPhone().replaceAll(".*-", "") : "");
        txtState.setText(currentUser.getPerson().getAddress().getState());
        txtCity.setText(currentUser.getPerson().getAddress().getCity());
        txtTown.setText(currentUser.getPerson().getAddress().getTown());
        txtParish.setText(currentUser.getPerson().getAddress().getParish());
        txtDetails.setText(currentUser.getPerson().getAddress().getAddressDetails());
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
        lbAccount = new javax.swing.JLabel();
        cbAccount = new javax.swing.JComboBox<>();
        btnUpdate = new javax.swing.JButton();
        lbQuestion1 = new javax.swing.JLabel();
        cbQuestions1 = new javax.swing.JComboBox<>();
        lbAnswer1 = new javax.swing.JLabel();
        lbQuestion2 = new javax.swing.JLabel();
        cbQuestions2 = new javax.swing.JComboBox<>();
        lbAnswer2 = new javax.swing.JLabel();
        lbQuestion3 = new javax.swing.JLabel();
        cbQuestions3 = new javax.swing.JComboBox<>();
        lbAnswer3 = new javax.swing.JLabel();
        txtAnswer2 = new javax.swing.JPasswordField();
        txtAnswer1 = new javax.swing.JPasswordField();
        txtAnswer3 = new javax.swing.JPasswordField();
        cbCode = new javax.swing.JComboBox<>();
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

        lbQuestion1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbQuestion1.setText("Pregunta de Seguridad 1");

        cbQuestions1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbQuestions1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "¿CÓMO SE LLAMA TU MASCOTA?", "¿EN QUÉ CIUDAD NACIÓ TU MADRE?", "¿EN QUÉ CIUDAD NACIÓ TU PADRE?", "¿CUÁL ES TU PELÍCULA FAVORITA?" }));

        lbAnswer1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbAnswer1.setText("Repuestas 1");

        lbQuestion2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbQuestion2.setText("Pregunta de Seguridad 2");

        cbQuestions2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbQuestions2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "¿CUÁL ES TU COMIDA FAVORITA?", "¿QUÉ NOMBRE RECIBIÓ TU PRIMERA MASCOTA?", "¿CUÁL ES TU LIBRO PREFERIDO?", "¿QUÉ DEPORTE TE GUSTA MÁS?" }));

        lbAnswer2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbAnswer2.setText("Repuestas 2");

        lbQuestion3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbQuestion3.setText("Pregunta de Seguridad 3");

        cbQuestions3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbQuestions3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "¿CUÁL ES TU SUEÑO MÁS GRANDE?", "¿QUÉ LENGUAJE TE GUSTARÍA APRENDER?", "¿CUÁL ES TU RECUERDO FAVORITO?", "¿QUÉ HACES EN TU TIEMPO LIBRE?" }));

        lbAnswer3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbAnswer3.setText("Repuestas 3");

        txtAnswer2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtAnswer1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtAnswer3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        cbCode.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbCode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0424", "0414", "0416", "0426", "0412" }));

        lbPhone.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbPhone.setText("Teléfono");

        txtPhone.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbState.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbState.setText("Estado");

        txtState.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbCity.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbCity.setText("Ciudad");

        txtCity.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbTown.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTown.setText("Municipio");

        txtTown.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbParish.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbParish.setText("Parroquia");

        txtParish.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbDetails.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDetails.setText("Dirección");

        txtDetails.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

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
                        .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbAccount)
                        .addComponent(cbAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbCity)
                                .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbPhone)
                                        .addComponent(cbCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtTown, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(lbTown)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(txtState)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lbState)
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbParish)
                            .addComponent(txtParish, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbDetails)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbQuestion3)
                    .addComponent(lbAnswer3)
                    .addComponent(lbQuestion2)
                    .addComponent(lbAnswer2)
                    .addComponent(lbQuestion1)
                    .addComponent(lbAnswer1)
                    .addComponent(cbQuestions1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbQuestions2, 0, 0, Short.MAX_VALUE)
                    .addComponent(cbQuestions3, 0, 0, Short.MAX_VALUE)
                    .addComponent(txtAnswer2)
                    .addComponent(txtAnswer3)
                    .addComponent(txtAnswer1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(207, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbQuestion1)
                        .addGap(0, 0, 0)
                        .addComponent(cbQuestions1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbAnswer1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtAnswer1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(lbQuestion2)
                        .addGap(0, 0, 0)
                        .addComponent(cbQuestions2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbAnswer2)
                        .addGap(0, 0, 0)
                        .addComponent(txtAnswer2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbQuestion3)
                        .addGap(0, 0, 0)
                        .addComponent(cbQuestions3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbAnswer3)
                        .addGap(0, 0, 0)
                        .addComponent(txtAnswer3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbPhone)
                                        .addGap(0, 0, 0)
                                        .addComponent(cbCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbTown)
                                    .addComponent(lbCity)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTown, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(lbState)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDetails)
                            .addComponent(lbParish)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtParish, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbAccount)
                        .addGap(0, 0, 0)
                        .addComponent(cbAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        controller.editAccount(parent, currentUser, txtFirstname, txtLastname, txtUsername, txtPassword, cbQuestions1, txtAnswer1, cbQuestions2, txtAnswer2, cbQuestions3, txtAnswer3, cbCode, txtPhone, txtState, txtCity, txtTown, txtParish, txtDetails, mAccount);
    }//GEN-LAST:event_btnUpdateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbAccount;
    private javax.swing.JComboBox<String> cbCode;
    private javax.swing.JComboBox<String> cbQuestions1;
    private javax.swing.JComboBox<String> cbQuestions2;
    private javax.swing.JComboBox<String> cbQuestions3;
    private javax.swing.JLabel lbAccount;
    private javax.swing.JLabel lbAnswer1;
    private javax.swing.JLabel lbAnswer2;
    private javax.swing.JLabel lbAnswer3;
    private javax.swing.JLabel lbCity;
    private javax.swing.JLabel lbDetails;
    private javax.swing.JLabel lbFirstname;
    private javax.swing.JLabel lbLastname;
    private javax.swing.JLabel lbParish;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbPhone;
    private javax.swing.JLabel lbQuestion1;
    private javax.swing.JLabel lbQuestion2;
    private javax.swing.JLabel lbQuestion3;
    private javax.swing.JLabel lbState;
    private javax.swing.JLabel lbTown;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JPasswordField txtAnswer1;
    private javax.swing.JPasswordField txtAnswer2;
    private javax.swing.JPasswordField txtAnswer3;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtDetails;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JTextField txtParish;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtState;
    private javax.swing.JTextField txtTown;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
