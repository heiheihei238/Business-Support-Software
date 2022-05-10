package service;

import entities.Staff;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

@Stateless
public class StaffService implements Serializable {
    @PersistenceContext
    EntityManager em;

    public Staff find(Integer staff_id) {
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

    // pagination
    public List<Staff> findAll(int page, int size) {
        return em.createQuery("select s from Staff s", Staff.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    // find the staffs whose manager is the given manager
    public List<Staff> findByManagerId(Integer manager_id) {
        return (List<Staff>) em.createQuery("select s from Staff s where s.managerId = :manager_id", Staff.class);
    }

    // find the staffs whose manager is not the given manager
    public List<Staff> findByManagerIdNot(Integer manager_id) {
        return (List<Staff>) em.createQuery("select s from Staff s where s.managerId != :manager_id", Staff.class);
    }

    // find staffs by ID or by name
    public List<Staff> findAllByIdAndName(String itemName) {
        Pattern pattern = Pattern.compile("[0-9]+");
        if(pattern.matcher(itemName).matches()) {
            Integer itemId = Integer.parseInt(itemName);
            Query query = em.createQuery("select s from Staff s where s.staffId = :itemName", Staff.class);
            query.setParameter("itemName", itemId);
            return query.getResultList();
        }
        else {
            Query query = em.createQuery("select s from Staff s where s.firstName like :itemName OR s.lastName like :itemName", Staff.class);
            query.setParameter("itemName", "%"+itemName+"%");
            return query.getResultList();
        }
    }

    // find staffs by ID or by name and show the page
    public List<Staff> findAllByIdAndName(int page, int size, String itemName) {
        Pattern pattern = Pattern.compile("[0-9]+");
        if(pattern.matcher(itemName).matches()) {
            Integer itemId = Integer.parseInt(itemName);
            Query query = em.createQuery("select s from Staff s where s.staffId = :itemName", Staff.class);
            query.setParameter("itemName", itemId);
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
            return query.getResultList();
        }
        else {
            Query query = em.createQuery("select s from Staff s where s.firstName like :itemName OR s.lastName like :itemName", Staff.class);
            query.setParameter("itemName", "%"+itemName+"%");
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
            return query.getResultList();
        }
    }
}
