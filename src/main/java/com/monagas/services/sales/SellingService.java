package com.monagas.services.sales;

import com.monagas.entities.login.CurrentUser;
import com.monagas.entities.login.User;
import com.monagas.entities.sales.Client;
import com.monagas.entities.sales.Commerce;
import com.monagas.entities.sales.Product;
import com.monagas.entities.sales.Selling;
import com.monagas.entities.sales.SellingProduct;
import com.monagas.services.EntityManagerFactoryProvider;
import com.monagas.view.sales.print.InvoiceReport;
import java.io.Serializable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class SellingService implements Serializable {

    private EntityManager getEntityManager() {
        return EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
    }

    public void createSelling(Client client, List<Product> products, Double total, Double totalBs, Integer amountTotal, List<Integer> amounts, List<Double> purchases, List<Double> subTotals, List<Double> purchasesBs, List<Double> subTotalsBs, String method) throws Exception {
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
            selling.setAmountTotal(amountTotal);
            selling.setTotal(total);
            selling.setTotalBs(totalBs);
            selling.setMethod(method);
            selling.setClient(existingClient);
            selling.setRegisteredBy(currentUser);

            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                Integer amount = amounts.get(i);
                Double purchase = purchases.get(i);
                Double subTotal = subTotals.get(i);
                Double purchaseBs = purchasesBs.get(i);
                Double subTotalBs = subTotalsBs.get(i);
                selling.addSellingProduct(product, amount, purchase, subTotal, purchaseBs, subTotalBs);
            }

            em.persist(selling);
            em.getTransaction().commit();

            Long sellingId = selling.getSellingId();

            InvoiceReport invoiceReport = new InvoiceReport();
            invoiceReport.generateInvoice(sellingId);
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
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Selling> cq = cb.createQuery(Selling.class);
            Root<Selling> rt = cq.from(Selling.class);
            cq.select(rt);

            TypedQuery<Selling> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            List<Selling> resultList = q.getResultList();
            em.getTransaction().commit();
            return resultList;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al encontrar las entidades de venta", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SellingProduct> findSellingProductsById(Long id) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            TypedQuery<SellingProduct> query = em.createQuery(
                    "SELECT sp FROM SellingProduct sp WHERE sp.sellingId = :sellingId", SellingProduct.class);
            query.setParameter("sellingId", em.getReference(Selling.class, id));
            List<SellingProduct> resultList = query.getResultList();
            em.getTransaction().commit();
            return resultList;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al encontrar los productos de venta por ID", ex);
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
