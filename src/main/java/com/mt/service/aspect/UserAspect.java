package com.mt.service.aspect;

import com.mt.bean.User;
import com.mt.service.UserService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Order(0)
@Component
@Aspect
public class UserAspect {
    @Autowired UserTransactionSynchronizer userTransactionSynchronizer;
    @Autowired UserService userService;

    /**
     * This is a good example:  the transaction didn't commit even the code go to this method even if
     * use @AfterReturning, @After because we didn't declare the @order for them then it will have a lower
     * priority
     * this can be solved by 2 solutions below:
     *
     * 1. declare the order value, like order=0, lower value of order. the higher priority it will be invoke and the
     * txAdvice is also a interceptor of spring transaction.  if you want your own interceptor not included in the
     * transaction. then you must define the order lower than txAdvice. see ctx-transaction-support.xml,
     * order(txAdvice)=1, order(createUserInterceptor)=2, order(UserAspect)=0 so the execute orders will be
     * UserAspect -> txAdvice -> createUserInterceptor -> createUserInterceptor -> txAdvice ->  UserAspect
     *
     *so UserAspect not include in the transaction but createUserInterceptor included in the transaction
     *
     *
     * 2. This also can be resolved by register a TransactionSynchronization if you dont want to define the order
     * @see UserTransactionSynchronizer
     *
     * @param user
     * @throws Throwable
     */
    @AfterReturning(pointcut = " execution(* com.mt.service.UserService.create(..))", returning = "user")
    public void after(Object user) throws Throwable {
        User u = (User) user;
        System.out.println(TransactionSynchronizationManager.isSynchronizationActive());

        userTransactionSynchronizer.doAfterCommit(()-> { doSomething(u.getUserId()); });
    }

//    @Around(" execution(* com.mt.service.UserService.create(..))")
//    public Object around(ProceedingJoinPoint jp) throws Throwable {
//        User u = (User) jp.getArgs()[0];
//        Object o = jp.proceed();
//        System.out.println("I think transaction was commit here? user aspect");
//        System.out.println(TransactionSynchronizationManager.isSynchronizationActive());
//
//        return o;
//
//    }

    private void doSomething(Long userId) {
        User u = userService.getUser(userId);
        System.out.println("do something, name: " + u.getUserName() + ", age: " + u.getStatus().name());
    }
}
