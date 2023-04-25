package com.example.storebackend1.Repos;


import com.example.storebackend1.Entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item,Long> {

    //Bygg egna queries här
}
