package com.example.storebackend1;

import com.example.storebackend1.Entities.Customer;
import com.example.storebackend1.Entities.Item;
import com.example.storebackend1.Repos.CustomerRepo;
import com.example.storebackend1.Repos.ItemRepo;
import com.example.storebackend1.Repos.PurchaseRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ItemsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ItemRepo mockItemRepo;
    @MockBean
    private CustomerRepo mockCustomerRepo;
    @MockBean
    private PurchaseRepo mockPurchaseRepo;

    @BeforeEach
    public void init() {
        Item item1 = new Item(1L,"Laptop", 199.99);
        Item item2 = new Item(2L,"TV", 148.49);
        Item item3 = new Item(3L,"Playstation", 300.00);

        Customer customer1 = new Customer(1L,"123456", "Karl Nilsson");
        Customer customer2 = new Customer(2L,"234567", "Anna Jakobsson");
        Customer customer3 = new Customer(3L,"345678", "Nils Svensson");

        when(mockItemRepo.findById(1L)).thenReturn(Optional.of(item1));
        when(mockItemRepo.findById(2L)).thenReturn(Optional.of(item2));
        when(mockItemRepo.findById(3L)).thenReturn(Optional.of(item3));
        when(mockItemRepo.findAll()).thenReturn(Arrays.asList(item1, item2, item3));

        when(mockCustomerRepo.findById(1L)).thenReturn(Optional.of(customer1));
        when(mockCustomerRepo.findById(2L)).thenReturn(Optional.of(customer2));
        when(mockCustomerRepo.findById(3L)).thenReturn(Optional.of(customer3));

    }

    @Test
    public void addItemTest() throws Exception {
        this.mockMvc.perform(post("/items/add").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"product4\", \"id\":4, \"price\":4.0}")).andExpect(status().isOk())
                .andExpect(content().string(equalTo("Item with name: product4 added")));
    }

    @Test
    public void getAllItemsTest() throws Exception {
        this.mockMvc.perform(get("/items")).andExpect(status().isOk()).andExpect(content()
                .json("[{\"name\":\"Laptop\", \"id\":1, \"price\":199.99},{\"name\":\"TV\", \"id\":2, \"price\":148.49},{\"name\":\"Playstation\", \"id\":3, \"price\":300.00}]"));
    }

    @Test
    public void getItemByIdTest() throws Exception {
        this.mockMvc.perform(get("/items/1")).andExpect(status().isOk()).andExpect(
                content().json("{\"name\":\"Laptop\", \"id\":1, \"price\":199.99}"));
    }

    @Test
    public void buyItemTest() throws Exception {
        mockMvc.perform(post("/items/buy")
                        .param("itemId", "1")
                        .param("customerId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Purchase successful"));
    }
}
