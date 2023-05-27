package com.javaconcurrency.main;

import com.javaconcurrency.impl.BlockingQueueUsingSynchronized;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    BlockingQueueUsingSynchronized<Integer> blockingQueueUsingSynchronized =
        new BlockingQueueUsingSynchronized<>(5);
    Thread t1 =
        new Thread(
            () -> {
              try {
                for (int i = 0; i < 50; i++) {
                  blockingQueueUsingSynchronized.enqueue(i);
                }
              } catch (InterruptedException ex) {
                // do something
              }
            });

    Thread t2 =
        new Thread(
            () -> {
              try {
                for (int i = 0; i < 25; i++) {
                  blockingQueueUsingSynchronized.dequeue();
                }
              } catch (InterruptedException ex) {
                // do something
              }
            });

    Thread t3 =
        new Thread(
            () -> {
              try {
                for (int i = 0; i < 25; i++) {
                  blockingQueueUsingSynchronized.dequeue();
                }
              } catch (InterruptedException ex) {
                // do something
              }
            });

    t1.start();
    Thread.sleep(4000);
    t2.start();
    t3.start();

    t1.join();
    t2.join();
    t3.join();
  }
}
