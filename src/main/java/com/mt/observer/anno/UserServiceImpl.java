package com.mt.observer.anno;

import com.mt.bean.User;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService{
    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ApplicationContext context;

    @Override public void registerComplete() {
        System.out.println(Thread.currentThread().getName()+"---register success");
        //need send email
        //need send skype
        // use spring publish, here we use observer design pattern
        User user = new User(1, "Tomcat");
        context.publishEvent(user);
    }
}
