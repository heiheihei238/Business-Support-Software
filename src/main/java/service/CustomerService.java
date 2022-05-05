package service;

import entities.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    // data count
    public long count() {
        return em.createQuery("select count(c) from Customer c", Long.class).getSingleResult();
    }

    // pagination
    public List<Customer> findAll(int page, int size) {
        return em.createQuery("select c from Customer c", Customer.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
}
