package com.monagas.view.sales.components;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class CustomJPanel extends JPanel {

    public CustomJPanel() {
        setBackground(new Color(255, 255, 255));
        setBorder(BorderFactory.createTitledBorder(""));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;");
    }
}
