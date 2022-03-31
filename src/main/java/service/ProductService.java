package service;

import entities.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        if (findByName(product.getName(), product.getPrice()) != null) {
            Product product1 = findByName(product.getName(), product.getPrice());
            product1.setAmount(product.getAmount()+product1.getAmount());
            update(product1);
        }
        else
            em.persist(product);
    }

    public Product findByName(String name, Double price) {
        Query query = em.createNamedQuery("find product by name and price");
        query.setParameter("name", name).setParameter("price", price);
        List<Product> list = query.getResultList( );
        if (list.size() != 0) return list.get(0);
        else return null;
    }

    public List<Product> all() {
        return em.createQuery("select a from Product a", Product.class).getResultList();
    }
}
