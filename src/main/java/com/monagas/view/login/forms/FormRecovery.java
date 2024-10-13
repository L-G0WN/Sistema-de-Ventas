package com.monagas.view.login.forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.monagas.view.login.Login;
import com.monagas.view.login.components.FocusablePanel;
import com.monagas.view.login.forms.button.ButtonCancel;
import com.monagas.view.login.components.PasswordStrengthStatus;
import com.monagas.view.login.services.UserService;
import com.monagas.view.login.forms.labels.LabelRecovery;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.Timer;
import net.miginfocom.swing.MigLayout;

public class FormRecovery extends JPanel implements FocusablePanel {

    private final String userContinue;
    private final ButtonCancel Cancel;

    public FormRecovery(Login Frame, String userContinue) {
        this.userContinue = userContinue;
        Cancel = new ButtonCancel(Frame);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setLayout(new MigLayout("wrap,fillx,insets 15 45 30 45", "fill,250:280"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:@background;");

        txtNewPassword = new JPasswordField();
        txtNewPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Introduce tu nueva contraseña");
        txtNewPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true");
        txtNewPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && btnChangePassword.isEnabled()) {
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
                if (e.getKeyCode() == KeyEvent.VK_ENTER && btnChangePassword.isEnabled()) {
                    ActionPerformedChangePassword();
                }
            }
        });

        passwordStrengthStatus = new PasswordStrengthStatus();
        passwordStrengthStatus.initPasswordField(txtNewPassword);

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
        add(txtNewPassword);
        add(passwordStrengthStatus, "gapy 0");
        add(new JLabel("Confirmar Contraseña"), "gapy 8");
        add(txtConfirmPassword);
        add(btnChangePassword, "gapy 10");
        add(Cancel, "width 70:70:70, gapy 10, align center");
    }

    private void ActionPerformedChangePassword() {
        String newPassword = new String(txtNewPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());
        int passwordStrength = passwordStrengthStatus.getPasswordStrengthType();

        if (newPassword.equals(confirmPassword)) {
            if ((passwordStrength == 2) || (passwordStrength == 3)) {
                if (UserService.changePassword(userContinue, newPassword)) {
                    txtNewPassword.setEditable(false);
                    txtConfirmPassword.setEditable(false);
                    btnChangePassword.setEnabled(false);
                    Cancel.setEnabled(false);
                    //notification(Type.SUCCESS, "La contraseña se ha cambiado exitosamente.");

                    Timer timer = new Timer(3000, ex -> {
                        Cancel.ActionPerfomedCancel();
                    });

                    timer.setRepeats(false);
                    timer.start();
                } else {
                    //notification(Type.ERROR, "Ha ocurrido un fallo al intentar cambiar la contraseña.");
                }
            } else {
                //notification(Type.ERROR, "La contraseña es muy debil...");
            }
        } else {
            //notification(Type.ERROR, "Las contraseñas no coinciden. Por favor revise y vuelve a intentarlo.");
        }
    }

    @Override
    public void focusTextField() {
        txtNewPassword.requestFocusInWindow();
    }

    private JPasswordField txtNewPassword;
    private JPasswordField txtConfirmPassword;
    private JButton btnChangePassword;
    private PasswordStrengthStatus passwordStrengthStatus;
}
