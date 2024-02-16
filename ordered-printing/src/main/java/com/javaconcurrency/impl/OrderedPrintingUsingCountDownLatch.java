package com.javaconcurrency.impl;

import java.util.concurrent.CountDownLatch;

public class OrderedPrintingUsingCountDownLatch {

    private CountDownLatch latch1 = new CountDownLatch(1);
    private CountDownLatch latch2 = new CountDownLatch(1);

    public void printFirst() throws InterruptedException {
        System.out.println("First");
        latch1.countDown();
    }

    public void printSecond() throws InterruptedException {
        latch1.await();
        System.out.println("Second");
        latch2.countDown();
    }

    public void printThird() throws InterruptedException {
        latch2.await();
        System.out.println("Third");
    }
}
