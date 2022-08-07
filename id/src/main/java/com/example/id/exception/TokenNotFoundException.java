package com.example.id.exception;

public class TokenNotFoundException extends RuntimeException{
    public TokenNotFoundException() {
    }

    public TokenNotFoundException(final String message) {
        super(message);
    }

    public TokenNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TokenNotFoundException(final Throwable cause) {
        super(cause);
    }

    public TokenNotFoundException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
