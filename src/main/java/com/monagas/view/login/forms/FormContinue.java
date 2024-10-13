package com.monagas.view.login.forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.monagas.view.login.Login;
import com.monagas.view.login.components.FocusablePanel;
import com.monagas.view.login.forms.button.ButtonCancel;
import com.monagas.view.login.services.UserService;
import com.monagas.view.login.forms.labels.LabelRecovery;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import net.miginfocom.swing.MigLayout;

public class FormContinue extends JPanel implements FocusablePanel {

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
                if (e.getKeyCode() == KeyEvent.VK_ENTER && btnContinue.isEnabled()) {
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

        add(LabelRecovery.restoreLabel(), "gapy 5");
        add(LabelRecovery.descriptionLabel(), "gapbottom 10");
        add(new JLabel("Nombre de Usuario"), "gapy 5");
        add(txtUserContinue);
        add(btnContinue, "gapy 10");
        add(Cancel, "width 70:70:70, gapy 10, align center");
    }

    private void ActionPerformedContinue() {
        String userContinue = txtUserContinue.getText();

        if (UserService.userExists(userContinue)) {
            txtUserContinue.setEditable(false);
            btnContinue.setEnabled(false);
            Cancel.setEnabled(false);
            //notification(Type.SUCCESS, "Se ha encontrado un usuario. Por favor, revise su\ninformación y responda a la siguiente pregunta secreta.");

            Timer timer = new Timer(3000, ex -> {
                FlatAnimatedLafChange.showSnapshot();
                Frame.switchPanel(new FormVerify(Frame, userContinue));
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
            });

            timer.setRepeats(false);
            timer.start();

        } else {
            //notification(Type.ERROR, "Usuario no encontrado. Por favor, revise y vuelve a intentarlo.");
        }
    }

    @Override
    public void focusTextField() {
        txtUserContinue.requestFocusInWindow();
    }

    private JButton btnContinue;
    private JTextField txtUserContinue;
}
