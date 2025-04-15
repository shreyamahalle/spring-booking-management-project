package com.shreya.spring.exception;

public class DeliveryAgentNotFound extends RuntimeException {
    public DeliveryAgentNotFound(String message) {

        super(message);
    }
}
