package com.monagas.services.login;

import com.monagas.entities.login.CurrentUser;
import com.monagas.entities.login.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class UserService implements Serializable {

    public UserService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public UserService() {
        emf = Persistence.createEntityManagerFactory("Sistema_de_VentasPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            User existingUsername = em.find(User.class, user.getUserId());
            if (existingUsername == null) {
                throw new Exception("El usuario no se ha encontrado.");
            }

            if (existingUsername.getUsername().equals("Ventas")) {
                throw new Exception("El usuario \"Ventas\" no se puede cambiar ya que es un usuario único en el sistema.");
            }

            if (doesUserExist(em, user.getUsername())) {
                if (!existingUsername.getUsername().equals(user.getUsername())) {
                    throw new Exception("El usuario \"" + user.getUsername() + "\" ya se encuentra registrado.");
                }
            }

            em.merge(user);
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

    public boolean destroy(Long id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            User user = em.find(User.class, id);
            if (user == null) {
                return false;
            }

            em.remove(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private boolean doesUserExist(EntityManager em, String username) {
        String query = "SELECT COUNT(u) FROM User u WHERE u.username = :username";
        Long count = em.createQuery(query, Long.class)
                .setParameter("username", username)
                .getSingleResult();
        return count > 0;
    }

    public List<User> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<User> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<User> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> rt = cq.from(User.class);
            cq.select(rt);

            TypedQuery<User> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public User findUserById(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public User findUserByUsername(String username) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Long getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<User> rt = cq.from(User.class);
            cq.select(cb.count(rt));

            Query q = em.createQuery(cq);
            return (Long) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public void login(String username, String password) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            List<User> users = query.getResultList();

            if (!users.isEmpty()) {
                User user = users.get(0);
                if (!user.isEnabled()) {
                    throw new Exception("La cuenta está desactivada.");
                }
                if (user.getPassword().equals(password)) {
                    CurrentUser.getInstance().setUser(user);
                } else {
                    throw new Exception("Contraseña incorrecta.");
                }
            } else {
                throw new Exception("Usuario no encontrado.");
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean verify(String username, String question, String answer) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            User user = findUserByUsername(username);

            if (user == null) {
                throw new Exception("Usuario no encontrado.");
            }

            if (!user.isEnabled()) {
                throw new Exception("La cuenta está desactivada.");
            }

            if (!user.getQuestion().equals(question) || !user.getAnswer().equals(answer)) {
                throw new Exception("La pregunta o la respuesta son incorrectas.");
            }

            return true;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean changePassword(String username, String password) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            User user = findUserByUsername(username);
            if (user == null) {
                throw new Exception("Usuario no encontrado.");
            }

            if (!user.isEnabled()) {
                throw new Exception("La cuenta está desactivada.");
            }

            user.setPassword(password);
            em.merge(user);

            transaction.commit();
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

    public boolean verifyPassword(String username, String password) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            User user = findUserByUsername(username);

            if (user == null) {
                throw new Exception("Usuario no encontrado.");
            }

            if (!user.getPassword().equals(password)) {
                throw new Exception("La contraseña no coinciden, por favor, verifique e intente nuevamente.");
            }

            return true;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
