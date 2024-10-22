package com.monagas.view.login.forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.monagas.controllers.login.LoginController;
import com.monagas.view.login.Login;
import com.monagas.view.login.components.FocusablePanel;
import com.monagas.view.login.forms.button.ButtonCancel;
import com.monagas.view.login.components.PasswordStrengthStatus;
import com.monagas.view.login.forms.labels.LabelRecovery;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.Timer;
import net.miginfocom.swing.MigLayout;

public class FormRecovery extends JPanel implements FocusablePanel {

    private final LoginController controller = new LoginController();

    private final Login Frame;
    private final String username;
    private final ButtonCancel Cancel;

    public FormRecovery(Login Frame, String username) {
        this.Frame = Frame;
        this.username = username;
        Cancel = new ButtonCancel(Frame);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setLayout(new MigLayout("wrap,fillx,insets 15 45 30 45", "fill,250:280"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:@background;");

        txtPassword = new JPasswordField();
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Introduce tu nueva contraseña");
        txtPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true");
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ActionPerformedChangePassword();
                }
            }
        });

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Confirma tu contraseña");
        txtConfirmPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true");
        txtConfirmPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ActionPerformedChangePassword();
                }
            }
        });

        passwordStrengthStatus = new PasswordStrengthStatus();
        passwordStrengthStatus.initPasswordField(txtPassword);

        btnChangePassword = new JButton("Cambiar Contraseña");
        btnChangePassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]background:lighten(@accentColor,5%);"
                + "foreground:lighten(#ff0,50%);"
                + "font:14;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");
        btnChangePassword.addActionListener(e -> {
            ActionPerformedChangePassword();
        });

        add(LabelRecovery.restoreLabel(), "gapy 5");
        add(LabelRecovery.descriptionLabel(), "gapbottom 10");
        add(new JLabel("Nueva Contraseña"), "gapy 5");
        add(txtPassword);
        add(passwordStrengthStatus, "gapy 0");
        add(new JLabel("Confirmar Contraseña"), "gapy 8");
        add(txtConfirmPassword);
        add(btnChangePassword, "gapy 10");
        add(Cancel, "width 70:70:70, gapy 10, align center");
    }

    private void ActionPerformedChangePassword() {
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());
        int passwordStrength = passwordStrengthStatus.getPasswordStrengthType();

        if (password.equals(confirmPassword)) {
            if ((passwordStrength == 2) || (passwordStrength == 3)) {
                if (controller.changePassword(username, password)) {
                    txtPassword.setEditable(false);
                    txtConfirmPassword.setEditable(false);
                    btnChangePassword.setEnabled(false);
                    Cancel.setEnabled(false);
                    JOptionPane.showMessageDialog(Frame, "La contraseña se ha cambiado exitosamente.", "Sistema de Ventas", JOptionPane.INFORMATION_MESSAGE);

                    Timer timer = new Timer(3000, ex -> {
                        Cancel.ActionPerfomedCancel();
                    });

                    timer.setRepeats(false);
                    timer.start();
                } else {
                    JOptionPane.showMessageDialog(Frame, "Ha ocurrido un error al intentar cambiar la contraseña.", "Sistema de Ventas", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(Frame, "La contraseña es muy debil...", "Sistema de Ventas", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(Frame, "Las contraseñas no coinciden. Por favor revise y vuelve a intentarlo.", "Sistema de Ventas", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void focusTextField() {
        txtPassword.requestFocusInWindow();
    }

    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JButton btnChangePassword;
    private PasswordStrengthStatus passwordStrengthStatus;
}
