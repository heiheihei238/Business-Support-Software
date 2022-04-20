package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer brand_id;

    private String brand_name;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;

    public Brand() {}

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Brands{" +
                "brand_id=" + brand_id +
                ", brand_name='" + brand_name + '\'' +
//                ", products=" + products +
                '}';
    }
}
