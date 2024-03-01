package com.javaconcurrency.impl;

import java.util.concurrent.Semaphore;

public class PrintNumberSeriesUsingSemaphore {
    private int n;
    private Semaphore oddSem, evenSem;

    public PrintNumberSeriesUsingSemaphore(int n) {
        this.n = n;
        oddSem = new Semaphore(1);
        evenSem = new Semaphore(0);
    }

    public void printOdd() throws InterruptedException {
        for (int i = 1; i <= n; i+=2) {
            oddSem.acquire();
            System.out.println(i);
            evenSem.release();
        }
    }

    public void printEven() throws InterruptedException {
        for (int i = 2; i <= n; i+=2) {
            evenSem.acquire();
            System.out.println(i);
            oddSem.release();
        }
    }
}
