package com.monagas.view.sales.forms.dialogs;

import com.formdev.flatlaf.FlatClientProperties;
import com.monagas.controllers.login.UserController;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class DialogFirstime extends JDialog {

    private final UserController controller = new UserController();

    private final Frame parent;
    private final Long id;

    public DialogFirstime(Frame parent, boolean modal, Long id) {
        super(parent, modal);
        this.parent = parent;
        this.id = id;
        initComponents();

        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Introduce tu nueva contraseña");
        txtPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true");

        txtConfirmPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Confirma tu contraseña");
        txtConfirmPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true");

        txtAnswer1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nueva Repuesta 1");
        txtAnswer1.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton: true");

        txtAnswer2.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nueva Repuesta 2");
        txtAnswer2.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton: true");
        
        txtAnswer3.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nueva Repuesta 3");
        txtAnswer3.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton: true");
        
        passwordStrengthStatus.initPasswordField(txtPassword);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        lbPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        lbConfirmPassword = new javax.swing.JLabel();
        txtConfirmPassword = new javax.swing.JPasswordField();
        lbQuestion1 = new javax.swing.JLabel();
        cbQuestions1 = new javax.swing.JComboBox<>();
        lbAnswer1 = new javax.swing.JLabel();
        lbQuestion2 = new javax.swing.JLabel();
        cbQuestions2 = new javax.swing.JComboBox<>();
        lbAnswer2 = new javax.swing.JLabel();
        lbQuestion3 = new javax.swing.JLabel();
        cbQuestions3 = new javax.swing.JComboBox<>();
        lbAnswer3 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnGeneral = new javax.swing.JButton();
        passwordStrengthStatus = new com.monagas.view.login.components.PasswordStrengthStatus();
        txtAnswer2 = new javax.swing.JPasswordField();
        txtAnswer1 = new javax.swing.JPasswordField();
        txtAnswer3 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Registro de Contraseña, Preguntas y Respuestas");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lbPassword.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbPassword.setText("Nueva Contraseña");

        txtPassword.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lbConfirmPassword.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbConfirmPassword.setText("Confirmar Contraseña");

        txtConfirmPassword.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

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

        btnCancel.setBackground(new java.awt.Color(185, 39, 39));
        btnCancel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("CANCELAR");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnGeneral.setBackground(new java.awt.Color(40, 188, 72));
        btnGeneral.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnGeneral.setForeground(new java.awt.Color(255, 255, 255));
        btnGeneral.setText("REGISTRAR");
        btnGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneralActionPerformed(evt);
            }
        });

        txtAnswer2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtAnswer1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtAnswer3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addComponent(btnGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbPassword)
                        .addGap(143, 143, 143))
                    .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtConfirmPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(passwordStrengthStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbConfirmPassword))
                .addGap(18, 18, 18)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                    .addComponent(txtAnswer1))
                .addGap(26, 26, 26))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addComponent(lbQuestion1)
                                .addGap(0, 0, 0)
                                .addComponent(cbQuestions1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbPassword)))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(passwordStrengthStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbConfirmPassword)
                        .addGap(0, 0, 0)
                        .addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelMainLayout.createSequentialGroup()
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
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtAnswer3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(26, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        Exit();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneralActionPerformed
        controller.isFirstime(parent, this, id, txtPassword, txtConfirmPassword, passwordStrengthStatus.getPasswordStrengthType(), cbQuestions1, txtAnswer1, cbQuestions2, txtAnswer2, cbQuestions3, txtAnswer3);
    }//GEN-LAST:event_btnGeneralActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Exit();
    }//GEN-LAST:event_formWindowClosing

    private void Exit() {
        int confirm = JOptionPane.showConfirmDialog(parent,
                "¿Estás seguro de salir del proceso de registrar información? Esta confirmación es necesaria para poder acceder a otras áreas del sistema.",
                "Confirmación",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirm == 0) {
            System.exit(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnGeneral;
    private javax.swing.JComboBox<String> cbQuestions1;
    private javax.swing.JComboBox<String> cbQuestions2;
    private javax.swing.JComboBox<String> cbQuestions3;
    private javax.swing.JLabel lbAnswer1;
    private javax.swing.JLabel lbAnswer2;
    private javax.swing.JLabel lbAnswer3;
    private javax.swing.JLabel lbConfirmPassword;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbQuestion1;
    private javax.swing.JLabel lbQuestion2;
    private javax.swing.JLabel lbQuestion3;
    private javax.swing.JPanel panelMain;
    private com.monagas.view.login.components.PasswordStrengthStatus passwordStrengthStatus;
    private javax.swing.JPasswordField txtAnswer1;
    private javax.swing.JPasswordField txtAnswer2;
    private javax.swing.JPasswordField txtAnswer3;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
