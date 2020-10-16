package com.mt.service;

import org.springframework.stereotype.Service;

@Service("royalCar")
public class RoyalCar implements CarService{
    @Override public void drive() {
        System.out.println("this is Royal car is driving");
    }
}
