package com.shreya.spring.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Customer {
    private int id;
    private String name;
    private String city;
    private int mobileNo;
    private int age;
}
