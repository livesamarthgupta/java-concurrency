package com.javaconcurrency.impl;

public class PrintNumberSeriesUsingMonitor {
    private int n;
    private int i;
    public PrintNumberSeriesUsingMonitor(int n) {
        this.n = n;
        this.i = 1;
    }

    public synchronized void printEven() throws InterruptedException {
        while (i != n) {
            while (i % 2 != 0) {
                wait();
            }
            System.out.println(i);
            i++;
            notifyAll();
        }
    }

    public synchronized void printOdd() throws InterruptedException {
        while (i != n) {
            while (i % 2 == 0) {
                wait();
            }
            System.out.println(i);
            i++;
            notifyAll();
        }
    }
}
