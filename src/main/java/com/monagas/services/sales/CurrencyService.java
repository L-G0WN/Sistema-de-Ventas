package com.monagas.services.sales;

import com.monagas.entities.sales.Currency;
import com.monagas.services.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.io.Serializable;

public class CurrencyService implements Serializable {

    private EntityManager getEntityManager() {
        return EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
    }

    public void create(Currency currency) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Currency existingCurrency = em.find(Currency.class, 1L);

            if (existingCurrency != null) {
                edit(currency, em);
            } else {
                em.persist(currency);
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

    public void edit(Currency currency, EntityManager em) throws Exception {
        try {
            Currency existingCurrency = em.find(Currency.class, 1L);

            if (existingCurrency == null) {
                throw new Exception("No existe un preico manual con ID 1 para actualizar.");
            }

            if (currency.getPrice() != null) {
                existingCurrency.setPrice(currency.getPrice());
            }
            existingCurrency.setStatus(currency.getStatus());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        }
    }

    public Currency findCurrencyById(Long id) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Currency currency = em.find(Currency.class, id);
            em.getTransaction().commit();
            return currency;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al encontrar el precio manual por ID", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Long getCurrencyCount() {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Currency> rt = cq.from(Currency.class);
            cq.select(cb.count(rt));
            Query q = em.createQuery(cq);
            Long count = (Long) q.getSingleResult();
            em.getTransaction().commit();
            return count;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al obtener el precio de manera manual", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
