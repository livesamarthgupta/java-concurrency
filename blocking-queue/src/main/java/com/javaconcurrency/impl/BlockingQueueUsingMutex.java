package com.javaconcurrency.impl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueUsingMutex<T> {
  T[] array;
  int size;
  int capacity;
  int head = 0;
  int tail = 0;
  Lock lock = new ReentrantLock();

  public BlockingQueueUsingMutex(int capacity) {
    this.capacity = capacity;
    array = (T[]) new Object[this.capacity];
  }

  public void enqueue(T item) {
    lock.lock();
    while (size == capacity) {
      lock.unlock();
      lock.lock();
    }
    System.out.println("enqueue: " + item);
    if (tail == capacity) {
      tail = 0;
    }
    array[tail] = item;
    tail++;
    size++;
    lock.unlock();
  }

  public T dequeue() {
    T item;
    lock.lock();
    while (size == 0) {
      lock.unlock();
      lock.lock();
    }
    if (head == capacity) {
      head = 0;
    }
    item = array[head];
    head++;
    size--;
    System.out.println("dequeue: " + item);
    lock.unlock();
    return item;
  }
}
