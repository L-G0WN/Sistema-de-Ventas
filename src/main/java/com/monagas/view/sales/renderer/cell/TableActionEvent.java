package com.monagas.view.sales.renderer.cell;

public interface TableActionEvent {

    public void onEdit(int row);

    public void onDelete(int row);

}
