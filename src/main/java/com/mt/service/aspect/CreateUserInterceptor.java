package com.mt.service.aspect;

import com.mt.service.UserService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
public class CreateUserInterceptor implements MethodInterceptor {
//    @Autowired UserService userService;

    @Override public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("action before---");
        System.out.println(TransactionSynchronizationManager.isSynchronizationActive());

        Object obj = invocation.proceed();


        System.out.println("action after");
        System.out.println(TransactionSynchronizationManager.isSynchronizationActive());
        return obj;
    }
}
