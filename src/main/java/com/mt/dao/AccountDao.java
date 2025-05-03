package com.mt.dao;

import com.mt.bean.Account;
import com.mt.bean.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Collection;

public interface AccountDao {
    public void updateNative(Integer id, String name);

    public Account getByIde(Integer id);
}

@Repository
class AccountDaoImpl implements AccountDao {
    @Autowired  SessionFactory sessionFactory;

    public void update(Account account) {
        sessionFactory.getCurrentSession().update(account);
    }

    @Override
    public void updateNative(Integer id, String name) {
        String sql = "UPDATE ACCOUNT SET NAME = :name WHERE ID = :id";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createNativeQuery(sql);
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Override
    public Account getByIde(Integer id) {
        String sql = "from Account WHERE id = :id";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        query.setParameter("id", id);
        return (Account) query.uniqueResult();

    }
}
