package com.monagas.controllers.sales;

import com.monagas.entities.login.CurrentUser;
import com.monagas.entities.login.User;
import com.monagas.entities.sales.Brand;
import com.monagas.entities.sales.Category;
import com.monagas.entities.sales.Product;
import com.monagas.services.login.UserService;
import com.monagas.services.sales.BrandService;
import com.monagas.services.sales.CategoryService;
import com.monagas.services.sales.ProductService;
import java.awt.Frame;
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
    private final BrandService brandService = new BrandService();
    private final CategoryService categoryService = new CategoryService();
    private final User currentUser = CurrentUser.getInstance().getUser();

    public Product createProduct(Frame parent, JDialog dialog, JTable table, JTextField txtDescription, JTextField txtPrice, JTextField txtPurchase, JTextField txtAmount, JComboBox cbBrands, JComboBox cbCategories) {
        String description = txtDescription.getText().toUpperCase();
        Double price = Double.valueOf(txtPrice.getText());
        Double purchase = Double.valueOf(txtPurchase.getText());
        Integer amount = Integer.valueOf(txtAmount.getText());
        String brandName = (cbBrands.getSelectedIndex() == 0) ? null : cbBrands.getSelectedItem().toString();
        String categoryName = (cbCategories.getSelectedIndex() == 0) ? null : cbCategories.getSelectedItem().toString();

        Product product = new Product();
        Brand brand;
        Category category;

        if (!description.isEmpty() && price != 0 && purchase != 0 && amount != 0) {
            brand = (brandName != null) ? brandService.findBrandByName(brandName) : null;
            category = (categoryName != null) ? categoryService.findCategoryByName(categoryName) : null;

            product.setDescription(description);
            product.setPrice(price);
            product.setPurchase(purchase);
            product.setAmount(amount);
            product.setBrand(brand);
            product.setCategory(category);
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
                    "Por favor, complete todos los campos requeridos para registrar al proveedor.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }

        return product;
    }

    public Product editProduct(Frame parent, JDialog dialog, JTable table, Long id, JTextField txtDescription, JTextField txtPrice, JTextField txtPurchase, JTextField txtAmount, JComboBox cbBrands, JComboBox cbCategories) {
        String description = txtDescription.getText().toUpperCase();
        Double price = Double.valueOf(txtPrice.getText());
        Double purchase = Double.valueOf(txtPurchase.getText());
        Integer amount = Integer.valueOf(txtAmount.getText());
        String brandName = (cbBrands.getSelectedIndex() == 0) ? null : (cbBrands.getSelectedItem() != null) ? cbBrands.getSelectedItem().toString() : null;
        String categoryName = (cbCategories.getSelectedIndex() == 0) ? null : (cbCategories.getSelectedItem() != null) ? cbCategories.getSelectedItem().toString() : null;

        Product product = new Product();
        Brand brand;
        Category category;

        if (!description.isEmpty() && price != 0 && purchase != 0 && amount != 0) {
            brand = (brandName != null) ? brandService.findBrandByName(brandName) : null;
            category = (categoryName != null) ? categoryService.findCategoryByName(categoryName) : null;

            product.setProductId(id);
            product.setDescription(description);
            product.setPrice(price);
            product.setPurchase(purchase);
            product.setAmount(amount);
            product.setBrand(brand);
            product.setCategory(category);
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

    public Product loadProductById(Long id, JTextField txtDescription, JTextField txtPrice, JTextField txtPurchase, JTextField txtAmount, JComboBox cbBrands, JComboBox cbCategories) {
        Product product = productService.findProductById(id);
        
        txtDescription.setText(product.getDescription());
        txtPrice.setText(product.getPrice().toString());
        txtPurchase.setText(product.getPurchase().toString());
        txtAmount.setText(product.getAmount().toString());
        cbBrands.setSelectedItem((product.getBrand() != null) ? product.getBrand().getName() : "-- Seleccionar Marca (Opcional)");
        cbCategories.setSelectedItem((product.getCategory() != null) ? product.getCategory().getName() : "-- Seleccionar Categoría (Opcional)");

        return product;
    }

    public List<Product> loadProducts(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        List<Product> products = productService.findProductEntities();
        int count = 1;

        for (Product product : products) {
            User registeredBy = userService.findUserById(product.getRegisteredBy().getUserId());
            User updatedBy = (product.getUpdatedBy() != null) ? userService.findUserById(product.getUpdatedBy().getUserId()) : null;
            Brand brandName = (product.getBrand() != null) ? brandService.findBrandById(product.getBrand().getBrandId()) : null;
            Category categoryName = (product.getCategory() != null) ? categoryService.findCategoryById(product.getCategory().getCategoryId()) : null;

            model.addRow(new Object[]{
                count++,
                "PD" + product.getProductId(),
                product.getDescription(),
                product.getPrice(),
                product.getPurchase(),
                (brandName != null) ? brandName.getName() : null,
                product.getAmount(),
                (categoryName != null) ? categoryName.getName() : null,
                product.getCreatedAt(),
                registeredBy.getFirstname() + " " + registeredBy.getLastname(),
                product.getUpdatedAt(),
                (updatedBy != null) ? updatedBy.getFirstname() + " " + updatedBy.getLastname() : null
            });
        }

        return products;
    }
}
