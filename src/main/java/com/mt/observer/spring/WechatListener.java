package com.mt.observer.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class WechatListener implements ApplicationListener<OrderEvent> {
    @Async
    @Override public void onApplicationEvent(OrderEvent event) {
        System.out.println(Thread.currentThread().getName()+"---send wechat successfully");
        System.out.println("in wechat listener--->" + event.getSource());
    }
}
