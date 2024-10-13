package com.monagas.view.login.forms.labels;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JLabel;

public class LabelRecovery {

    public static JLabel restoreLabel() {
        JLabel Label = new JLabel("¡Recuperación de Contraseña!");
        Label.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +10;"
                + "foreground:lighten(#000,20%);");
        return Label;
    }
    public static JLabel descriptionLabel() {
        JLabel Label = new JLabel("Por favor, restablezca su contraseña para acceder");
        Label.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]foreground:lighten(@foreground,30%);");
        return Label;
    }
}
