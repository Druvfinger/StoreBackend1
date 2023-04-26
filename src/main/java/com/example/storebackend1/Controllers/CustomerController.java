package com.example.storebackend1.Controllers;

import com.example.storebackend1.Entities.Customer;
import com.example.storebackend1.Services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)//Det svaret du förväntar dig att få
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }


    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable long id){
        return customerService.getCustomerById(id);
    }


    @PostMapping("/add")
    public String addCustomer(@RequestBody Customer customer){
      if (customer.getName() == null || customer.getSsn() == null){
          return "Could not add customer do better!";
      }
      customerService.addCustomer(customer);
      return "Customer with name: "+ customer.getName() +" added.";
    }



//EXTRA
    @GetMapping("/remove")
    public String deleteCustomer(long id){
        String name = customerService.getCustomerById(id).getName();
        customerService.deleteCustomer(id);
        return "Customer with name: " +name + " deleted";
    }
}
