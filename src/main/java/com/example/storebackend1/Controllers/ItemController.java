package com.example.storebackend1.Controllers;

import com.example.storebackend1.Models.Item;
import com.example.storebackend1.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    @GetMapping("/items")
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }
    @GetMapping("/{id}")
    public Item getItemById(@PathVariable long id){
        return itemService.getItemById(id);
    }


}
