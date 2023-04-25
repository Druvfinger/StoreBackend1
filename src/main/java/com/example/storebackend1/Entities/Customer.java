package com.example.storebackend1.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private long id;
    private String ssn;
    private String name;

    //@OneToMany
    //@JoinTable
    //private List<Purchase> purchases = new ArrayList<>();

    public Customer(String ssn, String name){
        this.name=name;
        this.ssn=ssn;
    }

   // public void addPurchase(Purchase p){
     //   purchases.add(p);
    //}

}
