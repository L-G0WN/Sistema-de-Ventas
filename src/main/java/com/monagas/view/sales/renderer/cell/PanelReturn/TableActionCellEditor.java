package com.monagas.view.sales.renderer.cell.PanelReturn;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditor extends DefaultCellEditor {

    private final TableReturnEvent event;

    public TableActionCellEditor(TableReturnEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        PanelReturn view = new PanelReturn();
        view.initEvent(event, row);
        view.setBackground(jtable.getSelectionBackground());
        return view;
    }
}