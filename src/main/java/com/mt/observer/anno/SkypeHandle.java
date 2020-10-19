package com.mt.observer.anno;

import com.mt.bean.User;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SkypeHandle {

    @Async
    @EventListener({User.class})
    public void sendSkype(User user) {
        System.out.println(user);
        System.out.println(Thread.currentThread().getName()+"---send skype successfully");
    }
}
