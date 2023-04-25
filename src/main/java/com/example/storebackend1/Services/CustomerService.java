package com.example.storebackend1.Services;


import com.example.storebackend1.Entities.Customer;
import com.example.storebackend1.Repos.CustomerRepo;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;


    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }
    public List<Customer> getCustomers(){
        return customerRepo.findAll();
    }
    public Customer getCustomerById(long id){
        return customerRepo.findById(id).get(); // maybe this should return an optional of Customer Instead ??
        //return new Customer(1,"123546","name");//for testing
    }
    public void addCustomer(Customer customer){
            customerRepo.save(customer);
    }
    public void deleteCustomer(long id){
        customerRepo.deleteById(id);
    }
}
