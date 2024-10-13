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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import net.miginfocom.swing.MigLayout;

public class FormVerify extends JPanel implements FocusablePanel {

    private final Login Frame;
    private final String userContinue;
    private final ButtonCancel Cancel;

    public FormVerify(Login Frame, String userContinue) {
        this.Frame = Frame;
        this.userContinue = userContinue;
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

        txtAnswer = new JTextField();
        txtAnswer.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingresa tu respuesta");
        txtAnswer.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && btnVerify.isEnabled()) {
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
        String cbQuestion = cbQuestions.getSelectedItem().toString();
        String answerVerify = txtAnswer.getText();

        if (UserService.verifyQuestion(userContinue, cbQuestion, answerVerify)) {
            txtAnswer.setEditable(false);
            cbQuestions.setEnabled(false);
            btnVerify.setEnabled(false);
            Cancel.setEnabled(false);
            //notification(Type.SUCCESS, "Enhorabuena! Los datos que has introducido son correctos.");

            Timer timer = new Timer(3000, ex -> {
                FlatAnimatedLafChange.showSnapshot();
                Frame.switchPanel(new FormRecovery(Frame, userContinue));
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
            });

            timer.setRepeats(false);
            timer.start();

        } else {
            //notification(Type.ERROR, "Pregunta o Respuesta son incorrectos. Por favor, revise y vuelve a intentarlo.");
        }
    }

    @Override
    public void focusTextField() {
        txtAnswer.requestFocusInWindow();
    }

    private JComboBox cbQuestions;
    private JTextField txtAnswer;
    private JButton btnVerify;
}
