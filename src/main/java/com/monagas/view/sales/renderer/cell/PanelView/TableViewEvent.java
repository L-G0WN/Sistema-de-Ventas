package com.monagas.view.sales.renderer.cell.PanelView;

public interface TableViewEvent {

    public void onView(int row);
    
    public void onReturn(int row);

    public void onInvoice(int row);
}
