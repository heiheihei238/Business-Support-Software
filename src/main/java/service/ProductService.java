package service;

import entities.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductService {
    @PersistenceContext
    EntityManager em;

    public Product find(Long product_id) {
        return em.find(Product.class,product_id);
    }

    public Product update(Product product) {
        return em.merge(product);
    }

    public void remove(Product product) {
        em.remove(em.merge(product));
    }

    public void save(Product product) {
        em.persist(product);
    }

    public List<Product> all() {
        return em.createQuery("select a from Product a", Product.class).getResultList();
    }
}
