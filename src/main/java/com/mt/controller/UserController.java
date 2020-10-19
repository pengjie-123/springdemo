package com.mt.controller;

import com.mt.observer.anno.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register() {
        userService.registerComplete();
        return "register ok";
    }
}
