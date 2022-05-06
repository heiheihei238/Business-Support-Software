package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@NamedQuery(name = "OrderItem.findByOrderId", query = "SELECT o FROM OrderItem o WHERE o.orderByOrderId.orderId = :orderId")
@Entity
@Table(name = "order_items", schema = "public", catalog = "bikes")
@IdClass(OrderItemPK.class)
public class OrderItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "item_id")
    private int itemId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id")
    private int orderId;
    @Basic
    @Column(name = "discount")
    private BigDecimal discount;
    @Basic
    @Column(name = "list_price")
    private BigDecimal listPrice;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "product_id")
    private Integer productId;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false, insertable = false, updatable = false)
    private Order orderByOrderId;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    private Product productByProductId;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem that = (OrderItem) o;
        return itemId == that.itemId && orderId == that.orderId && Objects.equals(discount, that.discount) && Objects.equals(listPrice, that.listPrice) && Objects.equals(quantity, that.quantity) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, orderId, discount, listPrice, quantity, productId);
    }

    public Order getOrdersByOrderId() {
        return orderByOrderId;
    }

    public void setOrdersByOrderId(Order orderByOrderId) {
        this.orderByOrderId = orderByOrderId;
    }

    public Product getProductsByProductId() {
        return productByProductId;
    }

    public void setProductsByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }
}
