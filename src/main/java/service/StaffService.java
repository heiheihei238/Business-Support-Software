package service;

import entities.Staff;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StaffService {
    @PersistenceContext
    EntityManager em;

    public Staff find(Long staff_id) {
        return em.find(Staff.class,staff_id);
    }

    public Staff update(Staff staff) {
        return em.merge(staff);
    }

    public void remove(Staff staff) {
            em.remove(em.merge(staff));
            em.flush();
    }

    public void save(Staff staff) {
        em.persist(staff);
    }

    public List<Staff> findAll() {
        return em.createQuery("select s from Staff s", Staff.class).getResultList();
    }

    // data count
    public long count() {
        return em.createQuery("select count(s) from Staff s", Long.class).getSingleResult();
    }

    // pagination
    public List<Staff> findAll(int page, int size) {
        return em.createQuery("select s from Staff s", Staff.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
}
