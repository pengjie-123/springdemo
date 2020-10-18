package com.mt.proxy;

import java.lang.reflect.Proxy;

public class ProxyMain {
    /**
     * jdk dynamic proxy
     * @param args
     */
    public static void main(String[] args) {
        IPlayer player = new BronzePlayer();
        Object proxyInstance = Proxy.newProxyInstance(player.getClass().getClassLoader(),
                                          player.getClass().getInterfaces(),
                                          new MyInvocationHandler(player));
        ((IPlayer) proxyInstance).killBoss();
        System.out.println(proxyInstance.getClass());
        System.out.println(player.getClass());

    }
}
