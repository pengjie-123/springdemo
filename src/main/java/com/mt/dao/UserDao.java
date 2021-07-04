package com.mt.dao;

import com.mt.bean.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public interface UserDao {
    public User getUser(Long userId);

}


@Repository
class UserDaoImpl implements UserDao {

    @Autowired SessionFactory sessionFactory;

    @Override public User getUser(Long userId) {
        return sessionFactory.getCurrentSession().get(User.class, userId);
    }
}
