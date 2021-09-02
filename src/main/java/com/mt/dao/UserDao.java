package com.mt.dao;

import com.mt.bean.User;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public interface UserDao {
    public User getUser(Long userId);

    public Collection<User> getUseBynNamer(String name);

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

    @Override public User updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
        return user;
    }
}
