package com.monagas.services.sales;

import com.monagas.entities.sales.Commerce;
import com.monagas.services.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.io.Serializable;

public class CommerceService implements Serializable {

    private EntityManager getEntityManager() {
        return EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
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
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Commerce commerce = em.find(Commerce.class, id);
            em.getTransaction().commit();
            return commerce;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al encontrar el comercio por ID", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Long getCommerceCount() {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Commerce> rt = cq.from(Commerce.class);
            cq.select(cb.count(rt));
            Query q = em.createQuery(cq);
            Long count = (Long) q.getSingleResult();
            em.getTransaction().commit();
            return count;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al obtener el conteo de comercios", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
