package com.example.storebackend1;

import com.example.storebackend1.Entities.Customer;
import com.example.storebackend1.Entities.Item;
import com.example.storebackend1.Entities.Purchase;
import com.example.storebackend1.Repos.CustomerRepo;
import com.example.storebackend1.Repos.ItemRepo;
import com.example.storebackend1.Repos.PurchaseRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class StoreBackend1Application {

    public static void main(String[] args) {
        SpringApplication.run(StoreBackend1Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepo customerRepo, ItemRepo itemRepo, PurchaseRepo purchaseRepo) {
        return args -> {

            Customer customer1 = new Customer("ssn1", "n1");
            Customer customer2 = new Customer("ssn2", "n2");
            Customer customer3 = new Customer("ssn3", "n3");
            Customer customer4 = new Customer("ssn4", "n4");
            Customer customer5 = new Customer("ssn5", "n5");

            customerRepo.save(customer1);
            customerRepo.save(customer2);
            customerRepo.save(customer3);
            customerRepo.save(customer4);
            customerRepo.save(customer5);



            Item item1 = new Item("item1", 1);
            Item item2 = new Item("item2", 2);
            Item item3 = new Item("item3", 3);
            Item item4 = new Item("item4", 4);
            Item item5 = new Item("item5", 5);

            itemRepo.save(item1);
            itemRepo.save(item2);
            itemRepo.save(item3);
            itemRepo.save(item4);
            itemRepo.save(item5);


            Purchase purchase = new Purchase(customer1, item1); // kom ihåg list of är final
            Purchase purchase1 = new Purchase(customer4, item2);
            Purchase purchase2 = new Purchase(customer3, item3);
            Purchase purchase3 = new Purchase(customer4, item4);



//                    Customer temp = customerRepo.findById(customer1.getId()).get();
//                    temp.addPurchase(purchase);
//                    customer3.addPurchase(purchase2);
//                    customer4.addPurchase(purchase1);
//                    customer4.addPurchase(purchase3);



            purchaseRepo.save(purchase);
            purchaseRepo.save(purchase1);
            purchaseRepo.save(purchase2);
            purchaseRepo.save(purchase3);

        };
    }

}
