package com.monagas.controllers.login;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.monagas.services.login.UserService;
import com.monagas.view.login.Login;
import com.monagas.view.login.components.CredentialManager;
import com.monagas.view.login.forms.FormRecovery;
import com.monagas.view.login.forms.FormVerify;
import com.monagas.view.login.forms.button.ButtonCancel;
import com.monagas.view.sales.Sales;
import java.awt.Frame;
import java.util.Optional;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

public class UserController {

    private final UserService userService = new UserService();

    public void login(Frame parent, JTextField txtUsername, JPasswordField txtPassword, JCheckBox cbRememberMe, JButton btnLogin, JButton btnForget) {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (!username.isEmpty() && !password.isEmpty()) {
            try {
                userService.login(username, password);

                txtUsername.setEditable(false);
                txtPassword.setEditable(false);
                cbRememberMe.setEnabled(false);
                btnLogin.setEnabled(false);
                btnForget.setEnabled(false);

                if (cbRememberMe.isSelected()) {
                    CredentialManager.saveCredentials(username);
                } else {
                    CredentialManager.clearCredentials();
                }

                Timer timer = new Timer(1500, ex -> {
                    parent.dispose();
                    new Sales().setVisible(true);
                });

                timer.setRepeats(false);
                timer.start();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        parent,
                        ex.getMessage(),
                        "Sistema de Ventas - Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "Por favor, complete todos los campos vacíos.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void findUserByUsername(Login parent, JTextField txtUserContinue, JButton btnContinue, JButton Cancel) {
        String username = txtUserContinue.getText();

        if (!username.isEmpty()) {
            if (Optional.ofNullable(userService.findUserByUsername(username)).isPresent()) {
                txtUserContinue.setEditable(false);
                btnContinue.setEnabled(false);
                Cancel.setEnabled(false);

                JOptionPane.showMessageDialog(
                        parent,
                        "Se ha encontrado un usuario. Por favor, revise su información\ny responda a la siguiente pregunta secreta.",
                        "Sistema de Ventas - Información",
                        JOptionPane.INFORMATION_MESSAGE);

                Timer timer = new Timer(1500, ex -> {
                    FlatAnimatedLafChange.showSnapshot();
                    parent.switchPanel(new FormVerify(parent, username));
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                });

                timer.setRepeats(false);
                timer.start();
            } else {
                JOptionPane.showMessageDialog(
                        parent,
                        "Usuario no encontrado. Por favor, revise y vuelve a intentarlo.",
                        "Sistema de Ventas - Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "Por favor, complete el campo vacio para continuar.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void verify(Login parent, String username, JComboBox cbQuestions, JTextField txtAnswer, JButton btnVerify, JButton Cancel) {
        String question = cbQuestions.getSelectedItem().toString();
        String answer = txtAnswer.getText();

        if (!answer.isEmpty()) {
            try {
                userService.verify(username, question, answer);

                txtAnswer.setEditable(false);
                cbQuestions.setEnabled(false);
                btnVerify.setEnabled(false);
                Cancel.setEnabled(false);

                JOptionPane.showMessageDialog(
                        parent,
                        "Enhorabuena! Los datos que has introducido son correctos.",
                        "Sistema de Ventas - Información",
                        JOptionPane.INFORMATION_MESSAGE);

                Timer timer = new Timer(1500, ex -> {
                    FlatAnimatedLafChange.showSnapshot();
                    parent.switchPanel(new FormRecovery(parent, username));
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                });

                timer.setRepeats(false);
                timer.start();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        parent,
                        ex.getMessage(),
                        "Sistema de Ventas - Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "Por favor, complete el campo vacio para continuar.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void changePassword(Login parent, String username, JPasswordField txtPassword, JPasswordField txtConfirmPassword, int passwordStrength, JButton btnChangePassword, JButton Cancel) {
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());

        if (!password.isEmpty() && !confirmPassword.isEmpty()) {
            if (password.equals(confirmPassword)) {
                if ((passwordStrength == 2) || (passwordStrength == 3)) {
                    try {
                        userService.changePassword(username, password);

                        txtPassword.setEditable(false);
                        txtConfirmPassword.setEditable(false);
                        btnChangePassword.setEnabled(false);
                        Cancel.setEnabled(false);

                        JOptionPane.showMessageDialog(
                                parent,
                                "La contraseña se ha cambiado exitosamente.",
                                "Sistema de Ventas - Información",
                                JOptionPane.INFORMATION_MESSAGE);

                        Timer timer = new Timer(1500, ex -> {
                            ButtonCancel back = new ButtonCancel(parent);
                            back.ActionPerfomedCancel();
                        });

                        timer.setRepeats(false);
                        timer.start();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(
                                parent,
                                ex.getMessage(),
                                "Sistema de Ventas - Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            parent,
                            "La contraseña es muy debil...",
                            "Sistema de Ventas - Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(
                        parent,
                        "Las contraseñas no coinciden. Por favor revise y vuelve a intentarlo.",
                        "Sistema de Ventas - Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "Por favor, complete todos los campos vacíos.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
