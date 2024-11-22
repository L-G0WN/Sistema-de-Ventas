package com.monagas.view.sales.print;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class InvoiceReport {

    public void generateInvoice(Long sellingId) {
        JasperPrint JP;
        JasperViewer JV;

        InputStream jasperStream = this.getClass().getResourceAsStream("/JasperReports/Invoices.jasper");
        Connection connection = null;

        try {
            if (jasperStream == null) {
                throw new IllegalArgumentException("El archivo Invoices.jasper no se encuentra en el JAR.");
            }

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sistema_de_Ventas", "root", "gafo1212");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("s_id", sellingId);

            JP = JasperFillManager.fillReport(jasperStream, parameters, connection);

            JV = new JasperViewer(JP, false);
            JV.setTitle("Sistema de Ventas - Factura");
            JV.setIconImage(new ImageIcon(getClass().getResource("/images/iconFrame20.png")).getImage());
            JV.setVisible(true);

        } catch (SQLException | JRException | IllegalArgumentException e) {
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
                            "Error al cerrar la conexión de la base de datos:\n" + e.getMessage(),
                            "Sistema de Ventas - Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            try {
                if (jasperStream != null) {
                    jasperStream.close();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Error al cerrar el flujo del archivo jasper:\n" + e.getMessage(),
                        "Sistema de Ventas - Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
