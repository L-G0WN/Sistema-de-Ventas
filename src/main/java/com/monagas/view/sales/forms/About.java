package com.monagas.view.sales.forms;

import javax.swing.JPanel;

public class About extends JPanel {

    public About() {
        initComponents();

        StringBuilder message = new StringBuilder();
        message.append("<html>");
        message.append("<strong>Acerca de la Aplicación</strong><br>");
        message.append("El Sistema de Información de Ventas nació de la necesidad de pequeñas y medianas<br>empresas de contar con una herramienta accesible y eficiente para gestionar sus ventas.<br><br>");
        message.append("<strong>Nombre de la Aplicación:</strong> Sistema de Ventas<br>");
        message.append("<strong>Versión:</strong> 0.6.5<br>");
        message.append("<strong>Desarrollado en:</strong> NetBeans IDE 22<br>");
        message.append("<strong>Lenguaje de Programación:</strong> Java 17 (64 bits)<br><br>");
        message.append("<strong>Requisitos del Sistema</strong><br>");
        message.append("<strong>Sistema Operativo:</strong> Windows 7, 8, 10, o 11 64 bits<br>");
        message.append("<strong>Memoria RAM:</strong> Mínimo 1 GB (recomendado 2 GB)<br>");
        message.append("<strong>Espacio en Disco:</strong> 200 MB disponibles");
        message.append("</html>");
        lbAbout.setText(message.toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbAbout = new javax.swing.JLabel();

        lbAbout.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbAbout.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbAbout, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbAbout, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbAbout;
    // End of variables declaration//GEN-END:variables
}
