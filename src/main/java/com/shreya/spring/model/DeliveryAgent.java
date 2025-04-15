package com.shreya.spring.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data

public class DeliveryAgent {
    public int mobileNo;
    private int id;
    private String name;
    private String city;

    public DeliveryAgent(int id, String name, String city, int mobileNo) {
    }
}
