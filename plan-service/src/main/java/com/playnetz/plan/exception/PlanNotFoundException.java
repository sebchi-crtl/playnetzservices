package com.playnetz.plan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PlanNotFoundException extends RuntimeException {


    public PlanNotFoundException() {
        super();
    }

    public PlanNotFoundException(String s) {
        super(s);
    }

    public PlanNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public PlanNotFoundException(Throwable cause) {
        super(cause);
    }
}
