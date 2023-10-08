package com.mitsko.financial_system.exception;

public class ValidationException extends RuntimeException {

    public ValidationException() {
    }

    public ValidationException(String message) {
        super("Validation exception: " + message);
    }

    public ValidationException(String message, Throwable cause) {
        super("Validation exception: " + message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

}
