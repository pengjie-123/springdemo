package com.mt.spring;

import com.mt.cache.TestService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlBeanDemo {
    Integer id=50;
    public XmlBeanDemo() {
        System.out.println("--------------------- I am created ----------------------" + this.getId());
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object      bean1 = context.getBean("bean1");
        TestService c = (TestService) context.getBean("test");
        System.out.println(c);
        System.out.println(c.getClass());
        System.out.println(c.find(2));
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public void init() {
        System.out.println("---------:init:" +id);
        this.id=203;
        System.out.println("---------:init2:" +id);
    }
}
