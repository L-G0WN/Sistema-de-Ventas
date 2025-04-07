package com.monagas.view.sales.forms;

import com.monagas.api.CurrencyApi;
import com.monagas.controllers.sales.ClientController;
import com.monagas.controllers.sales.ProductController;
import com.monagas.controllers.sales.SellingController;
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
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Sellings extends CustomJPanel {

    private final ClientController clientController = new ClientController();
    private final ProductController productController = new ProductController();
    private final SellingController sellingController = new SellingController();

    private final CurrencyApi currency = new CurrencyApi();

    private final Frame parent;
    private boolean isReturn;
    private Long invoiceId;
    private JDialog dialog;
    private JTable tblHistory;
    
    public Sellings(Frame parent, boolean isReturn, Long invoiceId, JDialog dialog, JTable tblHistory) {
        this.parent = parent;
        this.isReturn = isReturn;
        this.invoiceId = invoiceId;
        this.dialog = dialog;
        this.tblHistory = tblHistory;
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
                    case KeyEvent.VK_F1 ->
                        btnSell.doClick();
                    case KeyEvent.VK_F2 ->
                        btnCancel.doClick();
                    case KeyEvent.VK_F3 ->
                        btnProducts.doClick();
                    default -> {
                    }
                }
            }
            return false;
        });

        eventField(txtCedula);
        goReturn(isReturn, invoiceId);
    }

    private void goReturn(boolean isReturn, Long invoiceId) {
        cbType.setEnabled(!isReturn);
        txtCedula.setEnabled(!isReturn);
        btnSearch.setEnabled(!isReturn);
        btnClear.setEnabled(!isReturn);

        if (isReturn) {
            sellingController.loadClientByInvoiceId(invoiceId, cbType, txtCedula, txtFirstname, txtLastname, cbCode, txtPhone, txtState, txtCity, txtTown, txtParish, txtDetails);
        }
        refreshData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelProducts = new javax.swing.JPanel();
        spSellings = new javax.swing.JScrollPane();
        tblSellings = new CustomJTable();
        spSelling = new javax.swing.JScrollPane();
        panelSelling = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        cbType = new javax.swing.JComboBox<>();
        txtCedula = new CustomJTextField("Cedula");
        btnSearch = new javax.swing.JButton();
        txtLastname = new CustomJTextField("Lastname");
        txtFirstname = new CustomJTextField("Firstname");
        cbCode = new javax.swing.JComboBox<>();
        txtPhone = new CustomJTextField("Phone");
        txtState = new CustomJTextField("State");
        txtCity = new CustomJTextField("City");
        txtTown = new CustomJTextField("Town");
        txtParish = new CustomJTextField("Parish");
        txtDetails = new CustomJTextField("Details");
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnProducts = new javax.swing.JButton();
        btnPlus = new javax.swing.JButton();
        btnLess = new javax.swing.JButton();
        btnSell = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        panelTotal = new javax.swing.JPanel();
        lbTotal = new javax.swing.JLabel();
        lbTotalProducts = new javax.swing.JLabel();
        lbBs = new javax.swing.JLabel();

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
                "CÓD.", "PRODUCTO", "CANT.", "P.V. $", "SUBTOTAL $", "P.V. BS", "SUBTOTAL BS", ""
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProductsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spSellings, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelProductsLayout.setVerticalGroup(
            panelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spSellings, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        spSelling.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N
        spSelling.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

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

        cbCode.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbCode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0424", "0414", "0416", "0426", "0412" }));

        txtPhone.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtState.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtCity.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtTown.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtParish.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtDetails.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

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
                .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSellingLayout.createSequentialGroup()
                        .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtParish, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtLastname, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFirstname, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelSellingLayout.createSequentialGroup()
                                        .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSearch)))
                                .addComponent(txtDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelSellingLayout.createSequentialGroup()
                        .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSellingLayout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(lbTitle))
                            .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSell, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnProducts, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelSellingLayout.createSequentialGroup()
                                        .addComponent(btnPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLess, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(panelSellingLayout.createSequentialGroup()
                                            .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelSellingLayout.createSequentialGroup()
                                            .addComponent(cbCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtTown, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelSellingLayout.setVerticalGroup(
            panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSellingLayout.createSequentialGroup()
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbType)
                    .addComponent(txtCedula)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbCode, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTown, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtParish, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(txtDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLess, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSell, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        spSelling.setViewportView(panelSelling);

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
                .addComponent(spSelling, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spSelling, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        clientController.loadClientByCedula(parent, cbType, txtCedula, txtFirstname, txtLastname, cbCode, txtPhone, txtState, txtCity, txtTown, txtParish, txtDetails);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        clientController.editClient(parent, null, null, null, cbType, txtCedula, txtFirstname, txtLastname, cbCode, txtPhone, txtState, txtCity, txtTown, txtParish, txtDetails);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        cbType.setSelectedIndex(0);
        txtCedula.setText("");
        txtFirstname.setText("");
        txtLastname.setText("");
        cbCode.setSelectedIndex(0);
        txtPhone.setText("");
        txtState.setText("");
        txtCity.setText("");
        txtTown.setText("");
        txtParish.setText("");
        txtDetails.setText("");
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
        if (sellingController.commerceExist() != 0) {
            int amountTotal = Integer.parseInt(lbTotalProducts.getText().replace("Cantidad de Productos : ", ""));
            double total = Double.parseDouble(lbTotal.getText().replace("Monto : ", "").replace(",", ".").replace("$", ""));
            String Bs = lbBs.getText().replace("Monto en Bs. : ", "").replace(",", ".").replace(" Bs.", "");
            double totalBs = (Bs.equals("Error al obtener la información del dólar.")) ? 0.0 : Double.parseDouble(Bs);

            if (!txtCedula.getText().isEmpty() && !txtFirstname.getText().isEmpty() && !txtLastname.getText().isEmpty() && !txtPhone.getText().isEmpty()) {
                if (isReturn) {
                    new DialogMethod(parent, true, tblSellings, invoiceId, cbType, txtCedula, txtFirstname, txtLastname, cbCode, txtPhone, txtState, txtCity, txtTown, txtParish, txtDetails, amountTotal, total, totalBs, btnClear, lbTotal, lbBs, lbTotalProducts, isReturn, dialog, tblHistory).setVisible(true);
                } else {
                    new DialogMethod(parent, true, tblSellings, null, cbType, txtCedula, txtFirstname, txtLastname, cbCode, txtPhone, txtState, txtCity, txtTown, txtParish, txtDetails, amountTotal, total, totalBs, btnClear, lbTotal, lbBs, lbTotalProducts, isReturn, null, null).setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(
                        parent,
                        "Por favor, complete los datos del cliente requeridos para la facturación.",
                        "Sistema de Ventas - Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(parent,
                    "No hay información del comercio para realizar la factura.",
                    "Sistema de Ventas - Error",
                    JOptionPane.ERROR_MESSAGE);
        }
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
    private javax.swing.JScrollPane spSelling;
    private javax.swing.JScrollPane spSellings;
    private javax.swing.JTable tblSellings;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtDetails;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JTextField txtParish;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtState;
    private javax.swing.JTextField txtTown;
    // End of variables declaration//GEN-END:variables
}
