package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LoginService {

    @PersistenceContext
    EntityManager em;

    // 在staff表中查找是否有该管理员
    public boolean findAdmin(String username, String password) {
        return em.createQuery("select s from Staff s where s.email = :username and s.phone = :password and s.managerId = null")
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList().size() > 0;
    }

    // 在staff表中查找是否有该USER1
    public boolean findUser1(String username, String password) {
        return em.createQuery("select s from Staff s where s.email = :username and s.phone = :password and s.managerId = 1")
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList().size() > 0;
    }

    // 在staff表中查找是否有该USER2
    public boolean findUser2(String username, String password) {
        return em.createQuery("select s from Staff s where s.email = :username and s.phone = :password")
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList().size() > 0;
    }
}
