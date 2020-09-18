package com.mt.thread;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MycallableTest {
    public static void main(String[] args) throws Exception {
        ThreadService service = new ThreadService();
        //use callable to create thread

        ArrayList<String> list          = new ArrayList<>();
        long              ms1           = System.currentTimeMillis();
        Callable<String>  userCallable  = () -> service.getUserInfo();
        Callable<String>  orderCallable = () -> service.getOderInfo();
        Callable<String>  goodsCallable = () -> service.getGoodsInfo();

        FutureTask<String> userTask  = new FutureTask<>(userCallable);
        FutureTask<String> orderTask = new FutureTask<>(orderCallable);
        FutureTask<String> goodsTask = new FutureTask<>(goodsCallable);
        new Thread(userTask).start();
        new Thread(orderTask).start();
        new Thread(goodsTask).start();
        list.add(userTask.get());
        list.add(orderTask.get());
        list.add(goodsTask.get());
        long ms2 = System.currentTimeMillis();
        System.out.println("use total ms :" + (ms2 - ms1));
        System.out.println("list result is ----->>>>>>>" + list);


    }
}
