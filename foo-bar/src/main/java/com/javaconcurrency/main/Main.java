package com.javaconcurrency.main;

import com.javaconcurrency.impl.PrintFooBar;

public class Main {
    public static void main(String[] args) {
        final PrintFooBar printFooBar = new PrintFooBar(5);
        Thread fooThread = new Thread(() -> {
            try {
                printFooBar.printFoo();
            } catch (InterruptedException e) {
                // ignore
            }
        });

        Thread barThread = new Thread(() -> {
            try {
                printFooBar.printBar();
            } catch (InterruptedException e) {
                // ignore
            }
        });

        fooThread.start();
        barThread.start();
    }
}
