package com.monagas.view.login;

import com.monagas.view.login.components.FocusablePanel;
import com.monagas.view.login.forms.FormMain;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Login extends JFrame {

    private final FormMain Main = new FormMain(this);

    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setTitle("Sistema de Ventas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(400, 430));
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/images/iconFrame20.png")).getImage());
        setContentPane(Main);
    }

    public void switchPanel(JPanel newPanel) {
        setContentPane(newPanel);

        if (newPanel instanceof FocusablePanel focusablePanel) {
            focusablePanel.focusTextField();
        }
    }
}
