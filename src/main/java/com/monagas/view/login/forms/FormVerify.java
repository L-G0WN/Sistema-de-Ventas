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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class FormVerify extends JPanel implements FocusablePanel {

    private final UserController controller = new UserController();

    private final Login Frame;
    private final String username;
    private final ButtonCancel Cancel;

    public FormVerify(Login Frame, String username) {
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

        cbQuestions = new JComboBox();
        cbQuestions.setModel(new DefaultComboBoxModel<>(new String[]{"¿CÓMO SE LLAMA TU MASCOTA?", "¿EN QUÉ CIUDAD NACIÓ TU MADRE?", "¿EN QUÉ CIUDAD NACIÓ TU PADRE?", "¿CUÁL ES TU PELÍCULA FAVORITA?"}));
        cbQuestions.setEditable(false);

        txtAnswer = new JPasswordField();
        txtAnswer.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingresa tu respuesta");
        txtAnswer.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton: true");
        txtAnswer.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ActionPerformedVerify();
                }
            }
        });

        btnVerify = new JButton("Verificar");
        btnVerify.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]background:lighten(@accentColor,5%);"
                + "foreground:lighten(#ff0,50%);"
                + "font:14;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");
        btnVerify.addActionListener(e -> {
            ActionPerformedVerify();
        });

        add(new LabelLogo(), "gapright 40");
        add(LabelRecovery.restoreLabel(), "gapy 5");
        add(LabelRecovery.descriptionLabel(), "gapbottom 10");
        add(new JLabel("Pregunta de Seguridad"), "gapy 5");
        add(cbQuestions);
        add(new JLabel("Respuesta"), "gapy 8");
        add(txtAnswer);
        add(btnVerify, "gapy 10");
        add(Cancel, "width 70:70:70, gapy 10, align center");
    }

    private void ActionPerformedVerify() {
        controller.verify(Frame, username, cbQuestions, txtAnswer, btnVerify, Cancel);
    }

    @Override
    public void focusTextField() {
        txtAnswer.requestFocusInWindow();
    }

    private JComboBox cbQuestions;
    private JPasswordField txtAnswer;
    private JButton btnVerify;
}
