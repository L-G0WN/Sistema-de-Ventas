package com.monagas.controllers.sales;

import com.monagas.entities.login.CurrentUser;
import com.monagas.entities.login.User;
import com.monagas.entities.sales.Category;
import com.monagas.entities.sales.Product;
import com.monagas.entities.sales.Supplier;
import com.monagas.services.login.UserService;
import com.monagas.services.sales.CategoryService;
import com.monagas.services.sales.ProductService;
import com.monagas.services.sales.SupplierService;
import java.awt.Frame;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ProductController {

    private final UserService userService = new UserService();
    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();
    private final SupplierService supplierService = new SupplierService();
    private final User currentUser = CurrentUser.getInstance().getUser();

    public Product createProduct(Frame parent, JDialog dialog, JTable table, JTextField txtDescription, JTextField txtPrice, JTextField txtPurchase, JTextField txtAmount, JComboBox cbCategories, JComboBox cbSuppliers) {
        String description = txtDescription.getText().toUpperCase();
        Double price = (txtPrice.getText() != null && !txtPrice.getText().isEmpty()) ? Double.valueOf(txtPrice.getText()) : null;
        Double purchase = (txtPurchase != null && !txtPurchase.getText().isEmpty()) ? Double.valueOf(txtPurchase.getText()) : null;
        Integer amount = (txtAmount != null && !txtAmount.getText().isEmpty()) ? Integer.valueOf(txtAmount.getText()) : null;
        String categoryName = (cbCategories.getSelectedIndex() == 0) ? null : cbCategories.getSelectedItem().toString();
        String supplierName = (cbSuppliers.getSelectedIndex() == 0) ? null : cbSuppliers.getSelectedItem().toString();

        Product product = new Product();
        Category category;
        Supplier supplier;

        if (!description.isEmpty() && price != null && purchase != null && amount != null && categoryName != null && supplierName != null) {
            category = categoryService.findCategoryByName(categoryName);
            supplier = supplierService.findSupplierByName(supplierName);

            product.setDescription(description);
            product.setPrice(price);
            product.setPurchase(purchase);
            product.setAmount(amount);
            product.setCategory(category);
            product.setSupplier(supplier);
            product.setRegisteredBy(currentUser);

            try {
                productService.create(product);
                dialog.dispose();
                loadProducts(table);
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
                    "Por favor, complete todos los campos requeridos para registrar el producto.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }

        return product;
    }

    public Product editProduct(Frame parent, JDialog dialog, JTable table, Long id, JTextField txtDescription, JTextField txtPrice, JTextField txtPurchase, JTextField txtAmount, JComboBox cbCategories, JComboBox cbSuppliers) {
        String description = txtDescription.getText().toUpperCase();
        Double price = (txtPrice.getText() != null && !txtPrice.getText().isEmpty()) ? Double.valueOf(txtPrice.getText()) : null;
        Double purchase = (txtPurchase != null && !txtPurchase.getText().isEmpty()) ? Double.valueOf(txtPurchase.getText()) : null;
        Integer amount = (txtAmount != null && !txtAmount.getText().isEmpty()) ? Integer.valueOf(txtAmount.getText()) : null;
        String categoryName = (cbCategories.getSelectedIndex() == 0) ? null : cbCategories.getSelectedItem().toString();
        String supplierName = (cbSuppliers.getSelectedIndex() == 0) ? null : cbSuppliers.getSelectedItem().toString();

        Product product = new Product();
        Category category;
        Supplier supplier;

        if (!description.isEmpty() && price != null && purchase != null && amount != null && categoryName != null && supplierName != null) {
            category = categoryService.findCategoryByName(categoryName);
            supplier = supplierService.findSupplierByName(supplierName);

            product.setProductId(id);
            product.setDescription(description);
            product.setPrice(price);
            product.setPurchase(purchase);
            product.setAmount(amount);
            product.setCategory(category);
            product.setSupplier(supplier);
            product.setUpdatedBy(currentUser);

            try {
                productService.edit(product);
                dialog.dispose();
                loadProducts(table);
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
                    "Por favor, complete todos los campos requeridos para actualizar.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }

        return product;
    }

    public void deleteProduct(Frame parent, JDialog dialog, JTable table, Long id) {
        try {
            boolean isDeleted = productService.destroy(id);

            if (isDeleted) {
                dialog.dispose();
                loadProducts(table);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    parent,
                    ex.getMessage(),
                    "Sistema de Ventas - Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public Product loadProductById(Long id, JTextField txtDescription, JTextField txtPrice, JTextField txtPurchase, JTextField txtAmount, JComboBox cbCategories, JComboBox cbSuppliers) {
        Product product = productService.findProductById(id);

        txtDescription.setText(product.getDescription());
        txtPrice.setText(product.getPrice().toString());
        txtPurchase.setText(product.getPurchase().toString());
        txtAmount.setText(product.getAmount().toString());
        cbCategories.setSelectedItem(product.getCategory().getName());
        cbSuppliers.setSelectedItem(product.getSupplier().getName());

        return product;
    }

    public List<Product> loadProducts(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        List<Product> products = productService.findProductEntities();
        int count = 1;

        Object[] row;

        for (Product product : products) {
            User registeredBy = userService.findUserById(product.getRegisteredBy().getUserId());
            User updatedBy = (product.getUpdatedBy() != null) ? userService.findUserById(product.getUpdatedBy().getUserId()) : null;

            row = new Object[]{
                count++,
                "PD" + product.getProductId(),
                product.getDescription(),
                product.getPrice() + "$",
                product.getPurchase() + "$",
                product.getAmount(),
                product.getCategory().getName(),
                product.getSupplier().getName(),
                product.getCreatedAt(),
                registeredBy.getPerson().getFirstname() + " " + registeredBy.getPerson().getLastname(),
                product.getUpdatedAt(),
                (updatedBy != null) ? updatedBy.getPerson().getFirstname() + " " + updatedBy.getPerson().getLastname() : null
            };

            model.addRow(row);
        }

        return products;
    }

    public void Amount(Frame parent, JTable table, boolean isPlusOrNot) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    parent,
                    "Por favor, Seleccione un producto para ajustar la cantidad.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Long id = Long.valueOf(table.getValueAt(selectedRow, 0).toString().substring(2));
        Product product = productService.findProductById(id);
        int currentValue = Integer.parseInt(table.getValueAt(selectedRow, 2).toString());
        double purchasePrice = product.getPurchase();
        double purchaseBs = Double.parseDouble(table.getValueAt(selectedRow, 5).toString().replace(",", "."));

        if (isPlusOrNot) {
            if (currentValue >= product.getAmount()) {
                return;
            }
            currentValue++;
        } else {
            if (currentValue <= 1) {
                return;
            }
            currentValue--;
        }

        double valueTotal = purchasePrice * currentValue;
        double valueTotalBs = purchaseBs * currentValue;
        table.setValueAt(currentValue, selectedRow, 2);
        table.setValueAt(decimalFormat.format(valueTotal), selectedRow, 4);
        table.setValueAt(decimalFormat.format(valueTotalBs), selectedRow, 6);
    }

    public List<Product> loadSellings(JTable tblProducts) {
        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();
        model.setRowCount(0);

        List<Product> products = productService.findProductEntities();
        Object[] row;

        for (Product product : products) {
            row = new Object[]{
                "PD" + product.getProductId(),
                product.getDescription(),
                product.getPurchase() + "$",
                product.getAmount()
            };

            model.addRow(row);
        }

        return products;
    }
}
