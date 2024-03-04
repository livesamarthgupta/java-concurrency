package com.javaconcurrency.impl;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class H2OMachineUsingSemaphore {
    private static final Character HYDROGEN = 'H';
    private static final Character OXYGEN = 'O';
    private Character[] molecule;
    private AtomicInteger count;
    private Semaphore hydrogenSemaphore;
    private Semaphore oxygenSemaphore;

    public H2OMachineUsingSemaphore() {
        molecule = new Character[3];
        count = new AtomicInteger(0);
        hydrogenSemaphore = new Semaphore(2);
        oxygenSemaphore = new Semaphore(1);
    }

    public void hydrogenAtom() throws InterruptedException {
        hydrogenSemaphore.acquire();
        molecule[count.get()] = HYDROGEN;
        count.incrementAndGet();

        if (count.get() == 3) {
            System.out.print(Arrays.toString(molecule));
            molecule = new Character[3];
            count.set(0);
            hydrogenSemaphore.release(2);
            oxygenSemaphore.release();
        }
    }

    public void oxygenAtom() throws InterruptedException {
        oxygenSemaphore.acquire();
        molecule[count.get()] = OXYGEN;
        count.incrementAndGet();

        if (count.get() == 3) {
            System.out.print(Arrays.toString(molecule));
            molecule = new Character[3];
            count.set(0);
            hydrogenSemaphore.release(2);
            oxygenSemaphore.release();
        }
    }
}
