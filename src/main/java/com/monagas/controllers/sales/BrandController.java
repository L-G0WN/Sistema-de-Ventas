package com.monagas.controllers.sales;

import com.monagas.entities.sales.Brand;
import com.monagas.services.sales.BrandService;
import java.awt.Frame;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BrandController {

    private final BrandService brandService = new BrandService();

    public Brand createBrand(Frame parent, JDialog dialog, JTable table, JTextField txtName) {
        String name = txtName.getText().toUpperCase();

        Brand brand = new Brand();

        if (!name.isEmpty()) {
            brand.setName(name);

            try {
                brandService.create(brand);
                dialog.dispose();
                loadBrands(table);
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
                    "Por favor, complete todos los campos requeridos para registrar la nueva marca.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }

        return brand;
    }

    public Brand editBrand(Frame parent, JDialog dialog, JTable table, Long id, JTextField txtName) {
        String name = txtName.getText().toUpperCase();

        Brand brand = new Brand();

        if (!name.isEmpty()) {
            brand.setBrandId(id);
            brand.setName(name);
            
            try {
                brandService.edit(brand);
                dialog.dispose();
                loadBrands(table);
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

        return brand;
    }

    public void deleteBrand(Frame parent, JDialog dialog, JTable table, Long id) {
        try {
            boolean isDeleted = brandService.destroy(id);

            if (isDeleted) {
                dialog.dispose();
                loadBrands(table);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    parent,
                    ex.getMessage(),
                    "Sistema de Ventas - Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public Brand loadBrandById(Long id, JTextField txtName) {
        Brand brand = brandService.findBrandById(id);

        txtName.setText(brand.getName());

        return brand;
    }

    public List<Brand> loadBrands(JComboBox<String> combo) {
        if (combo.getItemCount() > 0) {
            for (int i = 1; i < combo.getItemCount(); i++) {
                combo.removeItemAt(i);
            }
        }

        List<Brand> brands = brandService.findBrandEntities();

        for (Brand brand : brands) {
            combo.addItem(brand.getName());
        }

        return brands;
    }

    public List<Brand> loadBrands(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        List<Brand> brands = brandService.findBrandEntities();
        int count = 1;

        for (Brand brand : brands) {
            model.addRow(new Object[]{
                count++,
                "MR" + brand.getBrandId(),
                brand.getName(),});
        }

        return brands;
    }
}
