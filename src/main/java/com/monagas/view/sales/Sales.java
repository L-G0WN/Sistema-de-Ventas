package com.monagas.view.sales;

import com.monagas.view.sales.forms.Categories;
import com.monagas.view.sales.forms.Clients;
import com.monagas.view.sales.forms.Products;
import com.monagas.view.sales.forms.Sellings;
import com.monagas.view.sales.forms.Suppliers;
import javax.swing.JFrame;

public class Sales extends JFrame {

    public Sales() {
        initComponents();
        
        tpWindows.addTab("Clientes (F1)", new Clients());
        tpWindows.addTab("Categorias (F2)", new Categories());
        tpWindows.addTab("Productos (F3)", new Products());
        tpWindows.addTab("Proveedores (F4)", new Suppliers());
        tpWindows.addTab("Ventas (F5)", new Sellings());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpWindows = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Ventas");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/iconFrame20.png")).getImage());
        setMinimumSize(new java.awt.Dimension(1024, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpWindows, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpWindows, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane tpWindows;
    // End of variables declaration//GEN-END:variables
}
