package com.monagas.view.sales;

import com.monagas.api.CurrencyApi;
import com.monagas.entities.login.CurrentUser;
import com.monagas.entities.login.User;
import com.monagas.services.EntityManagerFactoryProvider;
import com.monagas.view.login.Login;
import com.monagas.view.sales.forms.Clients;
import com.monagas.view.sales.forms.History;
import com.monagas.view.sales.forms.Products;
import com.monagas.view.sales.forms.Return;
import com.monagas.view.sales.forms.Sellings;
import com.monagas.view.sales.forms.Suppliers;
import com.monagas.view.sales.forms.dialogs.DialogFirstime;
import com.monagas.view.sales.forms.dialogs.DialogInput;
import com.monagas.view.sales.forms.dialogs.DialogSettings;
import com.monagas.view.sales.print.Reports;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Sales extends JFrame {

    private final User currentUser = CurrentUser.getInstance().getUser();
    private final Reports report = new Reports();

    private final CurrencyApi currency = new CurrencyApi();

    public Sales() {
        initComponents();

        tpWindows.putClientProperty("JTabbedPane.tabType", "card");
        tpWindows.putClientProperty("JTabbedPane.hasFullBorder", true);

        loadTaps();
        currentUser();

        Timer timer = new Timer(1000, e -> updateDateTime());
        Timer timerCurrency = new Timer(1000, e -> loadCurrency());
        timer.start();
        timerCurrency.start();
        
        if (currentUser.isFirstime()) {
            new DialogFirstime(this, true, currentUser.getUserId()).setVisible(true);
        }
    }

    private void loadTaps() {
        if (currentUser.getAccountType() == 1) {
            tpWindows.addTab("Ventas", new Sellings(this, false, null, null, null));
            tpWindows.addTab("Historial de Ventas", new History(this));
            tpWindows.addTab("Devoluciones", new Return(this));
            tpWindows.addTab("Clientes Registrados", new Clients(this));
            tpWindows.addTab("Productos", new Products(this));
            tpWindows.addTab("Proveedores", new Suppliers(this));
        } else {
            tpWindows.addTab("Ventas", new Sellings(this, false, null, null, null));
            mExport.setVisible(false);
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
        mExport = new javax.swing.JMenu();
        itemHistory = new javax.swing.JMenuItem();
        itemDate = new javax.swing.JMenuItem();
        itemEmploye = new javax.swing.JMenuItem();
        itemBoth = new javax.swing.JMenuItem();
        itemReturn = new javax.swing.JMenuItem();
        itemReturnDate = new javax.swing.JMenuItem();
        itemClients = new javax.swing.JMenuItem();
        itemExport = new javax.swing.JMenuItem();
        itemLogout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Ventas");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/iconFrame20.png")).getImage());
        setMinimumSize(new java.awt.Dimension(1024, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tpWindows.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        lbCurrentPrice.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N

        lbTime.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lbTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

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

        mExport.setText("Exportar Reportes");
        mExport.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        mExport.setMargin(new java.awt.Insets(8, 6, 8, 6));

        itemHistory.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        itemHistory.setText("Reporte Completo");
        itemHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemHistoryActionPerformed(evt);
            }
        });
        mExport.add(itemHistory);

        itemDate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        itemDate.setText("Reporte por Fecha");
        itemDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDateActionPerformed(evt);
            }
        });
        mExport.add(itemDate);

        itemEmploye.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        itemEmploye.setText("Reporte por Empleado");
        itemEmploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEmployeActionPerformed(evt);
            }
        });
        mExport.add(itemEmploye);

        itemBoth.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        itemBoth.setText("Reporte por Empleado y Fecha");
        itemBoth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBothActionPerformed(evt);
            }
        });
        mExport.add(itemBoth);

        itemReturn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        itemReturn.setText("Reporte de Devoluciones Completo");
        itemReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemReturnActionPerformed(evt);
            }
        });
        mExport.add(itemReturn);

        itemReturnDate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        itemReturnDate.setText("Reporte de Devoluciones por Fecha");
        itemReturnDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemReturnDateActionPerformed(evt);
            }
        });
        mExport.add(itemReturnDate);

        itemClients.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        itemClients.setText("Reporte de Clientes Completo");
        itemClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemClientsActionPerformed(evt);
            }
        });
        mExport.add(itemClients);

        mAccount.add(mExport);

        itemExport.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        itemExport.setText("Reporte de Ventas");
        itemExport.setMargin(new java.awt.Insets(8, 6, 8, 6));
        itemExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExportActionPerformed(evt);
            }
        });
        mAccount.add(itemExport);

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

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        EntityManagerFactoryProvider.closeEntityManagerFactory();
    }//GEN-LAST:event_formWindowClosing

    private void itemHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemHistoryActionPerformed
        report.generateReport("Reporte de Ventas Completo", null, null, null);
    }//GEN-LAST:event_itemHistoryActionPerformed

    private void itemBothActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBothActionPerformed
        new DialogInput(this, true, "Both").setVisible(true);
    }//GEN-LAST:event_itemBothActionPerformed

    private void itemDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDateActionPerformed
        new DialogInput(this, true, "Date").setVisible(true);
    }//GEN-LAST:event_itemDateActionPerformed

    private void itemEmployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEmployeActionPerformed
        new DialogInput(this, true, "Employe").setVisible(true);
    }//GEN-LAST:event_itemEmployeActionPerformed

    private void itemExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExportActionPerformed
        new DialogInput(this, true, "Individual").setVisible(true);
    }//GEN-LAST:event_itemExportActionPerformed

    private void itemReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemReturnActionPerformed
        report.generateReport("Reporte de Devoluciones Completo", null, null, null);
    }//GEN-LAST:event_itemReturnActionPerformed

    private void itemReturnDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemReturnDateActionPerformed
        new DialogInput(this, true, "Date2").setVisible(true);
    }//GEN-LAST:event_itemReturnDateActionPerformed

    private void itemClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemClientsActionPerformed
        report.generateReport("Reporte de Clientes Completo", null, null, null);
    }//GEN-LAST:event_itemClientsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem itemBoth;
    private javax.swing.JMenuItem itemClients;
    private javax.swing.JMenuItem itemDate;
    private javax.swing.JMenuItem itemEmploye;
    private javax.swing.JMenuItem itemExport;
    private javax.swing.JMenuItem itemHistory;
    private javax.swing.JMenuItem itemLogout;
    private javax.swing.JMenuItem itemReturn;
    private javax.swing.JMenuItem itemReturnDate;
    private javax.swing.JMenuItem itemSettings;
    private javax.swing.JLabel lbCurrentPrice;
    private javax.swing.JLabel lbTime;
    public static javax.swing.JMenu mAccount;
    private javax.swing.JMenu mExport;
    private javax.swing.JTabbedPane tpWindows;
    // End of variables declaration//GEN-END:variables
}
