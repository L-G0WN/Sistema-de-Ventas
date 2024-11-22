package com.monagas.services.login;

import com.monagas.entities.login.CurrentUser;
import com.monagas.entities.login.User;
import com.monagas.services.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class UserService implements Serializable {

    private EntityManager getEntityManager() {
        return EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
    }

    public void create(User user) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            if (doesUserExist(em, user.getUsername())) {
                throw new Exception("El usuario \"" + user.getUsername() + "\" ya se encuentra registrado.");
            }

            em.persist(user);
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

    public void edit(User user) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            User existingUsername = em.find(User.class, user.getUserId());
            if (existingUsername == null) {
                throw new Exception("El usuario no se ha encontrado.");
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

    public boolean destroy(Long id) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            User user = em.find(User.class, id);
            if (user == null) {
                throw new Exception("Usuario no encontrado.");
            }

            long count = (long) em.createQuery("SELECT COUNT(s) FROM Selling s WHERE s.registeredBy.id = :userId")
                    .setParameter("userId", id)
                    .getSingleResult();

            if (count > 0) {
                throw new Exception("No se puede eliminar el usuario porque hay ventas asociados a ella.");
            }

            em.remove(user);
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
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> rt = cq.from(User.class);
            cq.select(rt);

            TypedQuery<User> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }

            List<User> resultList = q.getResultList();
            em.getTransaction().commit();
            return resultList;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al encontrar las entidades de usuario", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public User findUserById(Long id) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            User user = em.find(User.class, id);
            em.getTransaction().commit();
            return user;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al encontrar el usuario por ID", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public User findUserByUsername(String username) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            User user = query.getSingleResult();
            em.getTransaction().commit();
            return user;
        } catch (NoResultException e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return null;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al encontrar el usuario por nombre de usuario", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Long getUsersCount() {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<User> rt = cq.from(User.class);
            cq.select(cb.count(rt));

            Query q = em.createQuery(cq);
            Long count = (Long) q.getSingleResult();
            em.getTransaction().commit();
            return count;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al obtener el conteo de usuarios", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void login(String username, String password) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

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
                    em.getTransaction().commit();
                } else {
                    throw new Exception("Contraseña incorrecta.");
                }
            } else {
                throw new Exception("Usuario no encontrado.");
            }
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

    public boolean verify(String username, String question, String answer) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

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

    public boolean changePassword(String username, String password) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            User user = findUserByUsername(username);
            if (user == null) {
                throw new Exception("Usuario no encontrado.");
            }

            if (!user.isEnabled()) {
                throw new Exception("La cuenta está desactivada.");
            }

            user.setPassword(password);
            em.merge(user);

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

    public boolean verifyPassword(String username, String password) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            User user = findUserByUsername(username);
            if (user == null) {
                throw new Exception("Usuario no encontrado.");
            }

            if (!user.getPassword().equals(password)) {
                throw new Exception("La contraseña no coincide, por favor, verifique e intente nuevamente.");
            }

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
}
