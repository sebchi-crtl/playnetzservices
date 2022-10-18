package com.playnetz.customer.exception;

import java.io.Serial;

public class CustomerAuthenticationConflictException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public CustomerAuthenticationConflictException() {
        super();
    }

    public CustomerAuthenticationConflictException(String s) {
        super(s);
    }

    public CustomerAuthenticationConflictException(String message, Throwable cause) {
        super(message, cause);
    }
    public CustomerAuthenticationConflictException(Throwable cause) {
        super(cause);
    }
}
