package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@NamedQuery(name = "OrderItem.findByOrderId", query = "SELECT o FROM OrderItem o WHERE o.order.order_id = :orderId")
@Entity
@Table(name = "order_items")
public class OrderItem implements Serializable {

    @EmbeddedId
    private OrderItemPK id;

//    @ManyToOne
//    @JoinColumn(name = "product_id", insertable = false, updatable = false)
//    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

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


//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//
//    public Product getProducts() {
//        return product;
//    }
//
//    public void setProducts(Product product) {
//        this.product = product;
//    }

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Order_items{" +
                "id=" + id +
//                ", products=" + product +
                ", product_id=" + product_id +
                ", discount=" + discount +
                ", list_price=" + list_price +
                ", quantity=" + quantity +
                '}';
    }
}
