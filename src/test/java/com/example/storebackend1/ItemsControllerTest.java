package com.example.storebackend1;

import com.example.storebackend1.Entities.Customer;
import com.example.storebackend1.Entities.Item;
import com.example.storebackend1.Repos.CustomerRepo;
import com.example.storebackend1.Repos.ItemRepo;
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

    @BeforeEach
    public void init() {
        Item item1 = new Item(1L, "product1", 1.0);
        Item item2 = new Item(2L, "product2", 2.0);
        Item item3 = new Item(3L, "product3", 3.0);

        Customer customer1 = new Customer(1L, "123", "Sara");
        Customer customer2 = new Customer(2L, "312", "Kalle");
        Customer customer3 = new Customer(3L, "432", "Kanin");

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
    public void getAllItemsTest() throws Exception { // UNTESTED
        this.mockMvc.perform(get("/items")).andExpect(status().isOk()).andExpect(content()
                .json("[{\"name\":\"product1\", \"id\":1, \"price\":1.0},{\"name\":\"product2\", \"id\":2, \"price\":2.0},{\"name\":\"product3\", \"id\":3, \"price\":3.0}]"));
    }

    @Test
    public void getItemByIdTest() throws Exception { //UNTESTED
        this.mockMvc.perform(get("/items/1")).andExpect(status().isOk()).andExpect(
                content().json("{\"name\":\"product1\", \"id\":1, \"price\":1.0}"));
    }

    //DENNA VETTE FAN
    @Test
    public void buyItemTest() throws Exception {
        this.mockMvc.perform(post("/items/buy"))
                .andExpect(status().isOk()).andExpect(content()
                .string(equalTo("Purchase successful")));
    }

    @Test
    public void testPost() throws Exception {
        mockMvc.perform(post("/items/buy")
                        .param("itemId", "1")
                        .param("customerId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Purchase successful"));
    }

    @Test
    public void buyItemTest2() throws Exception {
        long customerId = mockCustomerRepo.findById(1L).get().getId();
        long itemId = mockItemRepo.findById(1L).get().getId();
        this.mockMvc.perform(post("/items/buy").param("customerId", Long.toString(customerId)).param("itemId", Long.toString(itemId)))
                .andExpect(status().isOk())
                .andExpect(content().string("Purchase successful"));
    }




    @Test
    public void buyItemTest1() throws Exception {
        long customerId = mockCustomerRepo.findById(1L).get().getId();
        long itemId = mockItemRepo.findById(1L).get().getId();
        this.mockMvc.perform(post("/items/buy").param("customerId","1").param("itemId", "1")).andDo(print())
                .andExpect(status().isOk()).andExpect(content()
                        .string(equalTo("Purchase successful")));
    }
}
