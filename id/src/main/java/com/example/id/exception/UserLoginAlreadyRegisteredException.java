package com.example.id.exception;

public class UserLoginAlreadyRegisteredException extends RuntimeException {
    public UserLoginAlreadyRegisteredException() {
    }

    public UserLoginAlreadyRegisteredException(final String message) {
        super(message);
    }

    public UserLoginAlreadyRegisteredException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserLoginAlreadyRegisteredException(final Throwable cause) {
        super(cause);
    }

    public UserLoginAlreadyRegisteredException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
