package com.monagas.api;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyApp extends JFrame {

    private JComboBox<String> currencyComboBox;
    private JLabel priceLabel;
    private JLabel lastUpdateLabel;

    public CurrencyApp() {
        setTitle("Currency Prices");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        currencyComboBox = new JComboBox<>();
        priceLabel = new JLabel("Price: ");
        lastUpdateLabel = new JLabel("Last Update: ");

        // Cargar datos de la API
        loadCurrencyData();

        // Agregar componentes al JFrame
        add(currencyComboBox);
        add(priceLabel);
        add(lastUpdateLabel);

        // Escuchar cambios en el JComboBox
        currencyComboBox.addActionListener(e -> updateCurrencyInfo());

        updateCurrencyInfo();
        setVisible(true);
    }

    private void loadCurrencyData() {
        String urlString = "https://pydolarve.org/api/v1/dollar?page=bcv";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Procesar la respuesta JSON
                JSONObject jsonResponse = new JSONObject(response.toString());
                // Acceder a la clave "monitors"
                if (jsonResponse.has("monitors")) {
                    JSONObject monitors = jsonResponse.getJSONObject("monitors");

                    // Agregar solo las monedas "usd" y "eur" al JComboBox
                    String[] desiredCurrencies = {"usd", "eur"};
                    for (String currencyCode : desiredCurrencies) {
                        if (monitors.has(currencyCode)) {
                            currencyComboBox.addItem(currencyCode.toUpperCase()); // Agregar al JComboBox
                        }
                    }
                }
            }

        } catch (IOException e) {
            // Manejo de errores (puedes optar por mostrar un mensaje de error en la interfaz)
        }
    }

    private void updateCurrencyInfo() {
        String selectedCurrency = (String) currencyComboBox.getSelectedItem();
        String urlString = "https://pydolarve.org/api/v1/dollar?page=bcv";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Procesar la respuesta JSON
                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONObject data = jsonResponse.getJSONObject("monitors");
                JSONObject currency = data.getJSONObject(selectedCurrency.toLowerCase());

                double price = currency.getDouble("price");
                String lastUpdate = currency.getString("last_update");

                // Actualizar las etiquetas
                priceLabel.setText("Price: " + price);
                lastUpdateLabel.setText("Last Update: " + lastUpdate);
            }

        } catch (IOException e) {
            // Manejo de errores (puedes optar por mostrar un mensaje de error en la interfaz)
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CurrencyApp::new);
    }
}