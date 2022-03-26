package boundary;


import entities.Product;
import service.ProductService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class ProductOverview {

    private Product product;

    @Inject
    ProductService ps;

    public ProductOverview() {
        product = new Product();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product update() {
        return ps.update(this.product);
    }

    public String save() {
        Logger.getLogger(ProductOverview.class.getCanonicalName()).info("product saved: "+ product);
        ps.save(product);
        return null;
    }

    public void remove() {
        ps.remove(this.product);
    }

    public Product find(Long product_id) {
        return ps.find(product_id);
    }

    public List<Product> getAll() {
        return ps.all();
    }


}
