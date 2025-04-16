package com.shreya.spring.impl;

import com.shreya.spring.service.OrderNumberService;

import java.util.concurrent.atomic.AtomicInteger;

public class OrderNumberImpl implements OrderNumberService {

    @Override
    public void createOrderNo() {
        AtomicInteger orderNumberCounter = new AtomicInteger(100);
    }

//    @Override
//    public void displayOrder() {

}

