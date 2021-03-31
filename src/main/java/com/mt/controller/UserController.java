package com.mt.controller;

import com.mt.service.UserService;
import com.mt.spring.CreateBeanDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired CreateBeanDemo demo;

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register() {
        userService.registerComplete();
        System.out.println("------id:" + demo.getId());
        return "register ok";
    }
}
