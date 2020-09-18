package com.mt.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyRunnableTest {
//    private List<String> result = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        final List<String> result = new ArrayList<>();

        final ThreadService service = new ThreadService();
        long m1 = System.currentTimeMillis();
//        service.getUserInfo();
//        service.getGoodsInfo();
//        service.getOderInfo();
        Thread t2=  new Thread(
            () -> {
                String order = service.getOderInfo();
                result.add(order);
            }
        );
        Thread t3 = new Thread(
            () -> {
                String goods = service.getGoodsInfo();
                result.add(goods);
            }
        );
        Thread t1 =  new Thread(
            () -> {
                String user = service.getUserInfo();
                result.add(user);

            }
        );
        Thread t4=  new Thread(
            ()-> {
                    String order = service.getOderInfo();
                    result.add(order);
            }
        );
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println(result.size() + "----------->" + result );
        System.out.println("total time use---->>> " + (System.currentTimeMillis() - m1));


    }
}
