package com.mt.service;

import com.mt.bean.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    @Cacheable(value = "myCache", condition = "#root.target.isCache()")
    public User find(Integer id) {
        System.out.println("find from db");
        return new User(id, "tony"+id);
    }

    public boolean isCache(){
        return true;
    }
}
