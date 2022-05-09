package service;

import entities.Stock;
import entities.Store;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class StoreService {
    @PersistenceContext
    EntityManager em;

    public Store find(Integer store_id) {
        return em.find(Store.class,store_id);
    }

    public List<Store> findAll() {
        return em.createQuery("select s from Store s", Store.class).getResultList();
    }

    public List<Store> findAllById(Integer itemId) {
        Query query = em.createQuery("select s from Store s where s.storeId = :itemId", Store.class);
        query.setParameter("itemId", itemId);
        return query.getResultList();
    }

    // find staff by ID and show as page
    public List<Store> findAllById(int page, int size, Integer itemId) {
        Query query = em.createQuery("select s from Store s where s.storeId = :itemId", Store.class);
        query.setParameter("itemId", itemId);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        return query.getResultList();
    }
}
