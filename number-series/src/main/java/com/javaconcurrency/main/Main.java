package com.javaconcurrency.main;

import com.javaconcurrency.impl.PrintNumberSeries;

public class Main {
    public static void main(String[] args) {
        final PrintNumberSeries printNumberSeries = new PrintNumberSeries(10);

        Thread evenThread = new Thread(() -> {
            try {
                printNumberSeries.printEven();
            } catch (InterruptedException e) {
                // ignore
            }
        });

        Thread oddThread = new Thread(() -> {
            try {
                printNumberSeries.printOdd();
            } catch (InterruptedException e) {
                // ignore
            }
        });

        oddThread.start();
        evenThread.start();
    }
}
