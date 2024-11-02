package com.monagas.controllers.sales;

import com.monagas.entities.sales.Category;
import com.monagas.services.sales.CategoryService;
import java.awt.Frame;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CategoryController {

    private final CategoryService categoryService = new CategoryService();

    public Category createCategory(Frame parent, JDialog dialog, JTable table, JTextField txtName) {
        String name = txtName.getText().toUpperCase();

        Category category = new Category();

        if (!name.isEmpty()) {
            category.setName(name);

            try {
                categoryService.create(category);
                dialog.dispose();
                loadCategories(table);
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
                    "Por favor, complete todos los campos requeridos para registrar la categoría.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }

        return category;
    }

    public Category editCategory(Frame parent, JDialog dialog, JTable table, Long id, JTextField txtName) {
        String name = txtName.getText().toUpperCase();

        Category category = new Category();

        if (!name.isEmpty()) {
            category.setCategoryId(id);
            category.setName(name);

            try {
                categoryService.edit(category);
                dialog.dispose();
                loadCategories(table);
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

        return category;
    }

    public void deleteCategory(Frame parent, JDialog dialog, JTable table, Long id) {
        try {
            boolean isDeleted = categoryService.destroy(id);

            if (isDeleted) {
                dialog.dispose();
                loadCategories(table);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    parent,
                    ex.getMessage(),
                    "Sistema de Ventas - Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public Category loadCategoryById(Long id, JTextField txtName) {
        Category category = categoryService.findCategoryById(id);

        txtName.setText(category.getName());

        return category;
    }

    public List<Category> loadCategories(JComboBox<String> combo) {
        if (combo.getItemCount() > 0) {
            for (int i = 1; i < combo.getItemCount(); i++) {
                combo.removeItemAt(i);
            }
        }

        List<Category> categories = categoryService.findCategoryEntities();

        for (Category category : categories) {
            combo.addItem(category.getName());
        }

        return categories;
    }

    public List<Category> loadCategories(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        List<Category> categories = categoryService.findCategoryEntities();
        int count = 1;

        for (Category category : categories) {
            model.addRow(new Object[]{
                count++,
                "CT" + category.getCategoryId(),
                category.getName()
            });
        }

        return categories;
    }
}
