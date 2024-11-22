package com.monagas.services;

import com.monagas.entities.login.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class DataInitializer {

    public void initializeData() {
        EntityManager em = null;

        try {
            em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();

            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", "Ventas");
            User existingAdmin = query.getResultStream().findFirst().orElse(null);

            if (existingAdmin == null) {
                User admin = new User();
                admin.setUsername("Ventas");
                admin.setPassword("12345");
                admin.setFirstname("Administrador");
                admin.setLastname("General");
                admin.setQuestion("¿CÓMO SE LLAMA TU MASCOTA?");
                admin.setAnswer("Azul");
                admin.setAccountType(1);
                admin.setEnabled(true);

                em.persist(admin);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
