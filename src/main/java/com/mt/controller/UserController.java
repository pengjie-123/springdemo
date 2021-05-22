package com.mt.controller;

import com.mt.service.UserService;
import com.mt.spring.XmlBeanDemo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired XmlBeanDemo demo;

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register(final HttpServletResponse r) {
        r.setStatus(401);
        userService.registerComplete();
        System.out.println("------id:" + demo.getId());
        return "register ok";
    }
}
