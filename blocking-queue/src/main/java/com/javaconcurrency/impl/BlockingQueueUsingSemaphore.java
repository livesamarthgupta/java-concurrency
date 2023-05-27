package com.javaconcurrency.impl;

import java.util.concurrent.Semaphore;

public class BlockingQueueUsingSemaphore<T> {
  T[] array;
  int size;
  int capacity;
  int head = 0;
  int tail = 0;
  Semaphore semaphoreLock;
  Semaphore producerSemaphore;
  Semaphore consumerSemaphore;

  public BlockingQueueUsingSemaphore(int capacity) throws InterruptedException {
    this.capacity = capacity;
    array = (T[]) new Object[this.capacity];
    semaphoreLock = new Semaphore(1);
    producerSemaphore = new Semaphore(this.capacity);
    consumerSemaphore = new Semaphore(this.capacity);
    consumerSemaphore.acquire(this.capacity);
  }

  public void enqueue(T item) throws InterruptedException {
    producerSemaphore.acquire();
    semaphoreLock.acquire();
    System.out.println("enqueue: " + item);
    if (tail == capacity) {
      tail = 0;
    }
    array[tail] = item;
    tail++;
    size++;
    semaphoreLock.release();
    consumerSemaphore.release();
  }

  public T dequeue() throws InterruptedException {
    consumerSemaphore.acquire();
    semaphoreLock.acquire();
    if (head == capacity) {
      head = 0;
    }
    T item = array[head];
    head++;
    size--;
    System.out.println("dequeue: " + item);
    semaphoreLock.release();
    producerSemaphore.release();
    return item;
  }
}
