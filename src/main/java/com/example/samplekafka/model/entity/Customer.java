package com.example.samplekafka.model.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@ToString
public class Customer {
    @Id
    private String id;
    private String firsName;
    private String lastName;
    private int age;
    private String email;
    private String phoneNumber;

    public Customer() {}

    public Customer(String firsName, String lastName, int age, String email, String phoneNumber) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
