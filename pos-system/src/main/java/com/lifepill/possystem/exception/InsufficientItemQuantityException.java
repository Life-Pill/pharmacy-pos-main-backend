package com.lifepill.possystem.exception;

public class InsufficientItemQuantityException extends RuntimeException {
    public InsufficientItemQuantityException(String message) {
        super(message);
    }
}
