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

        cbQuestions1 = new JComboBox();
        cbQuestions1.setModel(new DefaultComboBoxModel<>(new String[]{
            "¿CÓMO SE LLAMA TU MASCOTA?",
            "¿EN QUÉ CIUDAD NACIÓ TU MADRE?",
            "¿EN QUÉ CIUDAD NACIÓ TU PADRE?",
            "¿CUÁL ES TU PELÍCULA FAVORITA?"}));
        cbQuestions1.setEditable(false);

        txtAnswer1 = new JPasswordField();
        txtAnswer1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingresa tu respuesta 1");
        txtAnswer1.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton: true");
        txtAnswer1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ActionPerformedVerify();
                }
            }
        });

        cbQuestions2 = new JComboBox();
        cbQuestions2.setModel(new DefaultComboBoxModel<>(new String[]{
            "¿CUÁL ES TU COMIDA FAVORITA?",
            "¿QUÉ NOMBRE RECIBIÓ TU PRIMERA MASCOTA?",
            "¿CUÁL ES TU LIBRO PREFERIDO?",
            "¿QUÉ DEPORTE TE GUSTA MÁS?"
        }));
        cbQuestions2.setEditable(false);

        txtAnswer2 = new JPasswordField();
        txtAnswer2.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingresa tu respuesta 2");
        txtAnswer2.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton: true");
        txtAnswer2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ActionPerformedVerify();
                }
            }
        });

        cbQuestions3 = new JComboBox();
        cbQuestions3.setModel(new DefaultComboBoxModel<>(new String[]{
            "¿CUÁL ES TU SUEÑO MÁS GRANDE?",
            "¿QUÉ LENGUAJE TE GUSTARÍA APRENDER?",
            "¿CUÁL ES TU RECUERDO FAVORITO?",
            "¿QUÉ HACES EN TU TIEMPO LIBRE?"
        }));
        cbQuestions3.setEditable(false);

        txtAnswer3 = new JPasswordField();
        txtAnswer3.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingresa tu respuesta 3");
        txtAnswer3.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton: true");
        txtAnswer3.addKeyListener(new KeyAdapter() {
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
        add(new JLabel("Pregunta de Seguridad 1"), "gapy 5");
        add(cbQuestions1);
        add(new JLabel("Respuesta 1"), "gapy 8");
        add(txtAnswer1);
        add(new JLabel("Pregunta de Seguridad 2"), "gapy 5");
        add(cbQuestions2);
        add(new JLabel("Respuesta 2"), "gapy 8");
        add(txtAnswer2);
        add(new JLabel("Pregunta de Seguridad 3"), "gapy 5");
        add(cbQuestions3);
        add(new JLabel("Respuesta 3"), "gapy 8");
        add(txtAnswer3);
        add(btnVerify, "gapy 10");
        add(Cancel, "width 70:70:70, gapy 10, align center");
    }

    private void ActionPerformedVerify() {
        controller.verify(Frame, username, cbQuestions1, txtAnswer1, cbQuestions2, txtAnswer2, cbQuestions3, txtAnswer3, btnVerify, Cancel);
    }

    @Override
    public void focusTextField() {
        txtAnswer1.requestFocusInWindow();
    }

    private JComboBox cbQuestions1;
    private JPasswordField txtAnswer1;
    private JComboBox cbQuestions2;
    private JPasswordField txtAnswer2;
    private JComboBox cbQuestions3;
    private JPasswordField txtAnswer3;
    private JButton btnVerify;
}
