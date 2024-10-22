package com.monagas.application;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.monagas.view.sales.Sales;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Run {

    public static void main(String args[]) {

        try {
            FlatRobotoFont.install();
            FlatLaf.registerCustomDefaultsSource("theme");
            UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
            FlatLightLaf.setup();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Failed to initialize LaF");
        }

        java.awt.EventQueue.invokeLater(() -> {
            new Sales().setVisible(true);
        });
    }
}
