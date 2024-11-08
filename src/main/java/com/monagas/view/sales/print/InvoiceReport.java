package com.monagas.view.sales.print;

import java.awt.Frame;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class InvoiceReport {

    public void generateInvoice(Long sellingId) {
        InputStream absolutePath = this.getClass().getResourceAsStream("/reports/Invoices.jrxml");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sistema_de_Ventas", "root", "gafo1212");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("s_id", sellingId);

            JasperReport jasperReport = JasperCompileManager.compileReport(absolutePath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            JasperViewer.viewReport(jasperPrint, false);
            
        } catch (SQLException | JRException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al generar el informe:\n" + e.getMessage(),
                    "Sistema de Ventas - Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null,
                            "Error al realizar la conexión de la base de datos:\n" + e.getMessage(),
                            "Sistema de Ventas - Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}