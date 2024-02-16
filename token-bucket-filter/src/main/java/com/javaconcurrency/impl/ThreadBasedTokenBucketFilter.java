package com.javaconcurrency.impl;

public class ThreadBasedTokenBucketFilter {
    private final int MAX_TOKENS;
    private int possibleTokens = 0;
    private static final int ONE_SECOND = 1000;

    public ThreadBasedTokenBucketFilter(int maxTokens) {
        this.MAX_TOKENS = maxTokens;
        Thread bgThread = new Thread(this::fillBucket);
        bgThread.setDaemon(true);
        bgThread.setName("background_thread");
        bgThread.start();
    }

    private void fillBucket() {
        while (true) {
            synchronized (this) {
                if(possibleTokens < MAX_TOKENS) {
                    possibleTokens++;
                }
                notifyAll();
            }
            try {
                Thread.sleep(ONE_SECOND);
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }

    public synchronized void getToken() throws InterruptedException {
        while (possibleTokens == 0) {
            wait();
        }

        possibleTokens--;

        System.out.println("Granting " + Thread.currentThread().getName() + " token at time: " + (System.currentTimeMillis()/1000));
    }

}
