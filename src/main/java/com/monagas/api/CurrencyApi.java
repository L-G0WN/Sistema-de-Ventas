package com.monagas.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class CurrencyApi extends JFrame {

    private double currentPrice = 0.0;

    public void loadCurrencyData(JComboBox<String> currency) {
        String urlString = "https://pydolarve.org/api/v1/dollar?page=bcv";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    response = new StringBuilder();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                if (jsonResponse.has("monitors")) {
                    JSONObject monitors = jsonResponse.getJSONObject("monitors");

                    String[] desiredCurrencies = {"usd", "eur"};
                    for (String currencyCode : desiredCurrencies) {
                        if (monitors.has(currencyCode)) {
                            currency.addItem(currencyCode.toUpperCase());
                        }
                    }
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "No se ha podido cargar la información del precio actual:\n" + e.getMessage(),
                    "Sistema de Ventas - Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

//    private void convertCurrency() {
//        try {
//            double amount = Double.parseDouble(amountField.getText());
//            String selectedType = (String) conversionTypeComboBox.getSelectedItem();
//
//            if (selectedType.equals("BS a Moneda Extranjera")) {
//                // Convertir de BS a moneda extranjera
//                double result = amount / currentPrice;
//                resultLabel.setText(String.format("Resulto: %.2f %s",
//                        result,
//                        currencyComboBox.getSelectedItem()));
//            } else {
//                // Convertir de moneda extranjera a BS
//                double result = amount * currentPrice;
//                resultLabel.setText(String.format("Resulto: %.2f BS", result));
//            }
//        } catch (NumberFormatException ex) {
//            resultLabel.setText("Please enter a valid number");
//        }
//    }
    public void updateCurrencyInfo(JComboBox<String> cbCurrency, JLabel price) {
        String selectedCurrency = (String) cbCurrency.getSelectedItem();
        String urlString = "https://pydolarve.org/api/v1/dollar?page=bcv";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    response = new StringBuilder();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONObject data = jsonResponse.getJSONObject("monitors");
                JSONObject currency = data.getJSONObject(selectedCurrency.toLowerCase());

                currentPrice = currency.getDouble("price");
                String lastUpdate = currency.getString("last_update");

                price.setText("Precio: " + currentPrice + " Bs. - Última actualización: " + lastUpdate);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "No se ha podido cargar la información del precio actual:\n" + e.getMessage(),
                    "Sistema de Ventas - Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
