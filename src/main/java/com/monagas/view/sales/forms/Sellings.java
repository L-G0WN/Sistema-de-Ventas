package com.monagas.view.sales.forms;

import com.monagas.api.CurrencyApi;
import com.monagas.controllers.sales.ClientController;
import com.monagas.controllers.sales.ProductController;
import com.monagas.view.sales.components.CustomJPanel;
import com.monagas.view.sales.components.CustomJTable;
import com.monagas.view.sales.components.CustomJTextField;
import com.monagas.view.sales.forms.dialogs.DialogMethod;
import com.monagas.view.sales.forms.dialogs.DialogSellings;
import com.monagas.view.sales.renderer.cell.PanelRemove.TableActionCellEditor;
import com.monagas.view.sales.renderer.cell.PanelRemove.TableActionCellRender;
import com.monagas.view.sales.renderer.cell.PanelRemove.TableRemoveEvent;
import com.monagas.view.sales.style.FlatStyle;
import java.awt.Frame;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Sellings extends CustomJPanel {

    private final ClientController clientController = new ClientController();
    private final ProductController productController = new ProductController();

    private final CurrencyApi currency = new CurrencyApi();

    private final Frame parent;

    public Sellings(Frame parent) {
        this.parent = parent;
        initComponents();

        FlatStyle.setStyle(spSellings, tblSellings);

        TableRemoveEvent event = (int row) -> {
            DefaultTableModel model = (DefaultTableModel) tblSellings.getModel();
            model.removeRow(row);
            refreshData();
        };

        tblSellings.getColumnModel().getColumn(tblSellings.getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
        tblSellings.getColumnModel().getColumn(tblSellings.getColumnCount() - 1).setCellEditor(new TableActionCellEditor(event));

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_F1 -> btnSell.doClick();
                    case KeyEvent.VK_F2 -> btnCancel.doClick();
                    case KeyEvent.VK_F3 -> btnProducts.doClick();
                    default -> {
                    }
                }
            }
            return false;
        });

        eventField(txtCedula);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelProducts = new javax.swing.JPanel();
        spSellings = new javax.swing.JScrollPane();
        tblSellings = new CustomJTable();
        panelTotal = new javax.swing.JPanel();
        lbTotal = new javax.swing.JLabel();
        lbTotalProducts = new javax.swing.JLabel();
        lbBs = new javax.swing.JLabel();
        panelSelling = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        cbType = new javax.swing.JComboBox<>();
        txtCedula = new CustomJTextField("Cedula");
        btnSearch = new javax.swing.JButton();
        txtLastname = new CustomJTextField("Lastname");
        txtFirstname = new CustomJTextField("Firstname");
        txtPhone = new CustomJTextField("Phone");
        txtAddress = new CustomJTextField("Address");
        cbCode = new javax.swing.JComboBox<>();
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnProducts = new javax.swing.JButton();
        btnPlus = new javax.swing.JButton();
        btnLess = new javax.swing.JButton();
        btnSell = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        tblSellings.setAutoCreateRowSorter(true);
        tblSellings.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblSellings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COD.", "PRODUCTO", "CANT.", "P.V. $", "SUBTOTAL $", "P.V. BS", "SUBTOTAL BS", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSellings.setGridColor(new java.awt.Color(102, 102, 102));
        tblSellings.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblSellings.setName("Clients"); // NOI18N
        tblSellings.setRowHeight(30);
        tblSellings.setSelectionBackground(new java.awt.Color(230, 230, 230));
        tblSellings.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblSellings.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSellings.setShowGrid(true);
        tblSellings.getTableHeader().setReorderingAllowed(false);
        spSellings.setViewportView(tblSellings);
        if (tblSellings.getColumnModel().getColumnCount() > 0) {
            tblSellings.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblSellings.getColumnModel().getColumn(1).setPreferredWidth(265);
            tblSellings.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblSellings.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblSellings.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblSellings.getColumnModel().getColumn(5).setPreferredWidth(80);
            tblSellings.getColumnModel().getColumn(6).setPreferredWidth(105);
            tblSellings.getColumnModel().getColumn(7).setPreferredWidth(50);
        }

        javax.swing.GroupLayout panelProductsLayout = new javax.swing.GroupLayout(panelProducts);
        panelProducts.setLayout(panelProductsLayout);
        panelProductsLayout.setHorizontalGroup(
            panelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spSellings, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        panelProductsLayout.setVerticalGroup(
            panelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spSellings, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        panelTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL A PAGAR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        lbTotal.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbTotal.setText("Monto :");

        lbTotalProducts.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbTotalProducts.setText("Cantidad de Productos :");

        lbBs.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbBs.setText("Monto en Bs. :");

        javax.swing.GroupLayout panelTotalLayout = new javax.swing.GroupLayout(panelTotal);
        panelTotal.setLayout(panelTotalLayout);
        panelTotalLayout.setHorizontalGroup(
            panelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTotal)
                    .addComponent(lbBs)
                    .addComponent(lbTotalProducts))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTotalLayout.setVerticalGroup(
            panelTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotalProducts)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelSelling.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        lbTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("CLIENTE");

        cbType.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "V-", "E-", "P-" }));

        txtCedula.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LogoSearch.png"))); // NOI18N
        btnSearch.setToolTipText("Buscar cliente registrado en el sistema.");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtLastname.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtFirstname.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtPhone.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtAddress.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        cbCode.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbCode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0424", "0414", "0416", "0426", "0412" }));

        btnUpdate.setBackground(new java.awt.Color(232, 213, 42));
        btnUpdate.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("ACTUALIZAR");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(185, 39, 39));
        btnClear.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("LIMPIAR");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnProducts.setBackground(new java.awt.Color(39, 92, 183));
        btnProducts.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnProducts.setForeground(new java.awt.Color(255, 255, 255));
        btnProducts.setText("AGREGAR PRODUCTO (F3)");
        btnProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductsActionPerformed(evt);
            }
        });

        btnPlus.setBackground(new java.awt.Color(39, 92, 183));
        btnPlus.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnPlus.setForeground(new java.awt.Color(255, 255, 255));
        btnPlus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconPlus.png"))); // NOI18N
        btnPlus.setText("CANT.");
        btnPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusActionPerformed(evt);
            }
        });

        btnLess.setBackground(new java.awt.Color(39, 92, 183));
        btnLess.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnLess.setForeground(new java.awt.Color(255, 255, 255));
        btnLess.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconLess20.png"))); // NOI18N
        btnLess.setText("CANT.");
        btnLess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLessActionPerformed(evt);
            }
        });

        btnSell.setBackground(new java.awt.Color(39, 92, 183));
        btnSell.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnSell.setForeground(new java.awt.Color(255, 255, 255));
        btnSell.setText("GENERAR VENTA (F1)");
        btnSell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSellActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(39, 92, 183));
        btnCancel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("CANCELAR VENTA (F2)");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSellingLayout = new javax.swing.GroupLayout(panelSelling);
        panelSelling.setLayout(panelSellingLayout);
        panelSellingLayout.setHorizontalGroup(
            panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSellingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSellingLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSell, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelSellingLayout.createSequentialGroup()
                        .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch)
                        .addGap(2, 2, 2))
                    .addGroup(panelSellingLayout.createSequentialGroup()
                        .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtLastname, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFirstname, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelSellingLayout.createSequentialGroup()
                        .addComponent(cbCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtAddress, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelSellingLayout.createSequentialGroup()
                        .addComponent(btnPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLess, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelSellingLayout.setVerticalGroup(
            panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSellingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle)
                .addGap(12, 12, 12)
                .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbType)
                    .addComponent(txtCedula)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbCode, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLess, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSell, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSelling, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelSelling, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        clientController.loadClientByCedula(parent, cbType, txtCedula, txtFirstname, txtLastname, cbCode, txtPhone, txtAddress);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        clientController.editClient(parent, null, null, null, cbType, txtCedula, txtFirstname, txtLastname, cbCode, txtPhone, txtAddress);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        cbType.setSelectedIndex(0);
        txtCedula.setText("");
        txtFirstname.setText("");
        txtLastname.setText("");
        cbCode.setSelectedIndex(0);
        txtPhone.setText("");
        txtAddress.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductsActionPerformed
        new DialogSellings(parent, true, tblSellings).setVisible(true);
        refreshData();
    }//GEN-LAST:event_btnProductsActionPerformed

    private void btnPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusActionPerformed
        productController.Amount(parent, tblSellings, true);
        refreshData();
    }//GEN-LAST:event_btnPlusActionPerformed

    private void btnLessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLessActionPerformed
        productController.Amount(parent, tblSellings, false);
        refreshData();
    }//GEN-LAST:event_btnLessActionPerformed

    private void btnSellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSellActionPerformed
        int amountTotal = Integer.parseInt(lbTotalProducts.getText().replace("Cantidad de Productos : ", ""));
        double total = Double.parseDouble(lbTotal.getText().replace("Monto : ", "").replace(",", ".").replace("$", ""));
        double totalBs = Double.parseDouble(lbBs.getText().replace("Monto en Bs. : ", "").replace(",", ".").replace(" Bs.", ""));

        new DialogMethod(parent, false, tblSellings, cbType, txtCedula, txtFirstname, txtLastname, cbCode, txtPhone, txtAddress, amountTotal, total, totalBs, btnClear, lbTotal, lbBs, lbTotalProducts).setVisible(true);
    }//GEN-LAST:event_btnSellActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblSellings.getModel();
        model.setRowCount(0);

        refreshData();
        btnClear.doClick();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        refreshData();
    }//GEN-LAST:event_formComponentShown

    public void refreshData() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double value = 0;

        if (tblSellings.getRowCount() > 0) {
            for (int i = 0; i < tblSellings.getRowCount(); i++) {
                value += Double.parseDouble(tblSellings.getValueAt(i, 4).toString().replace(",", ".").replace("$", ""));
            }

            lbTotal.setText("Monto : " + decimalFormat.format(value) + "$");
            currency.convertCurrency(parent, value, lbBs);
            lbTotalProducts.setText("Cantidad de Productos : " + tblSellings.getRowCount());
        } else {
            lbTotal.setText("Monto : " + 0 + "$");
            lbBs.setText("Monto en Bs. : " + 0 + " Bs.");
            lbTotalProducts.setText("Cantidad de Productos : " + 0);
        }
    }

    private void eventField(JTextField txtCedula) {
        txtCedula.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnSearch.doClick();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnLess;
    private javax.swing.JButton btnPlus;
    private javax.swing.JButton btnProducts;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSell;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbCode;
    private javax.swing.JComboBox<String> cbType;
    private javax.swing.JLabel lbBs;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JLabel lbTotalProducts;
    private javax.swing.JPanel panelProducts;
    private javax.swing.JPanel panelSelling;
    private javax.swing.JPanel panelTotal;
    private javax.swing.JScrollPane spSellings;
    private javax.swing.JTable tblSellings;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
