package com.monagas.services;

import com.monagas.entities.Address;
import com.monagas.entities.DetailPerson;
import com.monagas.entities.Person;
import com.monagas.entities.login.SecurityQuestion;
import com.monagas.entities.login.User;
import com.monagas.entities.sales.Currency;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class DataInitializer {

    public void initializeData() {
        initializeAdministrator();
        initializeCurrency();
    }

    private void initializeAdministrator() {
        EntityManager em = null;

        try {
            em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();

            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", "Ventas");
            User existingAdmin = query.getResultStream().findFirst().orElse(null);

            if (existingAdmin == null) {
                Address address = new Address();
                address.setState("Anzoátegui");
                address.setCity("El Tigre");
                address.setTown("Simón Rodríguez");
                address.setParish("Edmundo Barrios");
                address.setAddressDetails("Ejemplo N°10 Calle");

                DetailPerson detailPerson = new DetailPerson();
                detailPerson.setPhone(null);
                
                Person person = new Person();
                person.setFirstname("Administrador");
                person.setLastname("General");
                person.setAddress(address);
                person.setDetailPerson(detailPerson);

                SecurityQuestion sq = new SecurityQuestion();
                sq.setQuestion1(null);
                sq.setAnswer1(null);
                sq.setQuestion2(null);
                sq.setAnswer2(null);
                sq.setQuestion3(null);
                sq.setAnswer3(null);

                User admin = new User();
                admin.setUsername("Ventas");
                admin.setPassword("12345");
                admin.setAccountType(1);
                admin.setEnabled(true);
                admin.setFirstime(true);
                admin.setPerson(person);
                admin.setSecurityQuestions(sq);
                
                em.persist(sq);
                em.persist(detailPerson);
                em.persist(person);
                em.persist(admin);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void initializeCurrency() {
        EntityManager em = null;

        try {
            em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();

            TypedQuery<Currency> query = em.createQuery("SELECT c FROM Currency c WHERE c.currencyId = :id", Currency.class);
            query.setParameter("id", 1L);
            Currency existingCurrency = query.getResultStream().findFirst().orElse(null);

            if (existingCurrency == null) {
                Currency currency = new Currency();
                currency.setCurrencyId(1L);
                currency.setPrice(0.0);
                currency.setStatus(true);

                em.persist(currency);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
