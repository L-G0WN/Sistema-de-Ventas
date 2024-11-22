package com.monagas.services.sales;

import com.monagas.entities.sales.Category;
import com.monagas.services.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class CategoryService implements Serializable {

    private EntityManager getEntityManager() {
        return EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
    }

    public void create(Category category) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            if (doesCategoryExist(em, category.getName())) {
                throw new Exception("La categoría \"" + category.getName() + "\" ya se encuentra registrada.");
            }

            em.persist(category);
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

    public void edit(Category category) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Category existingCategory = em.find(Category.class, category.getCategoryId());
            if (existingCategory == null) {
                throw new Exception("Categoría no encontrada.");
            }

            if (doesCategoryExist(em, category.getName())) {
                if (!existingCategory.getName().equals(category.getName())) {
                    throw new Exception("La categoría \"" + category.getName() + "\" ya se encuentra registrada.");
                }
            }

            em.merge(category);
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

            Category category = em.find(Category.class, id);
            if (category == null) {
                throw new Exception("Categoría no encontrada.");
            }

            long count = (long) em.createQuery("SELECT COUNT(p) FROM Product p WHERE p.category.id = :categoryId")
                    .setParameter("categoryId", id)
                    .getSingleResult();

            if (count > 0) {
                throw new Exception("No se puede eliminar la categoría porque hay productos asociados a ella.");
            }

            em.remove(category);
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

    private boolean doesCategoryExist(EntityManager em, String name) {
        String query = "SELECT COUNT(b) FROM Category b WHERE b.name = :name";
        Long count = em.createQuery(query, Long.class)
                .setParameter("name", name)
                .getSingleResult();
        return count > 0;
    }

    public List<Category> findCategoryEntities() {
        return findCategoryEntities(true, -1, -1);
    }

    public List<Category> findCategoryEntities(int maxResults, int firstResult) {
        return findCategoryEntities(false, maxResults, firstResult);
    }

    private List<Category> findCategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Category> cq = cb.createQuery(Category.class);
            Root<Category> rt = cq.from(Category.class);
            cq.select(rt);

            TypedQuery<Category> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            List<Category> resultList = q.getResultList();
            em.getTransaction().commit();
            return resultList;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al encontrar las entidades de categoría", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Category findCategoryById(Long id) {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Category category = em.find(Category.class, id);
            em.getTransaction().commit();
            return category;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al encontrar la categoría por ID", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Category findCategoryByName(String name) {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class);
            query.setParameter("name", name);
            Category category = query.getSingleResult();
            em.getTransaction().commit();
            return category;
        } catch (NoResultException e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return null;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al encontrar la categoría por nombre", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Long getCategoryCount() {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Category> rt = cq.from(Category.class);
            cq.select(cb.count(rt));

            Query q = em.createQuery(cq);
            Long count = (Long) q.getSingleResult();
            em.getTransaction().commit();
            return count;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al obtener el conteo de categorías", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
