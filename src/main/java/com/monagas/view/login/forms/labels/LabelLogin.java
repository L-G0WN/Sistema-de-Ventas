package com.monagas.view.login.forms.labels;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JLabel;

public class LabelLogin {

    public static JLabel welcomeLabel() {
        JLabel Label = new JLabel("¡Bienvenido de nuevo!");
        Label.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +10;"
                + "foreground:lighten(#000,20%);");
        return Label;
    }

    public static JLabel descriptionLabel() {
        JLabel Label = new JLabel("Por favor, inicie sesión para acceder a su cuenta");
        Label.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]foreground:lighten(@foreground,30%);");
        return Label;
    }
}
