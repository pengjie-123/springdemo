package com.mt.thread;

import com.sun.javafx.binding.SelectBinding.AsDouble;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

public class TestUuidDuplicate {
}

 class MyTestThread implements Runnable {
    private final CountDownLatch startSignal;
    public CopyOnWriteArrayList<String> list;

    public MyTestThread(CountDownLatch startSignal, CopyOnWriteArrayList<String> list) {
        this.startSignal = startSignal;
        this.list = list;
    }
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is ready...");
            startSignal.await();
            //一直阻塞当前线程，直到计时器的值为0
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //实际测试操作
        doWork();
    }
    private void doWork() {
//        String uuid = UUID.randomUUID().toString();
//        System.out.println(uuid);
//        System.out.println(new Date().getTime());
        for (int i = 0; i < 200000; i++) {
            list.add(UUID.randomUUID().toString());
        }
    }
}

class MyTest {
    public static void main(String[] args) throws InterruptedException {
        // 初始化计数器为 1
        CountDownLatch start=new CountDownLatch(1);
        //模擬16个线程
        CopyOnWriteArrayList<String> uuids = new CopyOnWriteArrayList<>(new ArrayList<>(1000000));
        for(int i=0;i<50;i++){
            MyTestThread tt =new MyTestThread(start, uuids);
            Thread t = new Thread(tt);
            t.start();
        }
        //计数器減 1
        Thread.sleep(2000);
        System.out.println("------------------start--------------------------");
        start.countDown();
        while (uuids.size()<10000000) {
            System.out.println(uuids.size());
            Thread.sleep(10000);
        }
        System.out.println("--------------end-------------------");
        System.out.println(uuids.size());
        List<String> distinct = uuids.stream().distinct().collect(Collectors.toList());
        System.out.println(distinct.size());
        //计数器为0，所有线程释放，同时
        System.out.println();
    }
}
