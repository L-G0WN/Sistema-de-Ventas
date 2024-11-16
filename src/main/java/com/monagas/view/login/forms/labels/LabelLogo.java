package com.monagas.view.login.forms.labels;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class LabelLogo extends JLabel {
    
    public LabelLogo() {
        setText("SociAdm");
        setHorizontalAlignment(SwingUtilities.CENTER);
        setIcon(new ImageIcon(getClass().getResource("/images/iconFrame20.png")));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +10;"
                + "foreground:lighten(@accentColor,10%);");
    }
}
