package com.mt.service;

import com.mt.bean.Account;
import com.mt.bean.Order;
import com.mt.bean.User;
import com.mt.dao.AccountDao;
import com.mt.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;

public interface AccountService {
    void update(Integer id, String name);
    void updateNative(Integer id, String name);
}

@Service
class AccountServiceImpl implements  AccountService {

    @Autowired
    AccountDao accountDao;

    public void update(Integer id, String name) {
        Account a = accountDao.getByIde(id);
        a.setName(name);
    }

    public void updateNative(Integer id, String name) {
        accountDao.updateNative(id, name);
    }

}



