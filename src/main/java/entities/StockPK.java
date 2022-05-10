package entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StockPK implements Serializable {
    @Column(name = "product_id")
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(name = "store_id")
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeId;

    public StockPK(){}

    public StockPK(int productId, int storeId) {
        this.productId = productId;
        this.storeId = storeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockPK stockPK = (StockPK) o;
        return productId == stockPK.productId && storeId == stockPK.storeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, storeId);
    }
}
