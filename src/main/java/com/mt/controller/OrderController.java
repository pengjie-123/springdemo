package com.mt.controller;

import com.mt.observer.spring.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService service;

    @RequestMapping("/save")
    public String register() {
        service.saveOrder();
        return "save order ok";
    }
}
