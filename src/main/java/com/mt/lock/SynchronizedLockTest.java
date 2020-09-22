package com.mt.lock;

public class SynchronizedLockTest {

    public static void main(String[] args) {
        Runnable r = new MyRunnable();
        for (int i = 0; i < 5; i++) {
            new Thread(r).start();
        }
    }
}

class MyRunnable implements Runnable {
    static int a = 100;

    @Override public void run() {
        try {
            go();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //use synchronized
    void go() throws Exception {
        while (a > 0) {
            synchronized ("aa") {
                if (a > 0) {
                    System.out.println(Thread.currentThread().getName() + "---->>>>sale number " + a + " ticket");
                    a = a - 1;
                } else {
                    System.out.println(Thread.currentThread().getName() + " found ticket sale out!!!");
                }

            }
            Thread.sleep(100);
        }
    }
}

