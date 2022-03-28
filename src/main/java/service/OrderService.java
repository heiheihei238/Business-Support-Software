package service;

import entities.Order;
import entities.Product;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.Date;
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

    public void save(Order order) {
        Product product = em.find(Product.class, order.getProduct_id());
        if (product.getAmount() >= order.getAmount()) {
            product.setAmount(product.getAmount() - order.getAmount());
            em.merge(product);
            order.setTime(new Timestamp(System.currentTimeMillis()));
        }

        em.persist(order);

    }

    public List<Order> all() {
        return em.createQuery("select a from Order a", Order.class).getResultList();
    }

}
