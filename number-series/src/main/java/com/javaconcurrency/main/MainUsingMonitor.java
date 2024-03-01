package com.javaconcurrency.main;

import com.javaconcurrency.impl.PrintNumberSeriesUsingMonitor;

public class MainUsingMonitor {
    public static void main(String[] args) {
        final PrintNumberSeriesUsingMonitor printNumberSeriesUsingMonitor = new PrintNumberSeriesUsingMonitor(10);

        Thread evenThread = new Thread(() -> {
            try {
                printNumberSeriesUsingMonitor.printEven();
            } catch (InterruptedException e) {
                // ignore
            }
        });

        Thread oddThread = new Thread(() -> {
            try {
                printNumberSeriesUsingMonitor.printOdd();
            } catch (InterruptedException e) {
                // ignore
            }
        });

        oddThread.start();
        evenThread.start();
    }
}
