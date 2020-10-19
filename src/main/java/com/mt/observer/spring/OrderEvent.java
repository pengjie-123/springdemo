package com.mt.observer.spring;

import org.springframework.context.ApplicationEvent;

public class OrderEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public OrderEvent(Object source) {
        super(source);
    }
}
