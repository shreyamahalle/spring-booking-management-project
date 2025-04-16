package com.shreya.spring.model;

import lombok.*;

@Data
@Builder
@Setter
@NoArgsConstructor
public class Customer {

    private int id;
    private String name;
    private String city;
    private int mobileNo;
    private int age;

    public Customer(int id, String name, String city, int mobileNo, int age) {
    }
}