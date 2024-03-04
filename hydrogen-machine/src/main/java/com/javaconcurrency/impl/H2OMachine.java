package com.javaconcurrency.impl;

import java.util.Arrays;
import java.util.Collections;

public class H2OMachine {
    private static final Character HYDROGEN = 'H';
    private static final Character OXYGEN = 'O';
    private Character[] molecule;
    private int count;

    public H2OMachine() {
        this.molecule = new Character[3];
        this.count  = 0;
    }

    public synchronized void hydrogenAtom() throws InterruptedException {
        while(Collections.frequency(Arrays.asList(molecule), HYDROGEN) == 2) {
            wait();
        }

        molecule[count] = HYDROGEN;
        count++;

        if (count == 3) {
            System.out.print(Arrays.toString(molecule));
            molecule = new Character[3];
            count = 0;
            notifyAll();
        }
    }

    public synchronized void oxygenAtom() throws InterruptedException {
        while(Collections.frequency(Arrays.asList(molecule), OXYGEN) == 1) {
            wait();
        }

        molecule[count] = OXYGEN;
        count++;

        if (count == 3) {
            System.out.print(Arrays.toString(molecule));
            molecule = new Character[3];
            count = 0;
            notifyAll();
        }
    }
}
