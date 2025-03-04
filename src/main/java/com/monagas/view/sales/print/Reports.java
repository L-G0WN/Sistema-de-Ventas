package com.monagas.view.sales.print;

import com.monagas.services.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import java.io.InputStream;
import java.sql.Connection;
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
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;

public class Reports {

    private EntityManager getEntityManager() {
        return EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
    }

    public void generateInvoice(Long sellingId) throws Exception {
        JasperReport JR;
        JasperPrint JP;
        JasperViewer JV;

        InputStream jasperStream = this.getClass().getResourceAsStream("/prints/Invoices.jrxml");
        EntityManager em = null;
        Connection conn;

        try {
            em = getEntityManager();
            conn = getJdbcConnection(em);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("s_id", sellingId);

            JR = JasperCompileManager.compileReport(jasperStream);
            JP = JasperFillManager.fillReport(JR, parameters, conn);

            JV = new JasperViewer(JP, false);
            JV.setTitle("Sistema de Ventas - Factura");
            JV.setIconImage(new ImageIcon(getClass().getResource("/images/iconFrame20.png")).getImage());
            JV.setVisible(true);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al generar el informe:\n" + ex.getMessage(),
                    "Sistema de Ventas - Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private Connection getJdbcConnection(EntityManager em) throws Exception {
        Session session = em.unwrap(Session.class);
        SessionImplementor sessionImplementor = (SessionImplementor) session;
        return sessionImplementor.getJdbcConnectionAccess().obtainConnection();
    }

    private void fillReport(String pdf, String created_at, Long registered_by, String user) throws Exception {
        JasperReport JR;
        JasperPrint JP;
        JasperViewer JV;

        InputStream jasperStream;
        Map<String, Object> parameters = new HashMap<>();

        switch (pdf) {
            case "Reporte de Ventas Completo" ->
                jasperStream = this.getClass().getResourceAsStream("/prints/History.jrxml");
            case "Reporte de Ventas por Fecha" -> {
                jasperStream = this.getClass().getResourceAsStream("/prints/HistoryDate.jrxml");
                parameters.put("created_at", created_at);
            }
            case "Reporte de Ventas por Empleado" -> {
                jasperStream = this.getClass().getResourceAsStream("/prints/HistoryEmploye.jrxml");
                parameters.put("registered_by", registered_by);
            }
            case "Reporte de Ventas" -> {
                jasperStream = this.getClass().getResourceAsStream("/prints/HistoryBoth.jrxml");
                parameters.put("registered_by", registered_by);
                parameters.put("created_at", created_at);
            }
            default ->
                throw new Exception("No sé ha podido detectar el archivo, por favor verifique e intente nuevamente.");
        }

        EntityManager em = null;
        Connection conn;

        try {
            em = getEntityManager();
            conn = getJdbcConnection(em);

            JR = JasperCompileManager.compileReport(jasperStream);
            JP = JasperFillManager.fillReport(JR, (!parameters.isEmpty()) ? parameters : null, conn);

            JV = new JasperViewer(JP, false);
            JV.setAlwaysOnTop(true);
            JV.setTitle("Sistema de Ventas" + ((user != null) ? " - Reporte: " + user : ""));
            JV.setIconImage(new ImageIcon(getClass().getResource("/images/iconFrame20.png")).getImage());
            JV.setVisible(true);
        } catch (JRException ex) {
            throw new JRException(ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void generateReport(String pdf, String created_at, Long registered_by, String user) {
        try {
            fillReport(pdf, created_at, registered_by, user);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al generar el reporte de ventas:\n" + ex.getMessage(),
                    "Sistema de Ventas - Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
