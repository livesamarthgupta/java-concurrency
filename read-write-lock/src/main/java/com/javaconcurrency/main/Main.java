package com.javaconcurrency.main;

import com.javaconcurrency.impl.ReadWriteLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final ReadWriteLock lock = new ReadWriteLock();
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("Attempting to acquire write lock in t1: " + System.currentTimeMillis());
                lock.acquireWriteLock();
                System.out.println("write lock acquired t1: " + +System.currentTimeMillis());

                // Simulates write lock being held indefinitely
                for (; ; ) {
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                // do nothing
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("Attempting to acquire write lock in t2: " + System.currentTimeMillis());
                lock.acquireWriteLock();
                System.out.println("write lock acquired t2: " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                // do nothing
            }
        });

        Thread tReader1 = new Thread(() -> {
            try {
                lock.acquireReadLock();
                System.out.println("Read lock acquired: " + System.currentTimeMillis());
            } catch (InterruptedException ie) {
                // do nothing
            }
        });

        Thread tReader2 = new Thread(() -> {
            try {
                System.out.println("Read lock about to release: " + System.currentTimeMillis());
                lock.releaseReadLock();
                System.out.println("Read lock released: " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                // do nothing
            }
        });

        tReader1.start();
        t1.start();
        Thread.sleep(3000);
        tReader2.start();
        Thread.sleep(1000);
        t2.start();
        tReader1.join();
        tReader2.join();
        t2.join();

    }
}
