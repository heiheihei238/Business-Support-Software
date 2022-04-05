package entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StockPK implements Serializable {

    private Integer product_id;

    private Integer store_id;

    public StockPK() {
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }
}
