package com.mt.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentranLockTest {
    public static void main(String[] args) {
        saleTask saleTask = new saleTask();
        Thread   t1       = new Thread(saleTask);
        Thread   t2       = new Thread(saleTask);
        Thread   t3       = new Thread(saleTask);
        Thread   t4       = new Thread(saleTask);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class saleTask implements Runnable {
    ReentrantLock lock = new ReentrantLock(true);
    static int tickets = 20;

    @Override public void run() {
        while (tickets > 0) {
            lock.lock();
            if (tickets > 0) {
                System.out.println(Thread.currentThread().getName() + "正在出售第" + tickets + "张票");
                tickets--;
            } else {
                System.out.println("ticket is sale out");
            }
            lock.unlock();
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}