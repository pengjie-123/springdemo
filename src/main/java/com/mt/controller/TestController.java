package com.mt.controller;

import com.mt.bean.User;
import com.mt.service.TestService;
import com.mt.thread.ThreadService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private        Map<String,Object> cache = new HashMap<>();
    private static ExecutorService    pools = Executors.newFixedThreadPool(5);

    @Autowired     TestService        testService;
    @Autowired ApplicationContext context;

    @GetMapping("get")
    public String get(Integer id) {
        //testService.find();
        System.out.println(context.getBean("test"));
//        User user = testService.find(id);
        System.out.println(testService);
        return "ok";
    }

    //test cache how to work
    @GetMapping("cache")
    public String cache() {
        cache.put("u1",new User(1,"kaine"));
        cache.put("u2",new User(2,"tony"));
        cache.put("u3",new User(3,"tom"));
        return "cache ok";
    }

    //get cache
    @GetMapping("getcache")
    public String getCache() {
        System.out.println(cache);
        User user = (User) cache.get("u1");
        return user.getName();
    }

    @GetMapping("shut")
    public String pools() throws Exception {
        ThreadService   service = new ThreadService();
        ArrayList<String> list    = new ArrayList<>();
        long              ms1     = System.currentTimeMillis();

        Callable<String> userCallable  = () -> {System.out.println("user task --->>>" + Thread.currentThread().getName());return service.getUserInfo();};
        Callable<String> orderCallable = () -> {System.out.println("order task --->>>" + Thread.currentThread().getName());return service.getOderInfo();};
        Callable<String>  goodsCallable = () -> {System.out.println("goods task --->>>" + Thread.currentThread().getName());return service.getGoodsInfo();};

        Future<String> task1 = pools.submit(userCallable);
        Future<String> task2 = pools.submit(orderCallable);
        Future<String> task3 = pools.submit(goodsCallable);

        list.add(task1.get());
        list.add(task2.get());
        list.add(task3.get());
        //pools.shutdown(); if use this method , after finished execute these tasks, the pool will stop and reject any other task.
        long ms2 = System.currentTimeMillis();
        System.out.println("use total ms :" + (ms2 - ms1));
        System.out.println("list result is ----->>>>>>>" + list);
        return "pools";
    }
}
