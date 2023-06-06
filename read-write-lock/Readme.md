## Problem Statement
Imagine you have an application where you have multiple readers and multiple writers. You are asked to design a lock which lets multiple readers read at the same time, but only one writer write at a time.

## Solution
First of all let us define the APIs our class will expose. We'll need two for writer and two for reader. These are:

acquireReadLock
releaseReadLock
acquireWriteLock
releaseWriteLock

```java
public class ReadWriteLock {

    public synchronized void acquireReadLock() throws InterruptedException {
    }

    public synchronized void releaseReadLock() {
    }

    public synchronized void acquireWriteLock() throws InterruptedException {
    }

    public synchronized void releaseWriteLock() {
    }
}
```

This problem becomes simple if you think about each case:

- Before we allow a reader to enter the critical section, we need to make sure that there's no writer in progress. It is ok to have other readers in the critical section since they aren't making any modifications

- Before we allow a writer to enter the critical section, we need to make sure that there's no reader or writer in the critical section.




