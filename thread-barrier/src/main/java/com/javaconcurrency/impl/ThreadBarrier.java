package com.javaconcurrency.impl;

public class ThreadBarrier {
  private int count = 0;
  private int released = 0;
  private final int totalThreads;

  public ThreadBarrier(int totalThreads) {
    this.totalThreads = totalThreads;
  }

  public synchronized void await() throws InterruptedException {
    // block any new threads from proceeding till,
    // all threads from previous barrier are released
    while (count == totalThreads) wait();

    // increment the counter whenever a thread arrives at the barrier.
    count++;

    if (totalThreads == count) {
      notifyAll();
      // remember to reset count so that barrier can be reused
      released = totalThreads;
    } else {
      while (count < totalThreads) wait();
    }
    released--;
    if (released == 0) {
      count = 0;
      // remember to wakeup any threads waiting on line#15
      notifyAll();
    }
  }
}
