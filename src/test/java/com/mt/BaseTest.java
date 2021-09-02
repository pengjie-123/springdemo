package com.mt;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;

public class BaseTest {

    @Test public void hello() {
        System.out.println("this is a test");
        AtomicInteger a = new AtomicInteger(1);
        System.out.println(a.get());
//        System.out.println(a.compareAndSet()); //311, 30));
        System.out.println(a.get()); //30)); //31
//        System.out.println(a.incrementAndGet()); //31
        System.out.println(a.getAndIncrement()); //30
        System.out.println(a.get()); //31
    }
}
