package com.monagas.view.sales.forms.dialogs;

import com.monagas.entities.login.CurrentUser;
import com.monagas.entities.login.User;
import com.monagas.view.sales.forms.Account;
import com.monagas.view.sales.forms.Commerce;
import com.monagas.view.sales.forms.Controls;
import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class DialogSettings extends JDialog {

    private final User currentUser = CurrentUser.getInstance().getUser();

    public DialogSettings(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tpWindows.putClientProperty("JTabbedPane.tabType", "card");
        tpWindows.putClientProperty("JTabbedPane.hasFullBorder", true);

        loadTaps(parent);
    }

    private void loadTaps(Frame parent) {
        ImageIcon iconAccount = new ImageIcon(getClass().getResource("/images/iconAccount20.png"));
        ImageIcon iconCommerce = new ImageIcon(getClass().getResource("/images/iconCommerce20.png"));
        ImageIcon iconControls = new ImageIcon(getClass().getResource("/images/iconControls20.png"));

        if (currentUser.getAccountType() == 1) {
            tpWindows.addTab("Mi Cuenta", iconAccount, new Account(parent));
            tpWindows.addTab("Comercio", iconCommerce, new Commerce(parent));
            tpWindows.addTab("Control de Usuarios", iconControls, new Controls(parent));
        } else {
            tpWindows.addTab("Mi Cuenta", iconAccount, new Account(parent));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpWindows = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajustes de Cuenta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpWindows, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tpWindows, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane tpWindows;
    // End of variables declaration//GEN-END:variables
}
