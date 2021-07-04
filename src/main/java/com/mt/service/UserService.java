package com.mt.service;

import com.mt.bean.User;
import com.mt.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

public interface UserService {

    public void registerComplete();

    public User fetchUser(Long userId);
}

@Component
class UserServiceImpl implements UserService {

    @Autowired ApplicationContext context;
    @Autowired UserDao userDao;

    @Override public void registerComplete() {
        System.out.println(Thread.currentThread().getName()+"---register success");
        //need send email
        //need send skype
        // use spring publish, here we use observer design pattern
        User user = new User();
        user.setUserId(1L);
        user.setUserName("Tomcat");
        context.publishEvent(user);
    }

    @Override public User fetchUser(Long userId) {
        Assert.notNull(userId, "userId is required");
        return userDao.getUser(userId);
    }
}



