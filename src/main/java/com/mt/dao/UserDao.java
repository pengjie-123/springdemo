package com.mt.dao;

import com.mt.bean.User;
import java.util.Collection;
import javax.persistence.LockModeType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public interface UserDao {
    public User getUser(Long userId);
    public User getCurrentUser(Long userId);

    public Collection<User> getUseBynNamer(String name);

    public User getUserForUpdate(Integer siteId, String name);
    public User getUserForUpdateNoSleep(Integer siteId, String name);
    public User getUserForUpdateNoLock(Integer siteId, String name);

    public User updateUser(User user);

}


@Repository
class UserDaoImpl implements UserDao {

    @Autowired SessionFactory sessionFactory;

    @Override public User getUser(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        session.clear();
        return session.get(User.class, userId);
    }

    @Override public User getCurrentUser(Long userId) {
        String sql = "SELECT * from USER where PK_USER_ID = :userId FOR UPDATE";
        Session session = sessionFactory.getCurrentSession();
        session.clear();
        NativeQuery query = session.createNativeQuery(sql, User.class);
        query.setParameter("userId", userId);

        return (User) query.uniqueResult();
    }

    @Override public Collection<User> getUseBynNamer(String name) {
        String sql = "SELECT * FROM USER WHERE USER_NAME LIKE :name";
        NativeQuery<User> query = sessionFactory.getCurrentSession().createNativeQuery(sql, User.class);
        query.setParameter("name", name + "%");
        return query.list();
    }

    @Override public User getUserForUpdate(
        Integer siteId,
        String name
    ) {
        String hql = "from User u where u.siteId = :siteId and u.userName = :userName";
        Query  query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("userName", name);
        query.setParameter("siteId", siteId);
        query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
        User u = (User) query.uniqueResult();
        System.out.println(Thread.currentThread().getName() + "---" + u);
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "---" + u);
        return u;
    }

    @Override public User getUserForUpdateNoSleep(
        Integer siteId,
        String name
    ) {
        String      sql   = "SELECT * from USER where USER_NAME = :userName and SITE_ID = :siteId for update";
        NativeQuery query = sessionFactory.getCurrentSession().createNativeQuery(sql, User.class);
        query.setParameter("userName", name);
        query.setParameter("siteId", siteId);
        User u = (User) query.uniqueResult();
        System.out.println(Thread.currentThread().getName() + "---" + u);

        return u;
    }

    @Override public User getUserForUpdateNoLock(
        Integer siteId,
        String name
    ) {
        try {
            Thread.sleep(60000);
        }catch (Exception e) {
            e.printStackTrace();
        }
        String      sql   = "SELECT * from USER where USER_NAME = :userName and SITE_ID = :siteId";
        NativeQuery query = sessionFactory.getCurrentSession().createNativeQuery(sql, User.class);
        query.setParameter("userName", name);
        query.setParameter("siteId", siteId);
        User u = (User) query.uniqueResult();

        return u;
    }


    @Override public User updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
        return user;
    }
}
