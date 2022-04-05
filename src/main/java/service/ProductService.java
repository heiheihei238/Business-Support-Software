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
            em.persist(product);
    }

    public Product findByName(String name) {
        Query query = em.createQuery("SELECT p FROM Product p WHERE p.product_name = :name");
        query.setParameter("name", name);
        return (Product) query.getSingleResult();
    }

    public List findAll() {
        Query query = em.createQuery("SELECT p FROM Product p");
        return query.getResultList();
    }

}
