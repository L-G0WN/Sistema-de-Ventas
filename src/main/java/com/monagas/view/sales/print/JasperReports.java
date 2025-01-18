package com.monagas.view.sales.print;

import com.monagas.services.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;

public class JasperReports {

    private EntityManager getEntityManager() {
        return EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
    }

    public void generateInvoice(Long sellingId) throws Exception {
        JasperReport JR;
        JasperPrint JP;
        JasperViewer JV;

        InputStream jasperStream = this.getClass().getResourceAsStream("/JasperReports/Invoices.jrxml");
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

    public void generateHistory() throws Exception {
        JasperReport JR;
        JasperPrint JP;

        InputStream jasperStream = this.getClass().getResourceAsStream("/JasperReports/History.jrxml");
        EntityManager em = null;
        Connection conn;

        try {
            em = getEntityManager();
            conn = getJdbcConnection(em);

            JR = JasperCompileManager.compileReport(jasperStream);
            JP = JasperFillManager.fillReport(JR, null, conn);

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar como PDF");
            fileChooser.setSelectedFile(new File("historial_ventas.pdf"));

            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().toLowerCase().endsWith(".pdf");
                }

                @Override
                public String getDescription() {
                    return "Archivos PDF (*.pdf)";
                }
            });

            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                if (!fileToSave.getName().toLowerCase().endsWith(".pdf")) {
                    fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
                }

                JasperExportManager.exportReportToPdfFile(JP, fileToSave.getAbsolutePath());
            }
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al generar el historial de ventas:\n" + ex.getMessage(),
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
}
