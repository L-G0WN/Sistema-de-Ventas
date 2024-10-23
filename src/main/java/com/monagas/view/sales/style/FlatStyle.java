package com.monagas.view.sales.style;

import com.formdev.flatlaf.FlatClientProperties;
import com.monagas.view.sales.renderer.TableCenterRenderer;
import javax.swing.JTable;

public class FlatStyle {

    public static void setStyle(JTable table) {
        table.setDefaultRenderer(String.class, new TableCenterRenderer());
        table.setDefaultRenderer(Integer.class, new TableCenterRenderer());

        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "background:@accentColor;"
                + "foreground:#ffffff;"
                + "font:bold;");
    }
}
