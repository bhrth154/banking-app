package com.example.bankingapp.exceptions;

public class UnsufficientBalanceException extends RuntimeException{
    public UnsufficientBalanceException() {
    }

    public UnsufficientBalanceException(String message) {
        super(message);
    }

    public UnsufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsufficientBalanceException(Throwable cause) {
        super(cause);
    }

    public UnsufficientBalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
