package com.example.storebackend1.Controllers;

import com.example.storebackend1.Models.Customer;
import com.example.storebackend1.Services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("customersThymeleaf")
public class CustomerControllerThymeleaf {

    private final CustomerService customerService;

    public CustomerControllerThymeleaf(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public String getCustomers(Model model){
        List<Customer> customerList = customerService.getCustomers();
        model.addAttribute("customerList", customerList);
        model.addAttribute("listTitle", "Customer:");
        model.addAttribute("title", "All Customers!");
        return "getCustomer.html";
    }
}
