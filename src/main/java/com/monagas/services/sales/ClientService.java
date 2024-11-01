package com.monagas.services.sales;

import com.monagas.entities.login.CurrentUser;
import com.monagas.entities.login.User;
import com.monagas.entities.sales.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class ClientService implements Serializable {

    public ClientService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ClientService() {
        emf = Persistence.createEntityManagerFactory("Sistema_de_VentasPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Client client) throws Exception {
        User currentUser = CurrentUser.getInstance().getUser();
        if (currentUser == null) {
            throw new Exception("Usuario no autenticado.");
        }

        client.setRegisteredBy(currentUser);
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            if (doesSupplierExist(em, client.getType(), client.getCedula())) {
                throw new Exception("La cédula \"" + client.getType() + client.getCedula() + "\" ya se encuentra registrada.");
            }

            em.persist(client);
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

    public void edit(Client client) throws Exception {
        User currentUser = CurrentUser.getInstance().getUser();
        if (currentUser == null) {
            throw new Exception("Usuario no autenticado.");
        }

        client.setUpdatedBy(currentUser);
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Client existingSupplier = em.find(Client.class, client.getClientId());
            if (existingSupplier == null) {
                throw new Exception("Cliente no encontrado.");
            }

            if (doesSupplierExist(em, client.getType(), client.getCedula())) {
                if (!existingSupplier.getCedula().equals(client.getCedula())) {
                    throw new Exception("La cédula \"" + client.getType() + client.getCedula() + "\" ya se encuentra registrada.");
                }
            }

            em.merge(client);
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

            Client client = em.find(Client.class, id);
            if (client == null) {
                throw new Exception("Cliente no encontrado.");
            }

            em.remove(client);
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

    private boolean doesSupplierExist(EntityManager em, String type, String cedula) {
        String query = "SELECT COUNT(c) FROM Client c WHERE c.type = :type AND c.cedula = :cedula";
        Long count = em.createQuery(query, Long.class)
                .setParameter("type", type)
                .setParameter("cedula", cedula)
                .getSingleResult();
        return count > 0;
    }

    public List<Client> findClientEntities() {
        return findClientEntities(true, -1, -1);
    }

    public List<Client> findClientEntities(int maxResults, int firstResult) {
        return findClientEntities(false, maxResults, firstResult);
    }

    private List<Client> findClientEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Client> cq = cb.createQuery(Client.class);
            Root<Client> rt = cq.from(Client.class);
            cq.select(rt);

            TypedQuery<Client> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Client findClientById(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Client.class, id);
        } finally {
            em.close();
        }
    }

    public Long findIdByCedula(String type, String cedula) throws Exception {
        EntityManager em = getEntityManager();
        try {
            String query = "SELECT c.clientId FROM Client c WHERE c.type = :type AND c.cedula = :cedula";
            return em.createQuery(query, Long.class)
                    .setParameter("type", type)
                    .setParameter("cedula", cedula)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new Exception("No se han encontrado resultados: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    public Client findClientByCedula(String type, String cedula) throws Exception {
        EntityManager em = getEntityManager();
        try {
            String query = "SELECT c FROM Client c WHERE c.type = :type AND c.cedula = :cedula";
            return em.createQuery(query, Client.class)
                    .setParameter("type", type)
                    .setParameter("cedula", cedula)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new Exception("No se han encontrado resultados: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    public Long getClientCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Client> rt = cq.from(Client.class);
            cq.select(cb.count(rt));

            Query q = em.createQuery(cq);
            return (Long) q.getSingleResult();
        } finally {
            em.close();
        }
    }
}
