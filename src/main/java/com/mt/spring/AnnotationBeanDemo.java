package com.mt.spring;

import com.mt.service.TestCacheService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationBeanDemo {
    public AnnotationBeanDemo() {
        System.out.println("--------------------- I am created ----------------------");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.mt.service");
        Object  bean = context.getBean("test");
        System.out.println(bean);
        System.out.println(bean.getClass());
        TestCacheService b = (TestCacheService) bean;
        System.out.println(b.find(1));
    }
}
