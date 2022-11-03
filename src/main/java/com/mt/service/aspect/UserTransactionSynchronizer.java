package com.mt.service.aspect;


import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
public class UserTransactionSynchronizer implements TransactionSynchronization {
    private static final ThreadLocal<UserCallback> callback = new ThreadLocal<>();

    @Override public void afterCommit() {
        try {
            System.out.println("this is in method after commit");
            callback.get().doSend();
        } finally {
            callback.remove();
        }


    }

    public void doAfterCommit(UserCallback userCallback) {
        boolean active = TransactionSynchronizationManager.isSynchronizationActive();
        if (active) {
            callback.set(userCallback);
            TransactionSynchronizationManager.registerSynchronization(this);
        } else {
            userCallback.doSend();
        }

    }

}
