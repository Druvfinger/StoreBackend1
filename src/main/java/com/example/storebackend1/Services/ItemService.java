package com.example.storebackend1.Services;


import com.example.storebackend1.Entities.Customer;
import com.example.storebackend1.Entities.Item;
import com.example.storebackend1.Entities.Purchase;
import com.example.storebackend1.Repos.CustomerRepo;
import com.example.storebackend1.Repos.ItemRepo;
import com.example.storebackend1.Repos.PurchaseRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemService {

    public final ItemRepo itemRepo;
    public final CustomerRepo customerRepo;
    public final PurchaseRepo purchaseRepo;

    public ItemService(ItemRepo itemRepo, CustomerRepo customerRepo, PurchaseRepo purchaseRepo) {
        this.itemRepo = itemRepo;
        this.customerRepo = customerRepo;
        this.purchaseRepo = purchaseRepo;
    }

    public List<Item> getAllItems(){
        return itemRepo.findAll();
    }

    public Item getItemById(long id){
        return itemRepo.findById(id).get(); // Maybe should return Optional??
    }

    public void addItem(Item item){
        itemRepo.save(item);
    }
    public String buy(long itemId, long  customerId){
        Item item = itemRepo.findById(itemId).orElse(null);
        Customer customer = customerRepo.findById(customerId).orElse(null);
        System.out.println(item + " - " + customer);
        if (item != null){
            System.out.println("1");
            if (customer != null) {
                System.out.println("2");
                purchaseRepo.save(new Purchase(customer, item));
                return "Purchase successful";
            }
        }
        return "Something went wrong with the purchase!";
    }

}
