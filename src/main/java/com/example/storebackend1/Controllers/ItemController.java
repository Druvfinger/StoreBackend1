package com.example.storebackend1.Controllers;

import com.example.storebackend1.Entities.Item;
import com.example.storebackend1.Services.ItemService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable long id) {
        return itemService.getItemById(id);
    }

    @PostMapping("/add")
    public String addItem(@RequestBody Item item) {
        if (item.getName() != null) {
            if (item.getPrice() != 0.0) {
                itemService.addItem(item);
                return "Item with name: " + item.getName() + " added";
            }
        }
        return "Could not add item!";
    }

    @PostMapping("/buy")
    public String buyItem(@RequestParam long itemId, @RequestParam long customerId){
        return itemService.buy(itemId, customerId);
    }
}



