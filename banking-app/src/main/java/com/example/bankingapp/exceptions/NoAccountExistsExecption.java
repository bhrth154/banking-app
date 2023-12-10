package com.example.bankingapp.exceptions;

public class NoAccountExistsExecption extends RuntimeException {
    public NoAccountExistsExecption() {
    }

    public NoAccountExistsExecption(String message) {
        super(message);
    }

    public NoAccountExistsExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAccountExistsExecption(Throwable cause) {
        super(cause);
    }

    public NoAccountExistsExecption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
