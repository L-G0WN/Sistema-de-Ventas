package com.monagas.view.sales.renderer;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCenterRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setHorizontalAlignment(getAlignment(column));

        if (value != null) {
            if (value instanceof String) {
                value = value.toString().toUpperCase();
            }
        }
        
        return super.getTableCellRendererComponent(jtable, value, isSelected, hasFocus, row, column);
    }

    protected int getAlignment(int column) {
        return SwingConstants.CENTER;
    }
}
