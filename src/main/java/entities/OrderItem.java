package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@NamedQuery(name = "OrderItem.findByOrderId", query = "SELECT o FROM OrderItem o WHERE o.order.order_id = :orderId")
@NamedQuery(name = "OrderItem.findByOrderIdAndProductId", query = "SELECT o FROM OrderItem o WHERE o.order.order_id = :orderId AND o.product.product_id = :productId")
public class OrderItem implements Serializable {

    @EmbeddedId
    private OrderItemPK id;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    private Integer product_id;

    private BigDecimal discount;

    private BigDecimal list_price;

    private Integer quantity;

    public OrderItem() {
    }

    public OrderItemPK getId() {
        return id;
    }

    public void setId(OrderItemPK id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrders() {
        return order;
    }

    public void setOrders(Order order) {
        this.order = order;
    }

    public Product getProducts() {
        return product;
    }

    public void setProducts(Product product) {
        this.product = product;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getList_price() {
        return list_price;
    }

    public void setList_price(BigDecimal list_price) {
        this.list_price = list_price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order_items{" +
                "id=" + id +
                ", order=" + order +
                ", products=" + product +
                ", product_id=" + product_id +
                ", discount=" + discount +
                ", list_price=" + list_price +
                ", quantity=" + quantity +
                '}';
    }
}
