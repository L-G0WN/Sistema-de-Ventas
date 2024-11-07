package com.monagas.services.sales;

import com.monagas.entities.login.CurrentUser;
import com.monagas.entities.login.User;
import com.monagas.entities.sales.Client;
import com.monagas.entities.sales.Product;
import com.monagas.entities.sales.Selling;
import java.io.Serializable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class SellingService implements Serializable {

    public SellingService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public SellingService() {
        emf = Persistence.createEntityManagerFactory("Sistema_de_VentasPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createSelling(Client client, List<Product> products, Double total, List<Integer> amounts, List<Double> purchases, List<Double> subTotals) throws Exception {
        User currentUser = CurrentUser.getInstance().getUser();
        if (currentUser == null) {
            throw new Exception("Usuario no autenticado.");
        }

        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Client existingClient = em.find(Client.class, client.getClientId());
            if (existingClient == null) {
                throw new Exception("El cliente no existe.");
            }

            Selling selling = new Selling();
            selling.setTotal(total);
            selling.setClient(existingClient);
            selling.setRegisteredBy(currentUser);

            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                Integer amount = amounts.get(i);
                Double purchase = purchases.get(i);
                Double subTotal = subTotals.get(i);
                selling.addSellingProduct(product, amount, purchase, subTotal);
            }

            em.persist(selling);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al crear la venta: " + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Selling> findSellingEntities() {
        return findSellingEntities(true, -1, -1);
    }

    public List<Selling> findSellingEntities(int maxResults, int firstResult) {
        return findSellingEntities(false, maxResults, firstResult);
    }

    private List<Selling> findSellingEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Selling> cq = cb.createQuery(Selling.class);
            Root<Selling> rt = cq.from(Selling.class);
            cq.select(rt);

            TypedQuery<Selling> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
