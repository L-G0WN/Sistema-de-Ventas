package com.monagas.view.sales.style;

import com.formdev.flatlaf.FlatClientProperties;
import com.monagas.view.sales.renderer.TableCenterRenderer;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FlatStyle {

    public static void setStyle(JScrollPane scrollpane, JTable table) {
        table.setDefaultRenderer(Long.class, new TableCenterRenderer());
        table.setDefaultRenderer(String.class, new TableCenterRenderer());

        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "background:@accentColor;"
                + "foreground:#ffffff;"
                + "font:bold;");

        scrollpane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "trackInsets:3,3,3,3;"
                + "thumbInsets:3,3,3,3;"
                + "background:lighten(@accentColor, 45%);");

        scrollpane.getHorizontalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "trackInsets:3,3,3,3;"
                + "thumbInsets:3,3,3,3;"
                + "background:lighten(@accentColor, 45%);");
    }
}
