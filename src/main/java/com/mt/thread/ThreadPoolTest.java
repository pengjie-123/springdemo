package com.mt.thread;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolTest {
    static ExecutorService pools = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws Exception{
        ThreadService service = new ThreadService();
        ArrayList<String> list = new ArrayList<>();
        long ms1 = System.currentTimeMillis();

        Callable<String>  userCallable  = () -> service.getUserInfo();
        Callable<String>  orderCallable = () -> service.getOderInfo();
        Callable<String>  goodsCallable = () -> service.getGoodsInfo();

        Future<String> task1 = pools.submit(userCallable);
        Future<String> task2 = pools.submit(orderCallable);
        Future<String> task3 = pools.submit(goodsCallable);

        list.add(task1.get());
        list.add(task2.get());
        list.add(task3.get());
        pools.shutdown();
        long ms2 = System.currentTimeMillis();
        System.out.println("use total ms :" + (ms2 - ms1));
        System.out.println("list result is ----->>>>>>>" + list);

    }
}
