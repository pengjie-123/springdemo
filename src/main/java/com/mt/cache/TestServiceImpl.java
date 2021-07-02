package com.mt.cache;

import com.mt.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service("test")
public class TestServiceImpl implements TestService {
    @Autowired ApplicationContext context;

    @Override
    @Cacheable(value = "myCache", condition = "#root.target.isCache()")
    public User find(Integer id) {
        System.out.println("find from db");
        System.out.println(context.getBean("test"));
        System.out.println(context.getBean("test").getClass());
        return new User(id, "tony"+id);
    }

    public boolean isCache(){
        return true;
    }
}
