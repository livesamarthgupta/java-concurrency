package com.javaconcurrency.impl;

public class TokenBucketFilter {
    private final int MAX_TOKENS;
    private long lastRequestTime = System.currentTimeMillis();
    private long possibleTokens = 0;

    public TokenBucketFilter(int maxTokens) {
        MAX_TOKENS = maxTokens;
    }

    public synchronized void getToken() throws InterruptedException {
        possibleTokens += (System.currentTimeMillis() - lastRequestTime) / 1000;

        if(possibleTokens > MAX_TOKENS) {
            possibleTokens = MAX_TOKENS;
        }

        if(possibleTokens == 0) {
            Thread.sleep(1000);
        } else {
            possibleTokens--;
        }

        lastRequestTime = System.currentTimeMillis();
        System.out.println("Granting " + Thread.currentThread().getName() + " token at time: " + (lastRequestTime/1000));
    }

}
