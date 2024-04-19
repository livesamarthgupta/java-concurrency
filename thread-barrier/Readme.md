## Problem Statment
A barrier can be thought of as a point in the program code, which all or some of the threads need to reach at before any one of them is allowed to proceed further.

## Solution
We can immediately realize that our solution will need a count variable to track the number of threads that have arrived at the barrier. If we have n threads, then n-1 threads must wait for the nth thread to arrive. This suggests we have the n-1 threads execute the wait method and the nth thread wakes up all the asleep n-1 threads.

```java
public class Barrier {
  int count = 0;
  int totalThreads;

  public Barrier(int totalThreads) {
      this.totalThreads = totalThreads;
  }

 public synchronized void await() throws InterruptedException {
     // increment the counter whenever a thread arrives at the
     // barrier.
     count++;

     if (count == totalThreads) {
         // wake up all the threads.
         notifyAll();
         // remember to reset count so that barrier can be reused
         count = 0;
     } else {
         // wait if you aren't the nth thread
         while (count < totalThreads) 
             wait();
     }
  }
}
```


