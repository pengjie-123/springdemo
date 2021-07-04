package com.mt.service;

import com.mt.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

public interface TestCacheService {

    public User find(Integer id);
}

@Service("test")
class TestCacheServiceImpl implements TestCacheService {
    @Autowired ApplicationContext context;

    @Override
    @Cacheable(value = "myCache", condition = "#root.target.isCache()")
    public User find(Integer id) {
        System.out.println("find from db");
        System.out.println(context.getBean("test"));
        System.out.println(context.getBean("test").getClass());
        return new User();
    }

    public boolean isCache(){
        return true;
    }
}
