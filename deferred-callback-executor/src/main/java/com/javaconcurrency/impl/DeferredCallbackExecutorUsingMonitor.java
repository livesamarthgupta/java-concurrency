package com.javaconcurrency.impl;

import java.util.PriorityQueue;

public class DeferredCallbackExecutorUsingMonitor {
    private PriorityQueue<Callback> queue = new PriorityQueue<>((o1, o2) -> (int) (o1.executeAt - o2.executeAt));


    // called by consumer threads to add callback
    public synchronized void registerCallback(Callback callback) {
        queue.add(callback);
        notifyAll();
    }

    // called by executor thread
    public synchronized void start() throws InterruptedException {
        long sleepFor = 0;
        while (true) {
            // wait for callback to be added
            while (queue.size() == 0) {
                wait();
            }

            // wait for callback to be available
            while (queue.size() != 0) {
                sleepFor = findSleepDuration();
                if (sleepFor <= 0) {
                    break;
                }
                wait(sleepFor);
            }

            Callback callback = queue.poll();
            System.out.println("Executed at " + System.currentTimeMillis() / 1000 + " required at " + callback.executeAt / 1000 + ": message:" + callback.message);
        }
    }

    private long findSleepDuration() {
        long currentTime = System.currentTimeMillis();
        return queue.peek().executeAt - currentTime;
    }
}
