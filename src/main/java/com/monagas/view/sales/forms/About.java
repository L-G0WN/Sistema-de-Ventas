package com.monagas.view.sales.forms;

import javax.swing.JPanel;

public class About extends JPanel {

    public About() {
        initComponents();

        StringBuilder message = new StringBuilder();
        message.append("<html>");
        message.append("El proyecto fue diseñado en colaboración con el establecimiento comercial Inversiones ");
        message.append("Roselyn Barreto F.P.<br>que se dedica a la venta de víveres, productos de la cesta básica y");
        message.append("alimentos en general. La ubicación del<br>establecimiento es en el sector 17 de");
        message.append("diciembre II de la ciudad de El Tigre, seleccionada debido a la necesidad<br> de ");
        message.append("crecimiento y avance en la actividad económica del propietario.<br>");
        message.append("<br>");
        message.append("Proyecto Socio Tecnológico - Equipo de Investigación<br>");
        message.append("Colmenares, Aura 28.631.962<br>");
        message.append("Hernández, Neily 31.278.782<br>");
        message.append("López, Rosmeld 30.953.128<br>");
        message.append("Mata, Christina 30.602.309<br>");
        message.append("Unseyn, Juan 30.659.432<br>");
        message.append("IF02-T2F2 - UPTJAA");
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
