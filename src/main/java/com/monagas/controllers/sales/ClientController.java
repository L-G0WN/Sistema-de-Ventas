package com.monagas.controllers.sales;

import com.monagas.entities.login.CurrentUser;
import com.monagas.entities.login.User;
import com.monagas.entities.sales.Client;
import com.monagas.services.login.UserService;
import com.monagas.services.sales.ClientService;
import java.awt.Frame;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ClientController {

    private final UserService userService = new UserService();
    private final ClientService clientService = new ClientService();
    private final User currentUser = CurrentUser.getInstance().getUser();

    public Client createClient(Frame parent, JDialog dialog, JTable table, JComboBox cbType, JTextField txtCedula, JTextField txtFirstname, JTextField txtLastname, JComboBox cbCode, JTextField txtPhone, JTextField txtAddress) {
        String type = cbType.getSelectedItem().toString();
        String cedula = txtCedula.getText();
        String firstname = txtFirstname.getText().toUpperCase();
        String lastname = txtLastname.getText().toUpperCase();
        String code = cbCode.getSelectedItem().toString();
        String phone = txtPhone.getText();
        String address = txtAddress.getText().toUpperCase();

        Client client = new Client();

        if (!cedula.isEmpty() && !firstname.isEmpty() && !lastname.isEmpty() && !phone.isEmpty()) {
            client.setType(type);
            client.setCedula(cedula);
            client.setFirstname(firstname);
            client.setLastname(lastname);
            client.setCode(code);
            client.setPhone(phone);
            client.setAddress(address);
            client.setRegisteredBy(currentUser);

            try {
                clientService.create(client);
                if (dialog != null) dialog.dispose();
                if (table != null) loadClients(table);
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
                    "Por favor, complete todos los campos requeridos para registrar al cliente.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }

        return client;
    }

    public Client editClient(Frame parent, JDialog dialog, JTable table, Long id, JComboBox cbType, JTextField txtCedula, JTextField txtFirstname, JTextField txtLastname, JComboBox cbCode, JTextField txtPhone, JTextField txtAddress) {
        String type = cbType.getSelectedItem().toString();
        String cedula = txtCedula.getText();
        String firstname = txtFirstname.getText().toUpperCase();
        String lastname = txtLastname.getText().toUpperCase();
        String code = cbCode.getSelectedItem().toString();
        String phone = txtPhone.getText();
        String address = txtAddress.getText().toUpperCase();

        Client client = new Client();

        if (!cedula.isEmpty() && !firstname.isEmpty() && !lastname.isEmpty() && !phone.isEmpty()) {
            if (id == null) {
                try {
                    id = clientService.findIdByCedula(type, cedula);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            parent,
                            ex.getMessage(),
                            "Sistema de Ventas - Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            client.setClientId(id);
            client.setType(type);
            client.setCedula(cedula);
            client.setFirstname(firstname);
            client.setLastname(lastname);
            client.setCode(code);
            client.setPhone(phone);
            client.setAddress(address);
            client.setUpdatedBy(currentUser);

            try {
                clientService.edit(client);
                if (dialog != null) dialog.dispose();
                if (table != null) loadClients(table);
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

        return client;
    }

    public void deleteClient(Frame parent, JDialog dialog, JTable table, Long id) {
        try {
            boolean isDeleted = clientService.destroy(id);

            if (isDeleted) {
                dialog.dispose();
                loadClients(table);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    parent,
                    ex.getMessage(),
                    "Sistema de Ventas - Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public Client loadClientById(Long id, JComboBox cbType, JTextField txtCedula, JTextField txtFirstname, JTextField txtLastname, JComboBox cbCode, JTextField txtPhone, JTextField txtAddress) {
        Client client = clientService.findClientById(id);

        cbType.setSelectedItem(client.getType());
        txtCedula.setText(client.getCedula());
        txtFirstname.setText(client.getFirstname());
        txtLastname.setText(client.getLastname());
        cbCode.setSelectedItem(client.getCode());
        txtPhone.setText(client.getPhone());
        txtAddress.setText(client.getAddress());

        return client;
    }

    public Client loadClientByCedula(Frame parent, JComboBox cbType, JTextField txtCedula, JTextField txtFirstname, JTextField txtLastname, JComboBox cbCode, JTextField txtPhone, JTextField txtAddress) {
        String type = cbType.getSelectedItem().toString();
        String cedula = txtCedula.getText();

        Client client = null;

        try {
            if (!cedula.isEmpty()) {

                client = clientService.findClientByCedula(type, cedula);

                txtFirstname.setText(client.getFirstname());
                txtLastname.setText(client.getLastname());
                cbCode.setSelectedItem(client.getCode());
                txtPhone.setText(client.getPhone());
                txtAddress.setText(client.getAddress());
            } else {
                JOptionPane.showMessageDialog(
                        parent,
                        "Por favor, Ingrese una cedula para realizar la busqueda.",
                        "Sistema de Ventas - Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    parent,
                    ex.getMessage(),
                    "Sistema de Ventas - Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return client;
    }

    public List<Client> loadClients(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        List<Client> clients = clientService.findClientEntities();
        int count = 1;

        for (Client client : clients) {
            User registeredBy = userService.findUserById(client.getRegisteredBy().getUserId());
            User updatedBy = (client.getUpdatedBy() != null) ? userService.findUserById(client.getUpdatedBy().getUserId()) : null;

            model.addRow(new Object[]{
                count++,
                "C" + client.getClientId(),
                client.getType() + client.getCedula(),
                client.getFirstname() + " " + client.getLastname(),
                client.getCode() + "-" + client.getPhone(),
                client.getAddress(),
                client.getCreatedAt(),
                registeredBy.getFirstname() + " " + registeredBy.getLastname(),
                client.getUpdatedAt(),
                (updatedBy != null) ? updatedBy.getFirstname() + " " + updatedBy.getLastname() : null
            });
        }

        return clients;
    }
}
