package com.example.storebackend1.Services;

import com.example.storebackend1.Entities.Item;
import com.example.storebackend1.Entities.Purchase;
import com.example.storebackend1.Repos.CustomerRepo;
import com.example.storebackend1.Repos.ItemRepo;
import com.example.storebackend1.Repos.PurchaseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    public final PurchaseRepo purchaseRepo;
    public final CustomerRepo customerRepo;

    public final ItemRepo itemRepo;

    public PurchaseService(PurchaseRepo purchaseRepo, CustomerRepo customerRepo, ItemRepo itemRepo){
        this.purchaseRepo = purchaseRepo;
        this.customerRepo = customerRepo;
        this.itemRepo = itemRepo;
    }

    public List<Purchase> getAllPurchase(){
        return purchaseRepo.findAll();
    }

    public List<Purchase> getCustomerPurchases(long id){
        return purchaseRepo.findAllByCustomerId(id);
    }


}
