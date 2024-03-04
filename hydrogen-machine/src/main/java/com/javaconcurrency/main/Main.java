package com.javaconcurrency.main;

import com.javaconcurrency.impl.H2OMachine;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        final H2OMachine machine =  new H2OMachine();
        Set<Thread> threads = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            Thread hThread = new Thread(() -> {
                try {
                    machine.hydrogenAtom();
                } catch (InterruptedException e) {
                    // ignore;
                }
            });

            Thread oThread = new Thread(() -> {
                try {
                    machine.oxygenAtom();
                } catch (InterruptedException e) {
                    // ignore;
                }
            });

            threads.add(hThread);
            threads.add(oThread);
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
