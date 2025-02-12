package com.monagas.controllers.sales;

import com.monagas.entities.sales.Currency;
import com.monagas.services.sales.CurrencyService;
import java.awt.Frame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CurrencyController {

    private final CurrencyService currencyService = new CurrencyService();

    public void loadPrice(Frame parent, JRadioButton rdDolar, JRadioButton rdManual, JTextField txtPrice) {
        Currency currency = new Currency();

        if (rdDolar.isSelected()) {
            currency.setCurrencyId(1L);
            currency.setStatus(true);
        }

        if (rdManual.isSelected()) {
            double price = (!txtPrice.getText().isEmpty()) ? Double.parseDouble(txtPrice.getText()) : 0;

            if (price <= 0) {
                JOptionPane.showMessageDialog(parent,
                        "Por favor, complete el campo requerido para el precio manual del dólar.",
                        "Sistema de Ventas - Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            currency.setCurrencyId(1L);
            currency.setPrice(price);
            currency.setStatus(false);
        }

        try {
            currencyService.create(currency);
            JOptionPane.showMessageDialog(parent,
                    "Se ha guardado correctamente los cambios realizados.",
                    "Sistema de Ventas",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parent,
                    ex.getMessage(),
                    "Sistema de Ventas - Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void findPrice(JRadioButton rdDolar, JRadioButton rdManual, JTextField txtPrice) {
        Currency currency = (currencyService.getCurrencyCount() > 0) ? currencyService.findCurrencyById(1L) : null;

        if (currency != null) {
            if (currency.getStatus()) {
                rdDolar.setSelected(true);
                rdDolar.doClick();
            } else {
                rdManual.setSelected(true);
                rdManual.doClick();
            }

            txtPrice.setText("" + currency.getPrice());
        }
    }
}
