package com.mt;

import com.mt.bean.Account;
import com.mt.service.AccountService;
import com.mt.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.naming.Context;

public class Startuo {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        AccountService service = (AccountService) context.getBean("accountServiceImpl");
//        service.update(1, "aa9");
//        System.out.println("update success");
////
       service.updateNative(1, "aa9");
       System.out.println("native updaate success");
    }
}
