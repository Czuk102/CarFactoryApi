package com.portfolio.carfactoryapi.controler;

import com.portfolio.carfactoryapi.exception.CarNotFoudException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CarNotFoudException.class)
    public ResponseEntity<String> handleCarNotFoundException(Exception e) {
        return ResponseEntity.status(404).body("Object not found, message: " + e.getMessage());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> handleServerError(Exception e) {
        return ResponseEntity.status(502).body("Problem with server connection, message: " + e.getMessage());
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<String> handleBadRequest(Exception e) {
        return ResponseEntity.status(400).body("Problem with server connection, message: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(400).body("Unexpected exception occurred , message: " + e.getMessage());
    }
}
