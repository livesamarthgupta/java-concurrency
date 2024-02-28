package com.javaconcurrency.impl;

import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DeferredCallbackExecutorUsingMutex {
    private PriorityQueue<Callback> queue = new PriorityQueue<>((o1, o2) -> (int) (o1.executeAt - o2.executeAt));
    private ReentrantLock lock = new ReentrantLock();
    private Condition newCallbackArrived = lock.newCondition();

    // called by consumer threads to add callback
    public void registerCallback(Callback callback) {
        lock.lock();
        queue.add(callback);
        newCallbackArrived.signal();
        lock.unlock();
    }

    // called by executor thread
    public void start() throws InterruptedException {
        long sleepFor = 0;
        while (true) {
            lock.lock();
            // wait for callback to be added
            while (queue.size() == 0) {
                newCallbackArrived.await();
            }

            // wait for callback to be available
            while (queue.size() != 0) {
                sleepFor = findSleepDuration();
                if (sleepFor <= 0) {
                    break;
                }
                newCallbackArrived.await(sleepFor, TimeUnit.MILLISECONDS);
            }

            Callback callback = queue.poll();
            System.out.println("Executed at " + System.currentTimeMillis() / 1000 + " required at " + callback.executeAt / 1000 + ": message:" + callback.message);
            lock.unlock();
        }
    }

    private long findSleepDuration() {
        long currentTime = System.currentTimeMillis();
        return queue.peek().executeAt - currentTime;
    }
}
