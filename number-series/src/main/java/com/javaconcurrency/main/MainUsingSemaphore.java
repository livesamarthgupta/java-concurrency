package com.javaconcurrency.main;

import com.javaconcurrency.impl.PrintNumberSeriesUsingSemaphore;

public class MainUsingSemaphore {
    public static void main(String[] args) {
        final PrintNumberSeriesUsingSemaphore printNumberSeriesUsingSemaphore = new PrintNumberSeriesUsingSemaphore(10);

        Thread evenThread = new Thread(() -> {
            try {
                printNumberSeriesUsingSemaphore.printEven();
            } catch (InterruptedException e) {
                // ignore
            }
        });

        Thread oddThread = new Thread(() -> {
            try {
                printNumberSeriesUsingSemaphore.printOdd();
            } catch (InterruptedException e) {
                // ignore
            }
        });

        oddThread.start();
        evenThread.start();
    }
}
