package service;

import entities.Order;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
//        Order order = em.find(Order.class, order.getOrder_id());
//        if (product == null || order == null) return false;
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

    // 获取数据数量
    public long count() {
        return em.createQuery("SELECT COUNT(o) FROM Order o", Long.class).getSingleResult();
    }

    //分页查询
    public List<Order> findAll(int page, int size) {
        return em.createQuery("select o from Order o", Order.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
}
