package com.monagas.view.sales.renderer.cell.PanelAction;

import com.monagas.services.sales.ProductService;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditor extends DefaultCellEditor {

    private final ProductService service = new ProductService();
    private final TableActionEvent event;

    public TableActionCellEditor(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        PanelAction action = new PanelAction();
        action.initEvent(event, row);

        Long id = (jtable.getRowCount() > 0) ? Long.valueOf(jtable.getValueAt(row, 1).toString().substring(2)) : null;

        action.getBtnDelete().setEnabled((id != null) ? service.findProductRelation(id) : false);

        if (action.getBtnDelete().isEnabled()) {
            action.getBtnDelete().setForeground(new Color(255, 255, 255));
            action.getBtnDelete().setBackground(new Color(185, 39, 39));
        }

        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
