package com.javaconcurrency.main;

import com.javaconcurrency.impl.BlockingQueueUsingSemaphore;

public class MainUsingSemaphore {
  public static void main(String[] args) throws InterruptedException {
    BlockingQueueUsingSemaphore<Integer> blockingQueue = new BlockingQueueUsingSemaphore<>(5);
    Thread t1 =
        new Thread(
            () -> {
              try {
                for (int i = 0; i < 50; i++) {
                  blockingQueue.enqueue(i);
                }
              } catch (InterruptedException ex) {
                // do nothing
              }
            });

    Thread t2 =
        new Thread(
            () -> {
              try {
                for (int i = 0; i < 25; i++) {
                  System.out.println("t2 dequeue: " + blockingQueue.dequeue());
                }
              } catch (InterruptedException ex) {
                // do nothing
              }
            });

    Thread t3 =
        new Thread(
            () -> {
              try {
                for (int i = 0; i < 25; i++) {
                  System.out.println("t3 dequeue: " + blockingQueue.dequeue());
                }
              } catch (InterruptedException ex) {
                // do nothing
              }
            });

    t1.start();
    t2.start();
    t3.start();
    t3.join();
    t2.join();
    t1.join();
  }
}
