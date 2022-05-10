package service;

import entities.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.regex.Pattern;

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

    // find products by ID or name
    public List<Customer> findAllByIdAndName(String itemName) {
        Pattern pattern = Pattern.compile("[0-9]+");
        if(pattern.matcher(itemName).matches()){
            Integer itemId = Integer.parseInt(itemName);
            Query query = em.createQuery("select c from Customer c where c.customerId = :itemId", Customer.class);
            query.setParameter("itemId", itemId);
            return query.getResultList();
        }
        else {
            Query query = em.createQuery("select c from Customer c where c.firstName like :itemName OR c.lastName like :itemName", Customer.class);
            query.setParameter("itemName", "%"+itemName+"%");
            return query.getResultList();
        }
    }

    // find products by ID or name and show the page
    public List<Customer> findAllById(int page, int size, String itemName) {
        Pattern pattern = Pattern.compile("[0-9]+");
        if(pattern.matcher(itemName).matches()){
            Integer itemId = Integer.parseInt(itemName);
            Query query = em.createQuery("select c from Customer c where c.customerId = :itemId", Customer.class);
            query.setParameter("itemId", itemId);
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
            return query.getResultList();
        }
        else {
            Query query = em.createQuery("select c from Customer c where c.firstName like :itemName OR c.lastName like :itemName", Customer.class);
            query.setParameter("itemName", "%"+itemName+"%");
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
            return query.getResultList();
        }
    }
}
