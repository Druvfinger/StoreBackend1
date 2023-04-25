package com.example.storebackend1.Controllers;

import com.example.storebackend1.Models.Customer;
import com.example.storebackend1.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {


    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable long id){
        return customerService.getCustomerById(id);
    }
    @PostMapping("/add")
    public String addCustomer(@RequestParam String ssn ,@RequestParam String name){
        customerService.addCustomer(ssn,name);
        return "Customer with name: "+ name +" added.";
    }
}
