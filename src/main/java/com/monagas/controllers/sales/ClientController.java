package com.monagas.controllers.sales;

import com.monagas.entities.Address;
import com.monagas.entities.Person;
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

    public Client createClient(Frame parent,
            JDialog dialog,
            JTable table,
            JComboBox cbType, JTextField txtCedula,
            JTextField txtFirstname, JTextField txtLastname,
            JComboBox cbCode, JTextField txtPhone,
            JTextField txtState, JTextField txtCity, JTextField txtTown, JTextField txtParish, JTextField txtDetails) {
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

        if (!cedula.isEmpty() && !firstname.isEmpty() && !lastname.isEmpty() && !phone.isEmpty()
                && !state.isEmpty() && !city.isEmpty() && !town.isEmpty() && !parish.isEmpty() && !details.isEmpty()) {
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
                clientService.create(address, person, client);
                if (dialog != null) {
                    dialog.dispose();
                }
                if (table != null) {
                    loadClients(table);
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
                    "Por favor, complete todos los campos requeridos para registrar al cliente.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }

        return client;
    }

    public Client editClient(Frame parent,
            JDialog dialog,
            JTable table,
            String nCedula,
            JComboBox cbType, JTextField txtCedula,
            JTextField txtFirstname, JTextField txtLastname,
            JComboBox cbCode, JTextField txtPhone,
            JTextField txtState, JTextField txtCity, JTextField txtTown, JTextField txtParish, JTextField txtDetails) {
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

        Client client = clientService.findClientByCedula((nCedula != null) ? nCedula : type + cedula);

        if (!cedula.isEmpty() && !firstname.isEmpty() && !lastname.isEmpty() && !phone.isEmpty()
                && !state.isEmpty() && !city.isEmpty() && !town.isEmpty() && !parish.isEmpty() && !details.isEmpty()) {

            Address address = client.getPerson().getAddress();
            address.setState(state);
            address.setCity(city);
            address.setTown(town);
            address.setParish(parish);
            address.setAddressDetails(details);

            Person person = client.getPerson();
            person.setFirstname(firstname);
            person.setLastname(lastname);

            client.setCedula(type + cedula);
            client.setPhone(code + "-" + phone);
            client.setUpdatedBy(currentUser);

            try {
                clientService.edit(address, person, client);
                if (dialog != null) {
                    dialog.dispose();
                }
                if (table != null) {
                    loadClients(table);
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

    public Client loadClientByCedula(String cedula,
            JComboBox cbType, JTextField txtCedula,
            JTextField txtFirstname, JTextField txtLastname,
            JComboBox cbCode, JTextField txtPhone,
            JTextField txtState, JTextField txtCity, JTextField txtTown, JTextField txtParish, JTextField txtDetails) {
        Client client = clientService.findClientByCedula(cedula);

        cbType.setSelectedItem(client.getCedula().replaceAll("-.*", ""));
        txtCedula.setText(client.getCedula().replaceAll(".*-", ""));
        txtFirstname.setText(client.getPerson().getFirstname());
        txtLastname.setText(client.getPerson().getLastname());
        cbCode.setSelectedItem(client.getPhone().replaceAll("-.*", ""));
        txtPhone.setText(client.getPhone().replaceAll(".*-", ""));
        txtState.setText(client.getPerson().getAddress().getState());
        txtCity.setText(client.getPerson().getAddress().getCity());
        txtTown.setText(client.getPerson().getAddress().getTown());
        txtParish.setText(client.getPerson().getAddress().getParish());
        txtDetails.setText(client.getPerson().getAddress().getAddressDetails());

        return client;
    }

    public Client loadClientByCedula(Frame parent,
            JComboBox cbType, JTextField txtCedula,
            JTextField txtFirstname, JTextField txtLastname,
            JComboBox cbCode, JTextField txtPhone,
            JTextField txtState, JTextField txtCity, JTextField txtTown, JTextField txtParish, JTextField txtDetails) {
        String type = cbType.getSelectedItem().toString();
        String cedula = txtCedula.getText();

        Client client = null;

        try {
            if (!cedula.isEmpty()) {
                client = clientService.findClientByCedula(type + cedula);

                txtFirstname.setText(client.getPerson().getFirstname());
                txtLastname.setText(client.getPerson().getLastname());
                cbCode.setSelectedItem(client.getPhone().replaceAll("-.*", ""));
                txtPhone.setText(client.getPhone().replaceAll(".*-", ""));
                txtState.setText(client.getPerson().getAddress().getState());
                txtCity.setText(client.getPerson().getAddress().getCity());
                txtTown.setText(client.getPerson().getAddress().getTown());
                txtParish.setText(client.getPerson().getAddress().getParish());
                txtDetails.setText(client.getPerson().getAddress().getAddressDetails());
            } else {
                JOptionPane.showMessageDialog(
                        parent,
                        "Por favor, Ingrese una cédula para realizar la busqueda.",
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
                client.getCedula(),
                client.getPerson().getFirstname() + " " + client.getPerson().getLastname(),
                client.getPhone(),
                client.getPerson().getAddress().getState(),
                client.getPerson().getAddress().getCity(),
                client.getPerson().getAddress().getTown(),
                client.getPerson().getAddress().getParish(),
                client.getPerson().getAddress().getAddressDetails(),
                client.getCreatedAt(),
                registeredBy.getPerson().getFirstname() + " " + registeredBy.getPerson().getLastname(),
                client.getUpdatedAt(),
                (updatedBy != null) ? updatedBy.getPerson().getFirstname() + " " + updatedBy.getPerson().getLastname() : null
            });
        }

        return clients;
    }
}
