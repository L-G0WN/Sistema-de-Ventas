package com.monagas.controllers.sales;

import com.monagas.entities.login.CurrentUser;
import com.monagas.entities.login.User;
import com.monagas.entities.sales.Supplier;
import com.monagas.services.login.UserService;
import com.monagas.services.sales.SupplierService;
import java.awt.Frame;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SupplierController {

    private final UserService userService = new UserService();
    private final SupplierService supplierService = new SupplierService();
    private final User currentUser = CurrentUser.getInstance().getUser();

    public Supplier createSupplier(Frame parent, JDialog dialog, JTable table, JComboBox cbType, JTextField txtRif, JTextField txtName, JTextField txtEmail, JTextField txtCode, JTextField txtPhone, JTextField txtAddress) {
        String type = cbType.getSelectedItem().toString();
        String rif = txtRif.getText().toUpperCase();
        String name = txtName.getText().toUpperCase();
        String email = txtEmail.getText().toUpperCase();
        String code = txtCode.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText().toUpperCase();

        Supplier supplier = new Supplier();

        if (!rif.isEmpty() && !name.isEmpty() && !code.isEmpty() && !phone.isEmpty()) {
            supplier.setType(type);
            supplier.setRif(rif);
            supplier.setName(name);
            supplier.setEmail(email);
            supplier.setCode(code);
            supplier.setPhone(phone);
            supplier.setAddress(address);
            supplier.setRegisteredBy(currentUser);

            try {
                supplierService.create(supplier);
                dialog.dispose();
                loadSuppliers(table);
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

        return supplier;
    }

    public Supplier editSupplier(Frame parent, JDialog dialog, JTable table, Long id, JComboBox cbType, JTextField txtRif, JTextField txtName, JTextField txtEmail, JTextField txtCode, JTextField txtPhone, JTextField txtAddress) {
        String type = cbType.getSelectedItem().toString();
        String rif = txtRif.getText().toUpperCase();
        String name = txtName.getText().toUpperCase();
        String email = txtEmail.getText().toUpperCase();
        String code = txtCode.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText().toUpperCase();

        Supplier supplier = new Supplier();

        if (!rif.isEmpty() && !name.isEmpty() && !code.isEmpty() && !phone.isEmpty()) {
            supplier.setSupplierId(id);
            supplier.setType(type);
            supplier.setRif(rif);
            supplier.setName(name);
            supplier.setEmail(email);
            supplier.setCode(code);
            supplier.setPhone(phone);
            supplier.setAddress(address);
            supplier.setUpdatedBy(currentUser);

            try {
                supplierService.edit(supplier);
                dialog.dispose();
                loadSuppliers(table);
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

        return supplier;
    }

    public void deleteSupplier(Frame parent, JDialog dialog, JTable table, Long id) {
        try {
            boolean isDeleted = supplierService.destroy(id);

            if (isDeleted) {
                dialog.dispose();
                loadSuppliers(table);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    parent,
                    ex.getMessage(),
                    "Sistema de Ventas - Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public Supplier loadSupplierById(Long id, JComboBox cbType, JTextField txtRif, JTextField txtName, JTextField txtEmail, JTextField txtCode, JTextField txtPhone, JTextField txtAddress) {
        Supplier supplier = supplierService.findSupplierById(id);

        cbType.setSelectedItem(supplier.getType());
        txtRif.setText(supplier.getRif());
        txtName.setText(supplier.getName());
        txtEmail.setText(supplier.getEmail());
        txtCode.setText(supplier.getCode());
        txtPhone.setText(supplier.getPhone());
        txtAddress.setText(supplier.getAddress());

        return supplier;
    }

    public List<Supplier> loadSuppliers(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        List<Supplier> suppliers = supplierService.findSupplierEntities();
        int count = 1;

        for (Supplier supplier : suppliers) {
            User registeredBy = userService.findUserById(supplier.getRegisteredBy().getUserId());
            User updatedBy = (supplier.getUpdatedBy() != null) ? userService.findUserById(supplier.getUpdatedBy().getUserId()) : null;

            model.addRow(new Object[]{
                count++,
                "P" + supplier.getSupplierId(),
                supplier.getType() + supplier.getRif(),
                supplier.getName(),
                supplier.getCode() + "-" + supplier.getPhone(),
                supplier.getEmail(),
                supplier.getAddress(),
                supplier.getCreatedAt(),
                registeredBy.getFirstname() + " " + registeredBy.getLastname(),
                supplier.getUpdatedAt(),
                (updatedBy != null) ? updatedBy.getFirstname() + " " + updatedBy.getLastname() : null
            });
        }

        return suppliers;
    }
}
