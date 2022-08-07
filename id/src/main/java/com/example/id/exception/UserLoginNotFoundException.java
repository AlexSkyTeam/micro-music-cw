package com.example.id.exception;

public class UserLoginNotFoundException extends RuntimeException{
    public UserLoginNotFoundException() {
    }

    public UserLoginNotFoundException(final String message) {
        super(message);
    }

    public UserLoginNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserLoginNotFoundException(final Throwable cause) {
        super(cause);
    }

    public UserLoginNotFoundException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
