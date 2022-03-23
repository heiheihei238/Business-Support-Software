package service;

import entities.Kunde;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class KundeService {
    @PersistenceContext
    EntityManager em;

    public Kunde find(Integer kunde_id) {
        return em.find(Kunde.class,kunde_id);
    }

    public Kunde update(Kunde kunde) {
        return em.merge(kunde);
    }

    public void remove(Kunde kunde) {
        em.remove(em.merge(kunde));
    }

//    public void save(Kunde kunde) {
//        kunde.setLast_update();
//        em.persist(kunde);
//    }

    public List<Kunde> all() {
        return em.createQuery("select a from Kunde a", Kunde.class).getResultList();
    }
}
