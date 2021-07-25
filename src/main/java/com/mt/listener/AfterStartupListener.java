package com.mt.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class AfterStartupListener implements ApplicationListener<ContextRefreshedEvent> {
    static int count = 1;

    // we can do something after spring startup
    @Override public void onApplicationEvent(ContextRefreshedEvent event) {
        // ensure execute only one time, root Ioc execute, spring mvc Ioc not
        if (event.getApplicationContext().getParent() == null) {
            System.out.println("---------------I am executed " + count + " time--------------------------");
            count++;
        }

    }
}
