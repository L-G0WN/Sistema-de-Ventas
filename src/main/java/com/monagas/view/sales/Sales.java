package com.monagas.view.sales;

import com.monagas.api.CurrencyApi;
import com.monagas.entities.login.CurrentUser;
import com.monagas.entities.login.User;
import com.monagas.view.login.Login;
import com.monagas.view.sales.forms.Clients;
import com.monagas.view.sales.forms.History;
import com.monagas.view.sales.forms.Products;
import com.monagas.view.sales.forms.Sellings;
import com.monagas.view.sales.forms.Suppliers;
import com.monagas.view.sales.forms.dialogs.DialogSettings;
import com.monagas.view.sales.util.ApplicationUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Sales extends JFrame {

    private final User currentUser = CurrentUser.getInstance().getUser();

    private final CurrencyApi currency = new CurrencyApi();

    public Sales() {
        initComponents();

        tpWindows.putClientProperty("JTabbedPane.tabType", "card");
        tpWindows.putClientProperty("JTabbedPane.hasFullBorder", true);

        loadTaps();
        currentUser();

        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();

        updateDateTime();
        loadCurrency();
    }

    private void loadTaps() {
        if (currentUser.getAccountType() == 1) {
            tpWindows.addTab("Ventas", new Sellings(this));
            tpWindows.addTab("Historial de Ventas", new History(this));
            tpWindows.addTab("Clientes Registrados", new Clients(this));
            tpWindows.addTab("Productos", new Products(this));
            tpWindows.addTab("Proveedores", new Suppliers(this));
        } else {
            tpWindows.addTab("Ventas", new Sellings(this));
        }
    }

    private void currentUser() {
        String type = (currentUser.getAccountType() == 1) ? "Administrador: " : "Empleado: ";

        mAccount.setText(type + currentUser.getFirstname() + " " + currentUser.getLastname());
    }

    private void updateDateTime() {
        Date now = new Date();
        String dateFormat = new SimpleDateFormat("'Fecha :' EEEE, dd 'de' MMMM 'del' yyyy '- Hora :' hh:mm a").format(now);
        lbTime.setText(dateFormat);
    }

    private void loadCurrency() {
        currency.updateCurrencyInfo(this, lbCurrentPrice);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpWindows = new javax.swing.JTabbedPane();
        lbCurrentPrice = new javax.swing.JLabel();
        lbTime = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        mAccount = new javax.swing.JMenu();
        itemSettings = new javax.swing.JMenuItem();
        itemLogout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Ventas v" + ApplicationUtil.getVersion());
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/iconFrame20.png")).getImage());
        setMinimumSize(new java.awt.Dimension(1024, 600));

        tpWindows.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        lbCurrentPrice.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N

        lbTime.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lbTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTime.setText("DATE");

        mAccount.setText("Username");
        mAccount.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        itemSettings.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemSettings.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        itemSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconSettings16.png"))); // NOI18N
        itemSettings.setText("Ajustes de Cuenta");
        itemSettings.setMargin(new java.awt.Insets(8, 6, 8, 6));
        itemSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSettingsActionPerformed(evt);
            }
        });
        mAccount.add(itemSettings);

        itemLogout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemLogout.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        itemLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconLogout16.png"))); // NOI18N
        itemLogout.setText("Cerrar Sesión");
        itemLogout.setMargin(new java.awt.Insets(8, 6, 8, 6));
        itemLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLogoutActionPerformed(evt);
            }
        });
        mAccount.add(itemLogout);

        MenuBar.add(mAccount);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tpWindows, javax.swing.GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbCurrentPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tpWindows, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTime, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(lbCurrentPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void itemSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSettingsActionPerformed
        new DialogSettings(this, true, currentUser, mAccount).setVisible(true);
    }//GEN-LAST:event_itemSettingsActionPerformed

    private void itemLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLogoutActionPerformed
        CurrentUser.getInstance().clear();
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_itemLogoutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem itemLogout;
    private javax.swing.JMenuItem itemSettings;
    private javax.swing.JLabel lbCurrentPrice;
    private javax.swing.JLabel lbTime;
    public static javax.swing.JMenu mAccount;
    private javax.swing.JTabbedPane tpWindows;
    // End of variables declaration//GEN-END:variables
}
