package com.monagas.view.sales.renderer.cell.PanelRemove;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableActionCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
        PanelRemove remove = new PanelRemove();
        if (isSeleted == false && row % 2 == 0) {
            remove.setBackground(Color.WHITE);
        } else {
            remove.setBackground(com.getBackground());
        }
        return remove;
    }
}
