package service;

import entities.Order;
import entities.OrderItem;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class OrderItemService {
    @PersistenceContext
    private EntityManager em;

    public void save(OrderItem orderItem) {
        em.persist(orderItem);
    }

    public OrderItem update(OrderItem orderItem) {
        return em.merge(orderItem);
    }

    public void remove(OrderItem orderItem) {
        em.remove(orderItem);
    }

    public OrderItem find(int id) {
        return em.find(OrderItem.class, id);
    }

    public List<OrderItem> findAll() {
        return em.createQuery("SELECT o FROM OrderItem o", OrderItem.class).getResultList();

    }

//    public OrderItem getOrderItem(int orderId, int productId) {
//        return em.createNamedQuery("OrderItem.findByOrderIdAndProductId", OrderItem.class)
//                .setParameter("orderId", orderId)
//                .setParameter("productId", productId)
//                .getSingleResult();
//    }

    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        return em.createNamedQuery("OrderItem.findByOrderId", OrderItem.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }
}
