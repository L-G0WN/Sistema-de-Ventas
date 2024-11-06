package com.monagas.view.sales.renderer.cell.PanelAction;

public interface TableActionEvent {

    public void onEdit(int row);

    public void onDelete(int row);
}
