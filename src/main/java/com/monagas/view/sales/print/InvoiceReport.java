package com.monagas.view.sales.print;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class InvoiceReport {

    public void generateInvoice(Long sellingId) {
        JasperReport JR;
        JasperPrint JP;
        JasperViewer JV;

        InputStream jasperStream = this.getClass().getResourceAsStream("/JasperReports/Invoices.jrxml");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sistema_de_Ventas", "seller", "");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("s_id", sellingId);

            JR = JasperCompileManager.compileReport(jasperStream);
            JP = JasperFillManager.fillReport(JR, parameters, connection);

            JV = new JasperViewer(JP, false);
            JV.setTitle("Sistema de Ventas - Factura");
            JV.setIconImage(new ImageIcon(getClass().getResource("/images/iconFrame20.png")).getImage());
            JV.setVisible(true);

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
                            "Error al cerrar la conexión de la base de datos:\n" + e.getMessage(),
                            "Sistema de Ventas - Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
