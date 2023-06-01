package com.javaconcurrency.impl;

public class Callback {
  long executeAt;
  String message;

  public Callback(long executeAt, String message) {
    this.executeAt = System.currentTimeMillis() + (executeAt * 1000);
    this.message = message;
  }
}
