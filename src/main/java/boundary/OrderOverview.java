package boundary;


import entities.Order;
import service.CustomerService;
import service.OrderService;
import service.ProductService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class OrderOverview {

    private Order order;

    @Inject
    OrderService os;

    public OrderOverview() {
        order = new Order();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order update() {
        return os.update(this.order);
    }

    public String save() {
        Logger.getLogger(OrderOverview.class.getCanonicalName()).info("order saved: "+ order);
        os.save(order);
        return null;
    }

    public void remove() {
        os.remove(this.order);
    }

    public Order find(Long order_id) {
        return os.find(order_id);
    }

    public List<Order> getAll() {
        return os.all();
    }

}
