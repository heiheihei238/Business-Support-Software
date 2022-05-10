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
}