package com.playnetz.purchase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PurchaseNotFoundException extends RuntimeException {


    public PurchaseNotFoundException() {
        super();
    }

    public PurchaseNotFoundException(String s) {
        super(s);
    }

    public PurchaseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public PurchaseNotFoundException(Throwable cause) {
        super(cause);
    }
}


