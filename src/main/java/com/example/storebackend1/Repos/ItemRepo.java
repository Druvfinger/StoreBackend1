package com.example.storebackend1.Repos;


import com.example.storebackend1.Models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item,Long> {
}
