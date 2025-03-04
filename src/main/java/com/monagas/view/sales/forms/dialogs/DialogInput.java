package com.monagas.view.sales.forms.dialogs;

import com.monagas.controllers.login.UserController;
import com.monagas.entities.login.CurrentUser;
import com.monagas.entities.login.User;
import com.monagas.view.sales.print.Reports;
import java.awt.Frame;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import raven.datetime.component.date.DatePicker;

public class DialogInput extends JDialog {

    private final UserController controller = new UserController();
    private final User session = CurrentUser.getInstance().getUser();
    
    private final Reports report = new Reports();

    private final DatePicker Date = new DatePicker();

    private final String type;

    public DialogInput(Frame parent, boolean modal, String type) {
        super(parent, modal);
        this.type = type;
        initComponents();

        if (type.equals("Both")) {
            setTitle("Establecer Empleado y Fecha");
            controller.loadUsers(cbEmploye);
            Date.setEditor(tfDate);
        }

        if (type.equals("Date") || type.equals("Individual")) {
            setTitle("Establecer Fecha");
            cbEmploye.setVisible(false);
            Date.setEditor(tfDate);
        }

        if (type.equals("Employe")) {
            setTitle("Establecer Empleado");
            tfDate.setVisible(false);
            controller.loadUsers(cbEmploye);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPDF = new javax.swing.JButton();
        tfDate = new javax.swing.JFormattedTextField();
        cbEmploye = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        btnPDF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPDF.setText("GENERAR");
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbEmploye, 0, 200, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDate, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPDF, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        if (type.equals("Both")) {
            if (Date.getSelectedDateAsString() == null) {
                JOptionPane.showMessageDialog(null,
                        "Por favor, complete la información para generar el archivo seleccionado correctamente.",
                        "Sistema de Ventas - Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (cbEmploye.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null,
                        "No hay empleados registrados en el sistema.",
                        "Sistema de Ventas - Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Long id = Long.valueOf(cbEmploye.getSelectedItem().toString().replaceAll(" - .*", ""));
            String user = cbEmploye.getSelectedItem().toString().replaceAll(".* - ", "");
            report.generateReport("Reporte de Ventas", reverseDateFormat(Date.getSelectedDateAsString().replace("/", "-")), id, user);
            this.dispose();
        }

        if (type.equals("Date") || type.equals("Individual")) {
            if (Date.getSelectedDateAsString() == null) {
                JOptionPane.showMessageDialog(null,
                        "Por favor, complete la información para generar el archivo seleccionado correctamente.",
                        "Sistema de Ventas - Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (type.equals("Date")) {
                report.generateReport("Reporte de Ventas por Fecha", reverseDateFormat(Date.getSelectedDateAsString().replace("/", "-")), null, null);
            }

            if (type.equals("Individual")) {
                report.generateReport("Reporte de Ventas", reverseDateFormat(Date.getSelectedDateAsString().replace("/", "-")), session.getUserId(), session.getFirstname() + " " + session.getLastname());
            }
            this.dispose();
        }

        if (type.equals("Employe")) {
            if (cbEmploye.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null,
                        "No hay empleados registrados en el sistema.",
                        "Sistema de Ventas - Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Long id = Long.valueOf(cbEmploye.getSelectedItem().toString().replaceAll(" - .*", ""));
            String user = cbEmploye.getSelectedItem().toString().replaceAll(".* - ", "");
            report.generateReport("Reporte de Ventas por Empleado", null, id, user);
            this.dispose();
        }
    }//GEN-LAST:event_btnPDFActionPerformed

    private String reverseDateFormat(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate localDate = LocalDate.parse(date, inputFormatter);
            return localDate.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Fecha no válida: " + date, e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPDF;
    private javax.swing.JComboBox<String> cbEmploye;
    private javax.swing.JFormattedTextField tfDate;
    // End of variables declaration//GEN-END:variables
}
