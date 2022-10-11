package com.s3437237.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value= {ServiceException.class})
    public ResponseEntity<Object> handleCustomExceptionHandler (ServiceException e){

        HttpStatus notFound = HttpStatus.NOT_FOUND;
        Erroresponse erroresponse = new Erroresponse(
                e.getMessage(),
                e,
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        // return response

        return new ResponseEntity<>(erroresponse, notFound);

    }
}
