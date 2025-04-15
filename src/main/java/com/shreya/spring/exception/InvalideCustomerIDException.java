package com.shreya.spring.exception;

public class InvalideCustomerIDException extends RuntimeException {
    public InvalideCustomerIDException(String message) {
        super(message);
    }
}
