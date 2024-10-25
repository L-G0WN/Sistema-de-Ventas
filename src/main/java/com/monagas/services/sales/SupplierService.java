package com.monagas.services.sales;

import com.monagas.entities.login.CurrentUser;
import com.monagas.entities.login.User;
import com.monagas.entities.sales.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class SupplierService implements Serializable {

    public SupplierService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public SupplierService() {
        emf = Persistence.createEntityManagerFactory("Sistema_de_VentasPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Supplier supplier) throws Exception {
        User currentUser = CurrentUser.getInstance().getUser();
        if (currentUser == null) {
            throw new Exception("Usuario no autenticado.");
        }

        supplier.setRegisteredBy(currentUser);
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            if (doesSupplierExist(em, supplier.getType(), supplier.getRif())) {
                throw new Exception("El rif \"" + supplier.getType() + supplier.getRif() + "\" ya se encuentra registrado.");
            }

            em.persist(supplier);
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

    public void edit(Supplier supplier) throws Exception {
        User currentUser = CurrentUser.getInstance().getUser();
        if (currentUser == null) {
            throw new Exception("Usuario no autenticado.");
        }

        supplier.setUpdatedBy(currentUser);
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Supplier existingSupplier = em.find(Supplier.class, supplier.getSupplierId());
            if (existingSupplier == null) {
                throw new Exception("Proveedor no encontrado.");
            }

            if (doesSupplierExist(em, supplier.getType(), supplier.getRif())) {
                if (!existingSupplier.getRif().equals(supplier.getRif())) {
                    throw new Exception("El rif \"" + supplier.getType() + supplier.getRif() + "\" ya se encuentra registrado.");
                }
            }

            em.merge(supplier);
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

    public boolean destroy(Long id) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Supplier supplier = em.find(Supplier.class, id);
            if (supplier == null) {
                throw new Exception("Proveedor no encontrado.");
            }

            em.remove(supplier);
            em.getTransaction().commit();
            return true;
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

    private boolean doesSupplierExist(EntityManager em, String type, String rif) {
        String query = "SELECT COUNT(s) FROM Supplier s WHERE s.type = :type AND s.rif = :rif";
        Long count = em.createQuery(query, Long.class)
                .setParameter("type", type)
                .setParameter("rif", rif)
                .getSingleResult();
        return count > 0;
    }

    public List<Supplier> findSupplierEntities() {
        return findSupplierEntities(true, -1, -1);
    }

    public List<Supplier> findSupplierEntities(int maxResults, int firstResult) {
        return findSupplierEntities(false, maxResults, firstResult);
    }

    private List<Supplier> findSupplierEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Supplier> cq = cb.createQuery(Supplier.class);
            Root<Supplier> rt = cq.from(Supplier.class);
            cq.select(rt);

            TypedQuery<Supplier> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Supplier findSupplierById(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Supplier.class, id);
        } finally {
            em.close();
        }
    }

    public Long getSupplierCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Supplier> rt = cq.from(Supplier.class);
            cq.select(cb.count(rt));

            Query q = em.createQuery(cq);
            return (Long) q.getSingleResult();
        } finally {
            em.close();
        }
    }
}
