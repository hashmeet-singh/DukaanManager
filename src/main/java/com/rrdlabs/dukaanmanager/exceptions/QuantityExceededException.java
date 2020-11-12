package com.rrdlabs.dukaanmanager.exceptions;

public class QuantityExceededException extends RuntimeException  {
    public QuantityExceededException(String message) {
        super(message);
    }
}