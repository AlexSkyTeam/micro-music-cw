package com.example.id.exception;

public class UserPasswordNotMatchesException extends RuntimeException {
    public UserPasswordNotMatchesException() {
    }

    public UserPasswordNotMatchesException(final String message) {
        super(message);
    }

    public UserPasswordNotMatchesException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserPasswordNotMatchesException(final Throwable cause) {
        super(cause);
    }

    public UserPasswordNotMatchesException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
