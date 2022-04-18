package boundary;


import entities.OrderItem;
import service.OrderItemService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Named
@RequestScoped
public class OrderItemOverview {

    private OrderItem orderItem;

    private Integer order_id;

    @Inject
    OrderItemService os;

    public OrderItemOverview() {
        orderItem = new OrderItem();
        // read parameter from URL
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        order_id = Integer.parseInt(request.getParameter("order_id"));
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
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

    public List<OrderItem> getAllByOrderId(Integer order_id) {
        return os.getOrderItemsByOrderId(order_id);
    }

}
