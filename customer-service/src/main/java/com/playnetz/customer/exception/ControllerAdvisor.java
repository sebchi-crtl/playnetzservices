package com.playnetz.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ControllerAdvisor {
//extends ResponseEntityExceptionHandler
    @ExceptionHandler(value = {CustomerEmailNotValidException.class})
    public ResponseEntity<Object> handleEmailNotValidException(CustomerEmailNotValidException ex, WebRequest request) {

        HttpStatus notAcceptable = HttpStatus.NOT_ACCEPTABLE;

        ApiException apiException = new ApiException(
                ex.getMessage(),
                notAcceptable,
                request,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, notAcceptable);
//        return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = {CustomerAuthenticationConflictException.class})
    public ResponseEntity<Object> handleEmailNotValidException(CustomerAuthenticationConflictException ex, WebRequest request) {

        HttpStatus badRequest = HttpStatus.CONFLICT;

        ApiException apiException = new ApiException(
                ex.getMessage(),
                badRequest,
                request,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
//        return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }

//    @ExceptionHandler(value
//            = { IllegalArgumentException.class,
//            IllegalStateException.class })
//    protected ResponseEntity<Object> handleConflict(
//            RuntimeException ex, WebRequest request) {
//        String bodyOfResponse = "This should be application specific";
//        var responseEntity = handleExceptionInternal(ex, bodyOfResponse,
//                new HttpHeaders(), HttpStatus.CONFLICT, request);
//        return responseEntity;
//    }
}
