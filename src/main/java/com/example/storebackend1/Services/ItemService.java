package com.example.storebackend1.Services;


import com.example.storebackend1.Models.Item;
import com.example.storebackend1.Repos.ItemRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    public final ItemRepo itemRepo;

    public ItemService(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public List<Item> getAllItems(){
        return itemRepo.findAll();
    }
    public Item getItemById(long id){
        return itemRepo.findById(id).get(); // Maybe should return Optional??
    }

}
