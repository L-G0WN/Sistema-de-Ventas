package com.monagas.view.sales;

import com.monagas.view.sales.forms.Categories;
import com.monagas.view.sales.forms.Clients;
import com.monagas.view.sales.forms.Products;
import com.monagas.view.sales.forms.Sellings;
import com.monagas.view.sales.forms.Suppliers;
import com.monagas.view.sales.util.ApplicationUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Sales extends JFrame {

    public Sales() {
        initComponents();

        tpWindows.addTab("Clientes (F1)", new Clients());
        tpWindows.addTab("Productos (F2)", new Products());
        tpWindows.addTab("Categorias (F3)", new Categories());
        tpWindows.addTab("Proveedores (F4)", new Suppliers());
        tpWindows.addTab("Ventas (F5)", new Sellings());

        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();

        updateDateTime();
    }
//
    private void updateDateTime() {
        Date now = new Date();
        String dateFormat = new SimpleDateFormat("'Fecha :' EEEE, dd 'de' MMMM 'del' yyyy '- Hora :' hh:mm a").format(now);
        lbTime.setText(dateFormat);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpWindows = new javax.swing.JTabbedPane();
        lbTime = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        mAccount = new javax.swing.JMenu();
        itemLogout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Ventas v" + ApplicationUtil.getVersion());
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/iconFrame20.png")).getImage());
        setMinimumSize(new java.awt.Dimension(1024, 600));

        tpWindows.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        lbTime.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        lbTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTime.setText("DATE");

        mAccount.setText("Administrador: Angeles Hernandez");
        mAccount.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        itemLogout.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        itemLogout.setText("Cerrar Sesión");
        mAccount.add(itemLogout);

        MenuBar.add(mAccount);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpWindows, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tpWindows, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                .addGap(43, 43, 43)
                .addComponent(lbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem itemLogout;
    private javax.swing.JLabel lbTime;
    private javax.swing.JMenu mAccount;
    private javax.swing.JTabbedPane tpWindows;
    // End of variables declaration//GEN-END:variables
}
