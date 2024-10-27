package com.monagas.services.sales;

import com.monagas.entities.sales.Brand;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class BrandService implements Serializable {

    public BrandService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public BrandService() {
        emf = Persistence.createEntityManagerFactory("Sistema_de_VentasPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Brand brand) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            if (doesBrandExist(em, brand.getName())) {
                throw new Exception("La marca \"" + brand.getName() + "\" ya se encuentra registrada.");
            }

            em.persist(brand);
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

    public void edit(Brand brand) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Brand existingBrand = em.find(Brand.class, brand.getBrandId());
            if (existingBrand == null) {
                throw new Exception("Marca no encontrada.");
            }

            if (doesBrandExist(em, brand.getName())) {
                if (!existingBrand.getName().equals(brand.getName())) {
                    throw new Exception("La marca \"" + brand.getName() + "\" ya se encuentra registrada.");
                }
            }

            em.merge(brand);
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

            Brand brand = em.find(Brand.class, id);
            if (brand == null) {
                throw new Exception("Marca no encontrada.");
            }

            em.remove(brand);
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

    private boolean doesBrandExist(EntityManager em, String name) {
        String query = "SELECT COUNT(b) FROM Brand b WHERE b.name = :name";
        Long count = em.createQuery(query, Long.class)
                .setParameter("name", name)
                .getSingleResult();
        return count > 0;
    }

    public List<Brand> findBrandEntities() {
        return findBrandEntities(true, -1, -1);
    }

    public List<Brand> findBrandEntities(int maxResults, int firstResult) {
        return findBrandEntities(false, maxResults, firstResult);
    }

    private List<Brand> findBrandEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Brand> cq = cb.createQuery(Brand.class);
            Root<Brand> rt = cq.from(Brand.class);
            cq.select(rt);

            TypedQuery<Brand> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Brand findBrandById(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Brand.class, id);
        } finally {
            em.close();
        }
    }

    public Brand findBrandByName(String name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Brand> query = em.createQuery("SELECT b FROM Brand b WHERE u.name = :name", Brand.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Long getBrandCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Brand> rt = cq.from(Brand.class);
            cq.select(cb.count(rt));

            Query q = em.createQuery(cq);
            return (Long) q.getSingleResult();
        } finally {
            em.close();
        }
    }
}
