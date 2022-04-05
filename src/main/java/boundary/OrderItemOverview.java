package boundary;


import entities.Order;
import entities.OrderItem;
import service.OrderItemService;
import service.OrderService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class OrderItemOverview {

    private OrderItem orderItem;

    @Inject
    OrderItemService os;

    public OrderItemOverview() {
        orderItem = new OrderItem();
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public OrderItem update() {
        return os.update(this.orderItem);
    }

    public String save() {
//        Logger.getLogger(OrderItemOverview.class.getCanonicalName()).info("order saved: "+ order);
//        boolean showMessage =  os.save(orderItem);
//        if (!showMessage){
//            FacesMessage msg = new FacesMessage("Product not in Stock");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//            return null;
//        }
        return null;
    }

    public void remove(OrderItem orderItem) {
        os.remove(orderItem);
    }

    public OrderItem find(Integer orderItem_id) {
        return os.find(orderItem_id);
    }

    public List<OrderItem> getAll() {
        return os.findAll();
    }

}
