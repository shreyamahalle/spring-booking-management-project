package com.shreya.spring.model;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Customer {

    private int id;
    private String name;
    private String city;
    private int mobileNo;
    private int age;

}