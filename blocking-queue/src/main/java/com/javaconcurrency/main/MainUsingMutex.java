package com.javaconcurrency.main;

import com.javaconcurrency.impl.BlockingQueueUsingMutex;

public class MainUsingMutex {
  public static void main(String[] args) throws InterruptedException {
    BlockingQueueUsingMutex<Integer> blockingQueue = new BlockingQueueUsingMutex<>(5);
    Thread t1 =
        new Thread(
            () -> {
              for (int i = 0; i < 50; i++) {
                blockingQueue.enqueue(i);
              }
            });

    Thread t2 =
        new Thread(
            () -> {
              for (int i = 0; i < 25; i++) {
                System.out.println("t2 dequeue: " + blockingQueue.dequeue());
              }
            });

    Thread t3 =
        new Thread(
            () -> {
              for (int i = 0; i < 25; i++) {
                System.out.println("t3 dequeue: " + blockingQueue.dequeue());
              }
            });

    t1.start();
    Thread.sleep(4000);
    t2.start();
    t3.start();
    t2.join();
    t3.join();
    t1.join();
  }
}
