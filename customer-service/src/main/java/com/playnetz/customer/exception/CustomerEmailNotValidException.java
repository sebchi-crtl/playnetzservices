package com.playnetz.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

public class CustomerEmailNotValidException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public CustomerEmailNotValidException() {
        super();
    }

    public CustomerEmailNotValidException(String s) {
        super(s);
    }

    public CustomerEmailNotValidException(String message, Throwable cause) {
        super(message, cause);
    }
    public CustomerEmailNotValidException(Throwable cause) {
        super(cause);
    }
}
