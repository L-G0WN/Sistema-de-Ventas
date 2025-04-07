package com.monagas.controllers.sales;

import com.monagas.entities.Address;
import com.monagas.entities.Person;
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
import javax.swing.JDialog;
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
            JDialog dialog,
            JTable table,
            Long oldInvoiceId,
            JComboBox cbType,
            JTextField txtCedula,
            JTextField txtFirstname,
            JTextField txtLastname,
            JComboBox cbCode,
            JTextField txtPhone,
            JTextField txtState, JTextField txtCity, JTextField txtTown, JTextField txtParish, JTextField txtDetails,
            Integer amountTotal,
            Double total,
            Double totalBs,
            JButton button,
            String method,
            boolean isReturn,
            JTable tblHistory) {
        String type = cbType.getSelectedItem().toString();
        String cedula = txtCedula.getText();
        String firstname = txtFirstname.getText().toUpperCase();
        String lastname = txtLastname.getText().toUpperCase();
        String code = cbCode.getSelectedItem().toString();
        String phone = txtPhone.getText();
        String state = txtState.getText().toUpperCase();
        String city = txtCity.getText().toUpperCase();
        String town = txtTown.getText().toUpperCase();
        String parish = txtParish.getText().toUpperCase();
        String details = txtDetails.getText().toUpperCase();
        
        Address address = new Address();
        Person person = new Person();
        Client client = new Client();

        Selling selling = new Selling();

        if (table.getRowCount() > 0) {
            address.setState(state);
            address.setCity(city);
            address.setTown(town);
            address.setParish(parish);
            address.setAddressDetails(details);

            person.setFirstname(firstname);
            person.setLastname(lastname);
            person.setAddress(address);

            client.setPerson(person);
            client.setCedula(type + cedula);
            client.setPhone(code + "-" + phone);
            client.setRegisteredBy(currentUser);

            try {
                clientService.createIfNotExist(type + cedula, address, person, client);
                client.setClientId(clientService.findIdByCedula(type + cedula));

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
                                "Producto con CÓD. " + productId + " no encontrado.",
                                "Sistema de Ventas - Advertencia",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }

                sellingService.createSelling(oldInvoiceId, client, products, total, totalBs, amountTotal, amounts, purchases, subTotals, purchasesBs, subTotalsBs, method, isReturn);
                if (oldInvoiceId != null) {
                    dialog.dispose();
                }
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                button.doClick();
                if (tblHistory != null) {
                    loadSellings(tblHistory, false);
                }
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

        return selling;
    }

    public void editSelling(Frame parent,
            Long oldInvoiceId, boolean isReturn) {
        try {
            sellingService.editSelling(oldInvoiceId, isReturn);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    parent,
                    ex.getMessage(),
                    "Sistema de Ventas - Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Selling> loadSellings(JTable table, boolean isReturn) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        List<Selling> sellings = sellingService.findSellingEntities();

        Object[] row;

        for (Selling selling : sellings) {
            if (selling.isReturn() == isReturn) {
                User registeredBy = userService.findUserById(selling.getRegisteredBy().getUserId());
                Client client = clientService.findClientById(selling.getClient().getClientId());

                row = new Object[]{
                    "V" + selling.getSellingId(),
                    selling.getCreatedAt(),
                    client.getCedula(),
                    client.getPerson().getFirstname() + " " + client.getPerson().getLastname(),
                    selling.getTotal(),
                    selling.getTotalBs(),
                    selling.getMethod(),
                    registeredBy.getPerson().getFirstname() + " " + registeredBy.getPerson().getLastname()
                };
                model.addRow(row);
            }
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

    public Selling loadClientByInvoiceId(Long invoiceId,
            JComboBox cbType, JTextField txtCedula,
            JTextField txtFirstname, JTextField txtLastname,
            JComboBox cbCode, JTextField txtPhone,
            JTextField txtState, JTextField txtCity, JTextField txtTown, JTextField txtParish, JTextField txtDetails) {
        Selling selling = sellingService.findSellingById(invoiceId);

        cbType.setSelectedItem(selling.getClient().getCedula().replaceAll(".*-", ""));
        txtCedula.setText(selling.getClient().getCedula());
        txtFirstname.setText(selling.getClient().getPerson().getFirstname());
        txtLastname.setText(selling.getClient().getPerson().getLastname());
        cbCode.setSelectedItem(selling.getClient().getPhone().replaceAll(".*-", ""));
        txtPhone.setText(selling.getClient().getPhone());
        txtState.setText(selling.getClient().getPerson().getAddress().getState());
        txtCity.setText(selling.getClient().getPerson().getAddress().getCity());
        txtTown.setText(selling.getClient().getPerson().getAddress().getTown());
        txtParish.setText(selling.getClient().getPerson().getAddress().getParish());
        txtDetails.setText(selling.getClient().getPerson().getAddress().getAddressDetails());

        return selling;
    }

    public Long commerceExist() {
        return sellingService.getCommerceCount();
    }
}
