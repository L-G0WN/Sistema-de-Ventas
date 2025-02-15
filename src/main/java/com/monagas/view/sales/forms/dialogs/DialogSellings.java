package com.monagas.view.sales.forms.dialogs;

import com.monagas.api.CurrencyApi;
import com.monagas.controllers.sales.ProductController;
import com.monagas.view.sales.components.CustomJTable;
import com.monagas.view.sales.components.CustomJTextField;
import com.monagas.view.sales.renderer.cell.PanelSelected.TableActionCellEditor;
import com.monagas.view.sales.renderer.cell.PanelSelected.TableActionCellRender;
import com.monagas.view.sales.renderer.cell.PanelSelected.TableSelectedEvent;
import com.monagas.view.sales.style.FlatStyle;
import java.awt.Frame;
import java.text.DecimalFormat;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DialogSellings extends JDialog {

    private final ProductController controller = new ProductController();

    private final CurrencyApi currency = new CurrencyApi();

    private boolean alreadyExist;

    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public DialogSellings(Frame parent, boolean modal, JTable tblSellings) {
        super(parent, modal);
        initComponents();

        FlatStyle.setStyle(spProducts, tblProducts);

        TableSelectedEvent event = (int row) -> {
            String id = tblProducts.getValueAt(row, 0).toString();
            String description = tblProducts.getValueAt(row, 1).toString();
            double price = Double.parseDouble(tblProducts.getValueAt(row, 2).toString().replace("$", ""));
            int amount = Integer.parseInt(tblProducts.getValueAt(row, 3).toString());
            
            Double priceBs = currency.convertPrice(parent, price);
            
            alreadyExist = false;

            if (tblSellings.getRowCount() > 0) {
                for (int i = 0; i < tblSellings.getRowCount(); i++) {
                    if (tblSellings.getValueAt(i, 0).toString().equals(id)) {
                        alreadyExist = true;
                        break;
                    }
                }
            }

            if (!alreadyExist) {
                if (amount > 0) {
                    DefaultTableModel model = (DefaultTableModel) tblSellings.getModel();
                    model.addRow(new Object[]{id, description, 1, price, price, decimalFormat.format(priceBs), decimalFormat.format(priceBs)});
                } else {
                    JOptionPane.showMessageDialog(parent,
                            "No hay stock disponible para este producto seleccionado.",
                            "Sistema de Ventas - Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(parent,
                        "El producto ya se encuentra seleccionado, por favor verifique y continue.",
                        "Sistema de Ventas - Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        };

        tblProducts.getColumnModel().getColumn(tblProducts.getColumnCount() - 1).setCellRenderer(new TableActionCellRender());
        tblProducts.getColumnModel().getColumn(tblProducts.getColumnCount() - 1).setCellEditor(new TableActionCellEditor(event));

        controller.loadSellings(tblProducts);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spProducts = new javax.swing.JScrollPane();
        tblProducts = new CustomJTable();
        txtSearch = new CustomJTextField(tblProducts);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Productos");
        setResizable(false);

        tblProducts.setAutoCreateRowSorter(true);
        tblProducts.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "DESCRIPCIÓN", "PRECIO", "STOCK", "ACCIÓN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProducts.setGridColor(new java.awt.Color(102, 102, 102));
        tblProducts.setName("Products2"); // NOI18N
        tblProducts.setRowHeight(30);
        tblProducts.setRowMargin(1);
        tblProducts.setSelectionBackground(new java.awt.Color(230, 230, 230));
        tblProducts.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblProducts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblProducts.setShowGrid(true);
        tblProducts.getTableHeader().setReorderingAllowed(false);
        spProducts.setViewportView(tblProducts);
        if (tblProducts.getColumnModel().getColumnCount() > 0) {
            tblProducts.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblProducts.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblProducts.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        txtSearch.setName("Products2"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spProducts, javax.swing.GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
                    .addComponent(txtSearch))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spProducts, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane spProducts;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
