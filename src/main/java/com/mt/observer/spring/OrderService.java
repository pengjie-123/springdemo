package com.mt.observer.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

public interface OrderService {
    void saveOrder();
}

@Component
class OrderServiceImpl implements OrderService{
    @Autowired
    private ApplicationContext context;

    @Override public void saveOrder() {
        System.out.println(Thread.currentThread().getName()+ "--- save order success");
        //send sms
        //seng wechat
        context.publishEvent(new OrderEvent("hello order"));
    }
}
