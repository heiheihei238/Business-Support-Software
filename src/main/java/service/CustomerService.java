package service;

import entities.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CustomerService {
    @PersistenceContext
    EntityManager em;

    public Customer find(Integer customer_id) {
        return em.find(Customer.class,customer_id);
    }

    public Customer update(Customer customer) {
        return em.merge(customer);
    }

    public void remove(Customer customer) {
            em.remove(em.merge(customer));
            em.flush();
    }

    public void save(Customer customer) {
        em.persist(customer);
    }

    public List<Customer> findAll() {
        return em.createQuery("select c from Customer c", Customer.class).getResultList();
    }

    // pagination
    public List<Customer> findAll(int page, int size) {
        return em.createQuery("select c from Customer c", Customer.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    // find products by ID
    public List<Customer> findAllById(Integer itemName) {
        Query query = em.createQuery("select c from Customer c where c.customerId = :itemName", Customer.class);
        query.setParameter("itemName", itemName);
        return query.getResultList();
    }

    // find products by ID and show as page
    public List<Customer> findAllById(int page, int size, Integer itemName) {
        Query query = em.createQuery("select c from Customer c where c.customerId = :itemName", Customer.class);
        query.setParameter("itemName", itemName);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        return query.getResultList();
    }
}
