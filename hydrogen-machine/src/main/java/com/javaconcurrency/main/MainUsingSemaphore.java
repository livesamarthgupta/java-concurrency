package com.javaconcurrency.main;

import com.javaconcurrency.impl.H2OMachineUsingSemaphore;

import java.util.HashSet;
import java.util.Set;

public class MainUsingSemaphore {
    public static void main(String[] args) {
        final H2OMachineUsingSemaphore machine =  new H2OMachineUsingSemaphore();
        Set<Thread> threads = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> {
                try {
                    machine.hydrogenAtom();
                } catch (InterruptedException e) {
                    // ignore
                }
            }));

            threads.add(new Thread(() -> {
                try {
                    machine.oxygenAtom();
                } catch (InterruptedException e) {
                    // ignore
                }
            }));
        }

        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                // ignore;
            }
        });
    }
}
