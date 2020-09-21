package com.mt.controller;

import com.mt.component.TestSpringBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringController {
    @Autowired ApplicationContext context;

    @RequestMapping("bean")
    void getBean() {
        Object test = context.getBean("testSpringBean");
        if (test != null) {
            TestSpringBean bean = (TestSpringBean) test;
            bean.go();
        }
        System.out.println("-------------------------------------------");
        TestSpringBean test2 = (TestSpringBean) context.getBean("bean1");
        test2.go();
    }
}
