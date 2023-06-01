package com.javaconcurrency.impl;

public class Callback {
    long executeAt;
    String message;

    public Callback(long executeAt, String message) {
        this.executeAt = executeAt;
        this.message = message;
    }
}
