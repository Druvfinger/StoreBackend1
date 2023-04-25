package com.example.storebackend1.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;
    private Date purchaseDate;

    @OneToOne
    @JoinColumn
    private Customer customer;

    @OneToMany
    @JoinColumn
    List<Item> items;

    private Purchase(Date purchaseDate, Customer customer, List<Item> items){
        this.purchaseDate = purchaseDate;
        this.customer = customer;
        this.items = items;
    }
}
