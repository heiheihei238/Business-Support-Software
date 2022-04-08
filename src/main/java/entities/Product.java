package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    private BigDecimal list_price;

    private Integer model_year;

    private String product_name;

    @ManyToOne
    @JoinColumn(name = "brand_id", insertable = false, updatable = false)
    private Brand brand;

    private Integer brand_id;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    private Integer category_id;

    @OneToOne(mappedBy = "product")
    private Stock stocks;

    private Integer stock_id;

//    @OneToMany(mappedBy = "product")
//    private List<OrderItem> orderItems;

    public Product() {
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public BigDecimal getList_price() {
        return list_price;
    }

    public void setList_price(BigDecimal list_price) {
        this.list_price = list_price;
    }

    public Integer getModel_year() {
        return model_year;
    }

    public void setModel_year(Integer model_year) {
        this.model_year = model_year;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Brand getBrands() {
        return brand;
    }

    public void setBrands(Brand brand) {
        this.brand = brand;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public Category getCategories() {
        return category;
    }

    public void setCategories(Category category) {
        this.category = category;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Stock getStocks() {
        return stocks;
    }

    public void setStocks(Stock stocks) {
        this.stocks = stocks;
    }

    public Integer getStock_id() {
        return stock_id;
    }

    public void setStock_id(Integer stock_id) {
        this.stock_id = stock_id;
    }

//    public List<OrderItem> getOrderItems() {
//        return orderItems;
//    }
//
//    public void setOrderItems(List<OrderItem> orderItems) {
//        this.orderItems = orderItems;
//    }

    @Override
    public String toString() {
        return "Products{" +
                "product_id=" + product_id +
                ", list_price=" + list_price +
                ", model_year=" + model_year +
                ", product_name='" + product_name + '\'' +
                ", brands=" + brand +
                ", brand_id=" + brand_id +
                ", categories=" + category +
                ", category_id=" + category_id +
                ", stocks=" + stocks +
                ", stock_id=" + stock_id +
                '}';
    }
}
