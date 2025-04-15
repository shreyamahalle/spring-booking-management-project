package com.shreya.spring.exception;

public class InvalideCustomerNameException extends RuntimeException {
    public InvalideCustomerNameException(String message) {
        super(message);
    }
}
