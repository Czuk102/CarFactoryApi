package com.portfolio.carfactoryapi.exception;

public class CarNotFoudException extends RuntimeException {

    public CarNotFoudException(Long id) {
        super("Could not find car: " + id);
    }
}
