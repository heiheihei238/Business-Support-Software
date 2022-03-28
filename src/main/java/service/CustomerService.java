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

    public Customer find(Long customer_id) {
        return em.find(Customer.class,customer_id);
    }

    public Customer update(Customer customer) {
        return em.merge(customer);
    }

    public void remove(Customer customer) {
        em.remove(em.merge(customer));
    }

    public void save(Customer customer) {
        em.persist(customer);
    }

    public List<Customer> all() {
        return em.createQuery("select a from Customer a", Customer.class).getResultList();
    }
}
