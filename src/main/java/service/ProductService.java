package service;

import entities.Customer;
import entities.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.regex.Pattern;

@Stateless
public class ProductService {
    @PersistenceContext
    EntityManager em;

    public Product find(Integer product_id) {
        return em.find(Product.class,product_id);
    }

    public Product update(Product product) {
        return em.merge(product);
    }

    public void remove(Product product) {
        em.remove(em.merge(product));
        em.flush();
    }

    public void save(Product product) {
            em.persist(product);
    }

    public List<Product> findAll() {
        return em.createQuery("select c from Product c", Product.class).getResultList();
    }

    public List<Product> findAll(int page, int size) {
        return em.createQuery("select c from Product c", Product.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    // find products by category
    public List<Product> findAllByCategory(Integer categoryId) {
        Query query = em.createQuery("select c from Product c where c.categoryId = :category_id", Product.class);
        query.setParameter("category_id", categoryId);
        return query.getResultList();
    }


    // find products by category and show as page
    public List<Product> findAllByCategory(int page, int size, Integer categoryId) {
        Query query = em.createQuery("select c from Product c where c.categoryId = :category_id", Product.class);
        query.setParameter("category_id", categoryId);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    // find products by brand
    public List<Product> findAllByBrand(Integer brandId) {
        Query query = em.createQuery("select c from Product c where c.brandId = :brand_id", Product.class);
        query.setParameter("brand_id", brandId);
        return query.getResultList();
    }

    // find products by brand and show as page
    public List<Product> findAllByBrand(int page, int size, Integer brandId) {
        Query query = em.createQuery("select c from Product c where c.brandId = :brand_id", Product.class);
        query.setParameter("brand_id", brandId);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    // find products by ID or name
    public List<Product> findAllByIdAndName(String itemName) {
        Pattern pattern = Pattern.compile("[0-9]+");
        if(pattern.matcher(itemName).matches()){
            Integer itemId = Integer.parseInt(itemName);
            Query query = em.createQuery("select c from Product c where c.productId = :itemId", Product.class);
            query.setParameter("itemId", itemId);
            return query.getResultList();
        }
        else{
            Query query = em.createQuery("select c from Product c where c.productName like :itemName", Product.class);
            query.setParameter("itemName", "%"+itemName+"%");
            return query.getResultList();
        }
    }

    // find products by ID or name and show as page
    public List<Product> findAllByIdAndName(int page, int size, String itemName) {
        Pattern pattern = Pattern.compile("[0-9]+");
        if(pattern.matcher(itemName).matches()){
            Integer itemId = Integer.parseInt(itemName);
            Query query = em.createQuery("select c from Product c where c.productId = :itemId", Product.class);
            query.setParameter("itemId", itemId);
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
            return query.getResultList();
        }
        else {
            Query query = em.createQuery("select c from Product c where c.productName LIKE :itemName", Product.class);
            query.setParameter("itemName", "%"+itemName+"%");
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
            return query.getResultList();
        }
    }

}
