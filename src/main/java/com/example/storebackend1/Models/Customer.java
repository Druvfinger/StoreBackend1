package com.example.storebackend1.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
//@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private long id;
    private String ssn;
    private String name;

    @OneToMany
    @JoinColumn
    private List<Purchase> purchases;

    public Customer(String ssn, String name){
        this.name=name;
        this.ssn=ssn;
    }

}
