package com.example.storebackend1;

import com.example.storebackend1.Models.Customer;
import com.example.storebackend1.Models.Item;
import com.example.storebackend1.Models.Purchase;
import com.example.storebackend1.Repos.CustomerRepo;
import com.example.storebackend1.Repos.ItemRepo;
import com.example.storebackend1.Repos.PurchaseRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class StoreBackend1Application {

    public static void main(String[] args) {
        SpringApplication.run(StoreBackend1Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepo customerRepo, ItemRepo itemRepo, PurchaseRepo purchaseRepo) {
        return args -> {

            Customer customer1 = new Customer("123456", "Karl Nilsson");
            Customer customer2 = new Customer("234567", "Anna Jakobsson");
            Customer customer3 = new Customer("345678", "Nils Svensson");
            Customer customer4 = new Customer("456789", "Ida Andersson");
            Customer customer5 = new Customer("567890", "Gunnar larsson");

            customerRepo.save(customer1);
            customerRepo.save(customer2);
            customerRepo.save(customer3);
            customerRepo.save(customer4);
            customerRepo.save(customer5);



            Item item1 = new Item("Laptop", 199.99);
            Item item2 = new Item("TV", 148.49);
            Item item3 = new Item("Playstation", 300.00);
            Item item4 = new Item("Chair", 40.99);
            Item item5 = new Item("Mountainbike", 3499.98);

            itemRepo.save(item1);
            itemRepo.save(item2);
            itemRepo.save(item3);
            itemRepo.save(item4);
            itemRepo.save(item5);


            Purchase purchase = new Purchase(customer1, item1);
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
