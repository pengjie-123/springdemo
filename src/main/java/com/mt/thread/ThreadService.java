package com.mt.thread;


public class ThreadService {

    public String getUserInfo() {
        try {
            Thread.sleep(3000);
            System.out.println("get user success");
        } catch(Exception e) {}
        return "xiaowang";
    }
    public String getOderInfo() {
        try {
            Thread.sleep(4000);
            System.out.println("get order success");
        } catch(Exception e) {}
        return "100011101XXX";
    }
    public String getGoodsInfo() {
        try {
            Thread.sleep(5000);
            System.out.println("get goods success");
        } catch(Exception e) {}
        return "iphone16";
    }
}
