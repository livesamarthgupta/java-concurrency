## Problem Statment
A barrier can be thought of as a point in the program code, which all or some of the threads need to reach at before any one of them is allowed to proceed further.

## Solution
We can immediately realize that our solution will need a count variable to track the number of threads that have arrived at the barrier. If we have n threads, then n-1 threads must wait for the nth thread to arrive. This suggests we have the n-1 threads execute the wait method and the nth thread wakes up all the asleep n-1 threads.


