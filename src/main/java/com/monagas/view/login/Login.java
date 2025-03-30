package com.monagas.view.login;

import com.monagas.services.EntityManagerFactoryProvider;
import com.monagas.view.login.components.FocusablePanel;
import com.monagas.view.login.forms.FormMain;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EntityManagerFactoryProvider.closeEntityManagerFactory();
            }
        });

        setContentPane(Main);
    }

    public void switchPanel(JPanel newPanel, int height) {
        setContentPane(newPanel);
        
        setSize(400, (height != 0) ? height : 430);
        
        if (newPanel instanceof FocusablePanel focusablePanel) {
            focusablePanel.focusTextField();
        }
    }
}
