package com.monagas.view.sales.forms;

import com.monagas.view.sales.components.CustomJPanel;
import com.monagas.view.sales.components.CustomJTable;
import com.monagas.view.sales.components.CustomJTextField;
import com.monagas.view.sales.style.FlatStyle;

public class Sellings extends CustomJPanel {

    public Sellings() {
        initComponents();

        FlatStyle.setStyle(spSellings, tblSellings);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelProducts = new javax.swing.JPanel();
        spSellings = new javax.swing.JScrollPane();
        tblSellings = new CustomJTable();
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

        tblSellings.setAutoCreateRowSorter(true);
        tblSellings.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblSellings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "PRODUCTO", "CANTIDAD", "PRECIO", "SUBTOTAL", "ACCION"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
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
            tblSellings.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblSellings.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblSellings.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout panelProductsLayout = new javax.swing.GroupLayout(panelProducts);
        panelProducts.setLayout(panelProductsLayout);
        panelProductsLayout.setHorizontalGroup(
            panelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spSellings, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
        );
        panelProductsLayout.setVerticalGroup(
            panelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spSellings, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
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

        btnClear.setBackground(new java.awt.Color(185, 39, 39));
        btnClear.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("LIMPIAR");

        javax.swing.GroupLayout panelSellingLayout = new javax.swing.GroupLayout(panelSelling);
        panelSelling.setLayout(panelSellingLayout);
        panelSellingLayout.setHorizontalGroup(
            panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSellingLayout.createSequentialGroup()
                .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSellingLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelSellingLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSellingLayout.createSequentialGroup()
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtLastname)
                                .addComponent(txtFirstname)
                                .addGroup(panelSellingLayout.createSequentialGroup()
                                    .addComponent(cbCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelSellingLayout.createSequentialGroup()
                                .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch)
                        .addGap(0, 11, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelSellingLayout.setVerticalGroup(
            panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSellingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle)
                .addGap(12, 12, 12)
                .addGroup(panelSellingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbType, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(txtCedula)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSelling, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelSelling, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbCode;
    private javax.swing.JComboBox<String> cbType;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPanel panelProducts;
    private javax.swing.JPanel panelSelling;
    private javax.swing.JScrollPane spSellings;
    private javax.swing.JTable tblSellings;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
