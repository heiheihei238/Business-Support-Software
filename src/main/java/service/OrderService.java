package service;

import entities.Customer;
import entities.Order;
import entities.Product;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.List;

@Stateless
public class OrderService {
    @PersistenceContext
    EntityManager em;

    public Order find(Long order_id) {
        return em.find(Order.class,order_id);
    }

    public Order update(Order order) {
        return em.merge(order);
    }

    public void remove(Order order) {
        em.remove(em.merge(order));
    }

    public boolean save(Order order) {
//        Product product = em.find(Product.class, order.getProduct_id());
//        Customer customer = em.find(Customer.class, order.getCustomer_id());
//        if (product == null || customer == null) return false;
//        else if (product.getAmount() >= order.getAmount()){
//                product.setAmount(product.getAmount() - order.getAmount());
//                em.merge(product);
//                order.setTime(new Timestamp(System.currentTimeMillis()));
//                em.persist(order);
//                return true;
//            }
//            else return false;
        em.persist(order);
        return true;
    }


    public List<Order> findAll() {
        return em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }
}
