package com.monagas.view.sales.renderer.cell.PanelRemove;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditor extends DefaultCellEditor {

    private final TableRemoveEvent event;

    public TableActionCellEditor(TableRemoveEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        PanelRemove selected = new PanelRemove();
        selected.initEvent(event, row);
        selected.setBackground(jtable.getSelectionBackground());
        return selected;
    }
}