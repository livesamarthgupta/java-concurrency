package com.javaconcurrency.main;

import com.javaconcurrency.impl.H2OMachineUsingMonitor;

import java.util.HashSet;
import java.util.Set;

public class MainUsingMonitor {
    public static void main(String[] args) {
        final H2OMachineUsingMonitor machine =  new H2OMachineUsingMonitor();
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
