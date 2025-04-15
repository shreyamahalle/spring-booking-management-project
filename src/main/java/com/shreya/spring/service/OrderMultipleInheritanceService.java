package com.shreya.spring.service;

import com.shreya.spring.model.Order;

import java.util.Scanner;

public class OrderMultipleInheritanceService extends Order {
    Scanner sc = new Scanner(System.in);

    public void displayOrder() {

        System.out.println("Order are not accepted ");
    }
}