package com.monagas.services.login;

import com.monagas.entities.Address;
import com.monagas.entities.DetailPerson;
import com.monagas.entities.Person;
import com.monagas.entities.login.CurrentUser;
import com.monagas.entities.login.SecurityQuestion;
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

    public void create(User user, Address address, DetailPerson detailPerson, Person person, SecurityQuestion sq) throws Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            if (doesUserExist(em, user.getUsername())) {
                throw new Exception("El usuario \"" + user.getUsername() + "\" ya se encuentra registrado.");
            }

            em.persist(address);
            em.persist(detailPerson);
            em.persist(person);
            em.persist(sq);
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

    public void edit(User user, Address address, DetailPerson detailPerson, Person person, SecurityQuestion sq) throws Exception {
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

            if (address != null) {
                em.merge(address);
            }

            if (detailPerson != null) {
                em.merge(detailPerson);
            }

            if (person != null) {
                em.merge(person);
            }

            if (sq != null) {
                em.merge(sq);
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

    public boolean verify(String username, String question1, String answer1,
            String question2, String answer2,
            String question3, String answer3) throws Exception {
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

            if (user.getSecurityQuestions().getQuestion1() == null && user.getSecurityQuestions().getAnswer1() == null
                    && user.getSecurityQuestions().getQuestion2() == null && user.getSecurityQuestions().getAnswer2() == null
                    && user.getSecurityQuestions().getQuestion3() == null && user.getSecurityQuestions().getAnswer3() == null) {
                throw new Exception("Por favor, inicie sesión por primera vez para registrar las preguntas y respuestas en el sistema.");
            }

            if ((!user.getSecurityQuestions().getQuestion1().equals(question1) || !user.getSecurityQuestions().getAnswer1().equals(answer1))
                    || (!user.getSecurityQuestions().getQuestion2().equals(question2) || !user.getSecurityQuestions().getAnswer2().equals(answer2))
                    || (!user.getSecurityQuestions().getQuestion3().equals(question3) || !user.getSecurityQuestions().getAnswer3().equals(answer3))) {
                throw new Exception("Las preguntas o las respuestas son incorrectas.");
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

    public boolean findUserRelation(Long id) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            long clientCount = (long) em.createQuery("SELECT COUNT(c) FROM Client c WHERE c.registeredBy.id = :userId")
                    .setParameter("userId", id)
                    .getSingleResult();

            long productCount = (long) em.createQuery("SELECT COUNT(p) FROM Product p WHERE p.registeredBy.id = :userId")
                    .setParameter("userId", id)
                    .getSingleResult();

            long supplierCount = (long) em.createQuery("SELECT COUNT(s) FROM Supplier s WHERE s.registeredBy.id = :userId")
                    .setParameter("userId", id)
                    .getSingleResult();

            long sellingCount = (long) em.createQuery("SELECT COUNT(se) FROM Selling se WHERE se.registeredBy.id = :userId")
                    .setParameter("userId", id)
                    .getSingleResult();

            long totalCount = clientCount + productCount + supplierCount + sellingCount;

                System.out.println("TOTAL: " + totalCount);
            em.getTransaction().commit();

            return totalCount == 0;
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al obtener el conteo de relaciones de usuario", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
