package controller;


import entities.Order;
import entities.OrderItem;
import service.OrderItemService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Named
@RequestScoped
public class OrderItemController {

    private OrderItem orderItem;

    private Integer order_id;

    private Integer item_id;

    private static int temp;

    private static int tempItemId;

    @Inject
    OrderItemService os;

    public OrderItemController() {
        orderItem = new OrderItem();
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

    public String update() {
        orderItem.setOrderId(temp);
        orderItem.setItemId(tempItemId);
        os.update(this.orderItem);
        return "/sc/admin/order_Item.xhtml?item_id=" + tempItemId + "&faces-redirect=true";
    }

    public void init(){
        // read parameter from URL
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        order_id = Integer.parseInt(request.getParameter("order_id"));
        temp = order_id;
        orderItem.setOrderId(order_id);
    }

    public void initItemEdit(){
        // read parameter from URL
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        tempItemId = Integer.parseInt(request.getParameter("item_id"));
    }

    public void initItem(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        order_id = Integer.parseInt(request.getParameter("order_id"));
        orderItem.setOrderId(order_id);
        temp = order_id;
    }

    public String save() {
        orderItem.setOrderId(temp);
        os.save(orderItem);
        return "/sc/admin/order_item.xhtml?order_id=" + orderItem.getOrderId() + "&faces-redirect=true";
    }

    public String remove(OrderItem orderItem) {
        orderItem.setOrderId(temp);
        System.out.println(orderItem.toString());
        os.remove(orderItem);
        return "/sc/admin/order_item.xhtml?order_id=" + orderItem.getOrderId() + "&faces-redirect=true";
    }

    public OrderItem find(Integer orderItem_id) {
        return os.find(orderItem_id);
    }

    public List<OrderItem> getAll() {
        return os.findAll();
    }

    public List<OrderItem> getAllByOrderId(Integer order_id) {
        return os.getOrderItemsByOrderId(temp);
    }

}
