package service;

import entities.Brand;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BrandService {
    @PersistenceContext
    EntityManager em;

    public Brand find(Long brand_id) {
        return em.find(Brand.class,brand_id);
    }

    public Brand update(Brand brand) {
        return em.merge(brand);
    }

    public void remove(Brand brand) {
            em.remove(em.merge(brand));
            em.flush();
    }

    public void save(Brand brand) {
        em.persist(brand);
    }

    public List<Brand> findAll() {
        return em.createQuery("select s from Brand s", Brand.class).getResultList();
    }

    // data count
    public long count() {
        return em.createQuery("select count(s) from Brand s", Long.class).getSingleResult();
    }

    // pagination
    public List<Brand> findAll(int page, int size) {
        return em.createQuery("select s from Brand s", Brand.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
}
