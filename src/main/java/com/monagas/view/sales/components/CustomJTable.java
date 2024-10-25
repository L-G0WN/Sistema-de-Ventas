package com.monagas.view.sales.components;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class CustomJTable extends JTable {

    private final int MARGIN = 5;

    public CustomJTable() {
        getColumnModel().addColumnModelListener(new TableColumnModelListener() {
            @Override
            public void columnAdded(TableColumnModelEvent e) {
                adjustColumnWidths();
            }

            @Override
            public void columnRemoved(TableColumnModelEvent e) {
                adjustColumnWidths();
            }

            @Override
            public void columnMoved(TableColumnModelEvent e) {
                adjustColumnWidths();
            }

            @Override
            public void columnMarginChanged(ChangeEvent e) {
            }

            @Override
            public void columnSelectionChanged(ListSelectionEvent e) {
            }
        });
    }

    private void adjustColumnWidths() {
        TableColumnModel modelColumn = getColumnModel();

        int column = 0;

        while (column < getColumnCount()) {
            TableColumn tableColumn = modelColumn.getColumn(column);
            int preferredWidth = 160;

            TableCellRenderer headerRenderer = getTableHeader().getDefaultRenderer();
            Object headerValue = tableColumn.getHeaderValue();
            Component headerComponent = headerRenderer.getTableCellRendererComponent(this, headerValue, false, false, 0, column);
            preferredWidth = Math.max(preferredWidth, headerComponent.getPreferredSize().width);

            for (int row = 0; row < getRowCount(); row++) {
                TableCellRenderer cellRenderer = getCellRenderer(row, column);
                Component cellComponent = cellRenderer.getTableCellRendererComponent(this, null, false, false, row, column);
                preferredWidth = Math.max(preferredWidth, cellComponent.getPreferredSize().width);
            }

            preferredWidth += 2 * MARGIN;

            tableColumn.setPreferredWidth(preferredWidth);
            column++;
        }
    }
}
