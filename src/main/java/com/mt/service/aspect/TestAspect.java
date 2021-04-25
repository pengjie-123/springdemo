package com.mt.service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAspect {

    @Around("execution(* com.mt.service.TestServiceImpl.find(..))")
    public Object call(ProceedingJoinPoint point) throws Throwable {
        System.out.println("this is point cut  before method run");
        Object o = point.proceed();
        System.out.println("this is point cut after method run");
        return o;
    }
}
