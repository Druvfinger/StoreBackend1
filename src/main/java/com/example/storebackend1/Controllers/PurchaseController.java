package com.example.storebackend1.Controllers;

import com.example.storebackend1.Models.Purchase;
import com.example.storebackend1.Services.PurchaseService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public List<Purchase> getAllItems(){
        return purchaseService.getAllPurchase();
    }

    @GetMapping("/{customerId}")
    public List<Purchase> getPurchasesByCustomerId(@PathVariable long customerId){
        return purchaseService.getCustomerPurchases(customerId);
    }



}
