package com.monagas.view.sales;

import com.monagas.view.sales.forms.Categories;
import com.monagas.view.sales.forms.Clients;
import com.monagas.view.sales.forms.Products;
import com.monagas.view.sales.forms.Sellings;
import com.monagas.view.sales.forms.Suppliers;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Sales extends JFrame {

    public Sales() {
        initComponents();

        tpWindows.addTab("Clientes (F1)", new Clients());
        tpWindows.addTab("Categorias (F2)", new Categories());
        tpWindows.addTab("Productos (F3)", new Products());
        tpWindows.addTab("Proveedores (F4)", new Suppliers());
        tpWindows.addTab("Ventas (F5)", new Sellings());

        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();

        updateDateTime();
    }

    private void updateDateTime() {
        Date now = new Date();
        
        String longDateFormat = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy").format(now);
        String shortDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(now);
        
        lbTime.setText(longDateFormat + ", " + shortDateFormat);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpWindows = new javax.swing.JTabbedPane();
        lbTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Ventas");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/iconFrame20.png")).getImage());
        setMinimumSize(new java.awt.Dimension(1024, 600));

        lbTime.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTime.setText("TIME");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpWindows, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTime, javax.swing.GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tpWindows, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTime)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbTime;
    private javax.swing.JTabbedPane tpWindows;
    // End of variables declaration//GEN-END:variables
}
