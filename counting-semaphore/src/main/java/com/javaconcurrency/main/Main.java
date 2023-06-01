package com.javaconcurrency.main;

import com.javaconcurrency.impl.CountingSemaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final CountingSemaphore semaphore = new CountingSemaphore(1);

        Thread t1 = new Thread(() -> {
           try{
               for(int i = 0; i < 5; i++) {
                   semaphore.acquire();
               }
           } catch (InterruptedException ex) {
               // do nothing
           }
        });

        Thread t2 = new Thread(() -> {
           try{
               for(int i = 0; i < 5; i++) {
                   semaphore.release();
               }
           } catch (InterruptedException ex) {
               // do nothing
           }
        });

        t2.start();
        t1.start();
        t1.join();
        t2.join();
    }
}
