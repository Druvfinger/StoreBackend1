package com.example.storebackend1.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @CreationTimestamp
    private LocalDateTime purchaseDate;

    @OneToOne
    @JoinColumn
    private Customer customer;

    @OneToMany
    @JoinTable
    List<Item> items = new ArrayList<>();


    public Purchase(Customer customer, Item item){
        this.customer = customer;
        this.items = items;
    }
}
