package service;

import entities.Category;
import entities.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoryService {

    @PersistenceContext
    EntityManager em;

    // find all categories
    public List<Category> findAll() {
        return em.createQuery("select c from Category c", Category.class).getResultList();
    }

    // find category by id
    public Category find(Integer id) {
        return em.find(Category.class, id);
    }

    // update category
    public void update(Category category) {
        em.merge(category);
    }

    // save category
    public Category save(Category category) {
        return em.merge(category);
    }

    // delete category
    public void remove(Category category) {
        em.remove(em.merge(category));
        em.flush();
    }

    // pagination
    public List<Category> findAll(int page, int size) {
        return em.createQuery("select c from Category c", Category.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    // show products by category
    public List<Product> findProductsByCategory(Category category) {
        return em.createQuery("select p from Product p where p.category = :category", Product.class)
                .setParameter("category", category)
                .getResultList();
    }

}
