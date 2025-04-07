package com.monagas.services.sales;

import com.monagas.entities.sales.Product;
import com.monagas.services.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class ProductService implements Serializable {

    private EntityManager getEntityManager() {
        return EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
    }

    public void create(Product product) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            em.persist(product);
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

    public void edit(Product product) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Product existingProduct = em.find(Product.class, product.getProductId());
            if (existingProduct == null) {
                throw new Exception("Producto no encontrado.");
            }

            em.merge(product);
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

            Product product = em.find(Product.class, id);
            if (product == null) {
                throw new Exception("Producto no encontrado.");
            }

            long count = (long) em.createQuery("SELECT COUNT(sp) FROM SellingProduct sp WHERE sp.product.id = :productId")
                    .setParameter("productId", id)
                    .getSingleResult();

            if (count > 0) {
                throw new Exception("No se puede eliminar el producto porque hay ventas asociadas a ella.");
            }

            em.remove(product);
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

    public List<Product> findProductEntities() {
        return findProductEntities(true, -1, -1);
    }

    public List<Product> findProductEntities(int maxResults, int firstResult) {
        return findProductEntities(false, maxResults, firstResult);
    }

    private List<Product> findProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Product> cq = cb.createQuery(Product.class);
            Root<Product> rt = cq.from(Product.class);
            cq.select(rt);

            TypedQuery<Product> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            List<Product> resultList = q.getResultList();
            em.getTransaction().commit();
            return resultList;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al encontrar las entidades de producto", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Product findProductById(Long id) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Product product = em.find(Product.class, id);
            em.getTransaction().commit();
            return product;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al encontrar el producto por ID", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Long getProductCount() {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Product> rt = cq.from(Product.class);
            cq.select(cb.count(rt));

            Query q = em.createQuery(cq);
            Long count = (Long) q.getSingleResult();
            em.getTransaction().commit();
            return count;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al obtener el conteo de productos", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean findProductRelation(Long id) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            long count = (long) em.createQuery("SELECT COUNT(sp) FROM SellingProduct sp WHERE sp.product.id = :productId")
                    .setParameter("productId", id)
                    .getSingleResult();

            em.getTransaction().commit();

            return count == 0;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al obtener el conteo de productos", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void newAmount(Long id, int amount) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Product product = em.find(Product.class, id);
            if (product == null) {
                throw new Exception("Producto con ID " + id + " no encontrado.");
            }

            if (product.getAmount() < amount) {
                throw new Exception("No hay suficiente stock para el producto con ID " + id);
            }

            product.setAmount(product.getAmount() - amount);
            em.merge(product);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al actualizar la cantidad del producto: " + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
