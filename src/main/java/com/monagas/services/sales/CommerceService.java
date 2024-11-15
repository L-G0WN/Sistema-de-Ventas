package com.monagas.services.sales;

import com.monagas.entities.sales.Commerce;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.Serializable;

public class CommerceService implements Serializable {

    public CommerceService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public CommerceService() {
        emf = Persistence.createEntityManagerFactory("Sistema_de_VentasPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Commerce commerce) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Commerce existingCommerce = em.find(Commerce.class, 1L);

            if (existingCommerce != null) {
                edit(commerce, em);
            } else {
                em.persist(commerce);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception(ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Commerce commerce, EntityManager em) throws Exception {
        try {
            Commerce existingCommerce = em.find(Commerce.class, 1L);

            if (existingCommerce == null) {
                throw new Exception("No existe un comercio con ID 1 para actualizar.");
            }

            existingCommerce.setType(commerce.getType());
            existingCommerce.setRif(commerce.getRif());
            existingCommerce.setName(commerce.getName());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
    }

    public Commerce findCommerceById(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Commerce.class, id);
        } finally {
            em.close();
        }
    }
}
