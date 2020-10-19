package com.mt.observer.anno;

import com.mt.bean.User;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailHandle {


    @Async
    @EventListener({User.class})
    public void sendEmail(User user) {
        System.out.println(user);
        System.out.println(Thread.currentThread().getName()+"---send email successfully");
    }
}
