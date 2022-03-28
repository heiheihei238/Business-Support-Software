package boundary;


import entities.Order;
import entities.OrderItem;
import service.OrderService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class OrderOverview {

    private Order order;
    private List<OrderItem> orderItems;
    private OrderItem orderItem;



    @Inject
    OrderService ps;

    public OrderOverview() {
        order = new Order();
        orderItems = new ArrayList<OrderItem>();
        orderItem = new OrderItem();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItem) {
        this.orderItems = orderItem;
    }



    public Order update() {
        return ps.update(this.order);
    }

    public String save() {
        this.orderItems.add(orderItem);
        order.setOrderItems(this.orderItems);
        Logger.getLogger(OrderOverview.class.getCanonicalName()).info("order saved: "+ order);
        ps.save(order);
        return null;
    }

    public void remove() {
        ps.remove(this.order);
    }

    public Order find(Long order_id) {
        return ps.find(order_id);
    }

    public List<Order> getAll() {
        return ps.all();
    }

}
