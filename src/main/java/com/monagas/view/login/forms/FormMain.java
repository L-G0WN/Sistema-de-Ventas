package com.monagas.view.login.forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.monagas.view.login.Login;
import com.monagas.view.login.components.CredentialManager;
import com.monagas.view.login.components.FocusablePanel;
import com.monagas.view.login.forms.labels.LabelLogin;
import com.monagas.view.login.models.Session;
import com.monagas.view.login.models.User;
import com.monagas.view.login.services.UserService;
import com.monagas.view.sales.Sales;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import net.miginfocom.swing.MigLayout;

public class FormMain extends JPanel implements FocusablePanel {

    private final UserService SERVICE = new UserService();

    private final Login Frame;

    public FormMain(Login Frame) {
        this.Frame = Frame;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setLayout(new MigLayout("wrap,fillx,insets 10 45 30 45", "fill,250:280"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:@background;");

        txtUsername = new JTextField();
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Introduce tu nombre de usuario");
        txtUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ActionPerformedLogin();
                }
            }
        });

        txtPassword = new JPasswordField();
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingresa tu contraseña");
        txtPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true");
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ActionPerformedLogin();
                }
            }
        });

        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]background:lighten(@accentColor,5%);"
                + "foreground:lighten(#ff0,50%);"
                + "font:14;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");
        btnLogin.addActionListener(e -> {
            ActionPerformedLogin();
        });

        cbRememberMe = new JCheckBox("Recordar Información");

        loadStoredCredentials();

        btnForget = new JButton("<html><a href='#'>Restablecer</a></html>");
        btnForget.setContentAreaFilled(false);
        btnForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnForget.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:3,3,3,3");
        btnForget.addActionListener(e -> {
            ActionPerformedForget();
        });

        JLabel lbForget = new JLabel("¿No recuerdas tú contraseña?");
        lbForget.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]foreground:lighten(@foreground,30%);");

        add(LabelLogin.welcomeLabel(), "gapy 5");
        add(LabelLogin.descriptionLabel(), "gapbottom 10");
        add(new JLabel("Nombre de Usuario"), "gapy 5");
        add(txtUsername);
        add(new JLabel("Contraseña"), "gapy 8");
        add(txtPassword);
        add(cbRememberMe, "gapy 5");
        add(btnLogin, "gapy 10");
        add(lbForget, "split 2, gapy 10, gapleft 25");
        add(btnForget, "gapright 40, gaptop 10");
    }

    private void loadStoredCredentials() {
        Properties credentials = CredentialManager.loadCredentials();
        String username = credentials.getProperty("username");

        if (username != null) {
            txtUsername.setText(username);
            cbRememberMe.setSelected(true);
        }
    }

    private void ActionPerformedLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        User UserApplication = SERVICE.auth(username, password);

        if (UserApplication != null) {
            txtUsername.setEditable(false);
            txtPassword.setEditable(false);
            cbRememberMe.setEnabled(false);
            btnLogin.setEnabled(false);
            btnForget.setEnabled(false);
            JOptionPane.showMessageDialog(Frame, "Iniciando Sessón...", "Sistema de Ventas", JOptionPane.INFORMATION_MESSAGE);

            if (cbRememberMe.isSelected()) {
                CredentialManager.saveCredentials(username);
            } else {
                CredentialManager.clearCredentials();
            }

            Session.setCurrentUser(UserApplication);

            Timer timer = new Timer(3000, ex -> {
                Frame.dispose();
                new Sales().setVisible(true);
            });

            timer.setRepeats(false);
            timer.start();

        } else {
            JOptionPane.showMessageDialog(Frame, "Usuario o contraseña incorrectos. Por favor, revise y vuelve a intentarlo.", "Sistema de Ventas", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ActionPerformedForget() {
        FlatAnimatedLafChange.showSnapshot();
        Frame.switchPanel(new FormContinue(Frame));
        FlatLaf.updateUI();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    @Override
    public void focusTextField() {
        txtUsername.requestFocusInWindow();
    }

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JCheckBox cbRememberMe;
    private JButton btnLogin;
    private JButton btnForget;
}
