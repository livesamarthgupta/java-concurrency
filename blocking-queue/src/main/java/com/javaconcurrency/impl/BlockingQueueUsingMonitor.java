package com.javaconcurrency.impl;

public class BlockingQueueUsingMonitor<T> {
  T[] array;
  int size;
  int capacity;
  int head = 0;
  int tail = 0;

  public BlockingQueueUsingMonitor(int capacity) {
    this.capacity = capacity;
    array = (T[]) new Object[this.capacity];
  }

  public synchronized void enqueue(T item) throws InterruptedException {
    System.out.println("enqueue: " + item);
    if (size == capacity) {
      this.wait();
    }
    if (tail == capacity) {
      tail = 0;
    }
    array[tail] = item;
    tail++;
    size++;
    this.notifyAll();
  }

  public synchronized T dequeue() throws InterruptedException {
    if (size == 0) {
      this.wait();
    }
    if (head == capacity) {
      head = 0;
    }
    T item = array[head];
    head++;
    size--;
    this.notifyAll();
    System.out.println("dequeue: " + item);
    return item;
  }
}
