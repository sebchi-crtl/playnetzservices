package com.playnetz.fraud.exception;

import java.io.Serial;

public class FraudException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public FraudException() {
        super();
    }

    public FraudException(String s) {
        super(s);
    }

    public FraudException(String message, Throwable cause) {
        super(message, cause);
    }
    public FraudException(Throwable cause) {
        super(cause);
    }
}
