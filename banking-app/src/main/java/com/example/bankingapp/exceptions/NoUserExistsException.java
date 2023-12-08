package com.example.bankingapp.exceptions;

public class NoUserExistsException extends RuntimeException{
    public NoUserExistsException() {
    }

    public NoUserExistsException(String message) {
        super(message);
    }

    public NoUserExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoUserExistsException(Throwable cause) {
        super(cause);
    }

    public NoUserExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
