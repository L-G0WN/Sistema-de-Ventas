package com.monagas.application;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.monagas.services.DataInitializer;
import com.monagas.services.EntityManagerFactoryProvider;
import com.monagas.view.login.Login;
import javax.swing.JOptionPane;

public class Run {

    private static final DataInitializer initializer = new DataInitializer();

    public static void main(String[] args) {
        EntityManagerFactoryProvider.createEntityManagerFactory();

        try {
            initializer.initializeData();
            FlatLaf.registerCustomDefaultsSource("theme");
            FlatLightLaf.setup();

            java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Failed to initialize LaF: " + ex.getMessage());
        }
    }
}
