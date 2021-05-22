package com.mt.controller;

import com.mt.bean.User;
import com.mt.component.TestSpringBean;
import com.mt.service.TestService;
import com.mt.spring.XmlBeanDemo;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class SpringController {
    private String key = WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;
    @Autowired ServletContext servletContext;

    @Autowired ApplicationContext context;

    @Autowired TestService testService;
    @Autowired XmlBeanDemo demo;

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

    @RequestMapping("request")
    String request(HttpServletRequest request, User user) {
        String                queryString  = request.getQueryString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(parameterMap);
        System.out.println(queryString);
        System.out.println(user);
        return "ok";
    }

    @RequestMapping("container")
    void test() {
        System.out.println(testService);
        System.out.println(testService.getClass());
        testService.find(1);
    }

    // we create a same name bean in parent container and sub container and test if these two containers
    // are absolutely separately, the answer is yes
    @RequestMapping("container2")
    void test2(HttpServletRequest request) {
        // use default sub container bean, it is bean from sub container
        System.out.println(demo);
        System.out.println(demo.getId());

        // use parent container to get bean, o and o2 is same
        Object o = servletContext.getAttribute(key);
        WebApplicationContext o2 = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        demo = (XmlBeanDemo) o2.getBean("demo2");
        System.out.println(demo);
        System.out.println(demo.getId());

        // use sub container to get bean
        WebApplicationContext o3 = RequestContextUtils.findWebApplicationContext(request);
        demo = (XmlBeanDemo) o3.getBean("demo2");
        System.out.println(demo);
        System.out.println(demo.getId());

    }

}
