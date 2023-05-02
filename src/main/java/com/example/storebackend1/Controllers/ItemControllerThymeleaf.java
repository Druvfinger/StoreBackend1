package com.example.storebackend1.Controllers;

import org.springframework.ui.Model;
import com.example.storebackend1.Models.Item;
import com.example.storebackend1.Services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("itemsThymeleaf")
public class ItemControllerThymeleaf {

    private final ItemService itemService;

    public ItemControllerThymeleaf(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    public String getItems(Model model){
        List<Item> itemsList = itemService.getAllItems();
        model.addAttribute("itemsList", itemsList);
        model.addAttribute("listTitle", "Items");
        model.addAttribute("title", "All Items!");
        return "getItems.html";
    }


}
