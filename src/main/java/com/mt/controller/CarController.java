package com.mt.controller;

import com.mt.service.CarService;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    @Autowired
    Collection<CarService>  cars;
    @Autowired
    Map<String, CarService> data;

    @RequestMapping("/run")
    public String run() {
        //list
        for (CarService car : cars) {
            car.drive();
        }
        ;
        System.out.println("----------------------");
        Set<String> sets = data.keySet();
        for (String key : sets) {
            CarService o = (CarService) data.get(key);
            o.drive();
        }
        return "all cars are running";
    }
}
