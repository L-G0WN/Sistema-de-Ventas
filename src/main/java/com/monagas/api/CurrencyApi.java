package com.monagas.api;

import com.monagas.entities.sales.Currency;
import com.monagas.services.sales.CurrencyService;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class CurrencyApi {

    private double currentPrice;
    private double currentConvert;

    private final CurrencyService currencyService = new CurrencyService();

    private JSONObject fetchCurrencyData(Frame parent) {
        try {
            URL url = new URL("https://pydolarve.org/api/v1/dollar?page=bcv");
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

                return new JSONObject(response.toString());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(parent,
                    "No se ha podido cargar la información del precio actual:\n" + e.getMessage(),
                    "Sistema de Ventas - Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public void updateCurrencyInfo(Frame parent, JLabel price) {
        Currency currencyApp = (currencyService.getCurrencyCount() > 0) ? currencyService.findCurrencyById(1L) : null;

        if (currencyApp != null) {
            if (currencyApp.getStatus()) {
                JSONObject jsonResponse = fetchCurrencyData(parent);

                if (jsonResponse != null) {
                    JSONObject data = jsonResponse.getJSONObject("monitors");
                    JSONObject currency = data.getJSONObject("usd");

                    currentPrice = currency.getDouble("price");
                    String lastUpdate = currency.getString("last_update");

                    price.setText("Precio del Dolar : " + currentPrice + " Bs. - Última actualización: " + lastUpdate);
                } else {
                    price.setText("Error al obtener la información del dolar.");
                }
            } else {
                price.setText("Precio del Dolar : " + currencyApp.getPrice() + " Bs. - Precio Manual");
            }
        }
    }

    public void convertCurrency(Frame parent, double amount, JLabel result) {
        Currency currencyApp = (currencyService.getCurrencyCount() > 0) ? currencyService.findCurrencyById(1L) : null;

        if (currencyApp != null) {
            if (currencyApp.getStatus()) {
                JSONObject jsonResponse = fetchCurrencyData(parent);

                if (jsonResponse != null) {
                    JSONObject data = jsonResponse.getJSONObject("monitors");
                    JSONObject currency = data.getJSONObject("usd");

                    currentConvert = currency.getDouble("price");

                    double value = amount * currentConvert;
                    result.setText(String.format("Monto en Bs. : %.2f Bs.", value));
                } else {
                    result.setText("Monto en Bs. : Error al obtener la información del dolar.");
                }
            } else {
                double value = amount * currencyApp.getPrice();
                result.setText("Monto en Bs. : " + value + " Bs.");
            }
        }
    }

    public Double convertPrice(Frame parent, double amount) {
        Currency currencyApp = (currencyService.getCurrencyCount() > 0) ? currencyService.findCurrencyById(1L) : null;

        if (currencyApp != null) {
            if (currencyApp.getStatus()) {
                JSONObject jsonResponse = fetchCurrencyData(parent);

                if (jsonResponse != null) {
                    JSONObject data = jsonResponse.getJSONObject("monitors");
                    JSONObject currency = data.getJSONObject("usd");

                    currentConvert = currency.getDouble("price");
                    return amount * currentConvert;
                }
            } else {
                return amount * currencyApp.getPrice();
            }
        }
        return 0.0;
    }
}
