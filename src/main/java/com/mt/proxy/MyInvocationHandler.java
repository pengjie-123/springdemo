package com.mt.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    private IPlayer player;

    public MyInvocationHandler(IPlayer player) {
        this.player = player;
    }

    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before call the method");
        method.invoke(player, args);
        System.out.println("after call the method");
        return null;
    }
}
