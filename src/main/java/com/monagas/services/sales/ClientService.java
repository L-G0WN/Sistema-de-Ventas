package com.monagas.services.sales;

import com.monagas.entities.Address;
import com.monagas.entities.Person;
import com.monagas.entities.login.CurrentUser;
import com.monagas.entities.login.User;
import com.monagas.entities.sales.Client;
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

public class ClientService implements Serializable {

    public EntityManager getEntityManager() {
        return EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
    }

    public void create(Address address, Person person, Client client) throws Exception {
        User currentUser = CurrentUser.getInstance().getUser();
        if (currentUser == null) {
            throw new Exception("Usuario no autenticado.");
        }

        client.setRegisteredBy(currentUser);
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            if (doesClientExist(em, client.getCedula())) {
                throw new Exception("La cédula \"" + client.getCedula() + "\" ya se encuentra registrada.");
            }

            em.persist(address);
            em.persist(person);
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

    public void edit(Address address, Person person, Client client) throws Exception {
        User currentUser = CurrentUser.getInstance().getUser();
        if (currentUser == null) {
            throw new Exception("Usuario no autenticado.");
        }

        client.setUpdatedBy(currentUser);
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Client existingClient = em.find(Client.class, client.getClientId());
            if (existingClient == null) {
                throw new Exception("Cliente no encontrado.");
            }

            if (doesClientExist(em, client.getCedula())) {
                if (!existingClient.getCedula().equals(client.getCedula())) {
                    throw new Exception("La cédula \"" + client.getCedula() + "\" ya se encuentra registrada.");
                }
            }

            em.merge(address);
            em.merge(person);
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

            long count = (long) em.createQuery("SELECT COUNT(s) FROM Selling s WHERE s.client.id = :clientId")
                    .setParameter("clientId", id)
                    .getSingleResult();

            if (count > 0) {
                throw new Exception("No se puede eliminar el cliente porque hay ventas asociadas a ella.");
            }

            Person person = client.getPerson();
            Address address = client.getPerson().getAddress();

            em.remove(client);
            em.remove(person);
            em.remove(address);
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

    private boolean doesClientExist(EntityManager em, String cedula) {
        String query = "SELECT COUNT(c) FROM Client c WHERE c.cedula = :cedula";
        Long count = em.createQuery(query, Long.class)
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
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Client> cq = cb.createQuery(Client.class);
            Root<Client> rt = cq.from(Client.class);
            cq.select(rt);

            TypedQuery<Client> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            List<Client> resultList = q.getResultList();
            em.getTransaction().commit();
            return resultList;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al encontrar las entidades de clientes", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Long createIfNotExist(String cedula, Address address, Person person, Client client) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            String query = "SELECT c.clientId FROM Client c WHERE c.cedula = :cedula";
            Long existingClientId = em.createQuery(query, Long.class)
                    .setParameter("cedula", cedula)
                    .getSingleResult();
            em.getTransaction().commit();
            return existingClientId;
        } catch (NoResultException e) {
            create(address, person, client);
            return client.getClientId();
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al buscar o crear el cliente: " + ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Client findClientById(Long id) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Client client = em.find(Client.class, id);
            em.getTransaction().commit();
            return client;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al encontrar el cliente por ID", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Long findIdByCedula(String cedula) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            String query = "SELECT c.clientId FROM Client c WHERE c.cedula = :cedula";
            Long clientId = em.createQuery(query, Long.class)
                    .setParameter("cedula", cedula)
                    .getSingleResult();
            em.getTransaction().commit();
            return clientId;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("No se ha encontrado el cliente, por favor, verifique e intente nuevamente.", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Client findClientByCedula(String cedula) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            String query = "SELECT c FROM Client c WHERE c.cedula = :cedula";
            Client client = em.createQuery(query, Client.class)
                    .setParameter("cedula", cedula)
                    .getSingleResult();
            em.getTransaction().commit();
            return client;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("No se han encontrado resultados asociados a esa cédula.", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Long getClientCount() {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Client> rt = cq.from(Client.class);
            cq.select(cb.count(rt));

            Query q = em.createQuery(cq);
            Long count = (Long) q.getSingleResult();
            em.getTransaction().commit();
            return count;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al obtener el conteo de clientes", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public boolean findClientRelation(Long id) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            long count = (long) em.createQuery("SELECT COUNT(p) FROM Product p WHERE p.client.id = :clientId")
                    .setParameter("clientId", id)
                    .getSingleResult();

            em.getTransaction().commit();

            return count == 0;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al obtener el conteo de clientes", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
