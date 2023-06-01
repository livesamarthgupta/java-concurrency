package com.javaconcurrency.impl;

public class CountingSemaphore {
    int usedPermits = 0;
    int maxPermits;

    public CountingSemaphore(int maxPermits) {
        this.maxPermits = maxPermits;
    }

    public synchronized void acquire() throws InterruptedException {
        while (usedPermits == maxPermits)
            wait();
        System.out.println("acquiring");
        notify();
        usedPermits++;
    }

    public synchronized void release() throws InterruptedException {
        while (usedPermits == 0)
            wait();
        System.out.println("releasing");
        usedPermits--;
        notify();
    }
}
