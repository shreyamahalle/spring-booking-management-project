package com.shreya.spring.exception;


public class CustomerException extends RuntimeException {
    public CustomerException(String NotAvailable) {
        super(NotAvailable);
    }
}
