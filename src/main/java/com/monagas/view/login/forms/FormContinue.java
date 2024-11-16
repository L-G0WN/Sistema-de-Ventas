package com.monagas.view.login.forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.monagas.controllers.login.UserController;
import com.monagas.view.login.Login;
import com.monagas.view.login.components.FocusablePanel;
import com.monagas.view.login.forms.button.ButtonCancel;
import com.monagas.view.login.forms.labels.LabelLogo;
import com.monagas.view.login.forms.labels.LabelRecovery;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class FormContinue extends JPanel implements FocusablePanel {

    private final UserController controller = new UserController();

    private final Login Frame;
    private final ButtonCancel Cancel;

    public FormContinue(Login Frame) {
        this.Frame = Frame;
        Cancel = new ButtonCancel(Frame);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setLayout(new MigLayout("wrap,fillx,insets 15 45 30 45", "fill,250:280"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:@background;");

        txtUserContinue = new JTextField();
        txtUserContinue.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Introduce tu nombre de usuario");
        txtUserContinue.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ActionPerformedContinue();
                }
            }
        });

        btnContinue = new JButton("Continuar");
        btnContinue.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]background:lighten(@accentColor,5%);"
                + "foreground:lighten(#ff0,50%);"
                + "font:14;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");
        btnContinue.addActionListener(e -> {
            ActionPerformedContinue();
        });

        add(new LabelLogo(), "gapright 40");
        add(LabelRecovery.restoreLabel(), "gapy 5");
        add(LabelRecovery.descriptionLabel(), "gapbottom 10");
        add(new JLabel("Nombre de Usuario"), "gapy 5");
        add(txtUserContinue);
        add(btnContinue, "gapy 10");
        add(Cancel, "width 70:70:70, gapy 10, align center");
    }

    private void ActionPerformedContinue() {
        controller.findUserByUsername(Frame, txtUserContinue, btnContinue, Cancel);
    }

    @Override
    public void focusTextField() {
        txtUserContinue.requestFocusInWindow();
    }

    private JButton btnContinue;
    private JTextField txtUserContinue;
}
