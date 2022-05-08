package service;

import entities.Store;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
