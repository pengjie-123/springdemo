package com.mt.service;

import org.springframework.stereotype.Service;

@Service("audiCar")
public class AudiCar implements CarService{
    @Override public void drive() {
        System.out.println("this is Audi car driving");
    }
}
