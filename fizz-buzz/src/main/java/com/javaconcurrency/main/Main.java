package com.javaconcurrency.main;

import com.javaconcurrency.impl.FizzBuzz;

public class Main {
    public static void main(String[] args) {
        final FizzBuzz fizzBuzz = new FizzBuzz(15);
        Thread fizzThread = new Thread(() -> {
            try {
                fizzBuzz.fizz();
            } catch (InterruptedException e) {
                // ignore
            }
        });

        Thread buzzThread = new Thread(() -> {
            try {
                fizzBuzz.buzz();
            } catch (InterruptedException e) {
                // ignore
            }
        });

        Thread fizzbuzzThread = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz();
            } catch (InterruptedException e) {
                // ignore
            }
        });

        Thread numberThread = new Thread(() -> {
            try {
                fizzBuzz.number();
            } catch (InterruptedException e) {
                // ignore
            }
        });

        fizzThread.start();
        buzzThread.start();
        fizzbuzzThread.start();
        numberThread.start();
    }
}
