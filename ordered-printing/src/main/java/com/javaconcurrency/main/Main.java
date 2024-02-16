package com.javaconcurrency.main;

import com.javaconcurrency.impl.OrderedPrinting;

public class Main {
    public static void main(String[] args) {
        OrderedPrinting op = new OrderedPrinting();
        Thread t1 = new Thread(() -> {
            try {
                op.printFirst();
            } catch (InterruptedException e) {
                // ignore
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                op.printSecond();
            } catch (InterruptedException e) {
                // ignore
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                op.printThird();
            } catch (InterruptedException e) {
                // ignore;
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
