package com.example.bankingapp.exceptions;

public class SameAccountException extends RuntimeException{
    public SameAccountException() {
    }

    public SameAccountException(String message) {
        super(message);
    }

    public SameAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public SameAccountException(Throwable cause) {
        super(cause);
    }

    public SameAccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
