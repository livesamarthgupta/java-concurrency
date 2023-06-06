package com.javaconcurrency.impl;

public class ReadWriteLock {
  private boolean isWriteLockAcquired = false;
  private int readerCount = 0;

  public synchronized void acquireReadLock() throws InterruptedException {
    while (isWriteLockAcquired) {
      wait();
    }
    readerCount++;
  }

  public synchronized void releaseReadLock() throws InterruptedException {
    readerCount--;
    notify();
  }

  public synchronized void acquireWriteLock() throws InterruptedException {
    while (isWriteLockAcquired || readerCount != 0) {
      wait();
    }
    isWriteLockAcquired = true;
  }

  public synchronized void releaseWriteLock() throws InterruptedException {
    isWriteLockAcquired = false;
    notify();
  }
}
