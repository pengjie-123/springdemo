package com.mt.component;

import org.springframework.stereotype.Component;

@Component
public class TestSpringBean {
    private Integer provider = 10;
    private String customer = "huang";
    private String des = "something descriptions";
    private static String email = "huang@163.com";
    private static final String phone = "13881958191";

    public void setProvider(Integer provider) {
        this.provider = provider;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setEmail(String email) {
        TestSpringBean.email = email;
    }

    public void go() {
        Integer p = this.provider;
        System.out.println(p);
    }
}
