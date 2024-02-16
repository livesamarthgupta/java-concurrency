package com.javaconcurrency.impl;

public class OrderedPrinting {
    private int count;
    public OrderedPrinting() {
        count = 1;
    }
    public synchronized void printFirst() throws InterruptedException {
        while (count != 1) {
            wait();
        }
        System.out.println("First");
        count = 2;
        notifyAll();
    }

    public synchronized void printSecond() throws InterruptedException {
        while (count != 2) {
            wait();
        }
        System.out.println("Second");
        count = 3;
        notifyAll();
    }

    public synchronized void printThird() throws InterruptedException {
        while (count != 3) {
            wait();
        }
        System.out.println("Third");
        count = 1;
        notifyAll();
    }

}
