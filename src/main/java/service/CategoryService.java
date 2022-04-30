package service;

import entities.Category;

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
    public Category findOne(Long id) {
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
    public void remove(Long id) {
        em.remove(em.getReference(Category.class, id));
    }

    // pagination
    public List<Category> findAll(int page, int size) {
        return em.createQuery("select c from Category c", Category.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }


}
