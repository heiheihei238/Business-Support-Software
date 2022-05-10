package service;

import entities.Stock;
import entities.Store;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.regex.Pattern;

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

    public List<Store> findAllByIdAndName(String itemName) {
        Pattern pattern = Pattern.compile("[0-9]+");
        if(pattern.matcher(itemName).matches()) {
            Integer itemId = Integer.parseInt(itemName);
            Query query = em.createQuery("select s from Store s where s.storeId = :itemName", Store.class);
            query.setParameter("itemName", itemId);
            return query.getResultList();
        }
        else {
            Query query = em.createQuery("select s from Store s where s.storeName like :itemName", Store.class);
            query.setParameter("itemName", "%"+itemName+"%");
            return query.getResultList();
        }
    }

    // find staff by ID and show as page
    public List<Store> findAllByIdAndName(int page, int size, String itemName) {
        Pattern pattern = Pattern.compile("[0-9]+");
        if(pattern.matcher(itemName).matches()) {
            Integer itemId = Integer.parseInt(itemName);
            Query query = em.createQuery("select s from Store s where s.storeId = :itemName", Store.class);
            query.setParameter("itemName", itemId);
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
            return query.getResultList();
        }
        else {
            Query query = em.createQuery("select s from Store s where s.storeName like :itemName", Store.class);
            query.setParameter("itemName", "%"+itemName+"%");
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
            return query.getResultList();
        }
    }
}
