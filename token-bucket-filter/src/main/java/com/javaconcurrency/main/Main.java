package com.javaconcurrency.main;

import com.javaconcurrency.impl.TokenBucketFilter;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Thread> threads = new HashSet<>();
        final TokenBucketFilter bucketFilter = new TokenBucketFilter(5);
        // let bucket fill up
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            // ignore
        }

        // Generate 12 threads requesting tokens almost all at once.
        for (int i = 0; i < 12; i++) {
            Thread t = new Thread(() -> {
                try {
                    bucketFilter.getToken();
                } catch (InterruptedException e) {
                    // ignore
                }
            });

            t.setName("Thread:" + i);
            threads.add(t);
        }

        for(Thread t : threads) {
            t.start();
        }

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }
}
