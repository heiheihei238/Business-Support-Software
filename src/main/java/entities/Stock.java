package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stocks")
public class Stock implements Serializable {
    @EmbeddedId
    private StockPK stockPK;

    @OneToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "store_id", insertable = false, updatable = false)
    private Store store;

    private Integer quantity;

    public Stock() {
    }

    public StockPK getStockPK() {
        return stockPK;
    }

    public void setStockPK(StockPK stockPK) {
        this.stockPK = stockPK;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
