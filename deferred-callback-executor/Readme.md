## Problem Statement
Design and implement a thread-safe class that allows registration of callback methods that are executed after a user specified time interval in seconds has elapsed.

### Solution
Let us try to understand the problem without thinking about concurrency. Let's say our class exposes an API called registerCallback() that'll take a parameter of type Callback, which we'll define later. Anyone calling this API should be able to specify after how many seconds should our executor invoke the passed in callback.

One naive way to solve this problem is to have a busy thread that continuously loops over the list of callbacks and executes them as they become due. However, the challenge here is to design a solution which doesn't involve a busy thread.

If we restrict ourselves to use only concurrency constructs offered by Java then one possible solution is to have an execution thread that maintains a priority queue of callbacks ordered by the time remaining to execute each of the callbacks. The execution thread can sleep for the duration equal to the time duration before the earliest callback in the min-heap becomes due for execution.

Consumer threads can come and add their desired callbacks in the min-heap within a critical section. However, whenever a consumer thread requests a callback be registered, the caveat is to wake up the execution thread and recalculate the minimum duration it needs to sleep for before the earliest callback becomes due for execution. It is possible that a callback with an earlier due timestamp gets added by a consumer thread while the executor thread is current;y asleep for a duration, calculated for a callback due later than the one just added.

Consider this example: initially, the execution thread is sleeping for 30 mins before any callback in the min-heap is due. A consumer thread comes along and adds a callback to be executed after 5 minutes. The execution thread would need to wake up and reset itself to sleep for only 5 minutes instead of 30 minutes. Once we find an elegant way of capturing this logic our problem is pretty much solved.

Let's see how the skeleton of our class would look like:

```agsl
public class DeferredCallbackExecutor {

    PriorityQueue<CallBack> q = new PriorityQueue<CallBack>(new Comparator<CallBack>() {

        public int compare(CallBack o1, CallBack o2) {
            return (int) (o1.executeAt - o2.executeAt);
        }
    });

    // Run by the Executor Thread
    public void start() throws InterruptedException {

    }

    // Called by Consumer Threads to register callback
    public void registerCallback(CallBack callBack) {

    }

    /**
     * Represents the class which holds the callback. For simplicity instead of 
     * executing a method, we print a message.
     */
    static class CallBack {

        long executeAt;
        String message;

        public CallBack(long executeAfter, String message) {
            this.executeAt = System.currentTimeMillis() + executeAfter * 1000;
            this.message = message;
        }
    }
}
```

