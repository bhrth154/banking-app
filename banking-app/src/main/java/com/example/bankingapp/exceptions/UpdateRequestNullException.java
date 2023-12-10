package com.example.bankingapp.exceptions;

public class UpdateRequestNullException extends RuntimeException{
    public UpdateRequestNullException() {
    }

    public UpdateRequestNullException(String message) {
        super(message);
    }

    public UpdateRequestNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateRequestNullException(Throwable cause) {
        super(cause);
    }

    public UpdateRequestNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
