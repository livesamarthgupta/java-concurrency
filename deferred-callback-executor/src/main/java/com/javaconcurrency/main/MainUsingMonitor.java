package com.javaconcurrency.main;

import com.javaconcurrency.impl.Callback;
import com.javaconcurrency.impl.DeferredCallbackExecutorUsingMonitor;

public class MainUsingMonitor {
    public static void main(String[] args) throws InterruptedException {
        final DeferredCallbackExecutorUsingMonitor callbackExecutor = new DeferredCallbackExecutorUsingMonitor();

        Thread service = new Thread(() -> {
            try {
                callbackExecutor.start();
            } catch (InterruptedException ex) {
                // do nothing
            }
        });

        service.start();

        Thread lateThread = new Thread(() -> {
            Callback cb = new Callback(8, "Hello this is the callback submitted first");
            callbackExecutor.registerCallback(cb);
        });
        lateThread.start();

        Thread.sleep(3000);

        Thread earlyThread = new Thread(() -> {
            Callback cb = new Callback(1, "Hello this is the callback submitted second");
            callbackExecutor.registerCallback(cb);
        });
        earlyThread.start();

        lateThread.join();
        earlyThread.join();
    }
}
