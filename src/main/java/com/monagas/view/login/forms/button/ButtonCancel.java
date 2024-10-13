package com.monagas.view.login.forms.button;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.monagas.view.login.Login;
import com.monagas.view.login.forms.FormMain;
import java.awt.Cursor;
import javax.swing.JButton;

public class ButtonCancel extends JButton {

    private final Login Frame;

    public ButtonCancel(Login Frame) {
        this.Frame = Frame;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setText("<html><a href=\"#\">Cancelar</a></html>");
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "border:3,3,3,3");
        addActionListener(e -> {
            ActionPerfomedCancel();
        });
    }

    public void ActionPerfomedCancel() {
        FlatAnimatedLafChange.showSnapshot();
        Frame.switchPanel(new FormMain(Frame));
        FlatLaf.updateUI();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }
}
