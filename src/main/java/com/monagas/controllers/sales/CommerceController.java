package com.monagas.controllers.sales;

import com.monagas.entities.sales.Commerce;
import com.monagas.services.sales.CommerceService;
import java.awt.Frame;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CommerceController {

    private final CommerceService commerceService = new CommerceService();

    public void loadCommerce(Frame parent, JTextField txtName, JTextField txtRif, JComboBox cbType) {
        String name = txtName.getText().toUpperCase();
        String type = cbType.getSelectedItem().toString();
        String rif = txtRif.getText();

        Commerce commerce = new Commerce();
        
        if (!name.isEmpty() && !rif.isEmpty()) {
            commerce.setCommerceId(1L);
            commerce.setName(name);
            commerce.setType(type);
            commerce.setRif(rif);
            
            try {
                commerceService.create(commerce);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(parent,
                        ex.getMessage(),
                        "Sistema de Ventas - Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(parent,
                    "Por favor, complete los campos requeridos para el comercio.",
                    "Sistema de Ventas - Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void findCommerce(JTextField txtName, JTextField txtRif, JComboBox cbType) {
        Commerce commerce = commerceService.findCommerceById(1L);
        
        txtName.setText(commerce.getName());
        txtRif.setText(commerce.getRif());
        cbType.setSelectedItem(commerce.getType());
    }
}
