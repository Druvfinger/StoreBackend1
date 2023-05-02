package com.example.storebackend1.Repos;


import com.example.storebackend1.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Long> {

}
