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
    private JTextField amountField;
    private JLabel resultLabel;
    private JButton convertButton;
    private JComboBox<String> conversionTypeComboBox;
    private double currentPrice = 0.0;

    public CurrencyApp() {
        setTitle("Currency Converter");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Inicializar componentes
        currencyComboBox = new JComboBox<>();
        priceLabel = new JLabel("Precio: ");
        lastUpdateLabel = new JLabel("Última actualización: ");
        amountField = new JTextField(10);
        resultLabel = new JLabel("Resultado: ");
        convertButton = new JButton("Convertir");

        // Combo box para tipo de conversión
        conversionTypeComboBox = new JComboBox<>(new String[]{
            "BS a Moneda Extranjera",
            "Moneda Extranjera a BS"
        });

        // Cargar datos de la API
        loadCurrencyData();

        // Agregar componentes al JFrame
        add(currencyComboBox);
        add(priceLabel);
        add(lastUpdateLabel);
        add(new JLabel("Cantidad:"));
        add(amountField);
        add(conversionTypeComboBox);
        add(convertButton);
        add(resultLabel);

        // Escuchar cambios en el JComboBox
        currencyComboBox.addActionListener(e -> updateCurrencyInfo());

        // Agregar acción al botón de conversión
        convertButton.addActionListener(e -> convertCurrency());

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
                            currencyComboBox.addItem(currencyCode.toUpperCase());
                        }
                    }
                }
            }

        } catch (IOException e) {
            // Manejo de errores
            JOptionPane.showMessageDialog(this,
                    "Error loading currency data: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String selectedType = (String) conversionTypeComboBox.getSelectedItem();

            if (selectedType.equals("BS a Moneda Extranjera")) {
                // Convertir de BS a moneda extranjera
                double result = amount / currentPrice;
                resultLabel.setText(String.format("Resulto: %.2f %s",
                        result,
                        currencyComboBox.getSelectedItem()));
            } else {
                // Convertir de moneda extranjera a BS
                double result = amount * currentPrice;
                resultLabel.setText(String.format("Resulto: %.2f BS", result));
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid number");
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

                currentPrice = currency.getDouble("price");
                String lastUpdate = currency.getString("last_update");

                // Actualizar las etiquetas
                priceLabel.setText("Precio: " + currentPrice);
                lastUpdateLabel.setText("Última actualización: " + lastUpdate);
            }

        } catch (IOException e) {
            // Manejo de errores
            JOptionPane.showMessageDialog(this,
                    "Error updating currency info: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CurrencyApp::new);
    }
}
