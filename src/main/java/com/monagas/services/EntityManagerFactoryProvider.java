package com.monagas.services;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javax.swing.JOptionPane;

public class EntityManagerFactoryProvider {

    private static EntityManagerFactory entityManagerFactory;

    public static void createEntityManagerFactory() {
        try {
            if (entityManagerFactory == null) {
                entityManagerFactory = Persistence.createEntityManagerFactory("Sistema_de_VentasPU");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "No se ha podido establecer la conexión con la base de datos:\n" + ex.getMessage(),
                    "Sistema de Ventas - Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
