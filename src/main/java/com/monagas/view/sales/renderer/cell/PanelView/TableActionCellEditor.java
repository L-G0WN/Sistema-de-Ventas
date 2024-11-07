package com.monagas.view.sales.renderer.cell.PanelView;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditor extends DefaultCellEditor {

    private final TableViewEvent event;

    public TableActionCellEditor(TableViewEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        PanelView view = new PanelView();
        view.initEvent(event, row);
        view.setBackground(jtable.getSelectionBackground());
        return view;
    }
}