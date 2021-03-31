package com.mt.spring;

import com.mt.component.TestSpringBean;
import java.util.Map;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CreateBeanDemo {
    Integer id=50;
    public CreateBeanDemo() {
        System.out.println("--------------------- I am created ----------------------" + this.getId());
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object  bean1 = context.getBean("bean1");
        Map<String, TestSpringBean> beans = context.getBeansOfType(TestSpringBean.class);
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
