package com.example.storebackend1.Controllers;

import com.example.storebackend1.Entities.Purchase;
import com.example.storebackend1.Services.PurchaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("purchaseT")
public class PurchaseControllerThymeleaf {

    private final PurchaseService purchaseService;

    public PurchaseControllerThymeleaf(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping()
    public String getPurchase(Model model){
        List<Purchase> purchaseList = purchaseService.getAllPurchase();
        model.addAttribute("purchaseList", purchaseList);
        model.addAttribute("listTitle", "Purchase:");
        model.addAttribute("title", "All Purchases!");
        return "getPurchase.html";
    }

}
