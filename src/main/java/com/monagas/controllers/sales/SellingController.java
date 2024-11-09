package com.monagas.controllers.sales;

import com.monagas.entities.login.CurrentUser;
import com.monagas.entities.login.User;
import com.monagas.entities.sales.Client;
import com.monagas.entities.sales.Product;
import com.monagas.entities.sales.Selling;
import com.monagas.entities.sales.SellingProduct;
import com.monagas.services.login.UserService;
import com.monagas.services.sales.ClientService;
import com.monagas.services.sales.ProductService;
import com.monagas.services.sales.SellingService;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SellingController {

    private final UserService userService = new UserService();
    private final ClientService clientService = new ClientService();
    private final ProductService productService = new ProductService();
    private final SellingService sellingService = new SellingService();
    private final User currentUser = CurrentUser.getInstance().getUser();

    public Selling createSelling(Frame parent,
            JTable table,
            JComboBox cbType,
            JTextField txtCedula,
            JTextField txtFirstname,
            JTextField txtLastname,
            JComboBox cbCode,
            JTextField txtPhone,
            JTextField txtAddress,
            Integer amountTotal,
            Double total,
            Double totalBs,
            JButton button) {
        String type = cbType.getSelectedItem().toString();
        String cedula = txtCedula.getText();
        String firstname = txtFirstname.getText().toUpperCase();
        String lastname = txtLastname.getText().toUpperCase();
        String code = cbCode.getSelectedItem().toString();
        String phone = txtPhone.getText();
        String address = txtAddress.getText().toUpperCase();

        Client client = new Client();
        Selling selling = new Selling();

        if (!cedula.isEmpty() && !firstname.isEmpty() && !lastname.isEmpty() && !phone.isEmpty()) {
            if (table.getRowCount() > 0) {
                client.setType(type);
                client.setCedula(cedula);
                client.setFirstname(firstname);
                client.setLastname(lastname);
                client.setCode(code);
                client.setPhone(phone);
                client.setAddress(address);
                client.setRegisteredBy(currentUser);

                try {
                    clientService.createIfNotExist(type, cedula, client);
                    client.setClientId(clientService.findIdByCedula(type, cedula));

                    List<Product> products = new ArrayList<>();
                    List<Integer> amounts = new ArrayList<>();
                    List<Double> purchases = new ArrayList<>();
                    List<Double> subTotals = new ArrayList<>();
                    List<Double> purchasesBs = new ArrayList<>();
                    List<Double> subTotalsBs = new ArrayList<>();

                    for (int row = 0; row < table.getRowCount(); row++) {
                        Long productId = Long.valueOf(table.getValueAt(row, 0).toString().substring(2));
                        Integer amount = Integer.valueOf(table.getValueAt(row, 2).toString());
                        Double purchasePrice = Double.valueOf(table.getValueAt(row, 3).toString().replace(",", "."));
                        Double subTotal = Double.valueOf(table.getValueAt(row, 4).toString().replace(",", "."));
                        Double purchasePriceBs = Double.valueOf(table.getValueAt(row, 5).toString().replace(",", "."));
                        Double subTotalBs = Double.valueOf(table.getValueAt(row, 6).toString().replace(",", "."));

                        Product product = productService.findProductById(productId);
                        if (product != null) {
                            products.add(product);
                            amounts.add(amount);
                            purchases.add(purchasePrice);
                            subTotals.add(subTotal);
                            purchasesBs.add(purchasePriceBs);
                            subTotalsBs.add(subTotalBs);

                            productService.newAmount(productId, amount);
                        } else {
                            JOptionPane.showMessageDialog(
                                    parent,
                                    "Producto con ID " + productId + " no encontrado.",
                                    "Sistema de Ventas - Advertencia",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }

                    sellingService.createSelling(client, products, total, totalBs, amountTotal, amounts, purchases, subTotals, purchasesBs, subTotalsBs);
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0);
                    button.doClick();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            parent,
                            ex.getMessage(),
                            "Sistema de Ventas - Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(
                        parent,
                        "No hay productos registrados para la venta.",
                        "Sistema de Ventas - Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "Por favor, complete los datos del cliente requeridos para la facturación.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }

        return selling;
    }

    public List<Selling> loadSellings(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        List<Selling> sellings = sellingService.findSellingEntities();

        Object[] row;

        for (Selling selling : sellings) {
            User registeredBy = userService.findUserById(selling.getRegisteredBy().getUserId());
            Client client = clientService.findClientById(selling.getClient().getClientId());

            row = new Object[]{
                "V" + selling.getSellingId(),
                selling.getCreatedAt(),
                client.getType() + client.getCedula(),
                client.getFirstname() + " " + client.getLastname(),
                selling.getTotal(),
                selling.getTotalBs(),
                registeredBy.getFirstname() + " " + registeredBy.getLastname()
            };
            model.addRow(row);
        }

        return sellings;
    }

    public List<SellingProduct> loadProducts(JTable table, Long id) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        List<SellingProduct> products = sellingService.findSellingProductsById(id);

        Object[] row;

        for (SellingProduct product : products) {            
            row = new Object[]{
                "PD" + product.getProduct().getProductId(),
                product.getProduct().getDescription(),
                product.getAmount(),
                product.getPurchase(),
                product.getSubTotal(),
                product.getPurchaseBs(),
                product.getSubTotalBs()
            };
            model.addRow(row);
        }

        return products;
    }
}
