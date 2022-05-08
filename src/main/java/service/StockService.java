package service;

import entities.Stock;
import entities.StockPK;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StockService {
    @PersistenceContext
    EntityManager em;

    // find by product id and store id
    public Stock findByProductIdAndStoreId(int productId, int storeId) {

        return em.find(Stock.class, new StockPK(productId, storeId));
    }

    public Stock update(Stock stock) {
        return em.merge(stock);
    }

    public void remove(Stock stock) {
            em.remove(em.merge(stock));
            em.flush();
    }

    public void save(Stock stock) {
        em.persist(stock);
    }

    public List<Stock> findAll() {
        return em.createQuery("select c from Stock c", Stock.class).getResultList();
    }

    // pagination
    public List<Stock> findAll(int page, int size) {
        return em.createQuery("select c from Stock c", Stock.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
}
