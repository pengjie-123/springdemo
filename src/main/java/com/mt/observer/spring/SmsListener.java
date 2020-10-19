package com.mt.observer.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SmsListener implements ApplicationListener<OrderEvent> {

    @Override public void onApplicationEvent(OrderEvent event) {
        System.out.println(Thread.currentThread().getName()+"---send sms successfully");
        System.out.println("in sms listener--->" +event.getSource());
    }
}
