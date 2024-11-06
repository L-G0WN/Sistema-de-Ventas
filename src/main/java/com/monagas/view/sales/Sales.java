package com.monagas.view.sales;

import com.monagas.api.CurrencyApi;
import com.monagas.entities.login.CurrentUser;
import com.monagas.entities.login.User;
import com.monagas.view.sales.forms.Clients;
import com.monagas.view.sales.forms.Products;
import com.monagas.view.sales.forms.Sellings;
import com.monagas.view.sales.forms.Suppliers;
import com.monagas.view.sales.util.ApplicationUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Sales extends JFrame {

    private final User currentUser = CurrentUser.getInstance().getUser();

    private CurrencyApi currency = new CurrencyApi();

    public Sales() {
        initComponents();

        tpWindows.putClientProperty("JTabbedPane.tabType", "card");
        tpWindows.putClientProperty("JTabbedPane.hasFullBorder", true);

        tpWindows.addTab("Clientes Registrados (F1)", new Clients(this));
        tpWindows.addTab("Productos (F2)", new Products(this));
        tpWindows.addTab("Proveedores (F4)", new Suppliers(this));
        tpWindows.addTab("Ventas (F5)", new Sellings(this));

        currentUser();

        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();

        updateDateTime();
        loadCurrency();
    }

    private void currentUser() {
        String type = (currentUser.getAccountType() == 1) ? "Administrador: " : "Empleado: ";
        itemControls.setVisible(currentUser.getAccountType() == 1);

        mAccount.setText(type + currentUser.getFirstname() + " " + currentUser.getLastname());
    }

    private void updateDateTime() {
        Date now = new Date();
        String dateFormat = new SimpleDateFormat("'Fecha :' EEEE, dd 'de' MMMM 'del' yyyy '- Hora :' hh:mm a").format(now);
        lbTime.setText(dateFormat);
    }

    private void loadCurrency() {
        currency.loadCurrencyData(cbList);
        currency.updateCurrencyInfo(cbList, lbCurrentPrice);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpWindows = new javax.swing.JTabbedPane();
        cbList = new javax.swing.JComboBox<>();
        lbCurrentPrice = new javax.swing.JLabel();
        lbTime = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        mAccount = new javax.swing.JMenu();
        itemControls = new javax.swing.JMenuItem();
        itemLogout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Ventas v" + ApplicationUtil.getVersion());
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/iconFrame20.png")).getImage());
        setMinimumSize(new java.awt.Dimension(1024, 600));

        tpWindows.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        cbList.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        cbList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbListActionPerformed(evt);
            }
        });

        lbCurrentPrice.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N

        lbTime.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lbTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTime.setText("DATE");

        mAccount.setText("Administrador: Angeles Hernandez");
        mAccount.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        itemControls.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        itemControls.setText("Control de Usuarios");
        mAccount.add(itemControls);

        itemLogout.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        itemLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconLogout20.png"))); // NOI18N
        itemLogout.setText("Cerrar Sesión");
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
                        .addComponent(cbList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(lbCurrentPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tpWindows, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbTime, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                        .addComponent(lbCurrentPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cbList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbListActionPerformed
        currency.updateCurrencyInfo(cbList, lbCurrentPrice);
    }//GEN-LAST:event_cbListActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JComboBox<String> cbList;
    private javax.swing.JMenuItem itemControls;
    private javax.swing.JMenuItem itemLogout;
    private javax.swing.JLabel lbCurrentPrice;
    private javax.swing.JLabel lbTime;
    public static javax.swing.JMenu mAccount;
    private javax.swing.JTabbedPane tpWindows;
    // End of variables declaration//GEN-END:variables
}
