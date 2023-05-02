package com.example.storebackend1.Repos;

import com.example.storebackend1.Models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PurchaseRepo extends JpaRepository<Purchase,Long> {

    List<Purchase> findAllByCustomerId(long id);

}
