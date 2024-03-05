package com.javaconcurrency.impl;

public class FizzBuzz {
    private int n;
    private int i;
    public FizzBuzz(int n) {
        this.n = n;
        this.i = 1;
    }

    public synchronized void fizz() throws InterruptedException {
        while (i < n) {
            while(i % 3 != 0 || i % 5 == 0) {
                wait();
            }
            System.out.println("fizz");
            i++;
            notifyAll();
        }
    }

    public synchronized void buzz() throws InterruptedException {
        while (i < n) {
            while(i % 3 == 0 || i % 5 != 0) {
                wait();
            }
            System.out.println("buzz");
            i++;
            notifyAll();
        }
    }

    public synchronized void fizzbuzz() throws InterruptedException {
        while (i < n) {
            while(i % 3 != 0 || i % 5 != 0) {
                wait();
            }
            System.out.println("fizzbuzz");
            i++;
            notifyAll();
        }
    }

    public synchronized void number() throws InterruptedException {
        while (i < n) {
            while (i % 3 == 0 || i % 5 == 0) {
                wait();
            }
            System.out.println(i);
            i++;
            notifyAll();
        }
    }
}
