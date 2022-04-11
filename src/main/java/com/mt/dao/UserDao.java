package com.mt.dao;

import com.mt.bean.User;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public interface UserDao {
    public User getUser(Long userId);

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
        return sessionFactory.getCurrentSession().get(User.class, userId);
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
        String      sql   = "SELECT * from USER where USER_NAME = :userName and SITE_ID = :siteId for update";
        NativeQuery query = sessionFactory.getCurrentSession().createNativeQuery(sql, User.class);
        query.setParameter("userName", name);
        query.setParameter("siteId", siteId);
        User u = (User) query.uniqueResult();
        System.out.println(Thread.currentThread().getName() + "---" + u);
        try {
            Thread.sleep(60000);
        }catch (Exception e) {
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
