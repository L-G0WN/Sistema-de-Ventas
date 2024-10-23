package com.monagas.controllers.sales;

import com.monagas.entities.sales.Client;
import com.monagas.services.sales.ClientService;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClientController {

    private final ClientService clientService = new ClientService();

    public List<Client> loadClients(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        List<Client> clients = clientService.findClientEntities();

        for (Client client : clients) {
            model.addRow(new Object[]{
                client.getClientId(),
                client.getType() + client.getCedula(),
                client.getFirstname(),
                client.getLastname(),
                client.getCode() + "-" + client.getPhone(),
                client.getAddress()
            });
        }

        return clients;
    }
}
