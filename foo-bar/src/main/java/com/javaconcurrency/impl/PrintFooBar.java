package com.javaconcurrency.impl;

public class PrintFooBar {
    private int n;
    private boolean printFoo = true;

    public PrintFooBar(int n) {
        this.n = n;
    }

    public synchronized void printFoo() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (!printFoo) {
                wait();
            }
            System.out.print("foo");
            printFoo = false;
            notifyAll();
        }
    }

    public synchronized void printBar() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (printFoo) {
                wait();
            }
            System.out.println("bar");
            printFoo = true;
            notifyAll();
        }
    }
}
