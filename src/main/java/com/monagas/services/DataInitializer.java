package com.monagas.services;

import com.monagas.entities.login.User;
import com.monagas.services.login.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class DataInitializer {

    private final UserService userService = new UserService();

    public void initializeData() {
        EntityManager em = userService.getEntityManager();
        em.getTransaction().begin();

        try {
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
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
