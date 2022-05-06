package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id")
    private int productId;
    @Basic
    @Column(name = "list_price")
    private BigDecimal listPrice;
    @Basic
    @Column(name = "model_year")
    private Integer modelYear;
    @Basic
    @Column(name = "product_name")
    private String productName;
    @Basic
    @Column(name = "brand_id")
    private Integer brandId;
    @Basic
    @Column(name = "category_id")
    private Integer categoryId;
    @ManyToOne
    @JoinColumn(name = "brand_id", insertable = false, updatable = false)
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && Objects.equals(listPrice, product.listPrice) && Objects.equals(modelYear, product.modelYear) && Objects.equals(productName, product.productName) && Objects.equals(brandId, product.brandId) && Objects.equals(categoryId, product.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, listPrice, modelYear, productName, brandId, categoryId);
    }


    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", listPrice=" + listPrice +
                ", modelYear=" + modelYear +
                ", productName='" + productName + '\'' +
                ", brandId=" + brandId +
                ", categoryId=" + categoryId +
                '}';
    }
}
