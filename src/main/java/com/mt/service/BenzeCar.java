package com.mt.service;

import org.springframework.stereotype.Service;

@Service("benzCar")
public class BenzeCar implements CarService{
    @Override public void drive() {
        System.out.println("this is Benz is driving");
    }
}
